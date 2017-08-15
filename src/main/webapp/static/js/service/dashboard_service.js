'use strict';

mainApp.factory('DashboardService', ['$http', '$q', function($http, $q){

	return {
		
			
		    generateTree: function(parentId){
     		    	//console.log(user);
					return $http.get('http://localhost:8080/kafeneio/generateTree?parentId='+parentId)
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
