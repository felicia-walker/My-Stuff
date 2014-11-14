FreqAnalysis.prototype = {
	options: new MessageOptions(),
	updateStats: function(stats) {
		this.sortedByCount = stats.slice(0).sort(function(a, b) {return b[1] - a[1]});
		this.sortedBySymbol = stats.slice(0).sort(function(a, b) {return a[0].localeCompare(b[0])});
		this.numberOfSymbols = stats.length;
	}
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

	this.updateStats(rawData);
}

function getEnglishDistribution(total) {
	var stats = [['E', 1251, '12.51'],
				['T', 925, '9.25'],
				['A', 804, '8.04'],
				['O', 760, '7.60'],
				['I', 726, '7.26'],
				['N', 709, '7.09'],
				['S', 654, '6.54'],
				['R', 612, '6.12'],
				['H', 549, '5.49'],
				['L', 414, '4.14'],
				['D', 399, '3.99'],
				['C', 306, '3.06'],
				['U', 271, '2.71'],
				['M', 253, '2.53'],
				['F', 230, '2.30'],
				['P', 200, '2.00'],
				['G', 196, '1.96'],
				['W', 192, '1.92'],
				['Y', 173, '1.73'],
				['B', 154, '1.54'],
				['V', 99, '0.99'],
				['K', 67, '0.67'],
				['X', 19, '0.19'],
				['J', 16, '0.16'],
				['Q', 11, '0.11'],
				['Z', 9, '0.09']];

	var statsLength = stats.length;
	if (total !== undefined) {
		for (var i = 0; i < statsLength; i++) {
			stats[i][1] = Math.round((stats[i][2] / 100) * total);
		}
	}

	var freqObj = Object.create(FreqAnalysis.prototype);
	freqObj.updateStats(stats);
	
	return freqObj;
}