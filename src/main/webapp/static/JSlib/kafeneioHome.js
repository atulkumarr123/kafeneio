$( document ).ready(function() {
	 $("#menuChildDiv").hide();
	 
	$("#menu").click(function(){
		$("#menuChildDiv").toggle();
	});
//	alert("Inready");
	
    $("#invoiceGrid").jqGrid({
        colModel: [
            { name: "item", label: "Item", width: 140, align: "center" },
            { name: "amount", label: "Amount", width: 100, template: "number" }
        ],
        data: [
            {  name: "test",   amount: "600.88" },
            {  name: "test2",  amount: "300.00" }
           
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
		html +='<div class="col-lg-2 div-item" onclick="addItem('+"'"+row.foodItemDesc+"'"+','+row.amount+')">'+row.foodItemDesc+'<br>'+ '\u20B9' +row.amount+'</div>';
	//alert(row.foodItemDesc,row.amount);
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


function addItem(foodItemDesc,amount){
	alert("add Item");
	 $("#invoiceGrid").jqGrid("addRowData", 3, {  item:foodItemDesc ,  amount:amount  }, "last");
}