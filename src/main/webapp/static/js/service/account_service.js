'use strict';

mainApp.factory('AccountService', ['$http', '$q', function($http, $q){

	return {
		
			
		saveAccountDetailsOfUser: function(accountDetails){
     		    	//console.log(user);
					return $http.post('http://localhost:8080/kafeneio/saveAccountDetailsOfUser', accountDetails)
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
		    
		 
		getUserAccountDetails:function(user_id){
			return $http.get('http://localhost:8080/kafeneio/getAccountDetails?user_id='+user_id)
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
