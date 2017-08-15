'use strict';

mainApp.factory('SignUpService', ['$http', '$q', function($http, $q){

	return {
		
			
		    createUser: function(user){
     		    	console.log(user);
					return $http.post('http://localhost:8080/kafeneio/signUp/', user)
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
		    
		   getSponserName:function(sponserId){
			   console.log("SponsorID");
			   console.log(sponserId)
	return $http.get('http://localhost:8080/kafeneio/sponsorId/?sponsorId='+sponserId)
	.then(
			function(response){
				return response.data;
			},
			function(errResponse){
				return $q.reject(errResponse);
			}
	);
		   },
		
	};
	
	

}]);
