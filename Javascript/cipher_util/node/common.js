// Creates a one shot constructor to return an instance of a given object's prototype
exports clonePrototype = function(obj) {
	TmpObj.prototype = obj;
	function TmpObj() {}
	
	return new TmpObj();
}