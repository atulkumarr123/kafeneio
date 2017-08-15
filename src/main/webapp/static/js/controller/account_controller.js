mainApp.controller('AccountCtrl', ['$scope','AccountService', function($scope,AccountService) {
	$scope.account={userId:'',accountHolderName:'',bankName:'',bankBranch:'',accountNumber:'',ifscCode:''};
	$scope.submitAccountDetatils=function(){
		$scope.account.userId=JSON.parse(window.sessionStorage.getItem('loggedInUserId')).USER.registration.id;
		console.log($scope.account);
		AccountService.saveAccountDetailsOfUser($scope.account).then(
		function(d)	{
			
			console.log('success ');
		},
		function(errResponse){
			console.log('error');
		}
		)
	}
	
	 angular.element(document).ready(function () {

			
		 AccountService.getUserAccountDetails(JSON.parse(window.sessionStorage
					.getItem('loggedInUserId')).USER.registration.id)
	 	.then(
	 		function(d) {
					console.log(d);
					
					
					 $scope.account=d.ACCOUNT_DETAILS;

		               
					
				},
				
				function(errResponse){}
				);
		   
		});

}])