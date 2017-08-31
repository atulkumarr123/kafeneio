$( document ).ready(function() {
    $("#expensesGrid").jqGrid({
        colModel: [
        	{ name: "item", label: "Item",  align: "center"},
            { name: "amount", label: "Amount",  align: "center" },
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
        idPrefix: "gb1_",
        rownumbers: false,
        sortname: "invdate",
        sortorder: "desc",
        caption: "Add Expenses",       
        height: 'auto',
        onCellSelect: function (rowid,iCol,cellcontent,e) {
            if (iCol >= firstButtonColumnIndex) {
                removeItem(rowid);
            }
        }
    });
    grid = $("#expensesGrid"),
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


function addExpense(){
	var item = $("#itemDesc").val();
	//alert(item);
	var amount = $("#amount").val();
	var remarks = $("#remarks").val();
	 $("#expensesGrid").jqGrid("addRowData",null , { item : item, amount : amount , remarks:remarks}, "last");
	}


function saveExpenses() {
	var ctx = $("#contextPath").val();
	var allData = $("#expensesGrid").jqGrid("getGridParam", "data");
	//alert(JSON.stringify(allData));
	/*var expense={};
	expense["item"]=null;
	expense["amount"]=null;
	expense["remarks"]=null;*/
	var order={};
	order["orderNo"]=123;
	order["amount"]=null;
	order["creation_date"]=null;
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/expenses",
	      data: JSON.stringify(order),
	      success :function(result) {
	    	  alert("Expenses Saved Successfully");
	    	  //validateOrder();
	    	  //alert(JSON.stringify(result));
	     },
	   error:function(responseText) {
				alert("error"+JSON.stringify(responseText));
				$('#outputLabel').text("Error");
	   }
	  });
	 
}

