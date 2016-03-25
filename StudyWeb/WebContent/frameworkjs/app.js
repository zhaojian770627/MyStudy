requirejs.config({
	// By default load any module IDs from js/lib
	baseUrl : 'frameworkjs/lib',
	// except, if the module ID starts with "app",
	// load it from the js/app directory. paths
	// config is relative to the baseUrl, and
	// never includes a ".js" extension since
	// the paths config could be for a directory.
	paths : {
		"jquery" : "jquery-1.11.1",
		"ko":"knockout-3.4.0",
		"data":"data",
		app : '../app'
	}
});

requirejs([ 'jquery',"data","ko" ], function($,data,ko) {

	$(document).ready(function() {
		alert(data.data);
		alert(availableMeals);
		var d=data.data;
		alert(d);
		
		$('#sel').attr("data-bind","options: availableMeals, optionsText: 'mealName'");
		
		var viewModel = {
				/* we'll populate this in a moment */
				};
		ko.applyBindings(viewModel);
	});
	// alert(math.add(3, 4));
});