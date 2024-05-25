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
}

async function descargarJugadores() {
    return await fetch("/api/v1/jugador")
        .then((respuesta) => respuesta.json())
}

function crearFila({ dni, nombre, ubicacion, club }) {
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
    crearCampo(dni)
    crearCampo(nombre)
    crearCampo(ubicacion)
    crearCampo(club.nombre)
    if (PERMISOS) {
        crearEnlace("editar", `/jugadores/editar/${dni}`)
        crearEnlace("borrar", `/jugadores/borrar/${dni}`)
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
}

imprimirLista()

document.querySelector('.orden.Dni').addEventListener("click", () => imprimirLista("dni"))
document.querySelector('.orden.Nombre').addEventListener("click", () => imprimirLista("nombre"))
document.querySelector('.orden.Ubicacion').addEventListener("click", () => imprimirLista("ubicacion"))
document.querySelector('.orden.Club').addEventListener("click", () => imprimirLista("club"))

document.querySelector('.tabla>thead').addEventListener("click", marcarOrdenListado)

function marcarOrdenListado(e) {
    if (!e.target.classList.contains("orden")) return
    
    [...e.target.parentNode.querySelectorAll(".orden")]
        .forEach(campo => campo.classList.remove("asc", "desc"))

    e.target.classList.add(ORDEN.direccion == "asc" ? "asc" : "desc");
}