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
        ],
        /* Se editan las columnas 5 y 6 añadidas en HTML. Si se insertan desde JSON les añade un icono de ordenacion */
        aoColumnDefs: [
            {
            aTargets: [4],
            mData: "EDITAR",
            mRender: function() {
                return "<button id='btn-edit' type='button' class='btn btn-sm btn-custom ms-2' data-bs-toggle='modal' "
                    + "data-bs-target='#edit-product-modal'><i class='bi bi-pencil'></i></button>";
            }},
            {
                aTargets: [5],
                mData: "BORRAR",
                mRender: function() {
                    return "<button id='btn-remove' class='btn btn-sm btn-custom'><i class='bi bi-trash3'></i></button>";
                }
            }
        ]
    });

    // ADD NEW PRODUCT & CATEGORY BUTTONS TO DATATABLE
    let newProduct = "<button id='new-product' type='button' class='btn btn-new m-0 me-3 mb-4 px-2 py-0' data-bs-toggle='modal' data-bs-target='#new-product-modal'><span class='fs-4 m-0 me-1 p-0'>+</span><span>PRODUCTO</span></button>";
    let newCategory = "<button id='new-category' type='button' class='btn btn-new m-0 mb-4 px-2 py-0' data-bs-toggle='modal' data-bs-target='#new-category-modal'><span class='fs-4 m-0 me-1 p-0'>+</span><span>CATEGORÍA</span></button>";
    let newButtonsDiv = `<div class='ms-4 d-inline'>${newProduct}${newCategory}</div>`;
    window.onload = function() {
        $('#products-table_length').after(newButtonsDiv);
    }

    // NEW PRODUCT
    $(document).on('click', '#btn-new-product', function() {
        let product = {
            "name": $('#new-product-name').val(),
            "category": {
                "id": $('#new-product-category').find('option:selected').attr('id'),
                "name": $('#new-product-category').val()
            },
            "price": $('#new-product-price').val()
        }

        requestNewProduct(product);
    });

    // REMOVE PRODUCT
    $(document).on('click', '#btn-remove', function() {
        if (confirm("¿Seguro que quieres borrar el producto?\nEsta opción no se puede deshacer")) {
            let productId = $(this).closest('tr').children('td:first').text();
            requestRemoveProduct(productId);
        }
    });

    // Shows in a modal the information of product to edit
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

    // UPDATE PRODUCT
    $(document).on('click', '#btn-update', function() {
        let product = {
            "id": $('#product-id').val(),
            "name": $('#product-name').val(),
            "category": {
                "id": $('#product-category').find('option:selected').attr('id'),
                "name": $('#product-category').val()
            },
            "price": $('#product-price').val()
        }

        requestUpdateProduct(product);
    });

    // NEW CATEGORY
    $(document).on('click', '#btn-new-category', function() {
        let category = {
            "name": $('#new-category-name').val()
        }

        requestNewCategory(category);
    });
});

const token = $("meta[name='_csrf']").attr("content");

// REQUEST NEW PRODUCT
function requestNewProduct(data) {
    $.post({
        type: "POST",
        headers: {
            "X-CSRF-Token": token
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "/productos/nuevo",
        data: JSON.stringify(data),
        success: function() {
            window.location.replace("http://localhost:8080/productos")
        },
        error: function() {
            alert("El producto no se ha podido añadir");
        }
    })
}

// REQUEST UPDATE PRODUCT
function requestUpdateProduct(data) {
    $.post({
        type: "POST",
        headers: {
            "X-CSRF-Token": token
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "/productos/editar",
        data: JSON.stringify(data),
        success: function() {
            window.location.replace("http://localhost:8080/productos")
        },
        error: function() {
            alert("El producto no se ha podido actualizar");
        }
    })
}

// REQUEST REMOVE PRODUCT
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

// REQUEST NEW PRODUCT
function requestNewCategory(data) {
    $.post({
        type: "POST",
        headers: {
            "X-CSRF-Token": token
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        url: "/categorias/nueva",
        data: JSON.stringify(data),
        success: function() {
            window.location.replace("http://localhost:8080/productos")
        },
        error: function() {
            alert("La categoría no se ha podido añadir");
        }
    })
}