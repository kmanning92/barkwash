/**
 * Access the previously created module groomingApp in resource.js
 * and create a controller associated with our module
 */

let myGroomingApp = angular.module('groomingApp');

myGroomingApp.controller('resourceController', function($scope){
	$scope.appName = "BarkWash Dog Grooming";
	
});