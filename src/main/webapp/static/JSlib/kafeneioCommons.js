
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

