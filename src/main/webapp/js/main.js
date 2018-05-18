jQuery(document).ready(function($) {
	$("#loadCategory").on("button", function() {
		var iDiv = document.createElement('div');
		iDiv.id = 'someBook';
		iDiv.className = 'bookLine';
		
		document.getElementsByTagName('msg')[0].appendChild(iDiv);
		
		// Now create and append to iDiv
		var innerDiv = document.createElement('div');
		innerDiv.className = 'block-2';

		// The variable iDiv is still good... Just append to it.
		iDiv.appendChild(innerDiv);
		
		//$('#msg').html("This is updated by jQuery")
		return true;
	})
});