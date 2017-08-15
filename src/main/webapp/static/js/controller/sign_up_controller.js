'use strict';


mainApp.controller('SignUpCtrl', ['$rootScope','$scope','SignUpService','$location',  function($rootScope,$scope,SignUpService,$location) {
    
   
  
	$scope.errorMessage=''
	$scope.message=''

	$scope.user={parentId:0,position:'',sponsorId:'',sponserName:'',registration:{name:'',login:{email:'',password:''}, planType:'STP-10',termsAndCondition:true,}}  
	$scope.submit = function() {
		
		SignUpService.createUser($scope.user)
		
        .then(
        		
        		function(d) {
        			$scope.errorMessage='';
        			$scope.message=''
        			$rootScope.test=d
        			$scope.message='You are successfully register in kafeneio'
        		  //$location.path('dashboard')
        			
        		},
	              function(errResponse){
        			$scope.errorMessage='';
        			$scope.message='';
		              // console.error('Error while creating User.');
		               $scope.errorMessage=errResponse.data.Error.message;
	              }
    );
		console.log($scope.user);
    };
    
  
    $scope.getSponserName=function(){
    
    	SignUpService. getSponserName($scope.user.sponsorId)
    	.then(function(d)
    			
    	{
    		$scope.errorMessage='';
    		$scope.user.sponsorName=d.SPONSOR.name;
    		$scope.user.parentId=d.SPONSOR.id
    	},
    	function(errResponse){
    	
    		$scope.errorMessage=errResponse.data.Error.message;
    	}
    	);
    };
    

}]);