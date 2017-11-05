

$( document ).ready(function() {
    $("#foodItemsGrid").jqGrid({
        colModel: [
        	{ name: "id", label: "id",hidden:true},
        	{ name: "foodItemCode", label: "Food Item Code",  align: "center"},
            { name: "foodItemDesc", label: "Food Item Description",  align: "center" },
            { name: "amount", label: "Amount",  align: "center" },
            { name: "category", label: "Category",  align: "center" },
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
	var category = $("#category").find("option:selected").text();
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
	$("#foodItemsGrid").jqGrid("addRowData",id , { foodItemCode : foodItemCode, foodItemDesc : foodItemDesc, amount : amount , category:category, statusVisible : statusVisible, status : status}, "last");
}

function saveFoodItems() {
	var ctx = $("#contextPath").val();
	var allData = $("#foodItemsGrid").jqGrid("getGridParam", "data");
	var categoryId = $("#category").val();
	//alert(JSON.stringify(allData));
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

	searchAndEdit();
	});
   
   $('#gbox_foodItemsGrid').hide();
	$('#gbox_editFoodItemsGrid').hide();
});


function searchAndEditFoodItems(){
	$("#editFoodItemsGrid").jqGrid('GridUnload');	
	$.ajax({
		url :$("#contextPath").val()+"/",
		success : function(responseText) {
			searchAndEdit(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
	
}



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
            { name: "category", label: "Category",  align: "center" },
            { name: "status", label: "Status",  align: "center",hidden:true },
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
			caption: "Seached Food Items",
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
}