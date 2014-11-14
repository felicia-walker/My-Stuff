function createSection(paneName) {
	var paneId = paneName.toLowerCase();

	var label = document.createElement("div");
	label.className = "centered sectionLabel";
	label.appendChild(document.createTextNode(paneName));

	var messageArea = document.createElement("textarea");
	messageArea.className = "message";
	messageArea.id = paneId + "_message";
	var message = document.createElement("div");
	message.className = "centered";
	message.appendChild(messageArea);

	var buttons = document.createElement("div");
	buttons.id = paneId + "_buttons";
	buttons.className = "centered";

	var analysis = document.createElement("div");
	analysis.id = paneId + "_analysis";
	analysis.className = "analysisPane centered";
	var left = document.createElement("span");
	left.id = paneId + "_analysis_left";
	var right = document.createElement("span");
	right.id = paneId + "_analysis_right";
	var sortCheckbox = createCheckBox(analysis.id, "Sort by count", function(){doAnalysis(paneId, document.getElementById(paneId + "_message").value);});
	var fillCheckbox = createCheckBox(analysis.id, "Show missing letters", function(){doAnalysis(paneId, document.getElementById(paneId + "_message").value);});

	analysis.appendChild(sortCheckbox);
	analysis.appendChild(fillCheckbox);
	var tmpDiv = document.createElement("div");
	tmpDiv.appendChild(left);
	tmpDiv.appendChild(right);
	analysis.appendChild(tmpDiv);

	var section = document.getElementById(paneId);
	section.appendChild(label);
	section.appendChild(message);
	section.appendChild(buttons);
	section.appendChild(analysis);
}

function createCheckBox(baseId, text, action) {
	var checkbox = document.createElement("input");
	checkbox.type = "checkbox";
	checkbox.className = "checkbox";
	checkbox.id = baseId + "_checkbox_" + textToId(text);
	checkbox.onclick = action;

	var label = document.createElement("label");
	label.htmlFor = checkbox.id;
	label.appendChild(document.createTextNode(text));

	var container = document.createElement("span");
	container.appendChild(checkbox);
	container.appendChild(label);
	
	return container;
}

function createMessageButton(paneId, text, action) {
	var section = document.getElementById(paneId + "_buttons");
	var message = document.getElementById(paneId + "_message");

	var button = document.createElement("span");
	button.id = paneId + "_button_" + textToId(text);
	button.className = "button";
	button.appendChild(document.createTextNode(text));
	button.onclick = function(){action(paneId, message.value);};

	section.appendChild(button);
}

function encrypt(text) {
	var cipher = new CaeserShift(document.getElementById("caeserShift_key").value);
	var message = new Message(text);
	var cipherText = cipher.encrypt(message);

	document.getElementById("ciphertext_message").value = cipherText;
}

function decrypt(text) {
	var cipher = new CaeserShift(document.getElementById("caeserShift_key").value);
	var message = new Message(text);
	var plainText = cipher.decrypt(message);

	document.getElementById("plaintext_message").value = plainText;
}

function doAnalysis(baseId, text) {
	var message = new Message(text);
	var messageFreq = message.freqAnalysis();
	var englishDist = getEnglishDistribution(message.length());
	var left = document.getElementById(baseId + "_analysis_left");
	var right = document.getElementById(baseId + "_analysis_right");
	var sortByCount = document.getElementById(baseId + "_analysis_checkbox_sortbycount");
	var showMissingLetters = document.getElementById(baseId + "_analysis_checkbox_showmissingletters");
	var leftData = messageFreq.sortedBySymbol;
	var rightData = englishDist.sortedBySymbol;

	if (showMissingLetters.checked === true) {
		var leftLen = messageFreq.numberOfSymbols;
		var rightLen = englishDist.numberOfSymbols;

		outerLoop:
		for (var ri = 0; ri < rightLen; ri++) {
			for (var li = 0; li < leftLen; li++) {
				if (rightData[ri][0].toLowerCase() === leftData[li][0].toLowerCase()) {
					continue outerLoop;
				}
			}

			leftData.push([rightData[ri][0], 0, 0.00]);
		}

		messageFreq.updateStats(leftData);
	}

	// Always get leftData since it may have changed in the last block
	if (sortByCount.checked === true) {
		leftData = messageFreq.sortedByCount;
		rightData = englishDist.sortedByCount;
	} else {
		leftData = messageFreq.sortedBySymbol;
	}

	left.innerHTML = getAnalysisDataAsTable(leftData);
	right.innerHTML = getAnalysisDataAsTable(rightData);
}

function getAnalysisDataAsTable(data) {
	var len = data.length;
	var content = "<table class='analysisTable'><tr class='tableHeader'><td>Symbol</td><td>Count</td><td>Percent</td></tr>";
	for (var i = 0; i < len; i++) {
		content += "<tr><td>" + data[i][0].toUpperCase() + "</td>";
		content += "<td>" + data[i][1] + "</td>";
		content += "<td>" + data[i][2] + "</td></tr>";
	}

	content += "</table>";
	return content;
}

function textToId(text) {
	return text.toLowerCase().split(' ').join('');
}