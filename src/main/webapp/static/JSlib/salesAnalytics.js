$(document).ready(function(){

	$(window).on("resize", function () {
		var newWidth = $("#orderReportGrid").closest(".ui-jqgrid").parent().width();
		$("#orderReportGrid").jqGrid("setGridWidth", newWidth, true);
	});
	
	$('select[multiple]').multiselect({
	    columns: 2,
	    placeholder: 'Select options'
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


function searchSales(){
	$("#salesAnalyticsGrid").jqGrid('GridUnload');	
	var modes = [];
	$(':checkbox:checked').each(function(i){
		if($(this).val()!="" && $(this).val()!= null){
			modes[i] = $(this).val();
		}
	});
	var modesStr = modes.join(", ");
	var category = $("#category").val();
	//alert($("#category").val());
	var fromDate = $("#fromDateTimePicker").find("input").val();
	var toDate = $("#toDateTimePicker").find("input").val();
	var checked =$('input[name="modeOfPayment"]:checked').serialize();
	waitingDialog.show('Please wait...');
	$.ajax({
		url : $("#contextPath").val()+"/saleAnalysis?fromDate="+fromDate+"&&toDate="+toDate
		+"&&modes="+modesStr+"&&category="+category,
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			salesReport(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});

}
	
	
function salesReport(orderData){
	$("#salesAnalyticsGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/orderList?fromDate="+fromDate+"&&toDate="+toDate,
		//	datatype : "json",
		datatype : "local",
		//	mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "order.orderNo", label: "Order No",  align: "center",hidden:true},
			{ name: "foodDesc", label: "Food Description",  align: "left" },
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
			{ name: "quantity", label: "Quantity",  align: "center" },
			],

			data:orderData,
			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30,50,100],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#pager',
			sortname: 'orderNo',
			viewrecords: true,
			sortorder: "desc",
			autowidth: true,
			caption: "Sales Analysis",
			 loadComplete:function() {
				 adjustTotalOrder('salesAnalyticsGrid');
			    },
			    
			    /*
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
		                }
		            });
		            $subgrid.closest("div.ui-jqgrid-view")
		                .children("div.ui-jqgrid-hdiv")
		                .hide();
		        }, */

	    
	});
	/*$("#orderReportGrid").bind("jqGridAfterLoadComplete", function() {
		adjustTotalOrder();
	});*/
	waitingDialog.hide();
}
