function NonNullSet(){
	Set.apply(this,arguments);
}

NonNullSet.prototype=inherit(Set.prototype);
NonNullSet.prototype.constructor=NonNullSet;

NonNullSet.prototype.add=function(){
	for(var i=0;i<arguments.length;i++)
		if(arguments[i]==null)
			throw new Error("Can't add null or undefined to a NonNullSet");
	
	return Set.prototype.add.apply(this,arguments);
}
