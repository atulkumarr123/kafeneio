<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
 <link rel="stylesheet" href="static/css/font-awesome.min-4.7.0.css"></link>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="static/css/free-jqgrid/4.14.1/ui.jqgrid.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/free-jqgrid/4.14.1/jquery.jqgrid.min.js"></script>
    <script>
    $(function () {
        "use strict";
        $("#grid5").jqGrid({
            colModel: [
                { name: "name", label: "Client", width: 53 },
                { name: "invdate", label: "Date", width: 90, align: "center", sorttype: "date",
                    formatter: "date", formatoptions: { newformat: "d-M-Y" },
                    searchoptions: { sopt: ["eq"] } },
                { name: "amount", label: "Amount", width: 65, template: "number" },
                { name: "tax", label: "Tax", width: 41, template: "number" },
                { name: "total", label: "Total", width: 51, template: "number" },
                { name: "closed", label: "Closed", width: 59, template: "booleanCheckbox", firstsortorder: "desc" }
            ],
            data: [
                { id: "10",  invdate: "2015-10-01", name: "test",   amount: "" },
                { id: "20",  invdate: "2015-09-01", name: "test2",  amount: "300.00", tax: "20.00", closed: false, ship_via: "DH", total: "320.00" },
                { id: "30",  invdate: "2015-09-01", name: "test3",  amount: "400.00", tax: "30.00", closed: false, ship_via: "FE", total: "430.00" },
                { id: "40",  invdate: "2015-10-04", name: "test4",  amount: "200.00", tax: "10.00", closed: true,  ship_via: "TN", total: "210.00" },
                { id: "50",  invdate: "2015-10-31", name: "test5",  amount: "300.00", tax: "20.00", closed: false, ship_via: "FE", total: "320.00" },
                { id: "60",  invdate: "2015-09-06", name: "test6",  amount: "400.00", tax: "30.00", closed: false, ship_via: "FE", total: "430.00" },
                { id: "70",  invdate: "2015-10-04", name: "test7",  amount: "200.00", tax: "10.00", closed: true,  ship_via: "TN", total: "210.00" },
                { id: "80",  invdate: "2015-10-03", name: "test8",  amount: "300.00", tax: "20.00", closed: false, ship_via: "FE", total: "320.00" },
                { id: "90",  invdate: "2015-09-01", name: "test9",  amount: "400.00", tax: "30.00", closed: false, ship_via: "TN", total: "430.00" },
                { id: "100", invdate: "2015-09-08", name: "test10", amount: "500.00", tax: "30.00", closed: true,  ship_via: "TN", total: "530.00" },
                { id: "110", invdate: "2015-09-08", name: "test11", amount: "500.00", tax: "30.00", closed: false, ship_via: "FE", total: "530.00" },
                { id: "120", invdate: "2015-09-10", name: "test12", amount: "500.00", tax: "30.00", closed: false, ship_via: "FE", total: "530.00" }
            ],
            iconSet: "fontAwesome",
            idPrefix: "g5_",
            rownumbers: true,
            sortname: "invdate",
            sortorder: "desc",
            threeStateSort: true,
            sortIconsBeforeText: true,
            headertitles: true,
            toppager: true,
            pager: true,
            rowNum: 5,
            viewrecords: true,
            searching: {
                defaultSearch: "cn"
            },
            caption: "The grid, which demonstrates formatters, templates and the pager"
        }).jqGrid("filterToolbar");
    });
    </script>
</head>
<body>
<table id="grid1b"></table>
</body>
</html>