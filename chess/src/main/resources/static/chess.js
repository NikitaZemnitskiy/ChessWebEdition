 var divSquare = '<div id= "s$coord" class = "square $color"></div>';
 var divFigure = '<div id= "f$coord" class = "figure">$figure</div>';
 var map;
 var audio = new Audio();
 var path = location.pathname.split('/');
 var gameId = path[path.length-1];
 var turns = "";
 var turnCount = 0;
 const evtSource = new EventSource("/stream-sse/"+gameId);
     $(function(){
    start();

                evtSource.addEventListener("boardUpdated", function(event) {
                    showFigures(event.data);
     });

         evtSource.addEventListener("turnUpdated", function(event) {
             showTurns(event.data);
         });

     evtSource.addEventListener("statusUpdated", function(event) {
         showStatus(event.data);
     });

     evtSource.addEventListener("playersUpdated", function(event) {
         addPlayers(event.data);
     });


         window.onbeforeunload = function(e) {
             var dialogText = 'If you make it you lose your game';
             e.returnValue = dialogText;
             e.dialogeText = dialogText;
            // return dialogText;
         };
         window.onunload = function() {
             console.log("Leave!")
            windowClose();
         };

         information();
 });
async function start() {
    map = new Array(64);
    let playerColor = await fetch('/color/'+gameId);
    await playerColor.text() === "black"?addBSquares():addWSquares();

        let response = await fetch('/boardPosition/'+gameId);
        let responseText = await response.text();
        showFigures(responseText);
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
    parseFrCoord = parseCoord(frCord);
    parseToCoord = parseCoord(toCord);
    console.log('move from ' + parseFrCoord + ' to ' + parseToCoord);
    let response = await fetch("/turn/"+gameId , {method: 'POST', body: parseFrCoord + parseToCoord});

    if (response.ok) {
        soundTurn("/chessTurn.mp3");
        $('.myErrorMessage').html("                       ");

    } else {
       // console.log(await response.text());
        figure = map[frCord];
        showFigureAt(frCord, figure);
        soundTurn("/WrongTurn.mp3");
        $('.myErrorMessage').html(await response.text());
    }


 }

 function addWSquares(){
         $('.board').html('');
         for(var coord = 0; coord <64; coord++)
         $('.board').append(divSquare
         .replace('$coord', coord)
         .replace('$color',
         isBlackSquareAt(coord)? 'black' : 'white'));
         setDroppable();
 }

 function addBSquares(){
     $('.board').html('');
     for(var coord = 63; coord >=0; coord--)
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

 const figureMap = {
     'K' : '&#9812',
     'Q' : '&#9813',
     'R': '&#9814',
     'B': '&#9815',
     'N': '&#9816',
     'P': '&#9817',
     'k': '&#9818',
     'q': '&#9819',
     'r': '&#9820',
     'b': '&#9821',
     'n': '&#9822',
     'p': '&#9823',
     '1':''
 }


 function getChessSymbol(figure){
     return figureMap[figure]
 }

 function parseCoord(coord){
     let vert = Math.trunc(9-coord/8);
     let hor = coord % 8 + 1;
     console.log("hor" + hor)
     console.log("vert" + vert)
     let result = String.fromCharCode(('a'.charCodeAt(0) + hor)-1);
     return result === "a"? result+''+(vert-1):result+''+vert;
    /* switch (hor){
         case 1:return "a"+(vert-1);
         case 2:return "b"+vert;
         case 3:return "c"+vert;
         case 4:return "d"+vert;
         case 5:return "e"+vert;
         case 6:return "f"+vert;
         case 7:return "g"+vert;
         case 8:return "h"+vert;
         default:return "WrongSquare";
     }*/
 }
 function soundTurn(audioName) {
     audio.src = audioName;
     audio.autoplay = true;
 }
 function isBlackSquareAt(coord){
    return (coord % 8 + Math.floor(coord/8))%2;
 }
 function showTurns(turn){
    let figure = getChessSymbol(turn.charAt(0));
    turn = turn.substr(1);
    turnCount++;
    turn =turnCount +")"+figure + turn;
    turns = turns+turn+' ';
     $('.logs').html(turns);
 }
 async function information(){
     let status = await fetch('/status/'+gameId);
     showStatus(await status.text());

     let players = await fetch('/players/'+gameId);
     addPlayers(await players.text());
 }
 async function addPlayers(players) {
     $('.players').html(players);
 }

 async function showStatus(status) {
     $('.status').html(status);
 }
 async function windowClose() {
     await fetch("/surrendered/"+gameId , {method: 'POST'});
 }


