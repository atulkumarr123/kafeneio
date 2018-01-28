$( document ).ready(function() {
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/loadRestraMenu/",
		success : function(responseText) {
			$("#wholeMenu").val(JSON.stringify(responseText));
		},
		error:function(responseText) {
		//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}	
	});
	
	
	
		$("#menuChildDiv").show();
	//});	 
    // grid = $('#invoiceGrid'), firstButtonColumnIndex, buttonNames={};
     
    $("#invoiceGrid").jqGrid({
        colModel: [
        	{ name: "foodCode", label: "Code", hidden:true},
            { name: "foodDesc", label: "Item", width: 110, align: "left" },
            { name: "quantity", label: "Qty", width: 50, align: "center" },
            { name: "amount", label: "Amt", width: 60, template: "number",align: "right" },
            { name: 'decrease', label:"", width: 30, sortable: false, search: false, align: "center",
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
	if(rowid == -1000 || rowid == -1001 || rowid == -1002 ){
	new PNotify({ type:'error', title: 'Nopes!', text: 'Can not remove the GST!'});
		return false;
	}


	if(validateExistingBeforeRemoval(rowid)){
		new PNotify({ type:'info', title: 'Info', text: 'Can not remove items after discount applied, Please remove discount to removeitems!'});
		return false;
	}
	var rowData = $("#invoiceGrid").jqGrid("getRowData", rowid);
	//alert(rowData.amount);
	if(rowData.quantity!=1 && rowid != -999){	
		rowData.amount=parseInt(rowData.amount)-(parseInt(rowData.amount)/parseInt(rowData.quantity));
		rowData.quantity=parseInt(rowData.quantity)-1;
		//alert(rowData.quantity);
		//alert(amount);
		$("#invoiceGrid").jqGrid("setRowData", rowid, rowData);
	}
	else{
		$('#invoiceGrid').jqGrid('delRowData',rowid);
	}
	applyGST();
	adjustTotal();
}
function getItems(foodCategory){
//	var foodItemsObj = {"WWP":[{"id":606,"status":true,"date":1512757800000,"foodItemCode":"PASTAARABNVEG","foodItemDesc":"Pasta Arrabiata (Non-Veg) Red Sauce","amount":180},{"id":610,"status":true,"date":1512757800000,"foodItemCode":"MXSSPSTANV","foodItemDesc":"Mix Sauce Pasta (Non Veg)","amount":220},{"id":768,"status":true,"date":1512757800000,"foodItemCode":"FLLDD","foodItemDesc":"Fully Loaded","amount":50},{"id":766,"status":true,"date":1512757800000,"foodItemCode":"AddOns","foodItemDesc":"AddOn-Paneer","amount":30},{"id":605,"status":true,"date":1512757800000,"foodItemCode":"PASTAARABVEG","foodItemDesc":"Pasta Arrabiata (Veg) Red Sauce","amount":160},{"id":608,"status":true,"date":1512757800000,"foodItemCode":"PastaAlfNonVeg","foodItemDesc":"Pasta Alfredo (Non-Veg) White Sauce","amount":180},{"id":609,"status":true,"date":1512757800000,"foodItemCode":"MXSSPSTA","foodItemDesc":"Mix Sauce Pasta (Veg)","amount":200},{"id":611,"status":true,"date":1512757800000,"foodItemCode":"AddOnschk","foodItemDesc":"AddOn-Chicken","amount":40},{"id":772,"status":true,"date":1516893607000,"foodItemCode":"sdfgs","foodItemDesc":"sdfgs","amount":33},{"id":773,"status":false,"date":1516960425000,"foodItemCode":null,"foodItemDesc":null,"amount":2},{"id":774,"status":true,"date":1516971344000,"foodItemCode":"abc","foodItemDesc":"abc","amount":200},{"id":607,"status":true,"date":1512757800000,"foodItemCode":"PastaAlf","foodItemDesc":"Pasta Alfredo (Veg) White Sauce","amount":160}]};
	var foodItemsObj = JSON.parse($("#wholeMenu").val());
	var foodItems = foodItemsObj[foodCategory];
	writeDivsFromJson(foodItems);
	
	/*
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/food/"+foodCategory,
		success : function(responseText) {
			alert(JSON.stringify(responseText));
			writeDivsFromJson(responseText);
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
		//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}	
	});*/
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
	if(validateExistingGrid()){
		  new PNotify({ type:'info', title: 'Info', text: 'Items not allowed after discount applied, Please remove discount to add more items!'});
		  return false;
	}
	if(!increaseIfPresent(foodCode,amount)){
	 $("#invoiceGrid").jqGrid("addRowData",id , { foodCode:foodCode, foodDesc:foodItemDesc , quantity:1 ,  amount:amount  }, "last");
		}
	applyGST();
	adjustTotal();
	}

function setCaption(){
	//alert("going to set caption");
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/maxOrderNo",
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			$("#orderNumber").val(responseText);
			$("#invoiceGrid").jqGrid('setCaption',responseText+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+$("#currentDateTime").val());
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
			//alert("error"+JSON.stringify(responseText));
			$('#outputLabel').text("Error");
		}
	});
}

function validateExistingGrid(){
	var isDiscApplied = false;
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	$.each(allData, function(i, item){
		if(item.foodCode == "DSCNT") {
			isDiscApplied = true;
		}
	});
	return isDiscApplied;
}

function validateExistingBeforeRemoval(rowId){
	
	
	
	var isDiscApplied = false;
	var rowData = $("#invoiceGrid").jqGrid("getRowData", rowId);
	if(rowData.foodCode != 'DSCNT'){
		var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
		$.each(allData, function(i, item){
			if(item.foodCode == "DSCNT") {
				isDiscApplied = true;
			}
		});
	}
	return isDiscApplied;
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
		if(rowData.quantity != null && rowData.quantity != ''){
			quantity=parseInt(quantity)+parseInt(rowData.quantity);
		}
		amount=parseFloat(amount)+parseFloat(rowData.amount);
	});

	jQuery("#invoiceGrid").footerData('set',{foodDesc:'Total',quantity:quantity ,  amount:amount});

}



function generateBill() {
	if(!validateOrder()){
		return false;
	}
	openModeOfPaymentPopup(null); //Here order is getting passed as NULL, Since Order is not yet created
	
}

function saveOrderWithModeOfPayment(){
	
	var mopId = $('select[id = modeOfPayment]').val();
	saveOrder(mopId);
}


function saveOrder(mopId){
	
	var ctx = $("#contextPath").val();
	var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
	var orderNo = $("#orderNumber").val();
	var discountPercentage = $("#discountPercentage").val();
	var order={};
	order["orderNo"]=orderNo;
	order["amount"]=null;
	order["creation_date"]=null;
	order["orderDetails"]=allData;
	order["discountPercentage"]=discountPercentage;
	var localUrl = ctx+"/generateBill";
	if(mopId != null){
		localUrl = ctx+"/generateBill?mopId="+mopId;
	}
	//alert(JSON.stringify(allData));
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : localUrl,
	      data: JSON.stringify(order),
	      success :function(result) {
	    	  //alert(JSON.stringify(result));
		   	   $.print("#printGrid");
	    	  if(result.statusCode == '208'){
	    		  $(function(){
		    		  new PNotify({ type:'info', title: 'Info', text: result.message});
		    	  });
	    	  }
	    	  else if(result.statusCode == '200'){
	    		  $(function(){
		    		  new PNotify({ type:'success', title: 'Success', text: result.message});
		    	  });
	    	  }
	    	  else{
	    		   $(function(){
	    			  new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
	    		  });
	    	  }
	    
			  reloadGrid();
				 $( "#closeButton").click();
			 
	     },
	     error:function(responseText) {
	    	 new PNotify({ type:'error', title: 'Error', text: JSON.stringify(responseText)
	    	 });
	     }
	  });

}

function validateOrder(){
	var rowCount=jQuery('#invoiceGrid').jqGrid('getGridParam', 'reccount');
	  if(rowCount!=0){
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

function discount(){
	if(!validateOrder()){
		return false;
	}
	else{
	$('#discountModal').modal('show');
	}
}


$( document ).ready(function() {
	$("#discountModal").on('shown.bs.modal', function () {
	    $("#discountPercentage").focus();
	})
$('#okDiscountButton').click(function() {
	var isFormFilled = $("#menu").valid();
	var valid = validateDiscountModal();
	
	if(valid){
	calculateDiscount();
	}
	else{
		return false;
	}
	
	  $( "#cancelDiscountButton").click();

});
});

function validateDiscountModal(){
	var discountPercentage = $("#discountPercentage").val();
	var valid = true;
	if(!(/^\d{0,9}(\.\d{0,4})?$/.test(discountPercentage))){
		$("#discountPercentage-error").text("Enter only digits/decimals");
		$("#discountPercentage-error").show();
		valid = false;
	}

	if(discountPercentage>50){
		$("#discountPercentage-error").text("Discount cannot be more than 50");
		$("#discountPercentage-error").show();
		valid = false;
	}
	if(valid){
		$("#discountPercentage-error").hide();
	}
	return valid;
}

function calculateDiscount(){
	var discountPercentage = $("#discountPercentage").val();
	if(discountPercentage!=null){
		var isPresent = false;
		var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
		var amount=0;
		$.each(allData, function(i, item){
			var rowData = $("#invoiceGrid").jqGrid("getRowData", item.id);
			if(rowData.foodCode == 'DSCNT'){
				isPresent = true;
			}
			else{
				amount=parseInt(amount)+parseInt(rowData.amount);
			}
		});
		//alert((amount * discountPercentage)/100);
		var discountAmount = Math.floor((amount * discountPercentage)/100);
		//alert(discountAmount);
		if(isPresent){
			$("#invoiceGrid").jqGrid("setRowData", -999,  { foodCode:'DSCNT', quantity:null, foodDesc:'Discount' ,  amount:'-'+discountAmount });
			$('#discountPercentage').val("");
		}
		else{
			$("#invoiceGrid").jqGrid("addRowData",-999 , { foodCode:'DSCNT', quantity:null, foodDesc:'Discount' ,  amount:'-'+discountAmount }, "last");	
		}
		applyGST();
		adjustTotal();
	}

}



function applyGST(){
	$('#invoiceGrid').jqGrid('delRowData',-1000);
	$('#invoiceGrid').jqGrid('delRowData',-1001);
	$('#invoiceGrid').jqGrid('delRowData',-1002);
	
		var allData = $("#invoiceGrid").jqGrid("getGridParam", "data");
		var amount=0;
		$.each(allData, function(i, item){
			var rowData = $("#invoiceGrid").jqGrid("getRowData", item.id);
				amount=parseInt(amount)+parseInt(rowData.amount);
		});
		var amountAfterGST = (amount * 2.5)/100;
		$("#invoiceGrid").jqGrid("addRowData",-1000 , { foodCode:'SGST', quantity:null, foodDesc:'SGST(2.5%)' ,  amount:'+'+amountAfterGST }, "last");	
		$("#invoiceGrid").jqGrid("addRowData",-1001 , { foodCode:'CGST', quantity:null, foodDesc:'CGST(2.5%)' ,  amount:'+'+amountAfterGST }, "last");	
		$("#invoiceGrid").jqGrid("addRowData",-1002 , { foodCode:'IGST', quantity:null, foodDesc:'IGST' ,  }, "last");	
}
