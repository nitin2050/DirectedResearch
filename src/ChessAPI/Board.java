package ChessAPI;

public class Board {
	static final int ROWS = 8;
	static final int COLS = 8;
	private Square[][] board;
	
	
	public Board(){
		board = new Square[ROWS][COLS];
	}
	
	public void initBoard(){
		
		//Wait to be complete when related classes are more polished
	}
	
	public void resetBoard(){
		
		//Wait to be complete when related classes are more polished
	}
	
	//temporary implementation
	public Square getSquare(int x, int y){
		
		return board[x-1][y-1];
	}
	
	//temporary implementation
	public void setSquare(Square s, int x, int y){
		
		board[x-1][y-1] = s;
	}
	
	//other methods needed in the Board class....
	//adding now
	//testing comment
}
