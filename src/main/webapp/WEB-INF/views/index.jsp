<!DOCTYPE html>
<html>
<style>

.book {
	border: 1px solid black;
    padding: 10px;
    width: 20%;
    height: 350px;
    float: left;
    margin: 10px
}

.bookThumbNail {
	text-align: center;
}

.title {
	text-align: center;
	font-weight: bold;
}
</style>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<H1>Books by category</H1>

<select id="combobox" onchange="comboBoxSelected();">
	<option disabled selected>Select a category</option>
</select>

<div id="bookContainer">

</div>

<script>
window.onload = function() {
  initializeCombobox();
};

function initializeCombobox() {
	$.get("http://localhost:7777/api/categories", function(data, status) {
       	var x = document.getElementById("combobox");
    	for (let i = 0; i < data.length; i++) {
    		var option = document.createElement("option");
    		option.text = data[i];
    		x.add(option);
        }
            
        }, "json");
}

function comboBoxSelected() {
	var e = document.getElementById("combobox");
	var category = e.options[e.selectedIndex].value;
	$.get("http://localhost:7777/api/category/" + category + "/books", function(data, status) {
            insertBooks(data);
        }, "json");
}

function insertBooks(books) {
	// clear up all the previous content first.
	var element = document.getElementById("bookContainer");
	while (element.firstChild) {
		element.removeChild(element.firstChild);
	}

	for (let i = 0; i < books.length; i = i + 1) {
    	insertBook(books[i]);
    }    
}

function insertBook(book) {
	var x = document.getElementById("bookContainer");
    bookFrame = document.createElement("div");
    bookFrame.className = "book";
    
    thumbnail = document.createElement("div");
    thumbnail.className = "bookThumbNail";
    thumbnail.innerHTML = "<img src=\"" + book.thumbnailUrl + "\" \\><BR />";
    bookFrame.appendChild(thumbnail);
    
    title = document.createElement("div");
    title.className = "title";
    title.innerHTML = book.title;
    bookFrame.appendChild(title);
    
    details = document.createElement("div");
    details.innerHTML = "Rating: " + book.averageRating;
    var date = new Date(book.publishedDate);
    details.innerHTML += "<BR />Publication date:<BR />" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
    bookFrame.appendChild(details);
    
    x.appendChild(bookFrame);
}
</script>

</body>
</html>
