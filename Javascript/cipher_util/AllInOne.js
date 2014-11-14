// Creates a one shot constructor to return an instance of a given object's prototype
function clonePrototype(obj) {
	TmpObj.prototype = obj;
	function TmpObj() {}
	
	return new TmpObj();
}

// --------- Messages
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

// ------------ Frequency Analysis
FreqAnalysis.prototype = {
	options: new MessageOptions(),
}

function FreqAnalysis(text, options) {
	// Use an object rather than an array to make searching for a symbol easier, but we need
	// to manually keep track of the length
	var rawMap = {};
	var rawMapLength = 0;
	
	// Can supply a text object or a string
	if (text instanceof Message) {
		text = text.text;
	}
	
	// Remove the divider character
	if (options.dividerChar !== undefined) {
		text = text.replace(options.dividerChar, '');
	}

	for (var i = 0; i < text.length; i = i + options.symbolSize) {
		var curSymbol = text.slice(i, i + options.symbolSize);
		if (options.caseSensitive !== true) {
			curSymbol = curSymbol.toLowerCase();
		}

		var isAlpha = /[a-zA-Z]+/.test(curSymbol);
		var isDigit = /[0-9]+/.test(curSymbol);
		var alphaCheck = (isAlpha && options.includeAlpha);
		var digitCheck = (isDigit && options.includeDigits);
		var otherCheck = ((!isAlpha && !isDigit) && options.includeNonAlphaNum);
				if (!(alphaCheck || digitCheck || otherCheck)) {
			continue;
		}
		
		if (curSymbol in rawMap) {
			rawMap[curSymbol]++;
		} else {
			rawMap[curSymbol] = 1;
		}
		
		rawMapLength++;
	}

	// Set things up for sorting and add percentages
	var rawData = [];
	for (var key in rawMap) {
		rawData.push([key, rawMap[key], ((rawMap[key] / rawMapLength) * 100).toFixed(2)]);
	}

	this.sortedByCount = rawData.sort(function(a, b) {return b[1] - a[1]});
	this.sortedBySymbol = rawData.sort(function(a, b) {return b[0].localeCompare(a[0])});
	this.numberOfSymbols = rawMapLength;
}

CeaserShift.prototype = {
	key: undefined,
	encrypt: function(text) {
		return ceaserShift(text, this.key)
	},
	decrypt: function(text) {
		return ceaserShift(text, this.key * -1)
	}
}

function CeaserShift(key) {
	this.key = key;
}

function ceaserShift(text, key) {
	if (key !== Math.floor(key)) {
		console.log("Key must be an integer!");
		return;
	}
	
	// Can supply a text object or a string
	if (text instanceof Message) {
		text = text.text;
	}
	
	key = key % 26;
	if (key < 0) {
		key = (26 - (key * -1));
	}

	var output = "";
	for (var i = 0; i < text.length; i++) {
		var c = text.charCodeAt(i);
		if (c >= 65 && c <=  90) {
			output += String.fromCharCode((c - 65 + key) % 26 + 65);
		} else if (c >= 97 && c <= 122) {
			output += String.fromCharCode((c - 97 + key) % 26 + 97);
		} else {
			output += text.charAt(i);
		}
	}
	return output;
}