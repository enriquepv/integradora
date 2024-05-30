$.ajax({
    url: 'http://localhost:8181/productos/mostrar-vista-linda',
    type: 'GET',
    success: function (data) {
        $(".table").html(data);
    },
    error: function () {
        console.error('Error al cargar la vista');
    }
})