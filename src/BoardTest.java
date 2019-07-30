import static org.junit.jupiter.api.Assertions.*; 
import java.util.ArrayList; 
import org.junit.jupiter.api.Test;

class BoardTest 
{
	@Test
	public void testNewPosition() throws Exception
	{
		Board board = new Board();
		assertEquals(true,TicTacToe.getPlayerValue());
		assertEquals("\n[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]\n",board.toString());
	}
 
	@Test
	public void testMove() throws Exception
	{
		Board board=new Board("         ");
		Position position = new Position(0,0);
        board = new Board(board, position, PlayerSign.Cross);
		assertEquals(PlayerSign.Cross,TicTacToe.getCurrentPlayer());
        assertEquals("\n[x][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]\n",board.toString());
	}
	
	@Test
	public void testtoString() throws Exception 
	{
		Board board=new Board();
		assertEquals("\n[ ][ ][ ]\n[ ][ ][ ]\n[ ][ ][ ]\n",board.toString());
		Board board1=new Board("  x  x  x");
		assertEquals("\n[ ][ ][x]\n[ ][ ][x]\n[ ][ ][x]\n",board1.toString());
	}
	
	@Test 
	public void testhasWon() throws Exception
	{
		assertFalse(new Board().hasWon('x'));
		assertTrue(new Board("xxx      ").hasWon('x'));
		assertTrue(new Board("   xxx   ").hasWon('x'));
		assertTrue(new Board("      xxx").hasWon('x'));
		assertTrue(new Board("x   x   x").hasWon('x'));
		assertTrue(new Board("  x x x  ").hasWon('x'));
		assertTrue(new Board("x  x  x  ").hasWon('x'));
		assertTrue(new Board(" x  x  x ").hasWon('x'));
		assertTrue(new Board("  x  x  x").hasWon('x'));
	}
	
	@Test
	public void testgetFreePositions() throws Exception
	{
		ArrayList<Position> result = new ArrayList<Position>();
		result.add(new Position(2,2));
		assertEquals(new Board("xxxxxxxxe").getFreePositions().size() , 1);
		assertEquals(new Board("xxxexxxxe").getFreePositions().size() , 2);
		assertEquals(new Board("eeeeeeeeee").getFreePositions().size() , 9);
		assertEquals(new Board("xxxxxxxxx").getFreePositions().size() , 0);
	}
	
	@Test
	public void testisMarked() throws Exception
	{
		assertTrue(new Board("xxxxxxxxe").isMarked(new Position(0,0)));
		assertFalse(new Board("xxxxxxxxe").isMarked(new Position(2,2)));
		assertFalse(new Board("eeeeeeeee").isMarked(new Position(2,2)));
	}
	
	@Test
	public void testfindBestMove()
	{
		
	}
}
 