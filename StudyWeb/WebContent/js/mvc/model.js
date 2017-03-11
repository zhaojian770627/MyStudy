if (typeof Object.create !== "function")
	Object.create = function(o) {
		function F() {
		}
		F.prototype = o;
		return new F();
	};

Math.guid = function() {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
		return v.toString(16);
	}).toUpperCase();
}

var Model = {
	inherited : function() {
	},

	created : function() {

	},

	prototype : {
		init : function() {
		}
	},

	create : function() {
		var object = Object.create(this);
		object.parent = this;
		object.prototype = object.fn = Object.create(this.prototype);

		object.created();
		this.inherited(object);
		return object;
	},

	init : function() {
		var instance = Object.create(this.prototype);
		instance.parent = this;
		instance.init.apply(instance, arguments);
		return instance;
	},

	extend : function(o) {
		var extended = o.extended;
		jQuery.extend(this, o);
		if (extended)
			extended(this);
	},

	include : function(o) {
		var included = o.included;
		jQuery.extend(this.prototype, o);
		if (included)
			included(this);
	}
};

// -------------------------------------------------------
Model.records = {};

Model.include({
	newRecord : true,

	create : function() {
		if (!this.id)
			this.id = Math.guid();

		this.newRecord = false;
		this.parent.records[this.id] = this;
	},

	destroy : function() {
		delete this.parent.records[this.id];
	},

	update : function() {
		this.parent.records[this.id] = this;
	},

	save : function() {
		this.newRecord ? this.create() : this.update();
	},

	attributes : function() {
		var result = {};
		for ( var i in this.parent.attributes) {
			var attr = this.parent.attributes[i];
			result[attr] = this[attr];
		}
		result.id = this.id;
		return result;
	},

	toJSON : function() {
		return (this.attributes());
	},

	createRemote : function(url, callback) {
		$.post(url, this.attributes(), callback);
	},

	updateRemote : function(url, callback) {
		$.ajax({
			url : url,
			data : this.attributes(),
			success : callback,
			type : "PUT"
		});
	}
});

Model.extend({
	created : function() {
		this.records = {};
		this.attributes = [];
	},

	find : function(id) {
		return this.records[id] || "Unknown record";
	},

	populate : function(values) {
		this.records = {};
		for (var i = 0, il = values.length; i < il; i++) {
			var record = this.init(values[i]);
			record.newRecord = false;
			this.records[record.id] = record;
		}
	}
});

Model.LocalStorage = {
	saveLocal : function(name) {
		var result = [];
		for ( var i in this.records)
			result.push(this.records[i]);
		localStorage[name] = JSON.stringify(result);
	},
	loadLocal : function(name) {
		var result = JSON.parse(localStorage[name]);
		this.populate(result);
	}
};
