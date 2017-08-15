mainApp
		.controller(
				'LoginCtrl',
				[
						'$rootScope',
						'$scope',
						'$state',
						'LoginService',
						'commonService',
						'$location',
						function($rootScope, $scope, $state, LoginService,
								commonService, $location) {
							$scope.loginDetails = {
								email : '',
								password : '',
								isRememberMe : ''
							};
							$scope.errorMessage = '';
							$scope.login = function() {

								LoginService
										.login($scope.loginDetails)

										.then(
												function(d) {

													$rootScope.test = d
													$scope.$emit("MyEvent",d);
													window.sessionStorage
															.setItem(
																	'loggedInUserId',
																	JSON
																			.stringify(d));
													commonService
															.setdataDetails(d.USER);
													if(d.USER.registration.login.role=="USER"){
													$state.go('dashboard.profile');
													}
													else{
														$state.go('dashboard.totatPayment');
													}
												},
												function(errResponse) {
													console
															.error('Error while creating User.');
													console.log(errResponse);
													$scope.errorMessage = errResponse.data.Error.message;
												});

							};
						} ]);