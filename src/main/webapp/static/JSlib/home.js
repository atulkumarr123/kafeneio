$( document ).ready(function() {
		
    $("#newOrdersGrid").jqGrid({
    	url :  $("#contextPath").val()+"/report/getOrderListToday/NEW",
		datatype : "json",
		mtype : 'POST',
    	colModel: [
    		{ name: "id", label: "id", hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
			{ name: 'table', label:"Table", width: 120, sortable: false, search: false, align: "center", 
    			formatter:function(cellValue, option){
    				if(cellValue == null  || cellValue == 'undefined') cellValue = '';
    				return "<div style='display:flex;'><input type='text' name='table' id = 'tableText_"+option.rowId+"' value = '"+cellValue+"' class='form-control' style='font-size: medium;'>&nbsp" +
    						"<button class='btn btn-default'  onclick = 'seatIt("+option.rowId+")' id = 'tableButton_"+option.rowId+"' type='button' ><b>Seat</b></button> </div>"
    			}},
    		{ name: 'serve', label:"", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:#6060c7' type='button' ><b>Serve</b></button>"
    			}},
    		{ name: 'cancel', label:"", width: 80, sortable: false, search: false, align: "center",
    			formatter:function(){
    				return "<button class='btn btn-default' style='color:red' type='button' ><b>Cancel</b></button>"
    		}}
    			],
        data: [
        ],
       
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
            	//alert("serveButton");
                serveThisOrder(rowid);
           
            }
            else if (iCol == cancelButton) {
            	//alert("cancelButton");
            	cancelThisOrder(rowid);
            }
        }
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
});


/**********************************************************************        servedOrdersGrid      ***********************************************************************/
$( document ).ready(function() {
	
    $("#servedOrdersGrid").jqGrid({
    	url :  $("#contextPath").val()+"/report/getOrderListToday/SERVED",
		datatype : "json",
		mtype : 'POST',
    	colModel: [
    		{ name: "id", label: "id",hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center", width:100},
			{ name: "amount", label: "Amount",  align: "right",template: "number", width: 100},
			{ name: "table", label: "Table",  align: "center",  width: 80},
    			],
        data: [
        	{"id":1, "orderNo":123 , "amount":123}
        ],
       
        footerrow: true,
        userDataOnFooter : true,
        rowNum:10,
		rowList:[10,20,30],
		guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        pager: '#servedOrdersPager',
//        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "orderNo",
        sortorder: "desc",
        caption: "Served Orders",       
        height: 'auto',
        loadonce: true,
    });
    $("#servedOrdersGrid").bind("jqGridAfterLoadComplete", function() {
    	adjustTotal();
	});
});

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

$( document ).ready(function() {
    $("#cancelledOrdersGrid").jqGrid({
    	url :  $("#contextPath").val()+"/report/getOrderListToday/CANCELLED",
		datatype : "json",
		mtype : 'POST',	
    
    	colModel: [
    		{ name: "id", label: "id",hidden:true},
			{ name: "orderNo", label: "Order No",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
    			],
        data: [
        	{"id":1, "orderNo":123 , "amount":123}
        ],
       
        footerrow: true,
        userDataOnFooter : true,
        guiStyle: "bootstrap",
        iconSet: "fontAwesome",
        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Cancelled Orders",       
        height: 'auto',
        loadonce: true,
     
    });
});
function serveThisOrder(rowid){
	//alert("Served called");
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/order/serve/"+rowid,
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


function cancelThisOrder(rowid){
	//alert("cancelled called");
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/order/cancel/"+rowid,
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