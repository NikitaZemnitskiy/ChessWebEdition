 var divSquare = '<div id= "s$coord" class = "square $color"></div>';
 var divFigure = '<div id= "f$coord" class = "figure">$figure</div>';
 var map;
 $(function(){
start();
        });
function start() {
    map = new Array(64);
    addSquares();
    fetch('/board')
        .then(response => response.text())
        .then(figures => showFigures(figures));
//    showFigures('rnbqkbnrpppppppp11111111111111111111111111111111PPPPPPPPRNBQKBNR');
}
 function setDraggable() {
        $('.figure').draggable();
 }

 function setDroppable() {
     $('.square').droppable({
       drop : function (event, ui) {
           var frCord = ui.draggable.attr('id').substring(1);
           var toCord = this.id.substring(1);
           moveFigure(frCord, toCord);
       }
     })
 }
 async function moveFigure(frCord, toCord) {

     console.log('move from ' + parseCoord(frCord) + ' to ' +parseCoord(toCord));
     let response = await fetch("/turn", {method:'POST', body: parseCoord(frCord) + " " + parseCoord(toCord)});
     if(response.ok) {
         figure = map[frCord];
         showFigureAt(frCord, '1');
         showFigureAt(toCord, figure);
         soundTurn("chessTurn.mp3");
         return;
     }
     figure = map[frCord];
     showFigureAt(frCord, figure);
     soundTurn("WrongTurn.mp3");

 }

 function addSquares(){
 console.log('addSquares');
         $('.board').html('');
         for(var coord = 0; coord <64; coord++)
         $('.board').append(divSquare
         .replace('$coord', coord)
         .replace('$color',
         isBlackSquareAt(coord)? 'black' : 'white'));
         setDroppable();
 }

 function showFigures(figures) {
     for(var coord = 0; coord <64; coord++)
         showFigureAt(coord, figures.charAt(coord));
 }

 function showFigureAt(coord, figure){
 $('#s' + coord).html(divFigure
 .replace('$coord', coord)
 .replace('$figure', getChessSymbol(figure)));
     setDraggable();
     map[coord] = figure;
 }

 function getChessSymbol(figure){
     switch (figure) {
         case 'K' : return '&#9812';
         case 'Q' : return '&#9813';
         case 'R' : return '&#9814';
         case 'B' : return '&#9815';
         case 'N' : return '&#9816';
         case 'P' : return '&#9817';
         case 'k' : return '&#9818';
         case 'q' : return '&#9819';
         case 'r' : return '&#9820';
         case 'b' : return '&#9821';
         case 'n' : return '&#9822';
         case 'p' : return '&#9823';
         default : return '';
     }
 }
 function parseCoord(coord){
     let vert = Math.trunc(9-coord/8);
     let hor = coord % 8 + 1;
     switch (hor){
         case 1:return "a"+vert;
         case 2:return "b"+vert;
         case 3:return "c"+vert;
         case 4:return "d"+vert;
         case 5:return "e"+vert;
         case 6:return "f"+vert;
         case 7:return "g"+vert;
         case 8:return "h"+vert;
         default:return "WrongSquare";
     }
 }
 function soundTurn(audioName) {
     var audio = new Audio();
     audio.src = audioName;
     audio.autoplay = true;
 }
 function isBlackSquareAt(coord){
    return (coord % 8 + Math.floor(coord/8))%2;
 }