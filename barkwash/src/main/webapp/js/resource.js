		var groomingApp = angular.module('groomingApp', [ 'ngRoute' ]);

		groomingApp.config(function($routeProvider) {
			$routeProvider.when("/services", {
				templateUrl : "services.html",
				controller: "serviceController"
				
			}).when("/resume", {
				templateUrl : "resume.html"

				}).when("/gallery", {
				templateUrl : "gallery.html",
			   
				
				}).when("/contact", {
				templateUrl : "contact.html"
				
				}).when("/about", {
					templateUrl : "about.html"	
					
				}).when("/thanks",{
				templateUrl: "thanks.html"
				
				}).when("/diy", {
					templateUrl : "diy.html"
				
					
			    })
				.otherwise({
				templateUrl : "main.html"
	});
});
