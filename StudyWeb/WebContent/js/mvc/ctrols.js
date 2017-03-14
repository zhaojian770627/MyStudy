// 使用全局对象作为上下文，而不是window对象
// 用来创建全局对象
var exports = this;

(function($) {
	var mod = {};

	mod.create = function(includes) {
		var result = function() {
			this.init.apply(this, arguments);
		};

		result.fn = result.prototype;
		result.fn.init = function() {
		};

		result.proxy = function(func) {
			return $.proxy(func, this);
		};
		result.fn.proxy = result.proxy;

		result.include = function(obj) {
			$.extend(this.fn, obj);
		};

		result.extend = function(obj) {
			$.extend(this, obj);
		};

		if (includes)
			result.include(includes);

		return result;
	};

	exports.Controller = mod;
})(jQuery);