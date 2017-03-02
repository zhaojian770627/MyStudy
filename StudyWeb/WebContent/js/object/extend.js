function extend2(Child, Parent) {
	var p = Parent.prototype;
	var c = Child.prototype;

	for ( var i in p) {
		c[i] = p[i];
	}
	c.uber = p;
}

function extendCopy(p) {
	var c = {};
	for ( var i in p) {
		c[i] = p[i];
	}
	c.uber = p;
	return c;
}

function deepCopy(p, c) {
	var c = c || {};
	for ( var i in p) {
		if (typeof p[i] === 'object') {
			c[i] = (p[i].constructor === Array) ? [] : {};
			deepCopy(p[i], c[i]);
		} else {
			c[i] = p[i]
		}
	}
	return c;
}

function object(o) {
	var n;
	function F() {
	}
	F.prototype = o;
	n = new F();
	n.uber = o;
	return n;
}