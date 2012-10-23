package TestPaths;


import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardPathTests {
	private static Board board;
	
	@BeforeClass
	public static void setUp(){
		board = new Board();
	}
	
	@Test
	public void testAdjacenciesInsideRooms()
	{
		//Test a corner
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(0, 0));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(board.calcIndex(4, 0));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(board.calcIndex(14, 25));
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(board.calcIndex(17, 14));
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(board.calcIndex(12, 28));
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(board.calcIndex(4, 23));
		Assert.assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 3));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(4, 4)));
		// TEST DOORWAY LEFT
		testList = board.getAdjList(board.calcIndex(20, 12));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(20, 11)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(board.calcIndex(12, 25));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(13, 25)));
		//TEST DOORWAY UP
		testList = board.getAdjList(board.calcIndex(6, 11));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(5, 11)));
	}
	
	// Test a variety of walkway scenarios
		@Test
		public void testAdjacencyWalkways()
		{
			// Test on top edge of board, just one walkway piece
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(0, 4));
			Assert.assertTrue(testList.contains(5));
			Assert.assertEquals(1, testList.size());
			
			// Test on left edge of board, three walkway pieces
			testList = board.getAdjList(board.calcIndex(6, 0));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 0)));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 1)));
			Assert.assertTrue(testList.contains(board.calcIndex(7, 0)));
			Assert.assertEquals(3, testList.size());

			// Test between two rooms, walkways right and left
			testList = board.getAdjList(board.calcIndex(13, 22));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 21)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 23)));
			Assert.assertEquals(2, testList.size());

			// Test surrounded by 4 walkways
			testList = board.getAdjList(board.calcIndex(7,6));
			Assert.assertTrue(testList.contains(board.calcIndex(6, 6)));
			Assert.assertTrue(testList.contains(board.calcIndex(8, 6)));
			Assert.assertTrue(testList.contains(board.calcIndex(7, 7)));
			Assert.assertTrue(testList.contains(board.calcIndex(7, 5)));
			Assert.assertEquals(4, testList.size());
			
			// Test on bottom edge of board, next to 1 room piece
			testList = board.getAdjList(board.calcIndex(21, 17));
			Assert.assertTrue(testList.contains(board.calcIndex(21, 18)));
			Assert.assertTrue(testList.contains(board.calcIndex(20, 17)));
			Assert.assertEquals(2, testList.size());
			
			// Test on ridge edge of board, next to 1 room piece
			testList = board.getAdjList(board.calcIndex(19, 24));
			Assert.assertTrue(testList.contains(board.calcIndex(18, 24)));
			Assert.assertTrue(testList.contains(board.calcIndex(19, 23)));
			Assert.assertEquals(2, testList.size());
		}
		
		// Test adjacency at entrance to rooms
		@Test
		public void testAdjacencyDoorways()
		{
			// Test beside a door direction RIGHT
			LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 4));
			Assert.assertTrue(testList.contains(board.calcIndex(4, 3)));
			Assert.assertTrue(testList.contains(board.calcIndex(4, 5)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 4)));
			Assert.assertEquals(3, testList.size());
			// Test beside a door direction DOWN
			testList = board.getAdjList(board.calcIndex(13, 10));
			Assert.assertTrue(testList.contains(board.calcIndex(12, 10)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 11)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 9)));
			Assert.assertEquals(3, testList.size());
			// Test beside a door direction LEFT
			testList = board.getAdjList(board.calcIndex(4, 19));
			Assert.assertTrue(testList.contains(board.calcIndex(4, 20)));
			Assert.assertTrue(testList.contains(board.calcIndex(4, 18)));
			Assert.assertTrue(testList.contains(board.calcIndex(5, 19)));
			Assert.assertEquals(3, testList.size());
			// Test beside a door direction UP
			testList = board.getAdjList(board.calcIndex(14, 4));
			Assert.assertTrue(testList.contains(board.calcIndex(15, 4)));
			Assert.assertTrue(testList.contains(board.calcIndex(14, 5)));
			Assert.assertTrue(testList.contains(board.calcIndex(14, 3)));
			Assert.assertTrue(testList.contains(board.calcIndex(13, 4)));
			Assert.assertEquals(4, testList.size());
		}
		
		// Tests of just walkways, 1 step, includes on edge of board
		// and beside room
		// Have already tested adjacency lists on all four edges, will
		// only test two edges here
		@Test
		public void testTargetsOneStep() {
			board.calcTargets(board.calcIndex(0, 8), 1);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(2, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(0, 7))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(1, 8))));	
			
			board.calcTargets(board.calcIndex(14, 0), 1);
			targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 1))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 0))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(15, 0))));			
		}
		
		// Tests of just walkways, 2 steps
		@Test
		public void testTargetsTwoSteps() {
			board.calcTargets(board.calcIndex(9, 13), 2);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(9, 15))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(7, 13))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(8, 14))));
			
			board.calcTargets(board.calcIndex(10, 21), 2);
			targets= board.getTargets();
			Assert.assertEquals(3, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(10, 19))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(9, 20))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(11, 20))));			
		}
		
		// Tests of just walkways, 4 steps
		@Test
		public void testTargetsFourSteps() {
			board.calcTargets(board.calcIndex(9, 13), 4);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(10, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(5, 13))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(9, 17))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(11, 15))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(10, 16))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(8, 16))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(8, 14))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(7, 15))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(6, 14))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(9, 15))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(7, 13))));
			
			board.calcTargets(board.calcIndex(14, 0), 4);
			targets= board.getTargets();
			Assert.assertEquals(6, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(18, 0))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 4))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 2))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 3))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 1))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(15, 3))));
		}
		
		// Tests of just walkways plus one door, 6 steps and door that doesn't require all steps
		@Test
		public void testTargetsSixSteps() {
			board.calcTargets(board.calcIndex(15, 0), 6);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(8, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(15, 3))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(15, 4))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 5))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 2))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 4))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 3))));	
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(14, 1))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(13, 0))));
		}
		
		// Test getting into a room
		@Test 
		public void testTargetsIntoRoom()
		{
			// One room is exactly 2 away
			board.calcTargets(board.calcIndex(20, 19), 2);
			Set<BoardCell> targets= board.getTargets();
			Assert.assertEquals(7, targets.size());
			// directly left
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(20, 17))));
			// directly up can't go down
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(18, 19))));
			// directly right
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(20, 21))));
			// one up/down, one left/right
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(21, 20))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(21, 18))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(19, 18))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(19, 20))));
		}
		
		// Test getting out of a room
		@Test
		public void testRoomExit()
		{
			// Take one step, essentially just the adj list
			board.calcTargets(board.calcIndex(4, 20), 1);
			Set<BoardCell> targets= board.getTargets();
			// Ensure doesn't exit through the wall
			Assert.assertEquals(1, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(4, 19))));
			// Take two steps
			board.calcTargets(board.calcIndex(4, 20), 2);
			targets= board.getTargets();
			Assert.assertEquals(2, targets.size());
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(4, 18))));
			Assert.assertTrue(targets.contains(board.getCells().get(board.calcIndex(5, 19))));
		}

}
