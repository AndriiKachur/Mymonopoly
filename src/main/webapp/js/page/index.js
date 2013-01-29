function createGame(){
	jQuery.ajax({
		url: $('#homeHref').attr('href') + 'game/create',
		type: 'POST',
		dataType: 'json',
		data: $('#newGameModal form').serialize(),
		success: function(data) {
		    if (data.status){
		    	window.location.href = $('#homeHref').attr('href') + 'game/create';
		    } else {
		    	alert('Cant create game');
		    }
		  },
		 
	});
}