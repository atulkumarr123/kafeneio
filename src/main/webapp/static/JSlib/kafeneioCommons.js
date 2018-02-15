
function openModeOfPaymentPopup(rowid){
	var ctx = $("#contextPath").val();
	 $("#mopOrderId").val(rowid);
	 if( !$('#modeOfPayment').val()) {
		 $.ajax({
			 url : ctx+"/order/modeOfPayments",
			 success : function(responseText) {		
				 //alert(JSON.stringify(responseText));
				 writeMOP(responseText);

			 },
			 error:function(responseText) {
				 $(function(){
					 new PNotify({ type:'error', title: 'Error', text: 'Some Error!'});
				 });
			 }	
		 });
	 }
	 $('#modeOfPaymentModal').modal('show');
}

function writeMOP(data){
	$.each(data, function (i, data) {
	    $('#modeOfPayment').append($('<option>', { 
	        value: data.id,
	        text : data.description
	    }));
	});
}


function adjustTotalOrder(gridId){
	var data = $("#"+gridId).jqGrid("getGridParam", "data");
	var amount = 0;
	for (var i in data) {
		var row = data[i];
		amount=parseInt(amount)+parseInt(row.amount);
		
	}	
	jQuery("#"+gridId).footerData('set',{orderNo:'Total', amount:amount});
	return amount;
}

function formatDate(date) {
	  var monthNames = [
	    "01", "02", "03",
	    "04", "05", "06", "07",
	    "08", "09", "10",
	    "11", "12"
	  ];

	  var day = date.getDate();
	  var monthIndex = date.getMonth();
	  var year = date.getFullYear();

	  return day + '-' + monthNames[monthIndex] + '-' + year;
	}
