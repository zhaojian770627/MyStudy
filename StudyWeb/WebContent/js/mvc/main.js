require([ 'sth', 'check', 'jquery' ], function(sth, check, $) {
	if (check.ok) {
		sth.f1();
		$(document).ready(function() {
			$("#p1").click(function() {
				$(this).hide();
			});
		});
	} else {
		sth.f2()
	}
})