var chatEnd = false;

$(document).ready(function() {	
	if ($('#chatContainer').length > 0){
		$('#chatContainer').html('<div id="chat"></div>'
				+ '<center><form class="form-inline">Chat:<input id="message" class="input-xlarge" type="text"/>'
				+ '<input onclick="chatSend(); return false;" type="submit"'
				+ ' class="btn" value="Send"/></form></center>');
		window.setInterval("chatGet();", 2000);
	}	
});
	
	function chatSend(){
		if (chatEnd || $('#chatContainer').length < 1){
			return;
		}
		var message = $('#message').attr('value');
		$('#message').attr('value', '');
		$.ajax({
			url:  $('#homeHref').attr('href') + 'game/chat',
			data: 'message=' + message,
			type: 'POST',
			dataType: 'json',
			success: function(data) {
				console.log('message sent');
			}	
		});
	}

	function chatGet(){
		if (chatEnd || $('#chatContainer').length < 1){
			return;
		}
		$.ajax({
			url:  $('#homeHref').attr('href') + 'game/chat',
			type: 'GET',
			dataType: 'json',
			success: function(data) {
				if ( data.ended) {
					chatEnd = true;
				}
				if (data.status && data.messages != 'null'){
					$('#chat').append(data.messages);
					$('#chat').scrollTop(9999999);
				}
			}	
		});
	}