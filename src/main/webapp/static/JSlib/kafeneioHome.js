$( document ).ready(function() {
	 $("#menuChildDiv").hide();
	 
	$("#menu").click(function(){
		$("#menuChildDiv").toggle();
	});
//	alert("Inready");
	 

    // grid = $('#invoiceGrid'), firstButtonColumnIndex, buttonNames={};

     
    $("#invoiceGrid").jqGrid({
        colModel: [
        	{ name: "foodCode", label: "Code", width: 140, align: "center",hidden:true},
            { name: "foodDesc", label: "Item", width: 140, align: "left" },
            { name: "quantity", label: "Quantity", width: 80, align: "center" },
            { name: "amount", label: "Amount", width: 80, template: "number"},
            { name: 'decrease', label:"", width: 40, sortable: false, search: false, align: "center",
            	  formatter:function(){
            	      return "<span  style='cursor:pointer; display: inline-block;' class='ui-icon ui-icon-circle-minus'></span>"
            	  }}
        ],
        data: [
           /* {  name: "test",   amount: "600.88" },
            {  name: "test2",  amount: "300.00" }*/
           
        ],
       
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "#201",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }

    });

    grid = $("#invoiceGrid"),
	 getColumnIndexByName = function(grid,columnName) {
        var cm = grid.jqGrid('getGridParam','colModel');
        for (var i=0,l=cm.length; i<l; i++) {
            if (cm[i].name===columnName) {
                return i; // return the index
            }
        }
        return -1;
    },
    firstButtonColumnIndex = getColumnIndexByName(grid,'decrease');
});

function removeItem(rowid){
	var rowData = $("#invoiceGrid").jqGrid("getRowData", rowid);
	//alert(rowData.amount);
	if(rowData.quantity!=1){	
		rowData.amount=parseInt(rowData.amount)-(parseInt(rowData.amount)/parseInt(rowData.quantity));
		rowData.quantity=parseInt(rowData.quantity)-1;
		//alert(rowData.quantity);
		//alert(amount);
		$("#invoiceGrid").jqGrid("setRowData", rowid, rowData);
	}
	else{
		$('#invoiceGrid').jqGrid('delRowData',rowid);
	}
	adjustTotal();
}
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
	

	$('#itemsRow').html(html);
}


function addItem(id,foodCode, foodItemDesc, amount){
	//alert("add Item");
	if(!increaseIfPresent(foodCode,amount)){
	 $("#invoiceGrid").jqGrid("addRowData",id , { foodCode:foodCode, foodDesc:foodItemDesc , quantity:1 ,  amount:amount  }, "last");
		}
	adjustTotal();
	}
	


function increaseIfPresent(foodCode,amount){
	//alert(foodCode);
	var isPresent=false;
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	 $.each(allData, function(i, item){
		 //alert(item.foodCode);
		 
		    if (item.foodCode == foodCode) {
		    	isPresent=true;
		    	var rowData = $("#invoiceGrid").jqGrid("getRowData", item.id);
		    	rowData.quantity=parseInt(rowData.quantity)+1;
		    	//alert(rowData.quantity);
		    	rowData.amount=parseInt(amount)*parseInt(rowData.quantity);
		    	//alert(amount);
		    	$("#invoiceGrid").jqGrid("setRowData", item.id, rowData);
		    }
		  });
	 return isPresent;
}

function adjustTotal(){
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	var quantity=0;
	var amount=0;
	$.each(allData, function(i, item){
		var rowData = $("#invoiceGrid").jqGrid("getRowData", item.id);
			quantity=parseInt(quantity)+parseInt(rowData.quantity);
			amount=parseInt(amount)+parseInt(rowData.amount);
	});
	jQuery("#invoiceGrid").footerData('set',{foodDesc:'Total',quantity:quantity ,  amount:amount});
	
}

function generateBill() {
	var ctx = $("#contextPath").val();
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	var order={};
	order["orderNo"]="235";
	order["amount"]="100";
	order["creation_date"]="";
	order["orderDetails"]=allData;
	
	//alert(JSON.stringify(allData));
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/generateBill",
	      data: JSON.stringify(order),
	      success :function(result) {
	      	alert(JSON.stringify(result));
	     },
	   error:function(responseText) {
				//alert("error"+JSON.stringify(responseText));
				$('#outputLabel').text("Error");
	   }
	  });
}
