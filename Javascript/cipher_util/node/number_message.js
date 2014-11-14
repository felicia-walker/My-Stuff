var msg = require("./message.js");
var common = require("./common.js");

NumberMessage.prototype = common.clonePrototype(Message.prototype);

function NumberMessage(text) {
	this.text = text;
	this.options.includeAlpha = false;
	this.options.includeDigits = true;
}
module.exports = NumberMessage;