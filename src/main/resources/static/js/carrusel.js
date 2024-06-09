const video = document.querySelector("#player");
const htmlVideos = document.querySelector('#videos')

const fuentes = [
    '/img/media_001.mp4',
    '/img/media_002.mp4',
    '/img/media_003.mp4'
];
let actual = 0;


function actualizar(indice = actual) {
    if (indice == actual) 
        actualizarVideos()
    video.src = fuentes[indice];
}


function anterior() {
    actual = actual > 0 ? actual - 1 : fuentes.length - 1;
    actualizar();
}

function posterior() {
    actual = actual < fuentes.length - 1 ? actual + 1 : 0;
    actualizar();
}


video.src = fuentes[0]

function videoplay () {
    video.play();
    document.removeEventListener('click', video)
}

document.addEventListener('click', videoplay);


function actualizarVideos() {
    htmlVideos.replaceChildren()
    fuentes.forEach((fuente, i) => {

        const label = document.createElement('label')
        const input = document.createElement('input')
        input.type = 'radio';
        input.name = 'boton';
        if (fuente == fuentes[actual]) {
            input.checked = "true"
        }
        label.textContent = 'radio_button_checked'
        label.classList.add('material-symbols-outlined')
        label.appendChild(input)
        label.addEventListener('click', () => actualizar(i))
        htmlVideos.appendChild(label)

    })
}

actualizarVideos()

video.addEventListener('loadedmetadata', function () {
    video.play();
});

document.querySelector("#anterior").addEventListener('click', anterior)
document.querySelector("#posterior").addEventListener('click', posterior)