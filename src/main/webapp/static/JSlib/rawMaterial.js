$(document).ready(function(){

	$(window).on("resize", function () {
		var newWidth = $("#rawMaterialGrid").closest(".ui-jqgrid").parent().width();
		$("#rawMaterialGrid").jqGrid("setGridWidth", newWidth, true);

	});
});

$( document ).ready(function() {
    $("#rawMaterialGrid").jqGrid({
        colModel: [
        	{ name: "rawMaterialCode", label: "Raw Material Code",  align: "center"},
            { name: "rawMaterialDesc", label: "Raw Material Description",  align: "center" },
            { name: "unitDesc", label: "Unit",  align: "center" },
            { name: "unitValue", label: "Unit",  align: "center", hidden:true},
            { name: "quantity", label: "Quantity",  align: "center" },
            { name: "lowerLimit", label: "Lower Limit",  align: "center" },
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
        caption: "Add Raw Material",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#rawMaterialGrid"),
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
	
	$('#RawMaterialDateTime').val(currentDate);

	$('#addRawMaterialButton').click(function() {
	//	alert("in add expenses button");
		$('#gbox_rawMaterialGrid').show();
		$('#gbox_searchAndEditRawMaterialGrid').hide();
		$('#saveRawMaterialButton').show();
		$('#clearRawMaterialButton').show();
		
		var isFormFilled = $("#rawMaterial").valid();
		var valid = validateForm();
		if(isFormFilled && valid){
			//alert("validating form");
			addRawMaterial();
		}
	});

});

function validateForm(){
		var valid = true;
	return valid;
}

function addRawMaterial(){
	var rawMaterialCode = $("#rawMaterialCode").val();
	var rawMaterialDesc = $("#rawMaterialDesc").val();
	var unitDesc = $("#unit").find("option:selected").text();
	var quantity = $("#quantity").val();	
	var remarks = $("#remarks").val();
	var unitValue = $("#unit").val();
	var lowerLimit = $("#lowerLimit").val();
	//var date = $("#datetimepicker3").find("input").val();
	$("#rawMaterialGrid").jqGrid("addRowData",33 , { unitValue : unitValue, rawMaterialCode : rawMaterialCode, rawMaterialDesc : rawMaterialDesc ,unitDesc : unitDesc, quantity : quantity, remarks:remarks, lowerLimit: lowerLimit}, "last");
}

function saveRawMaterial() {

	var ctx = $("#contextPath").val();
	var allData = $("#rawMaterialGrid").jqGrid("getGridParam", "data");
	//alert(allData);
	if(jQuery.isEmptyObject(allData)){
		  new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: 'Please add atleast one Raw Material!'
		   });
		  return false;
	}
	//alert(JSON.stringify(allData));
	/*var expense={};
	expense["item"]=null;
	expense["amount"]=null;
	expense["remarks"]=null;*/

	var rawMaterial = {};
	rawMaterial["creationDate"] = null;
	rawMaterial["lastUpdatedDate"]  = null;

	/*rawMaterial["creationDate"] = $("#currentDate").val(); 
	rawMaterial["lastUpdatedDate"] = $("#lastUpdatedDate").val(); */
	
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/saveRawMaterials",
	      data: JSON.stringify(allData),
	      success :function(result) {
//	    	  alert(JSON.stringify(result));
	    	  new PNotify({
    			  type:'success',
    			  title: 'Success',
    			  text: result.message
	    	  });
	    	  $("#rawMaterialGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	     },
	   error:function(result) {
		  // alert(JSON.stringify(result.responseJSON.message));
		   new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: result.responseJSON.message
		   });
//		   alert("error"+JSON.stringify(result));
	   }
	  });
}

function removeItem(rowid){
	$('#rawMaterialGrid').jqGrid('delRowData',rowid);
}

function clearRawMaterial(){
	  $("#rawMaterialGrid").jqGrid("clearGridData", true).trigger("reloadGrid");

}

$( document ).ready(function() {
	$('#searchAndEditRawMaterialbutton').click(function() {
	$('#gbox_rawMaterialGrid').hide();
	$('#gbox_searchAndEditRawMaterialGrid').show();
	$('#saveRawMaterialButton').hide();
	$('#clearRawMaterialButton').hide();

	//searchAndEdit();
	});
/*   
   $('#gbox_expensesGrid').hide();
	$('#gbox_searchAndEditExpensesGrid').hide();*/
});



function searchAndEditRawMaterial(){
//	alert("in search and edit");
	$("#searchAndEditRawMaterialGrid").jqGrid('GridUnload');	
	
	//waitingDialog.show('Please wait...');
	
	$.ajax({
		url :$("#contextPath").val()+"/rawMaterialList",
		success : function(responseText) {
			alert(JSON.stringify(responseText));
			EditRawMaterial(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
	
}


function EditRawMaterial(expenseData){
	$("#searchAndEditRawMaterialGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
			{ name: "item", label: "Expense",  align: "center"},
			{ name: "amount", label: "Amount",  align: "right",template: "number"},
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
			data:RawMaterialData,
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
		                removeRawMaterial(rowid);
		            }
			
		            else if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopup(rowid);
					
				}

			},
			caption: "Search & Edit Raw Material",
			loadComplete:function() {
				adjustTotalRawMaterial();
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
	 	 $('#myModalRawMaterial').modal('show');
	 
	 	 
	 	var rowData = $("#searchAndEditRawMaterialGrid").jqGrid("getRowData", rowid);
	 	 $("#rawMaterialRowId").val(rowid);
	 	$('#foodItemDescModal').val(rowData.item);
	 	//alert(rowData.amount);
	 	$('#amountModal').val(rowData.amount);
	 	$('#rawMaterialDateTimeModal').val(rowData.creationDate);
	 	$('#remarksModal').val(rowData.remarks);
 } 
 
 
 function updateExpense(){
	 var rowId= $("#expenseRowId").val();
		var ctx = $("#contextPath").val();
	// alert(rowId);
	 	var rowData = $("#searchAndEditRawMaterialGrid").jqGrid("getRowData", rowId);
	 	rowData.item = $('#foodItemDescModal').val();
	 	rowData.amount = $('#amountModal').val();
	 	rowData.creationDate = $('#rawMaterialDateTimeModal').val();
	 	rowData.remarks = $('#remarksModal').val();
	 	rowData.editButton = '';
	 	$("#searchAndEditRawMaterialGrid").jqGrid("setRowData", rowId, rowData);
	   $.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url : ctx+"/report/updateRawMaterial",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		    	  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: result.message
		    	  });
		    	  $( "#cancelRawMaterialButton").click();
		    	  
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
 
/* 
function adjustTotalRawMaterial(){
	var data = $("#RawMaterialGrid").jqGrid("getGridParam", "data");
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
*/
