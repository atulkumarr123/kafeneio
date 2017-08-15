'use strict';

mainApp.factory('logoutService', ['$http', '$q','$state', function($http, $q,$state){

	return {
		
		logOutUser: function(accountDetails){
			console.log("In Log out services ");
			state.go("loginPage");
     		    	//console.log(user);
					/*return $http.post('http://localhost:8080/kafeneio/saveAccountDetailsOfUser', accountDetails)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);*/
		    },
		    
		
		
	};
	
	

}]);
