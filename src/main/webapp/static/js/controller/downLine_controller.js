mainApp.controller('DownLineCtrl', ['$rootScope','$scope','$location','DashboardService','commonService', function($rootScope,$scope,$location,DashboardService,commonService) {
    
	  angular.element(document).ready(function () {

		
	   DashboardService.generateTree(JSON.parse(window.sessionStorage
				.getItem('loggedInUserId')).USER.registration.id)
 	.then(
 		function(d) {
				console.log(d);
				
				 var testData = d.BINARYTREE;
	                	
	                    org_chart = $('#orgChart').orgChart({
	                        data: testData,
	                        showControls: true,
	                        allowEdit: true,
	                        onAddNode: function(node){ 
	                           
	                            org_chart.newNode(node.data.id); 
	                        },
	                        

	                    });
	               
				
			},
			
			function(errResponse){}
			);
	   
	});

	}]);