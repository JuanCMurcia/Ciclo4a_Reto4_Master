$(document).ready(function(){
    console.log("Estas en la pagina Admon");
    init();
})

function init()
{
    var MiNombreUsuario = sessionStorage.getItem("NombreUsuario");
    $(".nombreUsuario").html(MiNombreUsuario);
}