$(document).ready(function() {

	$("#loaddCategory").on("submit", function() {

		$.ajax({
			url : 'rest/userInfo',
			type : 'PUT',
			dataType : "xml",
			data : $("#loaddCategory").serialize(),
			success : function(xml) {
				console.log('gnaaaa');
			}
		});
		return true;
	})
});