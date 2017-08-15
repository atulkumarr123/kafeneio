$( document ).ready(function() {
	 $("#menuChildDiv").hide();
	 
	$("#menu").click(function(){
		$("#menuChildDiv").toggle();
	});
//	alert("Inready");
	
    $("#grid1b").jqGrid({
        colModel: [
            { name: "name", label: "Client", width: 53 },
            { name: "invdate", label: "Date", width: 80, align: "center", sorttype: "date",
                formatter: "date", formatoptions: { newformat: "d-M-Y" } },
            { name: "amount", label: "Amount", width: 65, template: "number" },
            { name: "tax", label: "Tax", width: 41, template: "number" },
            { name: "total", label: "Total", width: 51, template: "number" },
            { name: "closed", label: "Closed", width: 59, template: "booleanCheckbox", firstsortorder: "desc" }
        ],
        data: [
            { id: "10",  invdate: "2015-10-01", name: "test",   amount: "" },
            { id: "20",  invdate: "2015-09-01", name: "test2",  amount: "300.00", tax: "20.00", closed: false, total: "320.00" },
            { id: "30",  invdate: "2015-09-01", name: "test3",  amount: "400.00", tax: "30.00", closed: false, total: "430.00" },
            { id: "40",  invdate: "2015-10-04", name: "test4",  amount: "200.00", tax: "10.00", closed: true,  total: "210.00" },
            { id: "50",  invdate: "2015-10-31", name: "test5",  amount: "300.00", tax: "20.00", closed: false, total: "320.00" },
            { id: "60",  invdate: "2015-09-06", name: "test6",  amount: "400.00", tax: "30.00", closed: false,  total: "430.00" },
            { id: "70",  invdate: "2015-10-04", name: "test7",  amount: "200.00", tax: "10.00", closed: true,  total: "210.00" },
            { id: "80",  invdate: "2015-10-03", name: "test8",  amount: "300.00", tax: "20.00", closed: false, total: "320.00" },
            { id: "90",  invdate: "2015-09-01", name: "test9",  amount: "400.00", tax: "30.00", closed: false, total: "430.00" },
            { id: "100", invdate: "2015-09-08", name: "test10", amount: "500.00", tax: "30.00", closed: true,  total: "530.00" },
            { id: "110", invdate: "2015-09-08", name: "test11", amount: "500.00", tax: "30.00", closed: false, total: "530.00" },
            { id: "120", invdate: "2015-09-10", name: "test12", amount: "500.00", tax: "30.00", closed: false, total: "530.00" }
        ],
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        idPrefix: "gb1_",
        rownumbers: true,
        sortname: "invdate",
        sortorder: "desc",
        caption: "#201",       
        height: '70%',
    });
	
});

function getItems(foodCategory){
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/food/"+foodCategory,
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			writeDivsFromJson(responseText);
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
		//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}
			
	});
}

function writeDivsFromJson(data){
	var html = '';
	for (var i in data) {
		var row = data[i];
		html +='<div class="col-lg-2 div-item">'+row.foodItemDesc+'<br>'+ '\u20B9' +row.amount+'</div>';
	}
	
	/*<div class="row" style="margin-left: 5" id="firstRow">
	<div class="col-lg-2 div-item">Classic Coffee<br>$15.0</div>
	<div class="col-lg-2 div-item">Classic Coffee</div>
	<div class="col-lg-2 div-item">Classic Coffee</div>
	<div class="col-lg-2 div-item">Classic Coffee</div>
	<div class="col-lg-2 div-item">Classic Coffeedddddddddddddddddddd ddddddssdfs</div>
</div>*/
	 //alert(html);
	$('#itemsRow').html(html);
}