package ChessAPI;

import ChessAPI.Piece.Color;

public class Board {
	static final int ROWS = 8;
	static final int COLS = 8;
	private Square[][] board;
	
	
	public Board(){
		board = new Square[ROWS+1][COLS+1];
		
		for(int i=1; i<Board.ROWS+1; i++  ){
			for(int j=1; j<Board.COLS+1; j++  ){
				//have to make square alternate black and white...
				//INITIALIZE the square on the board to null,set color and co-ordinates for them
				board[i][j] = new Square(Color.white,i,j,null);  
			}
		}
	}
	
	public void initBoard(Player p1, Player p2){
		//initialize board for Player 1 
		//This sets Square.Piece
		board[1][1].setPiece(p1.rook[0]);
		board[1][2].setPiece(p1.knight[0]);
		board[1][3].setPiece(p1.bishop[0]);
		board[1][4].setPiece(p1.queen);
		board[1][5].setPiece(p1.king);
		board[1][6].setPiece(p1.rook[1]);
		board[1][7].setPiece(p1.knight[1]);
		board[1][8].setPiece(p1.bishop[1]);
		for(int i=1;i<=8;i++) {
			board[2][i].setPiece(p1.pawn[i-1]);
		}
		
		//initialize board for Player 2
		//This sets Square.Piece
		for(int i=1;i<=8;i++) {
			board[7][i].setPiece(p2.pawn[i-1]);
		}
		board[8][1].setPiece(p2.rook[0]);
		board[8][2].setPiece(p2.knight[0]);
		board[8][3].setPiece(p2.bishop[0]);
		board[8][4].setPiece(p2.queen);
		board[8][5].setPiece(p2.king);
		board[8][6].setPiece(p2.rook[1]);
		board[8][7].setPiece(p2.knight[1]);
		board[8][8].setPiece(p2.bishop[1]);
		
		//Also set the square in Piece to point to the initializtion
		//This sets Piece.Square
		p1.initPlayer();
		p2.initPlayer();
		//Wait to be complete when related classes are more polished
	}
	
	public void resetBoard(){
		
		//Wait to be complete when related classes are more polished
	}
	
	//temporary implementation
	public Square getSquare(int x, int y){
		
		return board[x][y];
	}
	
	//temporary implementation
	public void setSquare(Square s, int x, int y){
		
		board[x-1][y-1] = s;
	}
	
	//display Board {Req 5}
	public void displayBoard() {
		
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
	}
}
