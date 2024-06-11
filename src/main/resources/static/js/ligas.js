const PERMISOS = await comprobarPermisos()
const LIGAS = await descargarJugadores();

const ORDEN = {
    campo: "",
    direccion: "",
    elementos: []
}

async function comprobarPermisos() {
    return await fetch("/api/v1/auth/admin")
        .then((respuesta) => respuesta.status == 202)
        .catch(_ => false)
} // realiza una solicitud a la API para verificar si es ADMIN

async function descargarJugadores() {
    return await fetch("/api/v1/liga")
        .then((respuesta) => respuesta.json())
} // descarga la lista de ligas a traves de la API

function crearFila({ codigo, comunidadAutonoma, region }) {
    const FILA = document.createElement("tr")
    function crearCampo(valor) {
        const CAMPO = document.createElement("td")
        CAMPO.innerText = valor
        FILA.appendChild(CAMPO)
    }
    function crearEnlace(texto, valor) {
        const CAMPO = document.createElement("td")
        const ENLACE = document.createElement("a")
        ENLACE.setAttribute("href", valor)
        ENLACE.innerText = texto
        CAMPO.appendChild(ENLACE)
        FILA.appendChild(CAMPO)
    }
    crearCampo(codigo)
    crearCampo(comunidadAutonoma)
    crearCampo(region)
    if (PERMISOS) {
        crearEnlace("editar", `/liga/editar/${codigo}`)
        crearEnlace("borrar", `/liga/borrar/${codigo}`)
    }
    return FILA
} // crea la fila de la tabla y agrega enlaces si es ADMIN

function imprimirLista(orden = "") {
    if (orden) {
        if (ORDEN.campo == orden) {
            ORDEN.direccion = ORDEN.direccion == "desc" ? "asc" : "desc";
        } else {
            ORDEN.campo = orden
            ORDEN.direccion = "desc"
        }

        ORDEN.elementos = LIGAS
            .toSorted((a, b) => a[ORDEN.campo].toString().localeCompare(b[ORDEN.campo].toString()))

        if (ORDEN.direccion == "asc") {
            ORDEN.elementos.reverse()
        }
    } else {
        ORDEN.elementos = LIGAS
    }
    document.querySelector(".contenidoTabla")
        .replaceChildren(...ORDEN.elementos.map(crearFila))
} // imprime la lista de clubs y los ordena

imprimirLista()

document.querySelector('.orden.Codigo').addEventListener("click", () => imprimirLista("codigo"))
// asignan eventos de click para "codigo" y se ordena
document.querySelector('.orden.CA').addEventListener("click", () => imprimirLista("comunidadAutonoma"))
// asignan eventos de click para "CA" y se ordena
document.querySelector('.orden.Region').addEventListener("click", () => imprimirLista("region"))
// asignan eventos de click para "region" y se ordena

document.querySelector('.tabla>thead').addEventListener("click", marcarOrdenListado)

function marcarOrdenListado(e) {
    if (!e.target.classList.contains("orden")) return

    [...e.target.parentNode.querySelectorAll(".orden")]
        .forEach(campo => campo.classList.remove("asc", "desc"))

    e.target.classList.add(ORDEN.direccion == "asc" ? "asc" : "desc");
} // marca la direccion de la ordenacion en el encabezado