var MessageOptions = require("./message_options.js");

FreqAnalysis.prototype = {
	options: new MessageOptions(),
}

function FreqAnalysis(text, options) {
	// Use an object rather than an array to make searching for a symbol easier, but we need
	// to manually keep track of the length
	var rawMap = {};
	var rawMapLength = 0;
	
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

	this.sortedByCount = rawData.sort(function(a, b) {return a[1] - b[1]});
	this.sortedBySymbol = rawData.sort(function(a, b) {return a[0].localeCompare(b[0])});
	this.numberOfSymbols = rawMapLength;
}
module.exports = FreqAnalysis;