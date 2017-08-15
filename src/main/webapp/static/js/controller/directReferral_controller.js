mainApp.controller('DirectReferralCtrl', ['$scope','MyIncomeService', function($scope,MyIncomeService) {
	
	

	$scope.binaryIncomeDetails;
	MyIncomeService.myIncomeService(JSON.parse(window.sessionStorage.getItem('loggedInUserId')).USER.registration.id,
			false,'DI')
			.then(
					function(d) {
						console.log(d);
						
						
						$scope.binaryIncomeDetails=d.INCOME_DETAILS;
			              
						
					},
					
					function(errResponse){}
					);
	console.log($scope.binaryIncomeDetails);
	
}])