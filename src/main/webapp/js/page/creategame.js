$(document).ready(function() {
	checkPlayers();
	var timerMulti = window.setInterval("checkPlayers();", 3000);
	$('#modalKicked').on('hide', function() {
		window.location.href = 'leavegame';
	});
});

var creator = $('#creator').attr('value') == 'true';
var playerId = $('#playerId').attr('value');
var json = true;

function checkPlayers() {
	if (!json) {
		return;
	}
	$
			.ajax({
				url : 'getplayers',
				type : "GET",
				dataType : "json",
				success : function(data) {
					if (data) {
						if (data.status == false && data.ended == true) {
							$('#modalKicked').modal({});
							json = false;
						}
						if (data.status && data.code != undefined) {
							window.location.href = data.code;
						}
						var html = '<table class="table table-hover"><caption>Player list</caption><tbody>';
						for ( var i = 0; i < data.length; ++i) {
							html += '<tr><td>' + data[i].name
									+ removeLabel(data[i].id) + '</td></tr>'
						}
						html += '</tbody></table>';
						$('#players').html(html);
					}
				}
			});
}

function removeLabel(id) {
	var html = '';
	if (creator && playerId != id) {
		html = '<a class="btn btn-link" onclick="abandon(' + id
				+ ')">abandon</a>';
	}
	return html;
}

function abandon(id) {
	$.ajax({
		url : 'abandon?id=' + id,
		type : 'PUT',
		dataType : 'json',
		success : function(data) {
			console.log('abandoned id=' + id);
		}
	});
}

function startGame() {
	$.ajax({
		url : 'startgame',
		type : 'PUT',
		dataType : 'json',
		success : function(data) {
			if (data.status && data.code) {
				window.location.href = data.code;
			} else {
				alert('Cant start game right now');
			}
		}
	});
}