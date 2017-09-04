$( document ).ready(function() {
	 //$("#menuChildDiv").hide();
	 
	 
	//$("#menu").click(function(){
		//reloadGrid();
		$("#menuChildDiv").show();
	//});	 
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
           
        ],
       
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "#",       
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



function reloadGrid(){
	$("#invoiceGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	$("#invoiceGrid").jqGrid('setCaption','');
}
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
	//var space = '&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp';
	var rowCount=jQuery('#invoiceGrid').jqGrid('getGridParam', 'reccount'); 
	if(rowCount < 1){
		setCaption();
	}
	if(!increaseIfPresent(foodCode,amount)){
	 $("#invoiceGrid").jqGrid("addRowData",id , { foodCode:foodCode, foodDesc:foodItemDesc , quantity:1 ,  amount:amount  }, "last");
		}
	adjustTotal();
	}

function setCaption(){
	//alert("going to set caption");
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/maxOrderNo",
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			$("#invoiceGrid").jqGrid('setCaption',responseText);
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
			alert("error"+JSON.stringify(responseText));
			$('#outputLabel').text("Error");
		}
	});
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
	if(!validateOrder()){
		return false;
	}
	var ctx = $("#contextPath").val();
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	var orderNo = $("#invoiceGrid").jqGrid("getGridParam", "caption");
	var order={};
	order["orderNo"]=orderNo;
	order["amount"]=null;
	order["creation_date"]=null;
	order["orderDetails"]=allData;
	
	//alert(JSON.stringify(allData));
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/generateBill",
	      data: JSON.stringify(order),
	      success :function(result) {
	    	  $(function(){
	    		  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: 'Order <b>'+orderNo+'</b> Saved Successfully!'
	    		  });
	    	  });
			  reloadGrid();
	     },
	     error:function(responseText) {
	    	 new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: JSON.stringify(responseText)
	    	 });
	     }
	  });
	 
}


function validateOrder(){
	var rowCount=jQuery('#invoiceGrid').jqGrid('getGridParam', 'reccount');
	  
	  if(rowCount!=0){
		  //alert(isEmpty);    
		  return true;
	  }
	  else{
		  $(function(){
   		   new PNotify({
   			   type:'info',
   			   title: 'Info',
   		      text: 'Please Order!'
   		   });
   		});
	  return false;
	  }
}
