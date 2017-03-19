require([ 'sth', 'check' ], function(sth, check) {
	if (check.ok) {
		sth.f1()
	} else {
		sth.f2()
	}
})