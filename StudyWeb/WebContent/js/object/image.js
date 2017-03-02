function Point(x, y) {
	this.x = x;
	this.y = y;
}

function Line(p1, p2) {
	this.p1 = p1;
	this.p2 = p2;
	this.length = Math
			.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
}

function Shape() {
	this.points = [];
	this.lines = [];
	this.init();
}

Shape.prototype = {
	// reset pointer to constructor
	constructor : Shape,
	// initialization,sets this.context to point
	// to the context of the canvas object
	init : function() {
		if (typeof this.context === 'undefined') {
			var canvas = document.getElementById('canvas');
			Shape.prototype.context = canvas.getContext('2d');
		}
	},
	// method that draws a shape by looping through this.points
	draw : function() {
		var ctx = this.context;
		ctx.strokeStyle = this.getColor();
		ctx.beginPath();
		ctx.moveTo(this.points[0].x, this.points[0].y);
		for (var i = 1; i < this.points.length; i++) {
			ctx.lineTo(this.points[i].x, this.points[i].y);
		}
		ctx.closePath();
		ctx.stroke();
	},

	// method that generates a random color
	getColor : function() {
		var rgb = [];
		for (var i = 0; i < 3; i++) {
			rgb[i] = Math.round(255 * Math.random);
		}
		return 'rgb(' + rgb.join(',') + ')';
	},
	// method that loop through the points array,
	// creates Line instances and adds them to this.lines
	getLines : function() {
		if (this.lines.length > 0) {
			return this.lines;
		}
		var lines = [];
		for (var i = 0; i < this.points.length; i++) {
			lines[i] = new Line(this.points[i],
					(this.points[i + 1]) ? this.points[i + 1] : this.points[0]);
		}
		this.lines = lines;
		return lines;
	},

	// shell method,to be implemented by children
	getArea : function() {
	},

	// sums the lengths of all lines
	getPerimeter : function() {
		var lines = this.getLines();
		var perim = 0;
		for (var i = 0; i < lines.length; i++) {
			perim += lines[i].length;
		}
		return perim;
	}
}

function Triangle(a, bc) {
	this.points = [ a, b, c ];
	this.getArea = function() {
		var p = this.getPerimeter();
		var s = p / 2;
		return Math.sqrt(s * (s - this.lines[0].length)
				* (s - this.lines[1].length) * (s - this.lines[2].length));
	};
}

function Rectangle(p, side_a, side_b) {
	this.points = [ p, new Point(p.x + side_a, p.y),// top right
	new Point(p.x + side_a, p.y + side_b), // bottom right
	new Point(p.x, p.y + side_b) // bottom left
	];
	this.getArea = function() {
		return side_a * side_b;
	};
}

function Square(p, side) {
	Rectangle.call(this, p, side, side);
}

(function() {
	var s = new Shape();
	Triangle.prototype = s;
	Rectangle.prototype = s;
	Square.prototype = s;
})();