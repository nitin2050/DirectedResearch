package ChessAPI;

public class GameDump {
	Square sq[][];
	int noOfMovesTillNow;
	int turn;

	GameDump()
	{
		sq = new Square[8][8];
		noOfMovesTillNow = 0;
	}
}

