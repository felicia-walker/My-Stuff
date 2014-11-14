var FreqAnalysis = require("./frequency_analysis.js");
var MessageOptions = require("./message_options.js");

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
module.exports = Message;