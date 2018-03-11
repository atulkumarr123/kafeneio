

$( document ).ready(function() {
    $("#expenseTypeGrid").jqGrid({
        colModel: [
        	{ name: "id", label: "id",hidden:true},
        	{ name: "code", label: "Expense Type Code",  align: "center"},
            { name: "description", label: "Expense Type Description",  align: "center" },
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
        caption: "Add Expense Type",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#expenseTypeGrid"),
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
	$('#addExpenseTypeButton').click(function() {
		var isFormFilled = $("#expenseTypeForm").valid();
		if(isFormFilled){	
		addExpenseTypes();

		}
	});
});


function addExpenseType(){
	
	$('#gbox_expenseTypeGrid').show();
	$('#gbox_editExpenseTypeGrid').hide();
	
	var id = $("#expenseTypeGrid").jqGrid("getGridParam", "data").length;
	var expenseTypeCode = $("#expenseTypeCode").val();
	var expenseTypeDesc = $("#expenseTypeDesc").val();
	var status = $("#status").val();
	if(status == '1'){
		status = true;
		statusVisible = 'Active';
	}
	else{
		status = false;
		statusVisible = 'Inactive';
	}
	$("#expenseTypeGrid").jqGrid("addRowData",id , { code : expenseTypeCode, description : expenseTypeDesc, statusVisible : statusVisible, status : status}, "last");
	$('#saveExpenseTypeButton').show();
}

function saveExpenseType() {

	if(!validateExpenseType()){
		return false;
	}

	else
	{
		var ctx = $("#contextPath").val();
		var allData = $("#expenseTypeGrid").jqGrid("getGridParam", "data");
		$.ajax({
			type: "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			url : ctx+"/addExpenseType",
			data: JSON.stringify(allData),
			success :function(result) {
				if(result.statusCode == '200'){
					new PNotify({ type:'success', title: 'Success', text: result.message });
				}
				else if(result.statusCode == '500'){
					new PNotify({ type:'error', title: 'Error', text: result.message });
				}
				reloadGrid();
			},
			error:function(result) {	  
				new PNotify({ type:'error', title: 'Error', text: result.message });
			}
		});
	}
}

function removeItem(rowid){
	$('#expenseTypeGrid').jqGrid('delRowData',rowid);
}

function reloadGrid(){
	$("#expenseTypeGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
}



$( document ).ready(function() {
	$('#searchExpenseTypebutton').click(function() {
	$('#gbox_expenseTypeGrid').hide();
	$('#gbox_editExpenseTypeGrid').show();
	searchAndEdit();
	});
   
   $('#gbox_expenseTypeGrid').hide();
	$('#gbox_editExpenseTypeGrid').hide();
});

function searchAndEditExpenseType(){
	$('#saveExpenseTypeButton').hide();
	$("#editExpenseTypeGrid").jqGrid('GridUnload');	
		
	var ctx = $("#contextPath").val();
	
	waitingDialog.show('Please wait...');
	
	$.ajax({
		url : ctx+"/searchExpenseType",
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			editExpenseType(responseText);
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
		//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}	
	});
	
}

function editExpenseType(data){
	for (var i in data) {
		var row = data[i];
		//alert(row.status);
		if(row.status == true){
			row.status = 'Active';
		}
		else{
			//status = false;
			row.status = 'Inactive';
		}
		$("#editExpenseTypeGrid").jqGrid("addRowData",row.id , {id: row.id, code:row.code, description:row.description , status : row.status}, "last");
	}
	
}
function searchAndEdit(expenseType){
	$("#editExpenseTypeGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:true},
        	{ name: "code", label: "Expense Type Code",  align: "center"},
            { name: "description", label: "Expense Type Description",  align: "center" },
            { name: "status", label: "Status",  align: "center" },
          //  { name: "statusVisible", label: "Status",  align: "center" },
			{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
				}},
				
				{ name: 'decrease', label:"", sortable: false, search: false, align: "center",
	            	  formatter:function(){
	            	      return "<span  style='cursor:pointer; display: inline-block;' class='ui-icon ui-icon-circle-minus'></span>"
	            	  }}
			],
			data:units,
			footerrow: true,
			userDataOnFooter : true,
			rowNum:10,
			rowList:[10,20,30],
			guiStyle: "bootstrap",
			iconSet: "fontAwesome",
			pager: '#pager',
			viewrecords: true,
			sortorder: "desc",
			autowidth: true,
			loadonce: true,
			onCellSelect: function (rowid,iCol,cellcontent,e) {
		            if (iCol >= firstButtonColumnIndex) {
		            	removeUnit(rowid);
		            }
			
				//alert("iCol "+iCol);
		            else if (iCol == editButton) {
					//alert("reInitiateButton");
					openPopup(rowid);

				}

			},
			caption: "Searched Expense Types",
			/*loadComplete:function() {
				//adjustTotalExpense();
			}*/
	});
	
	grid = $("#editExpenseTypeGrid"),
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



function updateExpenseType(){
	//alert("in update");
	
	 var rowId= $("#expenseTypeRowId").val();
		var ctx = $("#contextPath").val();
		// alert(rowId);
	 	var rowData = $("#editExpenseTypeGrid").jqGrid("getRowData", rowId);
	 	rowData.code = $('#expenseTypeCodeModal').val();
	 	rowData.description = $('#expenseTypeDescriptionModal').val();
	 	//alert($('#status').val());
	 	var statusVal = $('#statusModal').val();
	 
	 	
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
	   $.ajax({
		      type: "POST",
		      contentType : 'application/json; charset=utf-8',
		      dataType : 'json',
		      url : ctx+"/updateExpenseType",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		    	  rowData.status= statusVisible;
		    	  $("#editExpenseTypeGrid").jqGrid("setRowData", rowId, rowData);
		    	  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: result.message
		    	  });
		    	  $( "#cancelExpenseTypeButton").click();
		    	  
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
		  }); 
} 


function openPopup(rowid){
	$('#expenseTypeModal').modal('show');
	var rowData = $("#editExpenseTypeGrid").jqGrid("getRowData", rowid);
	$("#expenseTypeRowId").val(rowid);
	$('#expenseTypeCodeModal').val(rowData.code);
	$('#expenseTypeDescriptionModal').val(rowData.description);
	//alert(rowData.status);
	var status = rowData.status;
	if(status == 'Active'){
		$('[id=statusModal] option').filter(function() { 
			return ($(this).text() == 'Active'); 
		}).prop('selected', true);
	}
	else {
		$('[id=statusModal] option').filter(function() { 
			return ($(this).text() == 'Inactive'); 
		}).prop('selected', true);
	}
	
	
} 

function reloadExpenseTypeGrid(){
	$("#editExpenseTypeGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	$("#editExpenseTypeGrid").jqGrid('setCaption','');

}



function validateExpenseType(){
	var rowCount=jQuery('#expenseTypeGrid').jqGrid('getGridParam', 'reccount');
	  if(rowCount!=0){
		  return true;
	  }
	  else{
		  $(function(){
   		   new PNotify({
   			   type:'error',
   			   title: 'Error',
   		      text: 'Please add atleast one Type of Expense!'
   		   });
   		});
	  return false;
	  }
}