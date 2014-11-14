var Message = require("./message.js");
var Ciphers = require("./ciphers.js");
var express = require("express");
var path = require("path");
var async = require("async");
var cors = require("cors");

var server = express();
server.use(cors());
server.listen(8080);

server.get("/encrypt", handle_encrypt);
server.get("/decrypt", handle_decrypt);
server.get("/analyze", handle_analyze);

function handle_encrypt(req, res) {
	console.log("Encrypt "+req.query);
}

function handle_decrypt(req, res) {
	console.log("Decrypt "+req.query);
}

function handle_analyze(req, res) {
	var text = req.query.text;
	var message = new Message(text);
	var result = message.freqAnalysis().sortedByCount;

	res.json(result);
}