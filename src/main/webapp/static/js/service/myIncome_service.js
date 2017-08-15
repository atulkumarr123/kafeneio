'use strict';

mainApp.factory('MyIncomeService', ['$http', '$q', function($http, $q){

	return {
		
			
		    myIncomeService: function(Id,status,incomeType){
		    	
					return $http.get('http://localhost:8080/kafeneio/getBinaryIncome?Id='+Id+'&status='+status+'&incomeType='+incomeType)
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
