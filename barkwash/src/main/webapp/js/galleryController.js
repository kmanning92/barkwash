(function() {
	var groomingApp = angular.module('groomingApp');
	
	groomingApp.controller('galleryController', function($scope, $http) {	
		
		// https://www.js-tutorials.com/angularjs-tutorial/html5-localstorage-sessionstorage-using-angularjs/
		
		
		$scope.itemsForSale = [{image:'gallery1.jpg'},
			{image:'gallery6.png',title:'Rocky'},
			{image:'gallery7.jpg',title:'Chuck'},
			{image:'gallery8.jpg',title:'Butch and Buddy'},
			{image:'gallery9.jpg',title:'Sable'},
			{image:'gallery10.jpg',title:'Raiden'},
			{image:'gallery12.jpg',title:'Waffle'},
			{image:'gallery13.jpg',title:'King'},
			{image:'gallery14.jpg',title:'Agro'},
			{image:'gallery15.jpg',title:'Foxtrot'},
			{image:'gallery16.jpg',title:'Nitra'},
			{image:'gallery17.jpg',title:'Duke'},
			{image:'gallery18.jpg',title:'Benny'},
			{image:'gallery19.jpg',title:'Cane'},
			{image:'gallery20.jpg',title:'Waffle'}];
			
		$scope.addToCart = function(item) {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = JSON.parse(localStorage.getItem('cart'));
				$scope.cart.push(item);
				localStorage.setItem('cart', JSON.stringify($scope.cart));
			} else {
				// new cart
				$scope.cart = [item];
				localStorage.setItem('cart', JSON.stringify($scope.cart));
			}			
		}
		
		$scope.getCart = function() {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = JSON.parse(localStorage.getItem('cart'));				
			} else {				
				$scope.cart = [];				
			}			
		}
		
		$scope.getCart();
		$scope.viewCart = false;
		
		$scope.viewMyCart = function() {			
			$scope.viewCart = true;
		}
		
		$scope.hideMyCart = function() {			
			$scope.viewCart = false;
		}
		
		$scope.emptyMyCart = function() {
			if (localStorage.getItem('cart') != null) {
				$scope.cart = [];
				localStorage.setItem('cart', JSON.stringify($scope.cart));			
			}			
		}
			
	});

})()