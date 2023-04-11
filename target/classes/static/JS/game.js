
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
            let index = "["+ row.toString()+", "+col.toString()+"]"
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
    dibujarTablero();
}


    const brickImg = new Image();
    brickImg.src = '../img/ladrilloRojo.png';

    const borderImg = new Image();
    brickImg.src = '../img/bloqueGris.png';

    function dibujarTablero(){
        // board.map((casillasList) => {
        //     casillasList.map((casilla)=>{
        //         //context.drawImage(borderImg, j * blockSize, i *blockSize, blockSize, blockSize)
        //         context.fillStyle = "#fff"
        //     })
        // })
        for (let i = 0; i < 13; i++){
            for (let j = 0; j < 21; j++){
                context.fillStyle = "#fff";
                context.fillRect(1, 1, 64, 64);
                console.log(board[i][j] === "0")
                // if(board[i][j] === "0"){
                //     //context.drawImage(borderImg, j * blockSize, i *blockSize, blockSize, blockSize)
                //     context.fillStyle = "#fff";
                //     context.fillRect(j * blockSize, i *blockSize, blockSize, blockSize);
                // }
                }
            }    
        }



        return{
            board2:board2
        };
})(client);