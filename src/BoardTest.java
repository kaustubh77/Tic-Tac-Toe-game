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
	
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testPossibleMoves() throws Exception
//	{
//		Board board=new Board();
//		ArrayList<Position> list=new ArrayList<>();
//		for(int i=0;i<Position.SIZE;i++)
//			list.add(new Position());
//		list.remove(new Integer(1));
//		list.remove(new Integer(2));
//		assertEquals(list,position.move(1).move(2).PossibleMoves());
//
//		/* We are placing an 'x' and 'o' at index 1 and 2
//		 * therefore all indexes apart from 1 and 2 will be
//		 * unoccupied and therefore will be all returned in a
//		 * list by the method PossibleMoves()
//		 */
//	}
	
	
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
 