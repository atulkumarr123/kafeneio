mainApp.controller('TodayTaskCtrl', ['$scope','todayTaskService', function($scope,todayTaskService) {
	
	$scope.getCurrentSelection = function(rowEntity) {
		
		var workAssigment={'linkId':rowEntity.id,'status':true,'userId':JSON.parse(window.sessionStorage.getItem('loggedInUserId')).USER.registration.id};
				todayTaskService.updateStatus(workAssigment).then(
			function(d) {
				
			console.log(d);	
			rowEntity.status=true;
			},
			function(errResponce) {
				
			}
		);
		};
	
	$scope.users =[];
	$scope.gridOptions={
			enableColumnResizing: true,
			//rowClass="style='color:red'"
	};
	$scope.gridOptions.onRegisterApi=function(gridApi) { //register grid data first within the gridOptions
		   $scope.gridApi = gridApi;
		 };
	
	$scope.getStatus = function(status){
		
		if(status){
		 return "fontColorGray";
			
		}else{
			 return "fontColorBlue"
		}
		
		
	}
	$scope.gridOptions.columnDefs = [
                                       { name: 'S.N',enableCellEditOnFocus:false,width: 270 , cellTemplate: '<div class="ui-grid-cell-contents">{{grid.renderContainers.body.visibleRowCache.indexOf(row)}}</div>' },
	                                { name: 'pageTitle',enableCellEditOnFocus:false,width: 275  },
	                                { name: 'pageLink',   displayName:'Page Link', width: 400, cellTemplate:"<button class='gridLink {{grid.appScope.getStatus(row.entity.status)}}' ng-class='' ng-disabled='row.entity.status===true'   ng-click='grid.appScope.getCurrentSelection(row.entity)'>{{row.entity.pageLink}}</button>"} ,
	                                
	                                
	                              ];
	
	
	
	
	
	todayTaskService.getTodayTask(JSON.parse(window.sessionStorage
				.getItem('loggedInUserId')).USER.registration.id)
				.then(
						function(d) {
							console.log(d);
							
							//$scope.users=d.TODAY_TASK;
							$scope.gridOptions.data=d.TODAY_TASK;
				              
							
						},
						
						function(errResponse){}
						);
	
}]);
