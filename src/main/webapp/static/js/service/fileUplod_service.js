'use strict';

mainApp.factory('FileUplodeService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				uploadProfileImg : function(file) {

					var userId = JSON.parse(window.sessionStorage
							.getItem('loggedInUserId')).USER.registration.id;

					var fd = new FormData();
					fd.append('file', file);

					return $http.post(
							'http://localhost:8080/kafeneio/upload?userId='
									+ userId, fd, {

								transformRequest : angular.identity,
								headers : {
									'Content-Type' : undefined,
									'Process-Data' : false
								},

							}

					).then(function(response) {

						return response.data;
					}, function(errResponse) {
						console.error('Error while creating user');
						// console.log(errResponse);
						return $q.reject(errResponse);
					});
				},

			};

		} ]);
