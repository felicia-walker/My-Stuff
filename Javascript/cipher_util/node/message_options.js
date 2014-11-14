MessageOptions.prototype = {
	caseSensitive: false,
	includeAlpha: true,
	includeDigits: false,
	includeNonAlphaNum: false,
	symbolSize: 1,
	dividerChar: undefined,
	equals: function(target) {
		if (target === null || target === undefined) {
			return false;
		}

		var isEqual = true;
		for (var key in this) {
			var thisHasProperty = Object.prototype.hasOwnProperty.call(this, key);
			var targetHasProperty = Object.prototype.hasOwnProperty.call(target, key);
			if (thisHasProperty && targetHasProperty) {
				isEqual = (this[key] === target[key]) ? isEqual : false;
			} else {
				isEqual = false;
			}
		}
		
		return isEqual;
	}
}

function MessageOptions() {};
module.exports = MessageOptions;