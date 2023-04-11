
var game = (function(client){

    const canvas = document.getElementById('game1');
    const context = canvas.getContext('2d');
    const blockSize = 64;
    const numRows = 13;
    const numCols = 21;
    var board = []

    function board2(){
        client.getBoard(tablero);
    }

    var tablero = function(data){
    for (let row = 0; row < numRows; row++){
        let fila = []
        for (let col = 0; col < numCols; col++){
            console.log("entro for");
            let index = "["+ row.toString()+", "+col.toString()+"]"
            console.log("entro for");
            console.log(data[index]);
            if (row === 0 || col === 0 || row === 12 || col === 20){
                fila.push("0");
            } else if (data[index].status === "EMPTY"){
                fila.push("");
            } else if (data[index].status === "PLAYER"){
                fila.push("1");
            } else if (data[index].status === "BOMB"){
                fila.push("2");
            }
            
        }
        board.push(fila);
    }
        console.log(board);
    }


    const brickImg = new Image();
    brickImg.src = '../img/ladrilloRojo.png';

    const borderImg = new Image();
    brickImg.src = '../img/bloqueGris.png';
        return{
            board2:board2
        };
})(client);