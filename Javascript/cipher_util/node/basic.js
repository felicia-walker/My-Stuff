var express = require('express');
var cors = require('cors');
var app = express();
 
app.use(cors());
app.listen(8080);

app.get('/', function(request, response) {
	console.log("Blah");
    response.send("This would be some HTML");
});