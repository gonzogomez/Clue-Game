package clueBoardTest;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.RoomCell;

import org.junit.Test;

public class clueGameTests {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 30;
	
	@BeforeClass
	public static void setUp(){
		board = new Board();
	}
	
	@Test
	public void testRooms() {
		Map<Character, String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Library", rooms.get('L'));
		assertEquals("Conservatory", rooms.get('C'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Study", rooms.get('S'));
		assertEquals("Walkway", rooms.get('W'));
	}
	
	@Test
	public void testBoardDimensions(){
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void FourDoorDirections(){
		RoomCell room = board.getRoomCellAt(4, 3);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(4, 14);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(15, 3);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(12, 25);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		
		room = board.getRoomCellAt(0, 0);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCells().get(board.calcIndex(0, 6));
		assertFalse(cell.isDoorway());	
	}
	
	@Test
	public void testNumberOfDoorways(){
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(660, totalCells);
		for (int i=0; i<totalCells; i++)
		{
			BoardCell cell = board.getCells().get(i);
			if (cell.isDoorway()){
				numDoors++;
			}
		}
		Assert.assertEquals(20, numDoors);
	}
	
	@Test
	public void testCalcIndex() {
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(NUM_COLUMNS-1, board.calcIndex(0, NUM_COLUMNS-1));
		assertEquals(630, board.calcIndex(NUM_ROWS-1, 0));
		assertEquals(659, board.calcIndex(NUM_ROWS-1, NUM_COLUMNS-1));
		// Test a couple others
		assertEquals(31, board.calcIndex(1, 1));
		assertEquals(80, board.calcIndex(2, 20));
	}
	
	@Test
	public void testRoomInitials() {
		assertEquals('L', board.getRoomCellAt(0, 0).getRoomInitial());
		assertEquals('C', board.getRoomCellAt(3, 10).getRoomInitial());
		assertEquals('K', board.getRoomCellAt(9, 0).getRoomInitial());
		assertEquals('H', board.getRoomCellAt(21, 22).getRoomInitial());
		assertEquals('D', board.getRoomCellAt(21, 0).getRoomInitial());
	}
}
