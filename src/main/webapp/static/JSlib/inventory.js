$(document).ready(function(){

	$(window).on("resize", function () {
		var newWidth = $("#inventoryGrid").closest(".ui-jqgrid").parent().width();
		$("#inventoryGrid").jqGrid("setGridWidth", newWidth, true);

	});
	
	$('#addInventoryButton').click(function() {
			//alert("in add expenses button");
			$('#gbox_inventoryGrid').show();
			$('#gbox_searchAndEditInventoryGrid').hide();
			$('#saveInventoryButton').show();
			$('#clearInventoryButton').show();
			
			var isFormFilled = $("#inventory").valid();
			var valid = validateForm();
			if(isFormFilled && valid){
				//alert("validating form");
				addInventory();
			}
		});
	
	$('#searchAndEditInventorybutton').click(function() {
		
		//searchAndEdit();
		});
});

$( document ).ready(function() {
    $("#inventoryGrid").jqGrid({
        colModel: [
        	{ name: "foodItemsId", label: "Food Items",  align: "center", hidden : true},
        	{ name: "foodItemsDesc", label: "Food Items",  align: "center"},
            { name: "rawMaterialId", label: "Raw Material",  align: "center", hidden : true },
            { name: "rawMaterialDesc", label: "Raw Material",  align: "center" },
            { name: "unitsDesc", label: "Unit",  align: "center" },
            { name: "unitsId", label: "Unit",  align: "center", hidden:true},
            { name: "quantity", label: "Quantity",  align: "center" },
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
        caption: "Add Inventory Item",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#inventoryGrid"),
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
	
	$('#inventoryDateTime').val(currentDate);

});

function validateForm(){
		var valid = true;
	return valid;
}


function addInventory(){
	//alert($("#foodItems").val());
	//alert($("#rawMaterialonInv").val());
	//alert($("#unitsOnInv").val());
	var foodItemsId = $("#foodItems").val();
	var foodItemsDesc = $("#foodItems").find("option:selected").text();
	var rawMaterialId = $("#rawMaterialonInv").val();
	var rawMaterialDesc = $("#rawMaterialonInv").find("option:selected").text();
	var unitsDesc = $("#unitsOnInv").find("option:selected").text();
	var quantity = $("#quantity").val();	
	var remarks = $("#remarks").val();
	var unitsId = $("#unitsOnInv").val();
	//var date = $("#datetimepicker3").find("input").val();
	$("#inventoryGrid").jqGrid("addRowData",33 , { rawMaterialId: rawMaterialId, rawMaterialDesc: rawMaterialDesc, foodItemsId: foodItemsId, foodItemsDesc: foodItemsDesc, unitsDesc : unitsDesc, unitsId : unitsId, quantity : quantity, remarks:remarks}, "last");
	}

function saveInventory() {
	var ctx = $("#contextPath").val();
	var allData = $("#inventoryGrid").jqGrid("getGridParam", "data");
	//alert(allData);
	if(jQuery.isEmptyObject(allData)){
		  new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: 'Please add atleast one Inventory!'
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
	      url : ctx+"/saveInventoryItems",
	      data: JSON.stringify(allData),
	      success :function(result) {
//	    	  alert(JSON.stringify(result));
	    	  new PNotify({
    			  type:'success',
    			  title: 'Success',
    			  text: result.message
	    	  });
	    	  $("#inventoryGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
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
	$('#inventoryGrid').jqGrid('delRowData',rowid);
}

function clearInventory(){
	  $("#inventoryGrid").jqGrid("clearGridData", true).trigger("reloadGrid");

}

$( document ).ready(function() {
	
/*   
   $('#gbox_expensesGrid').hide();
	$('#gbox_searchAndEditExpensesGrid').hide();*/
});



function searchAndEditInventory(){
//	alert("Going to hide");
	$('#gbox_inventoryGrid').hide();
	$('#gbox_searchAndEditInventoryGrid').show();
	$('#saveInventoryButton').hide();
	$('#clearInventoryButton').hide();
	
//	alert("in search and edit");
//	$("#rawMaterialGrid").jqGrid('GridUnload');
	$("#searchAndEditInventoryGrid").jqGrid('GridUnload');	
	
	waitingDialog.show('Please wait...');
	
	$.ajax({
		url :$("#contextPath").val()+"/inventoryList",
		success : function(responseText) {
			alert(JSON.stringify(responseText));
			editInventory(responseText);
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
			});
		}	
	});
	
}


function editInventory(inventoryData){
	$("#searchAndEditInventoryGrid").jqGrid({
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "foodItemsId", label: "Food Items",  align: "center", hidden : true},
        	{ name: "foodItemsDesc", label: "Food Items",  align: "center"},
            { name: "rawMaterialId", label: "Raw Material",  align: "center", hidden : true },
            { name: "rawMaterialDesc", label: "Raw Material",  align: "center" },
            { name: "unitsDesc", label: "Unit",  align: "center" },
            { name: "unitsId", label: "Unit",  align: "center", hidden:true},
            { name: "quantity", label: "Quantity",  align: "center" },
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
			data:inventoryData,
			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#pager',
			sortname: 'inventoryDesc',
			viewrecords: true,
			sortorder: "desc",
			autowidth: true,
			loadonce: true,
			 onCellSelect: function (rowid,iCol,cellcontent,e) {
		            if (iCol >= firstButtonColumnIndex) {
		                removeInventory(rowid);
		            }
			
		            else if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopup(rowid);
					
				}

			},
			caption: "Search & Edit Inventory Item",
			loadComplete:function() {
//				adjustTotalRawMaterial();
			}
	});
	
	grid = $("#searchAndEditInventoryGrid"),
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
	 	$('#rawMaterialDescModal').val(rowData.rawMaterialDesc);
	 	//alert(rowData.amount);
	 	$('#rawMaterialCodeModal').val(rowData.rawMaterialCode);
	 	$('#unitsModal').val(rowData.unitValue);
	 	$('#quantityModal').val(rowData.quantity);
	 	$('#lowerLimitModal').val(rowData.lowerLimit);
	 	$('#remarksModal').val(rowData.remarks);
 } 
 
 
 function updateRawMaterial(){
	 
	 var rowId= $("#rawMaterialRowId").val();
		var ctx = $("#contextPath").val();
	 	var rowData = $("#searchAndEditRawMaterialGrid").jqGrid("getRowData", rowId);
	 	rowData.rawMaterialDesc = $('#rawMaterialDescModal').val();
	 	rowData.rawMaterialCode = $('#rawMaterialCodeModal').val();
	 	rowData.unitValue = $('#unitsModal').val();
		rowData.unitDesc = $("#unitsModal option:selected").text();
	 	rowData.lowerLimit = $('#lowerLimitModal').val();
	 	rowData.quantity = $('#quantityModal').val();
	 	rowData.remarks = $('#remarksModal').val();
	   $.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url : ctx+"/updateRawMaterial",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		  	 	$("#searchAndEditRawMaterialGrid").jqGrid("setRowData", rowId, rowData);
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

*/

function removeRawMaterial(rowid){
	
	$.ajax({
		url : $("#contextPath").val()+"/deleteRawMaterial/"+rowid,
		success : function(responseText) {
			$(function(){
				new PNotify({ type:'success', title: 'Success', text: responseText.message});
				$('#searchAndEditRawMaterialGrid').jqGrid('delRowData',rowid);
			});
		},
		error:function(responseText) {
			$(function(){
				new PNotify({ type:'error', title: 'Error', text: responseText.message});
			});
		}	
	});
	
}

