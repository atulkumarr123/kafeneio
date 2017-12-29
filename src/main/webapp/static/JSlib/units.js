

$( document ).ready(function() {
    $("#unitsGrid").jqGrid({
        colModel: [
        	{ name: "id", label: "id",hidden:true},
        	{ name: "code", label: "Units Code",  align: "center"},
            { name: "description", label: "Units Description",  align: "center" },
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
        caption: "Add Unit",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#unitsGrid"),
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
	$('#addUnitsbutton').click(function() {
		var isFormFilled = $("#unitForm").valid();
		if(isFormFilled){	
		addUnits();


		}
	});
});


function addUnits(){
	
	$('#gbox_unitsGrid').show();
	$('#gbox_editUnitsGrid').hide();
	
	var id = $("#unitsGrid").jqGrid("getGridParam", "data").length;
	var unitCode = $("#unitCode").val();
	var unitDesc = $("#unitDesc").val();
	var status = $("#status").val();
	if(status == '1'){
		status = true;
		statusVisible = 'Active';
	}
	else{
		status = false;
		statusVisible = 'Inactive';
	}
	$("#unitsGrid").jqGrid("addRowData",id , { code : unitCode, description : unitDesc, statusVisible : statusVisible, status : status}, "last");
	$('#saveUnitButton').show();
}

function saveUnits() {

	if(!validateUnit()){
		return false;
	}

	else
	{
		var ctx = $("#contextPath").val();
		var allData = $("#unitsGrid").jqGrid("getGridParam", "data");
		$.ajax({
			type: "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			url : ctx+"/addUnits",
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
	$('#unitsGrid').jqGrid('delRowData',rowid);
}

function reloadGrid(){
	$("#unitsGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
}



$( document ).ready(function() {
	$('#searchUnitsbutton').click(function() {
	$('#gbox_unitsGrid').hide();
	$('#gbox_editUnitsGrid').show();
	searchAndEdit();
	});
   
   $('#gbox_unitsGrid').hide();
	$('#gbox_editUnitsGrid').hide();
});

function searchAndEditUnits(){
	$('#saveUnitButton').hide();
	$("#editUnitsGrid").jqGrid('GridUnload');	
		
	var ctx = $("#contextPath").val();
	$.ajax({
		url : ctx+"/searchUnits",
		success : function(responseText) {
			//alert(JSON.stringify(responseText));
			editUnits(responseText);
			//$('#outputLabel').text(JSON.stringify(responseText));
		},
		error:function(responseText) {
		//	alert("error"+responseText);
			$('#outputLabel').text("Error");
		}	
	});
	
}

function editUnits(data){
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
		$("#editUnitsGrid").jqGrid("addRowData",row.id , {id: row.id, code:row.code, description:row.description , status : row.status}, "last");
	}
	
}
function searchAndEdit(units){
	$("#editUnitsGrid").jqGrid({
		//url :  $("#contextPath").val()+"/report/expenseList?fromDate="+fromDate+"&&toDate="+toDate,
		//datatype : "json",
		datatype : "local",
		//mtype : 'POST',
		colModel: [
			{ name: "id", label: "id",hidden:false},
        	{ name: "code", label: "Units Code",  align: "center"},
            { name: "description", label: "Units Description",  align: "center" },
            { name: "status", label: "Status",  align: "center" },
          //  { name: "statusVisible", label: "Status",  align: "center" },
			{ name: 'editButton', label:"Edit", width: 80, sortable: false, search: false, align: "center",
				formatter:function(){
					return "<button class='btn btn-default' style='color:green;font-size: small;background: tan;' type='button' ><b>Edit</b></button>"
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
			//sortname: 'orderNo',
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
			caption: "Searched Units",
			/*loadComplete:function() {
				//adjustTotalExpense();
			}*/
	});
	
	grid = $("#editUnitsGrid"),
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



function updateUnit(){
	//alert("in update");
	
	 var rowId= $("#unitRowId").val();
		var ctx = $("#contextPath").val();
		// alert(rowId);
	 	var rowData = $("#editUnitsGrid").jqGrid("getRowData", rowId);
	 	rowData.code = $('#code').val();
	 	rowData.description = $('#description').val();
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
		      url : ctx+"/updateUnit",
		      data: JSON.stringify(rowData),
		      success :function(result) {
		    	  rowData.status= statusVisible;
		    	  $("#editUnitsGrid").jqGrid("setRowData", rowId, rowData);
		    	  new PNotify({
	    			  type:'success',
	    			  title: 'Success',
	    			  text: result.message
		    	  });
		    	  $( "#cancelUnitButton").click();
		    	  
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
	$('#unitsModal').modal('show');
	var rowData = $("#editUnitsGrid").jqGrid("getRowData", rowid);
	$("#unitRowId").val(rowid);
	$('#code').val(rowData.code);
	$('#description').val(rowData.description);
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

function reloadUnitGrid(){
	$("#editUnitsGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	$("#editUnitsGrid").jqGrid('setCaption','');

}



function validateUnit(){
	var rowCount=jQuery('#unitsGrid').jqGrid('getGridParam', 'reccount');
	  if(rowCount!=0){
		  return true;
	  }
	  else{
		  $(function(){
   		   new PNotify({
   			   type:'error',
   			   title: 'Error',
   		      text: 'Please add atleast one Unit!'
   		   });
   		});
	  return false;
	  }
}