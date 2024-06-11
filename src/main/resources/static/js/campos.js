const PERMISOS = await comprobarPermisos()
const JUGADORES = await descargarJugadores();

const ORDEN = {
    campo: "",
    direccion: "",
    elementos: []
}

async function comprobarPermisos() {
    return await fetch("/api/v1/auth/admin")
        .then((respuesta) => respuesta.status == 202)
        .catch(_ => false)
} // comprueba si el usuario tiene permisos de ADMIN

async function descargarJugadores() {
    return await fetch("/api/v1/campo")
        .then((respuesta) => respuesta.json())
} // descarga la lista de jugadores desde la API y devuelve un JSON

function crearFila({ nombre, ubicacion, club }) {
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
    crearCampo(nombre)
    crearCampo(ubicacion)
    crearCampo(club.nombre)
    if (PERMISOS) {
        crearEnlace("editar", `/campo/editar/${nombre}`)
        crearEnlace("borrar", `/campo/borrar/${nombre}`)
    }
    return FILA
} // crea la fila de la tabla

function imprimirLista(orden = "") {
    if (orden) {
        if (ORDEN.campo == orden) {
            ORDEN.direccion = ORDEN.direccion == "desc" ? "asc" : "desc";
        } else {
            ORDEN.campo = orden
            ORDEN.direccion = "desc"
        }

        ORDEN.elementos = JUGADORES
            .toSorted((a, b) => a[ORDEN.campo].toString().localeCompare(b[ORDEN.campo].toString()))

        if (ORDEN.direccion == "asc") {
            ORDEN.elementos.reverse()
        }

    } else {
        ORDEN.elementos = JUGADORES
    }

    document.querySelector(".contenidoTabla")
        .replaceChildren(...ORDEN.elementos.map(crearFila))
} // imprime la lista de campos, ordenandolos si es necesario

imprimirLista()

document.querySelector('.orden.Nombre').addEventListener("click", () => imprimirLista("nombre"))
// asignan eventos de click para "nombre" y se ordena
document.querySelector('.orden.Ubicacion').addEventListener("click", () => imprimirLista("ubicacion"))
// asignan eventos de click para "ubicacion" y se ordena
document.querySelector('.orden.Club').addEventListener("click", () => imprimirLista("club"))
// asignan eventos de click para "club" y se ordena

document.querySelector('.tabla>thead').addEventListener("click", marcarOrdenListado)

function marcarOrdenListado(e) {
    if (!e.target.classList.contains("orden")) return

    [...e.target.parentNode.querySelectorAll(".orden")]
        .forEach(campo => campo.classList.remove("asc", "desc"))

    e.target.classList.add(ORDEN.direccion == "asc" ? "asc" : "desc");
} // marca la direccion de la ordenacion en el encabezado