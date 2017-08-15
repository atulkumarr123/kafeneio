'use strict';

mainApp.factory('TotalPaymentService', ['$http', '$q', function($http, $q){

	return {
		
			
		    getUserDetailsForPayment: function(){
     		    	//console.log(user);
					return $http.get('http://localhost:8080/kafeneio/getUserDetailsForTotalPayment')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		 
		
	};
	
	

}]);
