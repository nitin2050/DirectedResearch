package ChessAPI;

import ChessAPI.Piece.Color;

public class Board {
	//singleton instance
	private static Board instance;
	
	//to prevent multiple instantiations
	protected Board(){
		board = new Square[ROWS+1][COLS+1];
		Color squareColor;
		
		for(int i=1; i<Board.ROWS+1; i++  ){
			
			if(i%2 == 0)
				squareColor = Color.white;
			else
				squareColor = Color.black;
			
			for(int j=1; j<Board.COLS+1; j++  ){
				//have to make square alternate black and white...
				//INITIALIZE the square on the board to null,set color and co-ordinates for them
				board[i][j] = new Square(squareColor,i,j,null); 
				if(squareColor == Color.white)
					squareColor = Color.black;
				else
					squareColor = Color.white;
				
			}
		}
	}

	public static Board getBoardInstance() {
		if(instance == null) {
			instance = new Board();
		}
		return instance;
	}
	
	static final int ROWS = 8;
	static final int COLS = 8;
	private Square[][] board;
	
	public void initBoard(Player p1, Player p2){
		//initialize board for Player 1 
		//This sets Square.Piece
		board[1][1].setPiece(p2.rook[1]);
		board[1][2].setPiece(p2.knight[1]);
		board[1][3].setPiece(p2.bishop[1]);
		board[1][4].setPiece(p2.queen);
		board[1][5].setPiece(p2.king);
		board[1][6].setPiece(p2.bishop[2]);
		board[1][7].setPiece(p2.knight[2]);
		board[1][8].setPiece(p2.rook[2]);
		for(int i=1;i<=8;i++) {
			board[2][i].setPiece(p2.pawn[i]);
		}
		
		// Set Squares for Pieces of Player 1		
		p2.rook[1].setSquare(board[1][1]);
		p2.knight[1].setSquare(board[1][2]);
		p2.bishop[1].setSquare(board[1][3]);
		p2.queen.setSquare(board[1][4]);
		p2.king.setSquare(board[1][5]);
		p2.bishop[2].setSquare(board[1][6]);
		p2.knight[2].setSquare(board[1][7]);
		p2.rook[2].setSquare(board[1][8]);
		for(int i=1;i<=8;i++) {
			p2.pawn[i].setSquare(board[2][i]);
		}
		
		//initialize board for Player 2
		//This sets Square.Piece
		for(int i=1;i<=8;i++) {
			board[7][i].setPiece(p1.pawn[i]);
		}
		board[8][1].setPiece(p1.rook[1]);
		board[8][2].setPiece(p1.knight[1]);
		board[8][3].setPiece(p1.bishop[1]);
		board[8][4].setPiece(p1.queen);
		board[8][5].setPiece(p1.king);
		board[8][6].setPiece(p1.bishop[2]);
		board[8][7].setPiece(p1.knight[2]);
		board[8][8].setPiece(p1.rook[2]);

		//This sets Square for each Piece
		for(int i=1;i<=8;i++) {
			p1.pawn[i].setSquare(board[7][i]);
		}
		p1.rook[1].setSquare(board[8][1]);
		p1.knight[1].setSquare(board[8][2]);
		p1.bishop[1].setSquare(board[8][3]);
		p1.queen.setSquare(board[8][4]);
		p1.king.setSquare(board[8][5]);
		p1.bishop[2].setSquare(board[8][6]);
		p1.knight[2].setSquare(board[8][7]);
		p1.rook[2].setSquare(board[8][8]);
		
	}
	
	public void resetBoard(){
		
		//Wait to be complete when related classes are more polished
	}
	
	//temporary implementation
	public Square getSquare(int x, int y){
		if(x>8 || y>8 || x<1 || y<1){
			
			return null;
		}
		else
			return board[x][y];
	}
	
	//temporary implementation
	public void setSquare(Square s, int x, int y){	
		board[x-1][y-1] = s;
	}
	
	//display Board {Req 5}
	public void displayBoard() {
		
		System.out.println("a           b           c           d           e           f           g           h");
		for(int i=1; i<Board.ROWS+1; i++  ) {
			for(int j=1; j<Board.COLS+1; j++  ){
				if(this.board[i][j].getPiece() == null){
					//print X if there is no Piece on this Square
					System.out.print(String.format("%-12s","X"));
				} else {
					//else print ColorInitial_PieceType
					if(this.board[i][j].getPiece().getColor() == Color.white){
						//Piece is white
						System.out.print(String.format("%-12s","W_"+this.board[i][j].getPiece().getType()));
					}else{
						//Piece is black
						System.out.print(String.format("%-12s","B_"+this.board[i][j].getPiece().getType()));
					}
				}
			}//end inner for
			System.out.println();
		}//end outer for
		System.out.println("a           b           c           d           e           f           g           h");
	}
	
	public GameDump getDump()
	{
		int i, j;
		Player P1, P2;
		P1 = new Player(Color.black);
		P2 = new Player(Color.white);

		GameDump currentDump;
		currentDump = new GameDump();

		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				currentDump.sq[i][j] = this.board[i][j];
			}
			// noOfMovesTillNow get it from Player class object
			// If Player1.isMyTurn = true then turn = 1
			// Else turn = 2

		currentDump.noOfMovesTillNow = P1.getNoMoves() + P2.getNoMoves();
		if (P1.getTurn() == true)
			currentDump.turn = 1;
		else
			currentDump.turn = 2;
		return currentDump;
	}
}
