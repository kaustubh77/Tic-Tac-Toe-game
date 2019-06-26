import java.util.ArrayList;

public class Board 
{
    private char[][] board; //e = empty, x = cross, o = circle.

    public Board()
    {
        board = new char[3][3];
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                board[x][y] = 'e'; //Board initially empty
    }

    public Board(Board from, Position position, PlayerSign sign)
    {
        board = new char[3][3];
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                board[x][y] = from.board[x][y];
        board[position.column][position.row] = sign==PlayerSign.Cross ? 'x':'o';
    }

    public ArrayList<Position> getFreePositions()
    {
        ArrayList<Position> retArr = new ArrayList<Position>();     
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                if(board[x][y] == 'e')
                    retArr.add(new Position(x, y));
        return retArr;
    }

    public GameState getGameState()
    {    
        if(hasWon('x'))
            return GameState.CrossWin;
        else if(hasWon('o'))
            return GameState.CircleWin;
        else if(getFreePositions().size() == 0)
            return GameState.Draw;
        else return GameState.Incomplete;
    }

    private boolean hasWon(char sign)
    { 
	        if(board[1][1] == sign)
	        { 
	            if(board[0][0] == sign && board[2][2] == sign)
	                return true;
	            if(board[0][2] == sign && board[2][0] == sign)
	                return true;
	            if(board[1][0] == sign && board[1][2] == sign)
	                return true;
	            if(board[0][1] == sign && board[2][1] == sign)
	                return true;
	        }
            if(board[0][0] == sign)
            {
                if(board[0][1] == sign && board[0][2] == sign)
                    return true;
                if(board[1][0] == sign && board[2][0] == sign)
                    return true;
            }
            if(board[2][2] == sign)
            {
                if(board[1][2] == sign && board[0][2] == sign)
                    return true;
                if( board[2][1] == sign && board[2][0] == sign)
                    return true;
            }   
            return false;
    }

    public boolean isMarked(Position position)
    {
        if(board[position.column][position.row] != 'e')
            return true;
        return false;
    }

    public String toString()
    {
        String retString = "\n";
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                if(board[x][y] ==  'x' || board[x][y] == 'o')
                    retString += "["+board[x][y]+"]";
                else
                    retString += "[ ]";
            }
            retString += "\n";
        }       
        return retString;
    }   
}