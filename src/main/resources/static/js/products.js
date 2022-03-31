$(document).ready(function () {

    $('#products-table').DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
        },
        "ajax": {
            "url": "http://localhost:8080/productos",
            "dataSrc": ""
        },
        "columns": [{
            title: "Nombre",
            data: "name"
        }, {
            title: "Categoría",
            data: "category.name"
        }, {
            title: "Precio Kg",
            data: "price"
        }]
    });

    // loadTableData();

});

/*

async function loadTableData() {
    const response = await fetch('/productos');
    const products = await response.json();

    let productsHTML = "";
    for (let product of products) {
        let productHTML = "<tr><td>" + product.name + "</td></tr>";
        productsHTML += productHTML;
    }

    document.querySelector("#products-table tbody").outerHTML = productsHTML;
}

*/