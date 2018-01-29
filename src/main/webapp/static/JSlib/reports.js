$(document).ready(function(){

	$(window).on("resize", function () {
		var newWidth = $("#orderReportGrid").closest(".ui-jqgrid").parent().width();
		$("#orderReportGrid").jqGrid("setGridWidth", newWidth, true);

		var newWidthEx = $("#expenseReportGrid").closest(".ui-jqgrid").parent().width();
		$("#expenseReportGrid").jqGrid("setGridWidth", newWidthEx, true);

//		var newWidth = $("#application-list").closest(".ui-jqgrid").parent().width();
//		$("#application-list").jqGrid("setGridWidth", newWidth, true);
	});
});

$( document ).ready(function() {
    $('#fromDateTimePicker').datetimepicker({
    	 format: $("#dateTimeFormatCalendar").val()
    	 
    });
    $('#toDateTimePicker').datetimepicker({
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
	var modes = [];
	$(':checkbox:checked').each(function(i){
		if($(this).val()!="" && $(this).val()!= null){
			//alert($(this).val());
			modes[i] = $(this).val();
		}
	});
	var modesStr = modes.join(", ");
	var fromDate = $("#fromDateTimePicker").find("input").val();
	var toDate = $("#toDateTimePicker").find("input").val();
	var checked =$('input[name="modeOfPayment"]:checked').serialize();
	waitingDialog.show('Please wait...');
	$.ajax({
		url : $("#contextPath").val()+"/report/orderList?fromDate="+fromDate+"&&toDate="+toDate+"&&modes="+modesStr,
		success : function(responseText) {
//			alert(JSON.stringify(responseText));
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
			{ name: "modeOfPayment.description", label: "Mode of Payment",  align: "center" },
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
				 adjustTotalOrder('orderReportGrid');
			    },
			    
			    
			    subGrid: true,
		        subGridRowExpanded: function (subgridDivId, rowId) {
		            var $subgrid = $("<table id='" + subgridDivId + "_t'></table>"),
		                colModel = $(this).jqGrid("getGridParam", "colModel");

		            $subgrid.appendTo("#" + $.jgrid.jqID(subgridDivId));
		            $subgrid.jqGrid({
		                datatype: "local",
		                data: $(this).jqGrid("getLocalRow", rowId).orderDetails,
		                colModel: [
		                    { name: "foodDesc", label:"Item", width: (colModel[2].width) },
		                    { name: "quantity", label:"Qty", width: (colModel[3].width/3), align:"right"},
		                    { name: "amount", label:"Amount", width: (colModel[3].width*2)/3, align:"right"}
		                ],
		                height: "100%",
		                rowNum: 10000,
		                autoencode: true,
		                gridview: true,
		                idPrefix: rowId + "_",
		               /* gridview: true,
		                rowattr: function (rd) {
		                        return {"style": "background-color:#bd7575"};
		                }*/
		            });
		            $subgrid.closest("div.ui-jqgrid-view")
		                .children("div.ui-jqgrid-hdiv")
		                .hide();
		        },

	    
	});
	/*$("#orderReportGrid").bind("jqGridAfterLoadComplete", function() {
		adjustTotalOrder();
	});*/
//	$('#pleaseWaitDialog').modal('hide');
	waitingDialog.hide();

}

function searchExpenses(){
	$("#expenseReportGrid").jqGrid('GridUnload');
	
	var fromDate = $("#fromDateTimePicker").find("input").val();
	var toDate = $("#toDateTimePicker").find("input").val();
	
	waitingDialog.show('Please wait...');

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
			{ name: "remarks", label: "Remarks",  align: "center" }
			/*{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
				}},
            { name: 'decrease', label:"", width: 30, sortable: false, search: false, align: "center",
          	  formatter:function(){
          	      return "<span  style='cursor:pointer; display: inline-block;' class='ui-icon ui-icon-circle-minus'></span>"
          	  }}*/
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
   firstButtonColumnIndex = getColumnIndexByName(grid,'decrease');
   
	waitingDialog.hide();

}

 function openPopup(rowid){
	 	 $('#myModal').modal('show');
	 
	 	 
	 	var rowData = $("#expenseReportGrid").jqGrid("getRowData", rowid);
	 	 $("#expenseRowId").val(rowid);
	 	$('#foodItemDesc').val(rowData.item);
	 	$('#amount').val(rowData.amount);
	 	$('#expenseDateTime').val(rowData.creationDate);
	 	$('#remarks').val(rowData.remarks);
 } 
 
 
 function updateExpense(){
	 var rowId= $("#expenseRowId").val();
		var ctx = $("#contextPath").val();
	// alert(rowId);
	 	var rowData = $("#expenseReportGrid").jqGrid("getRowData", rowId);
	 	rowData.item = $('#foodItemDesc').val();
	 	rowData.amount = $('#amount').val();
	 	rowData.creationDate = $('#expenseDateTime').val();
	 	rowData.remarks = $('#remarks').val();
	 	rowData.editButton = '';
	 	$("#expenseReportGrid").jqGrid("setRowData", rowId, rowData);
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

function removeExpense(rowid){
	
	$.ajax({
		url : $("#contextPath").val()+"/deleteExpense/"+rowid,
		success : function(responseText) {
			$(function(){
				new PNotify({ type:'success', title: 'Success', text: responseText.message});
				$('#expenseReportGrid').jqGrid('delRowData',rowid);
			});
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: responseText.message});
			});
		}	
	});
	
}

