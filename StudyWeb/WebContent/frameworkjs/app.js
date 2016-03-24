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
		app : '../app'
	}
});

requirejs([ 'jquery', 'math' ], function($, math) {
	// jQuery, canvas and the app/sub module are all
	// loaded and can be used here now.
	alert(math.add(3, 4));

});