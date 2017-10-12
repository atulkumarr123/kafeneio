$(document).ready(function(){
	
  $(window).on("resize", function () {
	  var newWidth = $("#orderReportGrid").closest(".ui-jqgrid").parent().width();
	  $("#orderReportGrid").jqGrid("setGridWidth", newWidth, true);
	  
	  var newWidthEx = $("#expenseReportGrid").closest(".ui-jqgrid").parent().width();
	  $("#expenseReportGrid").jqGrid("setGridWidth", newWidthEx, true);
	  
//	  var newWidth = $("#application-list").closest(".ui-jqgrid").parent().width();
//	  $("#application-list").jqGrid("setGridWidth", newWidth, true);
});
});
$( document ).ready(function() {
    $('#fromDateTimePicker').datetimepicker({
    	 format: $("#dateTimeFormatCalendar").val()
    	 
    });
    $('#toDateTimePicker').datetimepicker({
    	 format: $("#dateTimeFormatCalendar").val()	
   });
    
    $("#fromReportDate").val($("#fromDateTime").val());
    $("#toReportDate").val($("#toDateTime").val());
    
    
});

function searchOrders(){
	$("#orderReportGrid").jqGrid('GridUnload');	
	var fromDate = $("#fromDateTimePicker").find("input").val();
	var toDate = $("#toDateTimePicker").find("input").val();

	$.ajax({
		url : $("#contextPath").val()+"/report/orderList?fromDate="+fromDate+"&&toDate="+toDate,
		success : function(responseText) {
			orderReport(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});

}
	
	
function orderReport(orderData){
	$("#orderReportGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/orderList?fromDate="+fromDate+"&&toDate="+toDate,
		//	datatype : "json",
		datatype : "local",
		//	mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
			{ name: "creationDate", label: "Date",  align: "center" },
			],

			data:orderData,
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
			caption: "Order Detail Report",
			 loadComplete:function() {
				 adjustTotalOrder();
			    }
	});
	/*$("#orderReportGrid").bind("jqGridAfterLoadComplete", function() {
		adjustTotalOrder();
	});*/


}


function searchExpenses(){
	$("#expenseReportGrid").jqGrid('GridUnload');	
	var fromDate = $("#fromDateTimePicker").find("input").val();
	var toDate = $("#toDateTimePicker").find("input").val();
	
	$.ajax({
		url :$("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		success : function(responseText) {
			expensesReport(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
	
}


function expensesReport(expenseData){
	$("#expenseReportGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "item", label: "Expense",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
			{ name: "creationDate", label: "Date",  align: "center" },
			{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
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
				//alert("iCol "+iCol);
				if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopup(rowid);

				}

			},
			caption: "Expense Detail Report",
			loadComplete:function() {
				adjustTotalExpense();
			}
	});
	
	grid = $("#expenseReportGrid"),
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

	/*$("#expenseReportGrid").bind("jqGridAfterLoadComplete", function() {
		 adjustTotalExpense();
	});*/

}

 function openPopup(rowid){
	 	 $('#myModal').modal('show');
	 	 $("#foodItemCode1").val('Atul'+rowid);
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
	var data = $("#expenseReportGrid").jqGrid("getGridParam", "data");
	var amount = 0;
	for (var i in data) {
		var row = data[i];
		amount=parseInt(amount)+parseInt(row.amount);
		
	}	
//	alert(amount);
	jQuery("#expenseReportGrid").footerData('set',{orderNo:'Total', amount:amount});	
}

