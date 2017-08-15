'use strict';

mainApp.factory('LoginService', ['$http', '$q', function($http, $q){

	return {
		
			
		    login: function(loginDetails){
		    	
		    	
					return $http.post('http://localhost:8080/kafeneio/login/', loginDetails)
							.then(
									function(response){
										
										 window.localStorage.setItem('Authorization',response.headers('Authorization'))
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										//console.log(errResponse);
										return $q.reject(errResponse);
									}
							);
		    },
		    
		   
		
	};

}]);
