package ChessAPI;

import ChessAPI.Piece.Color;

public class temp {
	
	public static void main(String s[]){
		Board beta = new Board();
		Player pl11 = new Player(Color.white);
		Player pl12 = new Player(Color.black);
		beta.initBoard(pl11, pl12);
		
		Move temp = pl11.selectBestMove();
		System.out.println(temp.getSource().get_x()+" "+temp.getSource().get_y()+" "+temp.getSource().getPiece());
		System.out.println(temp.getDestinationSquare().get_x()+" "+temp.getDestinationSquare().get_y()+" "+temp.getDestinationSquare().getPiece());
		System.out.println(temp.getHeuristicValue());
		boolean result = pl11.moveTo(temp.getSource(), temp.getDestinationSquare());
		System.out.println(result);
	}
}
