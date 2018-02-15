
$( document ).ready(function() {
    $("#expensesGrid").jqGrid({
        colModel: [
        	{ name: "item", label: "Item",  align: "center"},
            { name: "amount", label: "Amount",  align: "center" },
            { name: "expenseTypeId", label: "Type",  align: "center", hidden : true },
            { name: "expenseTypeDesc", label: "Type",  align: "center" },
            { name: "creationDate", label: "Date",  align: "center" },
            { name: "remarks", label: "Remarks",  align: "center" },
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
        //idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Add Expenses",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#expensesGrid"),
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
	$('#datetimepicker3').datetimepicker({
		format: 'DD-MM-YYYY'
	});
	var currentDate = $("#currentDate").val();
	
	$('#expenseDateTime').val(currentDate);
	//alert(currentDate);
//	$( ".selector" ).datepicker( "setDate", new Date());
//	$('#datetimepicker3').val(currentDate);
	/*  $("#setMinDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setMinDate(new Date("june 12, 2013"));
    });                                
    $("#setMaxDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setMaxDate(new Date("july 4, 2013"));
    });
    $("#show").click(function () {
        $('#datetimepicker3').data("DateTimePicker").show();
    });
    $("#disable").click(function () {
        $('#datetimepicker3').data("DateTimePicker").disable();
    });
    $("#enable").click(function () {
        $('#datetimepicker3').data("DateTimePicker").enable();
    });
    $("#setDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setDate("10/23/2013");
    });
    $("#getDate").click(function () {
        alert($('#datetimepicker3').data("DateTimePicker").getDate());
    });*/

	$('#addExpensebutton').click(function() {
	//	alert("in add expenses button");
		$('#gbox_expensesGrid').show();
		$('#gbox_searchAndEditExpensesGrid').hide();
		$('#saveExpensesButton').show();
		$('#clearExpensesButton').show();
		
		var isFormFilled = $("#expenses").valid();
		var valid = validateForm();
		if(isFormFilled && valid){
			addExpense();
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
	if($("#type").val() == null || $("#type").val() == '' || $("#type").val() == '0' || $("#type").val() == undefined){
		$("#type").after('<label id="type-error" class="error" for="type">This field is required.</label>');
		valid = false;
	}
	return valid
}

function addExpense(){
	//alert("in add expenses");
	var item = $("#itemDesc").val();
	//alert($("#type").val());
	var amount = $("#amount").val();
	var remarks = $("#remarks").val();
	var expenseTypeDesc = $("#type").find("option:selected").text();
	var date = $("#datetimepicker3").find("input").val();
	var expenseTypeId = $("#type").val();
	$("#expensesGrid").jqGrid("addRowData",33 , {expenseTypeId : expenseTypeId, item : item, amount : amount , expenseTypeDesc : expenseTypeDesc, creationDate:date, remarks:remarks , }, "last");
}

function saveExpenses() {
	var ctx = $("#contextPath").val();
	var allData = $("#expensesGrid").jqGrid("getGridParam", "data");
	//alert(allData);
	if(jQuery.isEmptyObject(allData)){
		  new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: 'Please add atleast one expense!'
		   });
		  return false;
	}
	//alert(JSON.stringify(allData));
	/*var expense={};
	expense["item"]=null;
	expense["amount"]=null;
	expense["remarks"]=null;*/
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/expenses",
	      data: JSON.stringify(allData),
	      success :function(result) {
	    	  //alert(JSON.stringify(result));
	    	  new PNotify({
    			  type:'success',
    			  title: 'Success',
    			  text: result.message
	    	  });
	    	  $("#expensesGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	     },
	   error:function(result) {
		  /* alert(JSON.stringify(result.responseJSON.message));*/
		   new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: result.responseJSON.message
		   });
		   //alert("error"+JSON.stringify(responseText));
	   }
	  });
}

function removeItem(rowid){
	$('#expensesGrid').jqGrid('delRowData',rowid);
}

function clearExpenses(){
	  $("#expensesGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
}

$( document ).ready(function() {
	$('#searchAndEditExpensesbutton').click(function() {
	$('#gbox_expensesGrid').hide();
	$('#gbox_searchAndEditExpensesGrid').show();
	$('#saveExpensesButton').hide();
	$('#clearExpensesButton').hide();

	//searchAndEdit();
	});
/*   
   $('#gbox_expensesGrid').hide();
	$('#gbox_searchAndEditExpensesGrid').hide();*/
});



function searchAndEditExpenses(){
//	alert("in search and edit");
	$("#searchAndEditExpensesGrid").jqGrid('GridUnload');	
	
	waitingDialog.show('Please wait...');
	
	$.ajax({
		url :$("#contextPath").val()+"/expensesList",
		success : function(responseText) {
			EditExpenses(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
}

function EditExpenses(expenseData){
	$("#searchAndEditExpensesGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "item", label: "Expense",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
		    { name: "expenseTypeId", label: "expenseTypeId",  align: "center", hidden : true },
	        { name: "expenseTypeDesc", label: "Type",  align: "center" },
			{ name: "creationDate", label: "Date",  align: "center" },
			{ name: "remarks", label: "Remarks",  align: "center" },
			{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
				}},
            { name: 'decrease', label:"", width: 30, sortable: false, search: false, align: "center",
          	  formatter:function(){
          	      return "<span  style='cursor:pointer; display: inline-block;' class='ui-icon ui-icon-circle-minus'></span>"
          	  }}
			],
			data:expenseData,
			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#pager',
			sortname: 'orderNo',
			viewrecords: true,
			sortorder: "desc",
			autowidth: true,
			loadonce: true,
			 onCellSelect: function (rowid,iCol,cellcontent,e) {
		            if (iCol >= firstButtonColumnIndex) {
		                removeExpense(rowid);
		            }
			
		            else if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopup(rowid);	
				}
			},
			caption: "Search & Edit Expense",
			loadComplete:function() {
				adjustTotalExpense();
			}
	});
	grid = $("#searchAndEditExpensesGrid"),
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
   firstButtonColumnIndex = getColumnIndexByName(grid,'decrease');
	waitingDialog.hide();
}
 function openPopup(rowid){
	 	 $('#myModalExpenses').modal('show');
	 	$('#datetimepickerModal').datetimepicker({
			format: 'DD-MM-YYYY'
		});
	 	var rowData = $("#searchAndEditExpensesGrid").jqGrid("getRowData", rowid);
	 	 $("#expenseRowId").val(rowid);
	 	$('#foodItemDescModal').val(rowData.item);
	 	//alert(rowData.type);
	 	$('#amountModal').val(rowData.amount);
	 	$('#expenseDateTimeModal').val(rowData.creationDate);
	 	$('#remarksModal').val(rowData.remarks);
	 	$('#typeModal').val(rowData.expenseTypeId);		 
	 	 
		 /*	var rowData = $("#expenseReportGrid").jqGrid("getRowData", rowid);
		 	 $("#expenseRowId").val(rowid);
		 	$('#foodItemDesc').val(rowData.item);
		 	$('#amount').val(rowData.amount);
		 	$('#expenseDateTime').val(rowData.creationDate);
		 	$('#remarks').val(rowData.remarks);*/
 } 
 
 function updateExpense(){
	 var rowId= $("#expenseRowId").val();
		var ctx = $("#contextPath").val();
//		alert( $('#typeModal').val());
	 	var rowData = $("#searchAndEditExpensesGrid").jqGrid("getRowData", rowId);
	 	rowData.item = $('#foodItemDescModal').val();
	 	rowData.amount = $('#amountModal').val();
	 	rowData.creationDate = $('#expenseDateTimeModal').val();
	 	rowData.remarks = $('#remarksModal').val();
	 	rowData.expenseTypeDesc = $("#typeModal option:selected").text();
	 	rowData.expenseTypeId = $('#typeModal').val();
	 	rowData.editButton = '';
	 	$("#searchAndEditExpensesGrid").jqGrid("setRowData", rowId, rowData);
	   $.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url : ctx+"/report/updateExpenses",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		    	  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: result.message
		    	  });
		    	  $( "#cancelExpensesButton").click();
		    	  
		     },
		   error:function(result) {
			  // alert(JSON.stringify(result));
			   new PNotify({
		    		 type:'error',
		    		 title: 'Error',
		    		 text: result.responseJSON.message
			   });
			   //alert("error"+JSON.stringify(responseText));
		   }
		  });
 } 
 
 
function adjustTotalOrder(){
	var data = $("#orderReportGrid").jqGrid("getGridParam", "data");
	var amount = 0;
	for (var i in data) {
		var row = data[i];
		amount=parseInt(amount)+parseInt(row.amount);
		
	}	
	jQuery("#orderReportGrid").footerData('set',{orderNo:'Total', amount:amount});	
}

function adjustTotalExpense(){
	var data = $("#searchAndEditExpensesGrid").jqGrid("getGridParam", "data");
	var amount = 0;
	for (var i in data) {
		var row = data[i];
		amount=parseInt(amount)+parseInt(row.amount);
		
		
	}	
//	alert(amount);
	jQuery("#searchAndEditExpensesGrid").footerData('set',{orderNo:'Total', amount:amount});	
}

function removeExpense(rowid){
	
	$.ajax({
		url : $("#contextPath").val()+"/deleteExpense/"+rowid,
		success : function(responseText) {
			$(function(){
				new PNotify({ type:'success', title: 'Success', text: responseText.message});
				$('#searchAndEditExpensesGrid').jqGrid('delRowData',rowid);
			});
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: responseText.message});
			});
		}	
	});
	
}

