const PERMISOS = await comprobarPermisos()
const PARTIDOS = await descargarPartidos();

const ORDEN = {
    campo: "",
    direccion: "",
    elementos: []
}

async function comprobarPermisos() {
    return await fetch("/api/v1/auth/admin")
        .then((respuesta) => respuesta.status == 202)
        .catch(_ => false)
}

async function descargarPartidos() {
    return await fetch("/api/v1/partido")
        .then((respuesta) => respuesta.json())
}

function crearFila({ codigo, jornada, local, visitante }) {
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
    crearCampo(jornada)
    crearCampo(local.nombre)
    crearCampo(visitante.nombre)
    if (PERMISOS) {
        crearEnlace("editar", `/partido/editar/${codigo}`)
        crearEnlace("borrar", `/partido/borrar/${codigo}`)
    }
    return FILA
}

function imprimirLista(orden = "") {
    if (orden) {
        if (ORDEN.campo == orden) {
            ORDEN.direccion = ORDEN.direccion == "desc" ? "asc" : "desc";
        } else {
            ORDEN.campo = orden
            ORDEN.direccion = "desc"
        }

        ORDEN.elementos = PARTIDOS
            .toSorted((a, b) => a[ORDEN.campo].toString().localeCompare(b[ORDEN.campo].toString()))

        if (ORDEN.direccion == "asc") {
            ORDEN.elementos.reverse()
        }
            
    } else {
        ORDEN.elementos = PARTIDOS
    }

    document.querySelector(".contenidoTabla")
        .replaceChildren(...ORDEN.elementos.map(crearFila))
}

imprimirLista()

document.querySelector('.orden.Codigo').addEventListener("click", () => imprimirLista("codigo"))
document.querySelector('.orden.Jornada').addEventListener("click", () => imprimirLista("jornada"))
document.querySelector('.orden.Local').addEventListener("click", () => imprimirLista("local"))
document.querySelector('.orden.Visitante').addEventListener("click", () => imprimirLista("visitante"))

document.querySelector('.tabla>thead').addEventListener("click", marcarOrdenListado)

function marcarOrdenListado(e) {
    if (!e.target.classList.contains("orden")) return
    
    [...e.target.parentNode.querySelectorAll(".orden")]
        .forEach(campo => campo.classList.remove("asc", "desc"))

    e.target.classList.add(ORDEN.direccion == "asc" ? "asc" : "desc");
}