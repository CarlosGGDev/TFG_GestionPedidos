$(document).ready(function () {

    const token = $("meta[name='_csrf']").attr("content");

    $('#products-table').DataTable({
        language: {
            url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
        },
        "ajax": {
            type: "POST",
            headers: {
                "X-CSRF-Token": token
            },
            url: "http://localhost:8080/productos/listado",
            dataSrc: ""
        },
        "columns": [
            {
                title: "REF.",
                data: "id"
            },{
                title: "NOMBRE",
                data: "name"
            }, {
                title: "CATEGORÍA",
                data: "category.name"
            }, {
                title: "PRECIO KG",
                data: "price"
            }
        ]
    });
});