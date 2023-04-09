//Variables


//Navigation
function redirigirANombre() {
    window.location.href = "ventanaNombre.html";
}

function redirigirASeleccion() {
    window.location.href = "seleccionModo.html";
}

function redirigirACrear() {
    window.location.href = "crearSala.html";
}

function redirigirAPartida() {
    window.location.href = "partida.html";
}

function redirigirAUnirse() {
    window.location.href = "unirseSala.html";
}

//Other functions
function guardarNombre() {
    var valorNombre = document.getElementById("inputNombre").value;
    localStorage.setItem("valorInput", valorNombre);
}

function guardarValorB(){
    localStorage.setItem('valorPersonaje', 'img/down/blancoDownGIF.gif');
}

function guardarImg(urlImg){
    localStorage.setItem("imagenPersonaje", urlImg);
}

window.onload = function() {
    var codigoGenerado = "";
    codigoGenerado = Math.round(Math.random() * (999 - 100) + 100);
    document.getElementById("codigo").innerHTML = codigoGenerado;
}

