var code;
var playerId;
var showActions = true;
var gameEnd = false;

$(document).ready(function() {
	$('.cell').dropdown();
	code = $('#gamecode').prop('value');
	playerId = $('#playerId').prop('value');
	getState();
	$('#player'+playerId).attr('style','background-color: yellow');
	window.setInterval("getState();", 2000);
	window.setInterval("updateTime();", 1000);
});

$("#message").keyup(function(event){
    if(event.keyCode == 13){
        chatSend();
    }
});

	$(function() {
	  var moveLeft = 20;
	  var moveDown = 10;

	  $('div.cell').hover(function(e) {
		  $('#pop-up p').html(
			'<img src="'+$('#homeHref').attr('href')+'images/loading.gif"/>'	  
		  );
		  $.ajax({
			    type: 'GET',
			    dataType: "json",
			    url: "fieldPopover",
			    data: "cell="+this.id.replace('c','') ,
		        success: function(data)
		        {
		        	var html = $('#popoverTemplate').html();
		        	$('div#pop-up').html(html);
		        	if (data.name != null){
		        		$('div#pop-up #poname').html($('div#pop-up #poname').html().replace('{name}',data.name));
		        		$('div#pop-up #poname').attr('id','');
		        	} else {
		        		$('div#pop-up #poname').remove();
		        	}
		        	if (data.cost != 0){
		        		$('div#pop-up #pocost').html($('div#pop-up #pocost').html().replace('{cost}',data.cost));
		        		$('div#pop-up #pocost').attr('id','');
		        	} else {
		        		$('div#pop-up #pocost').remove();
		        	}
		        	if (data.rent != 0){
		        		$('div#pop-up #porent').html($('div#pop-up #porent').html().replace('{rent}',data.rent));
		        		$('div#pop-up #porent').attr('id','');
		        	} else {
		        		$('div#pop-up #porent').remove();
		        	}
		        	if (data.upgradeCost != 0){
		        		$('div#pop-up #poucost').html($('div#pop-up #poucost').html().replace('{ucost}',data.upgradeCost));
		        		$('div#pop-up #poucost').attr('id','');
		        	} else {
		        		$('div#pop-up #poucost').remove();
		        	}
		        	if (data.upgradeRent != 0){
		        		$('div#pop-up #pourent').html($('div#pop-up #pourent').html().replace('{urent}',data.upgradeRent));
		        		$('div#pop-up #pourent').attr('id','');
		        	} else {
		        		$('div#pop-up #pourent').remove();
		        	}
		        	if (data.mortage != 0){
		        		$('div#pop-up #pomortage').html($('div#pop-up #pomortage').html().replace('{mortage}',data.mortage));
		        		$('div#pop-up #pomortage').attr('id','');
		        	} else {
		        		$('div#pop-up #pomortage').remove();
		        	}
		        	if (data.owner != null){
		        		$('div#pop-up #poowner').html($('div#pop-up #poowner').html().replace('{owner}',data.owner));
		        		$('div#pop-up #poowner').attr('id','');
		        	} else {
		        		$('div#pop-up #poowner').remove();
		        	}
		        }
		    });
	    $('div#pop-up').show();
	      //.css('top', e.pageY + moveDown)
	      //.css('left', e.pageX + moveLeft)
	      //.appendTo('body');
	  }, function() {
	    $('div#pop-up').hide();
	  });

	  $('div.cell').mousemove(function(e) {
	    $("div#pop-up").css('top', e.pageY + moveDown).css('left', e.pageX + moveLeft);
	  });

	});

					
function updateTime(){
	var left = $('#timeLeft').text();
	if (!isNaN(left) && left >= 0){
		$('#timeLeft').text(left-1);
	}
}

function getState() {
	if (gameEnd) return;
	$.ajax({
		url : 'getstate?code=' + code,
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if ( data.ended){
				$('#timeLeft').text("The game is ended. For you at least :)");
				gameEnd = true;
				return;
			}
			var s = Math.round((data.options.timeLimit - (new Date() - data.lastMoveTime)) / 1000);
			if (s < 10) {
				s = "0" + s;
			}
			$('#number').text("Turn: " + data.currentPlayer.name);
			if (!isNaN(s) && s >= 0) {
				$('#timeLeft').text(s);
			}
			var playerInList = $('#' + data.currentPlayer.id).attr('value');
			$('#money' + playerInList).html(data.currentPlayer.money);
			$('#capital' + playerInList).html(data.currentPlayer.capital);

			$('div.cell.playertoken' + playerInList).removeClass('playertoken' + playerInList);
			$('#c' + data.currentPlayer.position).addClass('playertoken' + playerInList);
			if (data.currentPlayer.id == playerId && showActions) {
				$('#actions').html(
						"<a href='#' onclick='endTurn()'>End Turn</a>");
			} else {
				$('#actions').html("");
			}
			
			showActions = true;
		}
	});
}

function endTurn(){
	showActions = false;
	$('#actions').html("");
	$.ajax({
		url: 'endturn',
		type: 'POST',
		dataType: 'json',
		success: function(data) {			
		
		},
		error: function(error){
			alert('Cant end your turn');
			$('#actions').html("<a href='#' onclick='endTurn()'>End Turn</a>");
		}
	});
}

function leaveGame(){
	$.ajax({
		url: 'leavegame',
		type: 'POST',
		dataType: 'json',
		success: function(data) {
			console.log('leave game');
		}	
	});
}

function buy(cell){
	$.ajax({
		url: 'buy/'+cell,
		type: 'PUT',
		dataType: 'json',
		success: function(data) {
			console.log('buy something');
		}	
	});
}

function sell(cell){
	$.ajax({
		url: 'sell/'+cell,
		type: 'PUT',
		dataType: 'json',
		success: function(data) {
			console.log('sell something');
		}	
	});
}

function upgrade(cell){
	$.ajax({
		url: 'upgrade/'+cell,
		type: 'PUT',
		dataType: 'json',
		success: function(data) {
			console.log('upgrade something');
		}	
	});
}

function downgrade(cell){
	$.ajax({
		url: 'downgrade/'+cell,
		type: 'PUT',
		dataType: 'json',
		success: function(data) {
			console.log('downgrade something');
		}	
	});
}

function mortage(cell){
	$.ajax({
		url: 'mortage/'+cell,
		type: 'PUT',
		dataType: 'json',
		success: function(data) {
			console.log('mortage something');
		}	
	});
}