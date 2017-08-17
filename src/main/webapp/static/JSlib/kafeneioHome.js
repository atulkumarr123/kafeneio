$( document ).ready(function() {
	 $("#menuChildDiv").hide();
	 
	$("#menu").click(function(){
		$("#menuChildDiv").toggle();
	});
//	alert("Inready");
	
    $("#invoiceGrid").jqGrid({
        colModel: [
            { name: "name", label: "Client", width: 53 },
            { name: "invdate", label: "Date", width: 80, align: "center", sorttype: "date",
                formatter: "date", formatoptions: { newformat: "d-M-Y" } },
            { name: "amount", label: "Amount", width: 65, template: "number" }
         /*   { name: "tax", label: "Tax", width: 41, template: "number" },
            { name: "total", label: "Total", width: 51, template: "number" },
            { name: "closed", label: "Closed", width: 59, template: "booleanCheckbox", firstsortorder: "desc" }*/
        ],
        data: [
            {  invdate: "2015-10-01", name: "test",   amount: "600.88" },
            {  invdate: "2015-09-01", name: "test2",  amount: "300.00" },
            {  invdate: "2015-09-01", name: "test3",  amount: "400.00"},
            {  invdate: "2015-10-04", name: "test4",  amount: "200.00"},
            {  invdate: "2015-10-31", name: "test5",  amount: "300.00" },
            {  invdate: "2015-10-31", name: "test5",  amount: "300.00" },
            {  invdate: "2015-10-31", name: "test5",  amount: "300.00" },
            {  invdate: "2015-10-31", name: "test5",  amount: "300.00" }
           
        ],
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        idPrefix: "gb1_",
        rownumbers: true,
        sortname: "invdate",
        sortorder: "desc",
        caption: "#201",       
        height: 'auto',
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
		html +='<div class="col-lg-2 div-item" onclick="addItem()">'+row.foodItemDesc+'<br>'+ '\u20B9' +row.amount+'</div>';
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


function addItem(){
	 $("#invoiceGrid").jqGrid("addRowData", 9, {  invdate: "2015-10-31", name: "test5",  amount: "300.00" }, "last");
}