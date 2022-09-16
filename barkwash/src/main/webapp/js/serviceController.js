/**
 * Access the previously created module "movieapp"
 * and create a controller associated with our module.
 */

/**
 * 
 */
(function() {
	var groomingApp = angular.module("groomingApp");
	 groomingApp.controller("serviceController", function($scope, $http) {

		

		$scope.getResources = function() {

			

			$http.get("/barkwash/webapi/resources").then(function(response) {
				$scope.resources = response.data;
				console.log('number of resources: ' + $scope.resources.length);
			}, function(response) {
				console.log('error http GET resources: ' + response.status);

			});
			$scope.orderByColumn = function(column) {
				if($scope.reverse == true) {
				   $scope.reverse = false;
				   
				} else {
					$scope.reverse = true;
				}
			}
		}
		$scope.getResources();
		$scope.orderByColumn();
	})
})()