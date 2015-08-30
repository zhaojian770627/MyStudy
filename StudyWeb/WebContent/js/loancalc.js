function calculate() {
	var amount = document.getElementById("amount");
	var apr = document.getElementById("apr");
	var years = document.getElementById("years");
	var zipcode = document.getElementById("zipcode");
	var payment = document.getElementById("payment");
	var total = document.getElementById("total");
	var totalinterest = document.getElementById("totalinterest");

	var principal = parseFloat(amount.value);
	var interest = parseFloat(apr.value) / 100 / 12;
	var payments = parseFloat(years.value) * 12;

	var x = Math.pow(1 + interest, payments);
	var monthly = (principal * x * interest) / (x - 1);

	if (isFinite(monthly)) {
		payment.innerHTML = monthly.toFixed(2);
		total.innerHTML = (monthly * payments).toFixed(2);
		totalinterest.innerHTML = ((monthly * payments) - principal).toFixed(2);

		save(amount.value, apr.value, years.value, zipcode.value);

//		try {
//			getLenders(amount.valueOf(), apr.valueOf(), years.value,
//					zipcode.value);
//		} catch (e) {
//		}

		// 最后，用图表展示贷款余额，利息和资产收益
		chart(principal, interest, monthly, payments);
	} else {
		payment.innerHTML = "";
		total.innerHTML = "";
		totalinterest.innerHTML = "";
		chart();
	}
}

function save(amount, apr, years, zipcode) {
	if (window.localStorage) {
		localStorage.loan_amount = amount;
		localStorage.loan_apr = apr;
		localStorage.loan_years = years;
		localStorage.loan_zipcode = zipcode;
	}
}

function getLenders(amount, apr, years, zipcode) {
	if (!window.XMLHttpRequest)
		return;

	var ad = document.getElementById("lenders");
	if (!ad)
		return;

	var url = "getLenders.php" + "?amt=" + encodeURIComponent(amount) + "&apr="
			+ encodeURIComponent(apr) + "&yrs=" + encodeURIComponent(years)
			+ "&zip=" + encodeURIComponent(zipcode);

	var req = new XMLHttpRequest();
	req.open("GET", url);
	req.send(null);

	req.onreadystatechange = function() {
		if (req.readState == 4 && req.status == 200) {
			var response = req.responseText;
			var lenders = JSON.parse(response);

			var list = "";
			for ( var i = 0; i < lenders.length; i++) {
				list += "<li><a href='" + lenders[i].url + "'>"
						+ lenders[i].name + "</a>";
			}

			ad.innerHTML = "<ul>" + list + "</ul>";
		}
	}
}

function chart(principal, interest, monthly, payments) {
	var graph = document.getElementById("graph");
	graph.width = graph.width;

	if (arguments.length == 0 || !graph.getContext)
		return;

	var g = graph.getContext("2d");
	var width = graph.width;
	height = graph.height;

	function paymentToX(n) {
		return n * width / payments;
	}

	function amountToY(a) {
		return height - (a * height / (monthly * payments * 1.05));
	}

	g.moveTo(paymentToX(0), amountToY(0));
	g.lineTo(paymentToX(payments),amountToY(monthly * payments));
	g.lineTo(paymentToX(payments), amountToY(0));
	g.closePath();
	g.fillStyle = "#f88";
	g.fill();
	g.font = "bold 12px sans-serif";
	g.fillText("Total Interest Payments", 20, 20);

	var equity = 0;
	g.beginPath();
	g.moveTo(paymentToX(0), amountToY(0));
	for ( var p = 1; p <= payments; p++) {
		var thisMounthsInterest = (principal - equity) * interest;
		equity += (monthly - thisMounthsInterest);
		g.lineTo(paymentToX(p), amountToY(equity));
	}
	g.lineTo(paymentToX(payments), amountToY(0));
	g.closePath();
	g.fillStyle = "green";
	g.fill();
	g.fillText("Total Equity", 20, 35);

	var bal = principal;
	g.beginPath();
	g.moveTo(paymentToX(0), amountToY(bal));
	for ( var p = 1; p <= payments; p++) {
		var thisMonthsInterest = bal * interest;
		bal -= (monthly - thisMonthsInterest);
		g.lineTo(paymentToX(p), amountToY(bal));
	}
	g.lineWidth = 3;
	g.stroke();
	g.fillStyle = "black";
	g.fillText("Loan Balance", 20, 50);

	g.textAlign = "center";
	var y = amountToY(0);
	for ( var year = 1; year * 12 <= payments; year++) {
		var x = paymentToX(year * 12);
		g.fillRect(x - 0.5, y - 3, 1, 3);
		if (year == 1)
			g.fillText("Year", x, y - 5);
		if (year % 5 == 0 && year * 12 != payments)
			g.fillText(String(year), x, y - 5);
	}

	g.textAlign = "right";
	g.textBaseline = "middle";
	var ticks = [ monthly * payments, principal ];
	var rightEdge = paymentToX(payments);
	for ( var i = 0; i < ticks.length; i++) {
		var y = amountToY(ticks[i]);
		g.fillRect(rightEdge - 3, y - 0.5, 3, 1);
		g.fillText(String(ticks[i].toFixed(0)), rightEdge - 5, y);
	}
}

function prepare() {
	document.getElementById("amount").onchange = function() {
		calculate();
		return false;
	};
	document.getElementById("apr").onchange = function() {
		calculate();
		return false;
	};
	document.getElementById("years").onchange = function() {
		calculate();
		return false;
	};
	document.getElementById("zipcode").onchange = function() {
		calculate();
		return false;
	};
	document.getElementById("btncal").onclick = function() {
		calculate();
		return false;
	};

	if (window.localStorage && localStorage.loan_amount) {
		document.getElementById("amount").value = localStorage.loan_amount;
		document.getElementById("apr").value = localStorage.loan_apr;
		document.getElementById("years").value = localStorage.loan_years;
		document.getElementById("zipcode").value = localStorage.loan_zipcode;
	}
}

addLoadEvent(prepare);
