

$( document ).ready(function() {
    $("#expensesGrid").jqGrid({
        colModel: [
        	{ name: "item", label: "Item",  align: "center"},
            { name: "amount", label: "Amount",  align: "center" },
            { name: "date", label: "Date",  align: "center" },
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


$( document ).ready(function() {
	$('#datetimepicker3').datetimepicker({
		format: 'DD-MM-YYYY'
	});
	var currentDate = $("#currentDate").val();
	
	$('#expenseDateTime').val(currentDate);
	//alert(currentDate);
//	$( ".selector" ).datepicker( "setDate", new Date());
//	$('#datetimepicker3').val(currentDate);
	/*  $("#setMinDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setMinDate(new Date("june 12, 2013"));
    });                                
    $("#setMaxDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setMaxDate(new Date("july 4, 2013"));
    });
    $("#show").click(function () {
        $('#datetimepicker3').data("DateTimePicker").show();
    });
    $("#disable").click(function () {
        $('#datetimepicker3').data("DateTimePicker").disable();
    });
    $("#enable").click(function () {
        $('#datetimepicker3').data("DateTimePicker").enable();
    });
    $("#setDate").click(function () {
        $('#datetimepicker3').data("DateTimePicker").setDate("10/23/2013");
    });
    $("#getDate").click(function () {
        alert($('#datetimepicker3').data("DateTimePicker").getDate());
    });*/

	$('#addExpensebutton').click(function() {
		var isFormFilled = $("#expenses").valid();
		var valid = validateForm();
		if(isFormFilled && valid){
			addExpense();
		}
	});



});

function validateForm(){
	var amount = $("#amount").val();
	var valid = true;
	if(!(/^\d{0,9}(\.\d{0,4})?$/.test(amount))){
		$("#amount").after('<label id="amount-error" class="error" for="amount">This field is required.</label>');
		$("#amount-error").text("Enter only digits/decimals");
		valid = false;
	}
	return valid
}

function addExpense(){
	var item = $("#itemDesc").val();
	//alert(item);
	var amount = $("#amount").val();
	var remarks = $("#remarks").val();
	var date = $("#datetimepicker3").find("input").val();
	$("#expensesGrid").jqGrid("addRowData",33 , { item : item, amount : amount ,date:date, remarks:remarks}, "last");
}

function saveExpenses() {
	var ctx = $("#contextPath").val();
	var allData = $("#expensesGrid").jqGrid("getGridParam", "data");
	//alert(allData);
	if(jQuery.isEmptyObject(allData)){
		  new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: 'Please add atleast one expense!'
		   });
		  return false;
	}
	//alert(JSON.stringify(allData));
	/*var expense={};
	expense["item"]=null;
	expense["amount"]=null;
	expense["remarks"]=null;*/
	   $.ajax({
	      type: "POST",
	      contentType : 'application/json; charset=utf-8',
	      dataType : 'json',
	      url : ctx+"/expenses",
	      data: JSON.stringify(allData),
	      success :function(result) {
	    	  //alert(JSON.stringify(result));
	    	  new PNotify({
    			  type:'success',
    			  title: 'Success',
    			  text: result.message
	    	  });
	    	  $("#expensesGrid").jqGrid("clearGridData", true).trigger("reloadGrid");
	     },
	   error:function(result) {
		  /* alert(JSON.stringify(result.responseJSON.message));*/
		   new PNotify({
	    		 type:'error',
	    		 title: 'Error',
	    		 text: result.responseJSON.message
		   });
		   //alert("error"+JSON.stringify(responseText));
	   }
	  });
}

function removeItem(rowid){
	$('#expensesGrid').jqGrid('delRowData',rowid);
}

function clearExpenses(){
	  $("#expensesGrid").jqGrid("clearGridData", true).trigger("reloadGrid");

}