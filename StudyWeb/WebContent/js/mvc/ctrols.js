(function($, exports) {
	var mod = function(includes) {
		if (includes)
			this.include(includes);
	};

	mod.fn = mod.prototype;

	mod.fn.proxy = function(func) {
		return $.proxy(func, this);
	};

	mod.fn.load = function(func) {
		$(this.proxy(func));
	};

	mod.fn.include = function(obj) {
		$.extend(this, obj);
	};

	exports.Controller = mod;

})(jQuery, window);