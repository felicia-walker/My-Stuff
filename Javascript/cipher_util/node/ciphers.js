var Message = require("./message.js");

CaeserShift.prototype = {
	key: undefined,
	encrypt: function(text) {
		return caeserShift(text, this.key)
	},
	decrypt: function(text) {
		return caeserShift(text, this.key * -1)
	}
}

function CaeserShift(key) {
	this.key = key;
}
module.exports = CaeserShift;

exports.caeserShift = function(text, key) {
	key = parseInt(key);
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