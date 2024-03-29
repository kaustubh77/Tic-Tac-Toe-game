import java.util.ArrayList;
import java.util.Scanner;
 
//Uses minimax algorithm with alpha-beta pruning
public class TicTacToe 
{
    static boolean gameEnded = false;
    private static boolean player = true;
    private static Scanner in = new Scanner(System.in);
    public static Board board = new Board();
 
    public static void main(String[] args)
    {
        System.out.println(board);
        while(!gameEnded)
        {
            Position position = null;
            if(getPlayerValue())
            {
                position = makeMove();
                board = new Board(board, position, PlayerSign.Cross);
                System.out.print("\nYour move");
            }
            else
            {
                board = findBestMove(board);
                System.out.print("Computer's Move");
            }               
            setPlayer(!getPlayerValue());	
            System.out.println(board);
            evaluateGame();
        }
    }
 
    public static Board findBestMove(Board board) 
    {
        ArrayList<Position> positions = board.getFreePositions();
        Board bestChild = null;
        int previous = Integer.MIN_VALUE;
        for(Position p : positions)
        {
            Board child = new Board(board, p, PlayerSign.Circle);
            int current = min(child , Integer.MIN_VALUE , Integer.MAX_VALUE);
            if(current > previous)
            {
                bestChild = child;
                previous = current;
            }
        }
        return bestChild;
    }
 
    public static int max(Board board , int alpha , int beta)
    {
        GameState gameState = board.getGameState();
        if(gameState == GameState.CircleWin)
            return 1;
        else if(gameState == GameState.CrossWin)
            return -1;
        else if(gameState == GameState.Draw)
            return 0;
        ArrayList<Position> positions = board.getFreePositions();
        int best = Integer.MIN_VALUE;
        for(Position p : positions)
        {
            Board b = new Board(board, p, PlayerSign.Circle);
            int move = min(b , alpha , beta);
            best = Math.max(best, move); 
            alpha = Math.max(alpha, best); 
            if (beta <= alpha) 
                break; 
        }       
        return best;
    }
 
    public static int min(Board board , int alpha , int beta)
    {
        GameState gameState = board.getGameState();
        if(gameState == GameState.CircleWin)
            return 1;
        else if(gameState == GameState.CrossWin)
            return -1;
        else if(gameState == GameState.Draw)
            return 0;
        ArrayList<Position> positions = board.getFreePositions();
        int best = Integer.MAX_VALUE;
        for(Position p : positions)
        {
            Board b = new Board(board, p, PlayerSign.Cross);
            int move = max(b , alpha , beta );
            best = Math.min(best, move); 
            beta = Math.min(beta, best); 
            // Alpha Beta Pruning 
            if (beta <= alpha) 
                break; 
        }
        return best;
    }
 
    public static void evaluateGame()
    {
        GameState gameState = board.getGameState();
        gameEnded = true;
        switch(gameState)
        {
            case CrossWin : 
                System.out.println("Game Over! Cross Won!");
                break;
            case CircleWin : 
                System.out.println("Game Over! Circle Won!");
                break;
            case Draw : 
                System.out.println("Game Over! Game Drawn!");
                break;
            default : gameEnded = false;
                break;
        }
    }
 
    public static Position makeMove()
    {
        Position position = null;
        while(true)
        {
            System.out.print("Select row(y-axis): ");
            int row = getColOrRow();
            System.out.print("Select column(x-axis): ");
            int column = getColOrRow();
            position = new Position(column, row);
            if(board.isMarked(position))
                System.out.println("Position already marked!");
            else break;
        }
        return position;
    }
 
    private static int getColOrRow()
    {
        int ret = -1;
        while(true)
        {
            try
            {
                ret = Integer.parseInt(in.nextLine());
            } 
            catch (NumberFormatException e){}
            
            if(ret < 0 | ret > (Board.DIM-1))
                System.out.print("\nIllegal input... please re-enter: ");
            else break;
        }
        return ret;
    }
 
	public static boolean getPlayerValue() 
	{
		return player;
	}
 
	public static void setPlayer(boolean player) 
	{
		TicTacToe.player = player;
	}
	
	public static PlayerSign getCurrentPlayer()
	{
		return player? PlayerSign.Cross:PlayerSign.Circle;
	}
}
 
enum PlayerSign
{
    Cross, Circle
}
 
enum GameState 
{
    Incomplete, CrossWin, CircleWin, Draw
}