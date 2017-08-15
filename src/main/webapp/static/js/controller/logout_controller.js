mainApp.controller('LogOutCtrl', ['$scope','logoutService', function($scope,logoutService) {
	
	logoutService.logOutUser();
	
	/*$scope.binaryIncomeDetails;
	MyIncomeService.myIncomeService(JSON.parse(window.sessionStorage.getItem('loggedInUserId')).USER.registration.id,
			false,'BI')
			.then(
					function(d) {
						console.log(d);
						
						//$scope.users=d.TODAY_TASK;
						$scope.binaryIncomeDetails=d.INCOME_DETAILS;
			              
						
					},
					
					function(errResponse){}
					);
	console.log($scope.binaryIncomeDetails);*/
}])