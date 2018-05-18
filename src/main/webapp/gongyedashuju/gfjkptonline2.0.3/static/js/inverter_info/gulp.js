
gulpHtml('header-wrap','header.dat')
gulpHtml('section-left','section-left.dat');
function gulpHtml(elem,fileName) {
    var htmlXmlHttp = new XMLHttpRequest();
    htmlXmlHttp.onreadystatechange = function() {
        if (htmlXmlHttp.readyState === 4 && htmlXmlHttp.status === 200) {
            document.getElementById(elem).innerHTML = htmlXmlHttp.responseText;
        }
    };
    htmlXmlHttp.open("GET", fileName, true);
    htmlXmlHttp.send();
}