//Navigation
function redirigirANombre() {
    window.location.href = "ventanaNombre.html";
}

function redirigirASeleccion() {
    window.location.href = "seleccionModo.html";
}

//Other functions
function guardarNombre() {
    var valorNombre = document.getElementById("inputNombre").value;
    localStorage.setItem("valorInput", valorNombre);
}