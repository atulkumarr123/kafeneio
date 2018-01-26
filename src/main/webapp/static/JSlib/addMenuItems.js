

$( document ).ready(function() {
    $("#foodItemsGrid").jqGrid({
        colModel: [
        	{ name: "id", label: "id",hidden:true},
        	{ name: "foodItemCode", label: "Food Item Code",  align: "center"},
            { name: "foodItemDesc", label: "Food Item Description",  align: "center" },
            { name: "amount", label: "Amount",  align: "center" },
            { name: "foodCategory", label: "Category",  align: "center" },
            { name: "status", label: "Status",  align: "center",hidden:true },
            { name: "statusVisible", label: "Status",  align: "center" },
            { name: 'decrease', label:"", sortable: false, search: false, align: "center",
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
        caption: "Add Food Item",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#foodItemsGrid"),
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


$( document ).ready(function() {
	
	$('#addFoodItemsbutton').click(function() {
		var isFormFilled = $("#foodItems").valid();
		var valid = validateForm();
		if(isFormFilled && valid){
			addFoodItems();
		}
	});
});

function validateForm(){
	var amount = $("#amount").val();
	var valid = true;
	if(!(/^\d{0,9}(\.\d{0,4})?$/.test(amount))){
		$("#amount").after('<label id="amount-error" class="error" for="amount">This field is required.</label>');
		$("#amount-error").text("Enter only digits/decimals");
		valid = false;
	}
	
	var category = $("#category").val();
	//alert(category);
	if(category == '0'){
		$("#category").after('<label id="category-error" class="error" for="category">This field is required.</label>');
	//	$("#category-error").text("");
		valid = false;
		
	}
	
	return valid
}

function addFoodItems(){
	$('#gbox_foodItemsGrid').show();
	$('#gbox_editFoodItemsGrid').hide();

	var id = $("#foodItemsGrid").jqGrid("getGridParam", "data").length;
	var foodItemCode = $("#foodItemCode").val();
	var foodItemDesc = $("#foodItemDesc").val();
	//alert(item);
	var amount = $("#amount").val();
	var foodCategory = $("#category").find("option:selected").text();
//	var status = $("#status").find("option:selected").text();
//	alert(status);
	var status = $("#status").val();
	if(status == '1'){
		status = true;
		statusVisible = 'Active';
	}
	else{
		status = false;
		statusVisible = 'Inactive';
	}
	//alert(category + " "+ status);
	
	//foodGridRowId=parseInt(foodGridRowId)+parseInt(1);
	//var date = $("#datetimepicker3").find("input").val();
	$("#foodItemsGrid").jqGrid("addRowData",id , { foodItemCode : foodItemCode, foodItemDesc : foodItemDesc, amount : amount , foodCategory:foodCategory, statusVisible : statusVisible, status : status}, "last");
}

function saveFoodItems() {
	var ctx = $("#contextPath").val();
	var allData = $("#foodItemsGrid").jqGrid("getGridParam", "data");
	var categoryId = $("#category").val();
	allData.foodCategory = null;
	alert(JSON.stringify(allData));
//	console.log(JSON.stringify(allData));
//	return false;
	/*var expense={};
	expense["item"]=null;
	expense["amount"]=null;
	expense["remarks"]=null;*/
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/foodItems?categoryId="+categoryId,
	      data: JSON.stringify(allData),
	      success :function(result) {
	    	  //alert(JSON.stringify(result));
	    	  new PNotify({
    			  type:'success',
    			  title: 'Success',
    			  //text: JSON.stringify(result)
    			  text: result.message
	    	//  alert("Expenses Saved Successfully");
	    	  //validateOrder();
	    	  //alert(JSON.stringify(result));
	    	  });
	    	  reloadGrid();
	     },
	   error:function(result) {
		  /* alert(JSON.stringify(result.responseJSON.message));*/
		   new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: JSON.stringify(result)
		   });
		   //alert("error"+JSON.stringify(responseText));
	   }
	  });
}

function removeItem(rowid){
	$('#foodItemsGrid').jqGrid('delRowData',rowid);
}

function reloadGrid(){
	$("#foodItemsGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
}

$( document ).ready(function() {
	
	$('#searchAndEditFoodItemsbutton').click(function() {
	$('#gbox_foodItemsGrid').hide();
	$('#gbox_editFoodItemsGrid').show();

	//searchAndEdit();
	});
   
   $('#gbox_foodItemsGrid').hide();
	$('#gbox_editFoodItemsGrid').hide();
});


function searchAndEditFoodItems(){
	$("#editFoodItemsGrid").jqGrid('GridUnload');	
	var ctx = $("#contextPath").val();
	var status = $("#status").val();
	if(status == '1'){
		status = true;
		statusVisible = 'Active';
	}
	else{
		status = false;
		statusVisible = 'Inactive';
	}
	var category = $("#category").val();
	var foodItemCode = $("#foodItemCode").val();
	
	var foodItems={"foodItemCode":$("#foodItemCode").val(),"foodItemDesc":$("#foodItemDesc").val(),"amount":$("#amount").val(),"foodCategory":{id:$("#category").val()},"status":(status==1)?true:false};
	/*foodItems["status"]=(status==1)?true:false;
	foodItems["foodCategory"]=null;
	foodItems["foodItemCode"]=foodItemCode;
	foodItems["id"]=null;
	foodItems["foodItemDesc"]=null;
	foodItems["amount"]=foodItemCode;
	foodItems["date"]=null;
	*/
	//alert(JSON.stringify(foodItems));
	
	waitingDialog.show('Please wait...');

	$.ajax({
		type: "POST",
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		url : ctx+"/editFoodItems",
		data: JSON.stringify(foodItems),
		success : function(responseText) {
			alert(JSON.stringify(responseText));
			searchAndEdit(responseText);

			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
			//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}	
	});
}

/*
function editFoodItems(data){
	for (var i in data) {
		var row = data[i];
		$("#editFoodItemsGrid").jqGrid("addRowData",row.id , { foodItemCode:row.foodItemCode, foodItemDesc:row.foodItemDesc ,  amount:row.amount , category:row.category , status:row.status  }, "last");
	}
} */

function searchAndEdit(foodItems){
	$("#editFoodItemsGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
        	{ name: "foodItemCode", label: "Food Item Code",  align: "center"},
            { name: "foodItemDesc", label: "Food Item Description",  align: "center" },
            { name: "amount", label: "Amount",  align: "center" },
            { name: "foodCategory.foodDesc", label: "Category",  align: "center" },
            { name: "status", label: "Status",  align: "center", hidden:true },
            { name: "statusVisible", label: "Status",  align: "center" },
			{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
				}}
			],
			data:foodItems,
			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#pager',
			//sortname: 'orderNo',
			viewrecords: true,
			sortorder: "desc",
			autowidth: true,
			loadonce: true,
			caption: "Searched Food Items",
			onCellSelect: function (rowid,iCol,cellcontent,e) {
				//alert("iCol "+iCol);
				if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopupFoodItem(rowid);
				}
			}
			/*loadComplete:function() {
				//adjustTotalExpense();
			}*/
	});
	
	grid = $("#editFoodItemsGrid"),
	 getColumnIndexByName = function(grid,columnName) {
       var cm = grid.jqGrid('getGridParam','colModel');
       for (var i=0,l=cm.length; i<l; i++) {
           if (cm[i].name===columnName) {
               return i; // return the index
           }
       }
       return -1;
   },
		editButton = getColumnIndexByName(grid,'editButton');
   
	waitingDialog.hide();

}

function updateFoodItem(){
	//alert("in update");
	
	 var rowId= $("#foodItemsRowId").val();
		var ctx = $("#contextPath").val();
		// alert(rowId);
	 	var rowData = $("#editFoodItemsGrid").jqGrid("getRowData", rowId);
	 	rowData.code = $('#foodItemCode').val();
	 	rowData.description = $('#foodItemDesc').val();
	 	//alert($('#status').val());
	 	rowData.amount = $('#amount').val();
	 	var statusVal = $('#itemStatusModal').val();
	 
	 	
	 	if(statusVal == '1'){
	 		statusVal = true;
			statusVisible = 'Active';
		}
		else{
			statusVal = false;
			statusVisible = 'Inactive';
		}
	 //	rowData.statusVisible= statusVisible;
	 	rowData.status= statusVal;
	 	rowData.creationDate = $('#addMenuItemDateTime').val();
	 	rowData.editButton = '';
	 	
	 //	$("#editUnitsGrid").jqGrid("setRowData", rowId, rowData);
	   /*$.ajax({
}

function updateFoodItem(){
	//alert("in update");
	
	 var rowId= $("#foodItemsRowId").val();
		var ctx = $("#contextPath").val();
		// alert(rowId);
	 	var rowData = $("#editFoodItemsGrid").jqGrid("getRowData", rowId);
	 	rowData.code = $('#code').val();
	 	rowData.description = $('#description').val();
	 	//alert($('#status').val());
	 	rowData.amount = $('#amount').val();
	 	var statusVal = $('#itemStatusModal').val();
	 
	 	
	 	if(statusVal == '1'){
	 		statusVal = true;
			statusVisible = 'Active';
		}
		else{
			statusVal = false;
			statusVisible = 'Inactive';
		}
	 //	rowData.statusVisible= statusVisible;
	 	rowData.status= statusVal;
	 	rowData.creationDate = $('#expenseDateTime').val();
	 	rowData.editButton = '';
	 	
	 //	$("#editUnitsGrid").jqGrid("setRowData", rowId, rowData);
	  /* $.ajax({
>>>>>>> branch 'master' of https://github.com/atulkumarr123/kafeneio.git
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url : ctx+"/updateUnit",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		    	  rowData.status= statusVisible;
		    	  $("#editUnitsGrid").jqGrid("setRowData", rowId, rowData);
		    	  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: result.message
		    	  });
		    	  $( "#cancelUnitButton").click();
		    	  
		     },
		   error:function(result) {
			   alert(JSON.stringify(result));
			   new PNotify({
		    		 type:'error',
		    		 title: 'Error',
		    		 text: result.responseJSON.message
			   });
			   //alert("error"+JSON.stringify(responseText));
		   }
		  }); */
} 


function openPopupFoodItem(rowid){
	$('#searchedFoodItemsModal').modal('show');
	var rowData = $("#editFoodItemsGrid").jqGrid("getRowData", rowid);
	$("#foodItemsRowId").val(rowid);
	$('#foodItemCode').val(rowData.foodItemCode);
	$('#foodItemDesc').val(rowData.foodItemDesc);
	//alert(rowData.status);
	var status = rowData.status;
	if(status == 'Active'){
		$('[id=itemStatusModal] option').filter(function() { 
			return ($(this).text() == 'Active'); 
		}).prop('selected', true);
	}
	else {
		$('[id=itemStatusModal] option').filter(function() { 
			return ($(this).text() == 'Inactive'); 
		}).prop('selected', true);
	}
	
	
} 

