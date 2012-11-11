// ==UserScript==
// @name           FA Submissions Conglomorator
// @namespace      http://userscripts.org/
// @description    Gathers every item in a FurAffinity submissions page and displays them as a list of links in a new window.
// @include        http://www.furaffinity.net/msg/submissions/*
// ==/UserScript==

var g_httpRequest;
var g_curPage = 0;
var g_lastindex = 0;
var g_latsItemUrl;

var ITEMS_PER_PAGE = 60;
var GALLERY_URL_BASE = "http://www.furaffinity.net/msg/submissions/new";
var LIST_WIN_LINK_DIV = "FASC_linkDiv";

function g_rGetLinks() {
	var listDiv = this.listWin.document.getElementById(LIST_WIN_LINK_DIV);
	var newLinks = document.createElement("div");
	newLinks.innerHTML = "<br><b>Page " + g_curPage + ":</b><br>";
	listDiv.appendChild(newLinks);

	// We only want to process the page if it has properly loaded.
	if (this.readyState == 4 && this.status == 200) {

		// Get the index of the last image on the page
		var pageText = this.responseText;
		var pattern = /a href=\"\/view\/([0-9]{7,9})\//g;
		while (match = pattern.exec(pageText)) {
			tmpindex = match[1];
		}

		// We're done if this last index is the same as the previous one
		if (tmpindex == g_lastindex) {
			newLinks.innerHTML += "<b>1:</b> ";
			newLinks.innerHTML += "<a href=\"" + pageText[i] + "\" target=\"_blank\">"
					+ g_lastItemUrl.substring(g_lastItemUrl.lastIndexOf("/") + 1, g_lastItemUrl.length) + "</a><br>";

			newLinks.innerHTML += "<b>End of gallery reached.</b>";
		} else {
			g_lastindex = tmpindex;
			pageText = pageText.match(/<img[^>]*src=["'][^"']*\/art\/[^>]*>/ig);
			newLinks.innerHTML += pageText;
			for ( var i = 0; i < pageText.length; i++) {
				var urlStart = pageText[i].indexOf("src=") + 5;
				var quoteChar = pageText[i].charAt(urlStart - 1);
				var url = pageText[i].substring(urlStart, pageText[i].indexOf(quoteChar, urlStart));

				url = url.split("/art/")[1];
				url = url.replace(/(thumbnail|half)\./i, "");

				var an = url.substring(0, url.indexOf('/'));
				var imn = url.substring(url.lastIndexOf('/'), url.length);
				imn = imn.toLowerCase();
				an = an.toLowerCase();
				if (imn.indexOf(an) == -1) {
					if (url.split('.')[1] != "") {
						url = url.split('.');
						url[1] = an + '.' + url[1];
						url = url.join('.');
					}
				}

				url = "http://d.furaffinity.net/art/" + url;
				url = url.replace(/(\....)\..../, "$1");

				pageText[i] = url;
				g_lastItemUrl = url;
			}

			// Add each link to the list.
			for ( var i = 0; i < pageText.length - 1; i++) {
				newLinks.innerHTML += "<b>" + ((g_curPage - 1) * (ITEMS_PER_PAGE - 1) + i + 1) + ":</b> ";
				newLinks.innerHTML += "<a href=\"" + pageText[i] + "\" target=\"_blank\">"
						+ pageText[i].substring(pageText[i].lastIndexOf("/") + 1, pageText[i].length) + "</a><br>";
			}

			// Continue to the next gallery page
			g_getNextPage(this.listWin);
		}
	}
}

/**
 * This function makes an HTTP request to get the next submissions page
 * 
 * @param listWin
 */
function g_getNextPage(listWin) {
	g_curPage++;

	// The first page doesn't have a lastindex in the URL
	if (g_curPage == 1) {
		url = "@" + ITEMS_PER_PAGE + "/";
	} else {
		url = "~" + g_lastindex + "@" + ITEMS_PER_PAGE + "/";
	}
	
	// Make the request
	g_httpRequest = new XMLHttpRequest();
	g_httpRequest.listWin = listWin;
	g_httpRequest.onload = g_rGetLinks;
	g_httpRequest.open("POST", GALLERY_URL_BASE + url, true);
	g_httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	g_httpRequest.send("limit=" + ITEMS_PER_PAGE);
}

/**
 * Called by the new button. Opens a new window and starts the conglomoration process.
 */
function g_getLinks() {
	g_curPage = 0;
	g_lastindex = 0;
	g_lastItemUrl = "";
	
	var listWin = window.open();
	if (listWin == null) {
		alert("FA Submissions Conglomerator couldn't open a new window!");
		return;
	}

	// Add an element to the new page so we have somewhere to put stuff
	listWin.document.write("<b>Your pesonal submissions page conglomoration:</b><div id=\"" + LIST_WIN_LINK_DIV
			+ "\"></div>");

	g_getNextPage(listWin);
}

// Main section ----------------------------------------------------
var gmscript = document.createElement("script");
gmscript.type = "text/javascript";
gmscript.text = "var g_httpRequest;";
gmscript.text = "var g_curPage = 0;";
gmscript.text = "var g_lastIndex = 0";
gmscript.text = "var g_lastItemUrl;";
gmscript.text = "var ITEMS_PER_PAGE = " + ITEMS_PER_PAGE + ";";
gmscript.text += "var GALLERY_URL_BASE = \"" + GALLERY_URL_BASE + "\";";
gmscript.text += "var LIST_WIN_LINK_DIV = \"" + LIST_WIN_LINK_DIV + "\";";
gmscript.text += g_getNextPage + g_getLinks + g_rGetLinks;

document.body.appendChild(gmscript);

// Make a button that calls getLinks and put it in the FA nav bar
var browsebar = document.getElementsByName("messages-form")[0].parentNode;
browsebar.innerHTML = "<input type=\"button\" value=\"Conglomorate Submissions\" onclick=\"g_getLinks();\">"
		+ browsebar.innerHTML;