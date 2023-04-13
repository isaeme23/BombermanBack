
var game = (function(client){

    const canvas = document.getElementById('game1');
    const context = canvas.getContext('2d');
    const blockSize = 64;
    const numRows = 13;
    const numCols = 21;
    var board = []
    const nombre = localStorage.getItem("valorInput");
    var stompClient = null;
    var loser = null;
    var dataplayer = {};

    function board2(){
        client.getBoard(tablero);
        getName();
    }

    function boardAgain(){
        client.getBoard(tablero);

    }

    function getName(){
        let name = localStorage.getItem("valorInput");
        client.putNamePlayer(name);
    }

var tablero = function(data){
    board = [];
    for (let row = 0; row < numRows; row++){
        let fila = []
        for (let col = 0; col < numCols; col++){
            let index = "["+ col.toString()+", "+row.toString()+"]"
            if (row === 0 || col === 0 || row === 12 || col === 20){
                fila.push("0");
            } else if (data[index].status === "EMPTY"){
                fila.push("");
            } else if (data[index].status === "PLAYER"){
                fila.push("1");
            } else if (data[index].status === "BOMB"){
                fila.push("2");
            } else if (data[index].status === "BOMBPLAYER"){
                fila.push("3");
            } else if (data[index].status === "DEAD"){
                fila.push("4");
            }
        }
        board.push(fila);
    }
    dibujarTablero();
}

function dibujarTablero(){
    context.clearRect(0, 0, canvas.width, canvas.height);
    for (let i = 0; i < board.length; i++){
        for (let j = 0; j < board[i].length; j++){
            if(board[i][j] === "0"){
                context.fillStyle = 'black';
                context.fillRect(blockSize*j, blockSize*i, blockSize, blockSize);
                context.fillStyle = 'white';
                context.fillRect(blockSize*j, blockSize*i, blockSize - 2, blockSize - 2);
                context.fillStyle = '#a9a9a9';
                context.fillRect((blockSize*j) + 2, (blockSize*i)+2, blockSize - 4, blockSize - 4);
            }
            else if(board[i][j] === "1") {
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/2.5, 0, 2*Math.PI);
                context.fillStyle = 'white';
                context.fill();
            }
            else if(board[i][j] === "2") {
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/4, 0, 2*Math.PI);
                context.fillStyle = 'black';
                context.fill();
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/8, 0, 2*Math.PI);
                context.fillStyle = 'red';
                context.fill();
            }
            else if(board[i][j] === "3") {
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/2.5, 0, 2*Math.PI);
                context.fillStyle = 'white';
                context.fill();
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/4, 0, 2*Math.PI);
                context.fillStyle = 'black';
                context.fill();
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/8, 0, 2*Math.PI);
                context.fillStyle = 'red';
                context.fill();
            }
            else if(board[i][j] === "4") {
                context.beginPath();
                context.arc((j+0.5)*blockSize, (i+0.5)*blockSize, blockSize/2.5, 0, 2*Math.PI);
                context.fillStyle = 'red';
                context.fill();
            }
        }
    }
}

    function getPlayers(){
        client.getPlayers(players)
    }

    var x = "";
    var y = "";

    var players = function(data){
        x = data[nombre].x;
        y = data[nombre].y;
    }

document.addEventListener('keydown', function(e) {
  
    // encuentra la posición actual del jugador en la matriz board
    for (let i = 0; i < board.length; i++){
        for (let j = 0; j < board[i].length; j++){
            if (board[i][j] === "1"||board[i][j] === "3") {
                row = i;
                col = j;
                break;
            }
        }
    }
    
    // left arrow key
    if (e.code === "ArrowLeft") {
        let movement = {
            player : nombre,
            movement : "Left"
        };
        client.putPlayerMovement(movement);
        // verifica que no esté en el borde izquierdo y que no esté muerto
        if (col > 1 && board[row][col]!=="4") {
            //Verifica si un jugador está colocando una bomba
            if (board[row][col]==="3"){
                board[row][col] = "2";
                board[row][col-1] = "1"
            }
            else{
                //verifica que si me muevo a una bomba cambie estado
                if(board[row][col-1] === "2"){
                    board[row][col] = "";
                    board[row][col-1] = "4";
                }
                else{
                    // actualiza la posición del jugador en la matriz
                    board[row][col] = "";
                    board[row][col-1] = "1";
                }
            }
        }
    }
    // up arrow key
    else if (e.code === "ArrowUp") {
        let movement = {
            player : nombre,
            movement : "Up"
        };
        client.putPlayerMovement(movement);
        // verifica que no esté en el borde superior y que no esté muerto
        if (row > 1 && board[row][col]!=="4") {
            //Verifica si es un jugador colocando bomba
            if (board[row][col]==="3"){
                board[row][col] = "2";
                board[row-1][col] = "1";
            }
            else{
                //verifica que si me muevo a una bomba cambie estado
                if(board[row-1][col] === "2"){
                    board[row][col] = "";
                    board[row-1][col] = "4";
                }
                else{
                    // actualiza la posición del jugador en la matriz
                    board[row][col] = "";
                    board[row-1][col] = "1";
                }
            }
        }
    }
    // right arrow key
    else if (e.code === "ArrowRight") {
        let movement = {
            player : nombre,
            movement : "Right"
            }
        client.putPlayerMovement(movement);
        // verifica que no esté en el borde derecho y que no esté muerto
        if (col < numCols-2 && board[row][col]!=="4") {
            //Verifica si es un jugador colocando bomba
            if (board[row][col]==="3"){
                board[row][col] = "2";
                board[row][col+1] = "1";
            }
            else{
                //verifica que si me muevo a una bomba cambie estado
                if(board[row][col+1] === "2"){
                    board[row][col] = "";
                    board[row][col+1] = "4";
                }
                else{
                // actualiza la posición del jugador en la matriz
                    board[row][col] = "";
                    board[row][col+1] = "1";
                }
            }
        }
    }
    // down arrow key
    else if (e.code === "ArrowDown") {
        let movement = {
            player : nombre,
            movement : "Down"
        };
        client.putPlayerMovement(movement);
        // verifica que no esté en el borde inferior y que no esté muerto
        if (row < numRows-2 && board[row][col]!=="4") {
            //Verifica si es un jugador colocando bomba
            if (board[row][col]==="3"){
                board[row][col] = "2";
                board[row+1][col] = "1";
            }
            else{
                //verifica que si me muevo a una bomba cambie estado
                if(board[row+1][col] === "2"){
                    board[row][col] = "";
                    board[row+1][col] = "4";
                }
                else{
                // actualiza la posición del jugador en la matriz
                    board[row][col] = "";
                    board[row+1][col] = "1";
                }
            }
        }
    }
    // space key
    else if (e.code === "Space") {
        let movement = {
            player : nombre,
            movement : "Bomb"
        };
        client.putPlayerMovement(movement);
        // verifica que el jugador no haya colocado una bomba previamente en la posición actual y que no esté muerto
        if (board[row][col] !== "2" && board[row][col] !== "4") {
            // actualiza la posición del jugador en la matriz y coloca la bomba
            board[row][col] = "3";
        }
    }

    // dibuja el tablero con la nueva posición del jugador
    //dibujarTablero();
    //boardAgain();
    getPlayers();
    console.log(board[x][y]);
    if (board[x][y]=== "4"){
        loser = {
            name : nombre
        };
    }
    publishBoard();

});

var connectAndSubscribe = function () {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);

        //subscribe to /topic/TOPICXX when connections succeed
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/board', function (eventbody) {
                boardAgain();
                var theObject=JSON.parse(eventbody.body);
                if (theObject !== null){
                    alert(JSON.stringify(theObject));
                }
            });
        });
    };

    var publishBoard = function(){
         stompClient.send("/topic/board", {}, JSON.stringify(loser));
    }

return{
    init: function () {
        board2();
        connectAndSubscribe();
    },

    board2:board2,
    boardAgain:boardAgain,
    connectAndSubscribe
};

})(client);