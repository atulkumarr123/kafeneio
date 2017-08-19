$( document ).ready(function() {
	 $("#menuChildDiv").hide();
	 
	$("#menu").click(function(){
		$("#menuChildDiv").toggle();
	});
//	alert("Inready");
	
    $("#invoiceGrid").jqGrid({
        colModel: [
        	{ name: "foodCode", label: "Code", width: 140, align: "center",hidden:true},
            { name: "item", label: "Item", width: 140, align: "center" },
            { name: "quantity", label: "Quantity", width: 80, align: "center" },
            { name: "amount", label: "Amount", width: 80, template: "number"}
        ],
        data: [
           /* {  name: "test",   amount: "600.88" },
            {  name: "test2",  amount: "300.00" }*/
           
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
		html +='<div class="col-lg-2 div-item" onclick="addItem('
			+"'"+row.id+"'"+','
			+"'"+row.foodItemCode+"'"+','
			+"'"+row.foodItemDesc+"'"+','
			+row.amount+')">'+row.foodItemDesc+'<br>'+ '\u20B9' +row.amount+'</div>';
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


function addItem(id,foodCode, foodItemDesc, amount){
	//alert("add Item");
	if(!increaseIfPresent(foodCode)){
	 $("#invoiceGrid").jqGrid("addRowData",id , { foodCode:foodCode, item:foodItemDesc , quantity:1 ,  amount:amount  }, "last");
		}
	}
	


function increaseIfPresent(foodCode){
	//alert(foodCode);
	var isPresent=false;
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	 $.each(allData, function(i, item){
		 //alert(item.foodCode);
		    if (item.foodCode == foodCode) {
		    	isPresent=true;
		    	var rowData = $("#invoiceGrid").jqGrid("getRowData", item.id);
		    	rowData.quantity=parseInt(rowData.quantity)+1;
		    	$("#invoiceGrid").jqGrid("setRowData", item.id, rowData);
		    }
		  });
	 return isPresent;
}