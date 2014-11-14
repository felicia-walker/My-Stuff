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

// Assumed to be string of single char tokens
Message.prototype = {
	options: new MessageOptions(),
	freqAnalysis: function() {
		if (!this.options.equals(this.lastOptions)) {
			this.lastOptions = this.options;
			delete this.freqAnalysisMap;
		}
		if (this.freqAnalysisMap === undefined) {
			this.freqAnalysisMap = new FreqAnalysis(this.text, this.options);
		}
		
		return this.freqAnalysisMap;
	},
	length: function() {
		return (this.text.length || 0);
	}
}

function Message(text) {
	this.text = text;
}

NumberMessage.prototype = clonePrototype(Message.prototype);

function NumberMessage(text) {
	this.text = text;
	this.options.includeAlpha = false;
	this.options.includeDigits = true;
}