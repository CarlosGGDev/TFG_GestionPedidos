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
            },
            {
            title: "NOMBRE",
            data: "name"
            }, {
            title: "CATEGORÍA",
            data: "category.name"
            }, {
            title: "PRECIO KG",
            data: "price"
            }
        ],
        /* Se editan las columnas 3 y 4 añadidas en HTML. Si se insertan desde JSON les añade un icono de ordenacion */
        aoColumnDefs: [
            {
            aTargets: [4],
            mData: "EDITAR",
            mRender: function() {
                return "<button id='btn-edit' type='submit' class='btn btn-sm btn-custom ms-2' data-bs-toggle='modal' "
                    + "data-bs-target='#edit-product-modal'><i class='bi bi-pencil'></i></button>";
                }
            },
            {
                aTargets: [5],
                mData: "BORRAR",
                mRender: function() {
                    return "<button id='btn-remove' class='btn btn-sm btn-custom'><i class='bi bi-trash3'></i></button>";
                }
            }
        ]
    });

    // REMOVE PRODUCT
    $(document).on('click', '#btn-remove', function() {
        if (confirm("¿Seguro que quieres borrar el producto?\nEsta opción no se puede deshacer")) {
            let productId = $(this).closest('tr').children('td:first').text();
            requestRemoveProduct(productId);
        }
    });

    // EDIT PRODUCT
    $(document).on('click', '#btn-edit', function() {
        let productId = $(this).closest('tr').children('td:nth-child(1)').text();
        let productName = $(this).closest('tr').children('td:nth-child(2)').text();
        let productCategory = $(this).closest('tr').children('td:nth-child(3)').text();
        let productPrice = $(this).closest('tr').children('td:nth-child(4)').text();
        $('#product-id').val(productId);
        $('#product-name').val(productName);
        $('#product-category').val(productCategory);
        $('#product-price').val(productPrice);
    });

});

const token = $("meta[name='_csrf']").attr("content");

// REQUEST REMOVE ORDER
function requestRemoveProduct(productId) {
    $.ajax({
        type: "DELETE",
        headers: {
            "X-CSRF-Token": token
        },
        url: "/productos/" + productId
    })
    $('#product-removed').modal('show');
    $('.modal').on('hidden.bs.modal', function () {
        window.location.replace("http://localhost:8080/productos")
    });
}