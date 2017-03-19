define(function(require){
	var p1=require('part/part1');
	var p2=require('part/part2');
	return{
		f1:p1.doSome,
		f2:p2.doOther
	}
})