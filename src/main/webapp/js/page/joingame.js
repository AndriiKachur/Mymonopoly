function joinGame(code){
	$.ajax({
		url: 'joingame',
		type: 'POST',
		dataType: "json",
		data: 'code='+code,
		success: function(data) {
		    if (data.status){
		    	window.location.href = 'create';
		    } else {
		    	alert('Cant join this game');
		    }
		}
	});
}
