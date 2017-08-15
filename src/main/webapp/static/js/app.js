////'use strict';
///*var mainApp = angular.module("mainApp", ['ngRoute',"ngMaterial", "ngAnimate",'ui.bootstrap']);
//
//mainApp.config(['$routeProvider', function($routeProvider) {
//   
//   $routeProvider
//   .when('/', {
//      templateUrl: 'static/pages/home.html'
//   })
//   .when('/aboutUs', {
//      templateUrl: 'static/pages/aboutUs.html'
//   })
//   
//   
//   .when('/plans', {
//      templateUrl: 'static/pages/plans.html'
//   })
//   .when('/termsAndConditions', {
//      templateUrl: 'static/pages/termsAndConditions.html'
//   })
//   .when('/whyUs', {
//      templateUrl: 'static/pages/whyUs.html'
//   })
//   .when('/fAQ', {
//      templateUrl: 'static/pages/fAQ.html'
//   })
//   .when('/contactUs', {
//      templateUrl: 'static/pages/contactUs.html'
//   })
//   .when('/loginPage', {
//      templateUrl: 'static/pages/loginPage.html',controller:'LoginCtrl'
//   })
//   
//   .when('/signUp', {
//      templateUrl: 'static/pages/signUp.html',controller:'SignUpCtrl'
//   })
//   
//   .when('/dashboard', {
//      templateUrl: 'static/pages/dashboard.html',controller:'DashBoardCtrl'
//   })
//   
//   .when('/binaryTree', {
//      templateUrl: 'static/pages/binaryTree.html',controller:'BinaryTreeCtrl'
//   })
//   .otherwise({redirectTo: '/' });
//}]);
//
//mainApp.run(function($rootScope) {
//    $rootScope.test;
//})
//*/
//
//
//
//var mainApp=angular.module('mainApp', ['ui.router','ui.bootstrap','ngImgCrop','ui.grid','ui.grid.edit', 'ui.grid.cellNav']);
//
//mainApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
//    
//$urlRouterProvider.otherwise('/home');
//    
//    $stateProvider
//        
//        // HOME STATES AND NESTED VIEWS ========================================
//        .state('home', {
//            url: '/home',
//            templateUrl: 'static/pages/home.html'
//        })
//        
//        // nested list with custom controller
//        /*.state('home.list', {
//            url: '/list',
//            templateUrl: 'partial-home-list.html',
//            controller: function($scope) {
//                $scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
//            }
//        })
//        */
//        // nested list with just some random string data
//       /* .state('home.paragraph', {
//            url: '/paragraph',
//            template: 'I could sure use a drink right now.'
//        })
//        */
//        
//        
//        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
//        
//
//        
//        .state('aboutUs', {
//            url: '/about',
//            templateUrl: 'static/pages/aboutUs.html'
//            
//        })
//    
//   .state('plans', {
//        url: '/plans',
//        templateUrl: 'static/pages/plans.html'
//        
//    })
//    
//    
//    
//  .state('termsAndConditions', {
//        url: '/termsAndConditions',
//        templateUrl: 'static/pages/termsAndConditions.html'
//        
//    })
//      .state('whyUs', {
//        url: '/whyUs',
//        templateUrl: 'static/pages/whyUs.html'
//        
//    })
//    .state('fAQ.html', {
//        url: '/fAQ.html',
//        templateUrl: 'static/pages/fAQ.html.html'
//        
//    })
//    .state('contactUs', {
//        url: '/contactUs',
//        templateUrl: 'static/pages/contactUs.html'
//        
//    })
//    
//    .state('loginPage', {
//        url: '/loginPage',
//        templateUrl: 'static/pages/loginPage.html'
//        	,controller:'LoginCtrl'
//        
//    })
//    
//     .state('signUp', {
//        url: '/signUp',
//        templateUrl: 'static/pages/signUp.html'
//        	,controller:'SignUpCtrl'
//        
//    })
//    
//    
//     .state('logout', {
//        url: '/logOut',
//        controller:'LogOutCtrl'
//        
//    })
//    
//     .state('dashboard', {
//        url: '/dashboard',
//        templateUrl: 'static/pages/dashboard.html'
//        	,controller:'DashBoardCtrl'
//        
//    }) 
//    
//    
//    .state('dashboard.profile', {
//            url: '/profile',
//            templateUrl: 'static/pages/profile.html',
//            	 controller:'ProfileCtrl',
//            	 resolve:{
//            		 resa:function(){
//            			 if(window.sessionStorage.getItem('loggedInUserId')===null){
//            					$state.go('loginPage');
//            					return
//            				}	 
//            		 }
//            	 }
//            	 
//            
//        })
//    
//        .state('dashboard.account', {
//            url: '/account',
//            templateUrl: 'static/pages/account.html',
//            controller:'AccountCtrl'
//            
//        })
//         .state('dashboard.downLine', {
//            url: '/downline',
//            templateUrl: 'static/pages/downLine.html',
//            controller:'DownLineCtrl'
//            
//            
//            
//        })
//         .state('dashboard.rewards', {
//            url: '/rewards',
//            templateUrl: 'static/pages/rewards.html',
//            
//        })
//        
//        .state('dashboard.todayTask', {
//            url: '/todayTask',
//            templateUrl: 'static/pages/todayTask.html',
//            controller:'TodayTaskCtrl'
//            
//        })
//        
//        .state('dashboard.directReferralIncome', {
//            url: '/directReferralIncome',
//            templateUrl: 'static/pages/directReferral.html',
//            controller:'DirectReferralCtrl'
//            
//        })
//        
//        .state('dashboard.binaryIncome', {
//            url: '/binaryIncome',
//            templateUrl: 'static/pages/binaryIncome.html',
//            controller:'BinaryIncomeCtrl'
//            
//        })
//        
//        
//        
//        .state('dashboard.totatPayment', {
//        url: '/totalPayment',
//        templateUrl: 'static/pages/totalpayment.html'
//        	,controller:'TotalpaymentCtrl'
//        
//    })
//    ;
//    
//    
//        
//}]);
//
//
//mainApp.controller('IndexCtrl', ['$scope','$state', function($scope,$state) {
//	
//	$scope.$on("MyEvent", function(evt,data){ 
//		  // handler code here 
//		console.log(evt);
//		console.log(data.USER.registration.name);
//		$scope.name=data.USER.registration.name;
//	
//	});
//}])
//
//
