client=(function(){

	return {
		getBoard:function(callback){
			$.get(`http://localhost:8080/board`, function(data) {callback(data)});
		}
		/*putUpdateBoard:function(board){
		   return $.ajax({
               url: `http://localhost:8080/board`,
               type: 'PUT',
               data: JSON.stringify(blueprint),
               contentType: "application/json"
           });
		},
		deletePlayer:function(player){
		    return $.ajax({
               url: `http://localhost:8080/board/${player.name}`,
               type: 'DELETE',
               data: JSON.stringify(blueprint),
               contentType: "application/json"
           });
		}*/
	}

})();