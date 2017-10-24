

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
			addUnits();
	});
});


function addUnits(){
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
}

function saveUnits() {
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

function removeItem(rowid){
	$('#unitsGrid').jqGrid('delRowData',rowid);
}

function reloadGrid(){
	$("#unitsGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
}
