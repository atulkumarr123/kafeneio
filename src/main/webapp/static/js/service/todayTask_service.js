'use strict';

mainApp.factory('todayTaskService', ['$http', '$q', function($http, $q){

	return {
		  
		getTodayTask: function(userId){
		   	console.log(userId);
			return $http.get('http://localhost:8080/kafeneio/todayTask?userId='+userId)
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
    updateStatus: function(workAssignment){
    	
	return $http.put('http://localhost:8080/kafeneio/updateStatus',workAssignment)
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
