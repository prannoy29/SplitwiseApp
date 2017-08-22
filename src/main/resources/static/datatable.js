$(document).ready( function () {
    var a = $('#tf').val();
    var table = $('#employeesTable').DataTable({

    "sAjaxSource": "http://localhost:8080/user/findById?id="+ a,
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
        { "mData": "id"},
        { "mData": "name" },
        { "mData": "debt" },
    ]
})
})