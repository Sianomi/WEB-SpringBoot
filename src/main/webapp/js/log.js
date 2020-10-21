$(document).ready(function() {
    $('#logtable').DataTable(

        {

            "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
            "iDisplayLength": 5
        }
    );
} );