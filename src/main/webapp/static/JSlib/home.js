$( document ).ready(function() {
		getOrders('NEW',$("#currentDate").val());
		getOrders('SERVED',$("#currentDate").val());
		getOrders('CANCELLED',$("#currentDate").val());
		getOrders('YEST_NEW',$("#yesterdayDate").val());
});

function getOrders(status,date){
	
	$.ajax({
		url : $("#contextPath").val()+"/report/getOrderList/"+status+"?date="+date,
		success : function(responseText) {
			if(status=='NEW'){
				//alert(JSON.stringify(responseText));
				createPendingOrderGrid(responseText);
			}
			else if(status=='SERVED'){
				//alert(JSON.stringify(responseText));
				createServedOrdersGrid(responseText);
			}
			else if(status=='CANCELLED'){
				createCancelledOrdersGrid(responseText);
			}
			else if(status=='YEST_NEW'){
				createYestPendingOrderGrid(responseText);
			}
		},
		error:function(responseText) {
			$(function(){
				//new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
}

function createPendingOrderGrid(pendingOrders){
    $("#newOrdersGrid").jqGrid({
    	datatype : "local",
    	colModel: [
    		{ name: "id", label: "id", hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center", width:80},
			{ name: "amount", label: "Amt",  align: "right",template: "number",  width: 80},
			{ name: "discountPercentage", label: "Disc(%)",  align: "center", width:70},
			{ name: "orderTime", label: "Since",  align: "center", width:80,},
			
			{ name: 'table', label:"Table", width: 120, sortable: false, search: false, align: "center", 
    			formatter:function(cellValue, option){
    				if(cellValue == null  || cellValue == 'undefined') cellValue = '';
    				return "<div style='display:flex;'><input type='text' name='table' id = 'tableText_"+option.rowId+"' value = '"+cellValue+"' class='form-control' style='font-size: medium;'>&nbsp" +
    						"<button class='btn btn-default'  style='font-size: small;background: tan;' onclick = 'seatIt("+option.rowId+")' id = 'tableButton_"+option.rowId+"' type='button' ><b>Seat</b></button> </div>"
    			}},
    		{ name: 'serve', label:"Serve", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:#6060c7;font-size: small;background: tan;' type='button' ><b>Serve</b></button>"
    			}},
    		{ name: 'cancel', label:"Cancel", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:#c12f2f;font-size: small;background: tan;' type='button' ><b>Cancel</b></button>"
    		}}
    			],
        data: pendingOrders,
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        //idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Pending Orders", 
        loadonce: true,
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
        	//alert("iCol "+iCol);
            if (iCol == serveButton) {
            	modeOfPaymentCheck(rowid);
           
            }
            else if (iCol == cancelButton) {
            	//alert("cancelButton");
            	 $('#reasonForCancellationModal').modal('show');
            	 $('#rowIdOnModal').val(rowid);
            	
            }
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
    grid = $("#newOrdersGrid"),
	 getColumnIndexByName = function(grid,columnName) {
        var cm = grid.jqGrid('getGridParam','colModel');
        for (var i=0,l=cm.length; i<l; i++) {
            if (cm[i].name===columnName) {
                return i; // return the index
            }
        }
        return -1;
    },
    
    serveButton = getColumnIndexByName(grid,'serve');
    
    cancelButton = getColumnIndexByName(grid,'cancel');
  //  alert(serveButton);
   // alert(cancelButton);
}

/**********************************************************************        servedOrdersGrid      ***********************************************************************/

function createServedOrdersGrid(servedOrders){
	$("#servedOrdersGrid").jqGrid({
		//	url :  $("#contextPath").val()+"/report/getOrderListToday/SERVED",
//		datatype : "json",
		datatype : "local",
//		mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center", width:80},
			{ name: "amount", label: "Amt",  align: "right",template: "number", width: 80},
			{ name: "discountPercentage", label: "Disc(%)",  align: "center", width:70},
			{ name: "amount", label: "Amt",  align: "right",template: "number", width: 90},
			{ name: 'reInitiateButton', label:"ReInitiate", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Initiate</b></button>"
				}}
			],
			data: servedOrders,

			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#servedOrdersPager',
//			idPrefix: "gb1_",
			rownumbers: false,
			sortname: "orderNo",
			sortorder: "desc",
			caption: "Served Orders",       
			height: 'auto',
			loadonce: true,
			onCellSelect: function (rowid,iCol,cellcontent,e) {
				//alert("iCol "+iCol);
				if (iCol == reInitiateButton) {
					//alert("reInitiateButton");
					reInitiateThisOrder(rowid);

				}

			},
			loadComplete:function() {
				//adjustTotal();
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
						{ name: "foodDesc", width: (colModel[2].width) },
						{ name: "quantity", label:"Qty", width: (colModel[3].width/3), align:"right"},
		                { name: "amount", label:"Amount", width: (colModel[3].width*2)/3, align:"right"}
						],
						height: "100%",
						rowNum: 10000,
						autoencode: true,
						gridview: true,
						idPrefix: rowId + "_"
				});
				$subgrid.closest("div.ui-jqgrid-view")
				.children("div.ui-jqgrid-hdiv")
				.hide();
			},

	});

	grid = $("#servedOrdersGrid"),
	getColumnIndexByName = function(grid,columnName) {
		var cm = grid.jqGrid('getGridParam','colModel');
		for (var i=0,l=cm.length; i<l; i++) {
			if (cm[i].name===columnName) {
				return i; // return the index
			}
		}
		return -1;
	},

	reInitiateButton = getColumnIndexByName(grid,'reInitiateButton');

	$("#servedOrdersGrid").bind("jqGridAfterLoadComplete", function() {
		//adjustTotal();
	});

}

function adjustTotal(){
	var data = $("#servedOrdersGrid").jqGrid("getGridParam", "data");
	var amount = 0;
	for (var i in data) {
		var row = data[i];
		amount=parseInt(amount)+parseInt(row.amount);
		
	}	
//	alert(amount);
	jQuery("#servedOrdersGrid").footerData('set',{amount:amount});	
}
/*******************************************************************        cancelledOrdersGrid     **********************************************************************/

function createCancelledOrdersGrid(cancelledOrders) {
	
	
    $("#cancelledOrdersGrid").jqGrid({
 //   	url :  $("#contextPath").val()+"/report/getOrderListToday/CANCELLED",
	//	datatype : "json",
		datatype : "local",
//		mtype : 'POST',	
    
    	colModel: [
    		{ name: "id", label: "id",hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center",  width: 80},
			{ name: "amount", label: "Amt",  align: "right",template: "number",  width: 80},
			{ name: "discountPercentage", label: "Disc(%)",  align: "center", width:70},
			{ name: "reason", label: "Reason",  align: "left",  width: 270},
		/*	{ name: 'reInitiateButton', label:"ReInitiate", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Initiate</b></button>"
    		}}*/
    			],
        data: cancelledOrders,
       
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
//        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Cancelled Orders",       
        height: 'auto',
        loadonce: true,
        onCellSelect: function (rowid,iCol,cellcontent,e) {
        	//alert("iCol "+iCol);
            if (iCol == reInitiateButton) {
            	//alert("reInitiateButton");
                reInitiateThisOrder(rowid);
           
            }
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
                    { name: "foodDesc", width: (colModel[2].width) },
                    { name: "quantity", label:"Qty", width: (colModel[3].width/3), align:"right"},
                    { name: "amount", label:"Amount", width: (colModel[3].width*2)/3, align:"right"}
                ],
                height: "100%",
                rowNum: 100,
                autoencode: true,
                gridview: true,
                idPrefix: rowId + "_"
            });
            $subgrid.closest("div.ui-jqgrid-view")
                .children("div.ui-jqgrid-hdiv")
                .hide();
        },
     
    });
    
    grid = $("#cancelledOrdersGrid"),
	 getColumnIndexByName = function(grid,columnName) {
      var cm = grid.jqGrid('getGridParam','colModel');
      for (var i=0,l=cm.length; i<l; i++) {
          if (cm[i].name===columnName) {
              return i; // return the index
          }
      }
      return -1;
  },
  
  reInitiateButton = getColumnIndexByName(grid,'reInitiateButton');
}



function createYestPendingOrderGrid(pendingOrders){
    $("#newYestOrdersGrid").jqGrid({
    	datatype : "local",
    	colModel: [
    		{ name: "id", label: "id", hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center", width:80},
			{ name: "amount", label: "Amt",  align: "right",template: "number",  width: 80},
			{ name: "discountPercentage", label: "Disc(%)",  align: "center", width:70},
			{ name: "orderTime", label: "Since",  align: "center", width:80,},
			
			{ name: 'table', label:"Table", width: 120, sortable: false, search: false, align: "center", 
    			formatter:function(cellValue, option){
    				if(cellValue == null  || cellValue == 'undefined') cellValue = '';
    				return "<div style='display:flex;'><input type='text' name='table' id = 'tableText_"+option.rowId+"' value = '"+cellValue+"' class='form-control' style='font-size: medium;'>&nbsp" +
    						"<button class='btn btn-default'  style='font-size: small;background: tan;' onclick = 'seatIt("+option.rowId+")' id = 'tableButton_"+option.rowId+"' type='button' ><b>Seat</b></button> </div>"
    			}},
    		{ name: 'serve', label:"Serve", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:#6060c7;font-size: small;background: tan;' type='button' ><b>Serve</b></button>"
    			}},
    		{ name: 'cancel', label:"Cancel", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:#c12f2f;font-size: small;background: tan;' type='button' ><b>Cancel</b></button>"
    		}}
    			],
        data: pendingOrders,
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        //idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Yesterday's Pending Orders", 
        loadonce: true,
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
        	//alert("iCol "+iCol);
            if (iCol == serveButton) {
            	modeOfPaymentCheck(rowid);
           
            }
            else if (iCol == cancelButton) {
            	 $('#reasonForCancellationModal').modal('show');
            	 $('#rowIdOnModal').val(rowid);
            }
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
    grid = $("#newOrdersGrid"),
	 getColumnIndexByName = function(grid,columnName) {
        var cm = grid.jqGrid('getGridParam','colModel');
        for (var i=0,l=cm.length; i<l; i++) {
            if (cm[i].name===columnName) {
                return i; // return the index
            }
        }
        return -1;
    },
    
    serveButton = getColumnIndexByName(grid,'serve');
    
    cancelButton = getColumnIndexByName(grid,'cancel');
  //  alert(serveButton);
   // alert(cancelButton);
}


function serveThisOrder(orderId, mopId){
	var ctx = $("#contextPath").val();
	var localUrl = ctx+"/order/serve/"+orderId;
	if(mopId != null){
		localUrl = ctx+"/order/serve/"+orderId+"?mopId="+mopId;
	}
	
	$.ajax({
		url : localUrl,	
		success : function(responseText) {
			location.reload();
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
}


function cancelThisOrder(){
	//alert("cancelled called");
	var reason = $("#reasonForCancellation").val();
	if(reason == '' || reason == 'undefined' || reason == null){	
		$("#reasonForCancellation-error").text("Please enter a reason for cancellation");
		$("#reasonForCancellation-error").show();
		return false;
	}
	var ctx = $("#contextPath").val();
	var rowid = $("#rowIdOnModal").val();
	$.ajax({
		url : ctx+"/order/cancel/"+rowid+"?reason="+reason,
		success : function(responseText) {
			location.reload();
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
	
	$('#reasonForCancellationModal').hide();
}

function reInitiateThisOrder(rowid){
	//alert("reInitiateThisOrder called");
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/order/reInitiate/"+rowid,
		success : function(responseText) {
			location.reload();
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
}


function seatIt(orderId){
	var ctx = $("#contextPath").val();
	var table = $("#tableText_"+orderId).val();
	$.ajax({
		url : ctx+"/order/seatIt?orderId="+orderId+"&table="+table,
		success : function(responseText) {
			location.reload();
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
}



function writeMOP(data){
	$.each(data, function (i, data) {
	    $('#modeOfPayment').append($('<option>', { 
	        value: data.id,
	        text : data.description
	    }));
	});
}

function serveOrderOnOk(){
	var orderId= $("#mopOrderId").val();
	var mopId = $('select[id = modeOfPayment]').val();
	serveThisOrder(orderId, mopId);
}

function modeOfPaymentCheck(rowid){
	var ctx = $("#contextPath").val();
	 $.ajax({
		url : ctx+"/order/modeOfPaymentCheck/"+rowid,
		success : function(responseText) {
			if(responseText.modeOfPayment == null ){
				openModeOfPaymentPopup(responseText.id);
			}
			else{
				  serveThisOrder(responseText.id, null);
			}
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: JSON.stringify(responseText)});
			});
		}	
	});
}

function closeCancellation(){
	$("#reasonForCancellation-error").hide();
	$("#reasonForCancellation").val('');

}
