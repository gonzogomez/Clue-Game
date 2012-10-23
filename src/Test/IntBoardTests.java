package Test;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import CluePath.IntBoard;


public class IntBoardTests {
	private IntBoard board;
	@Before
	public void boardSetUp(){
		board = new IntBoard();
	}

	@Test
	public void testcalcIndex() {
		int expected = 5;
		int actual = board.calcIndex(1,1);
		Assert.assertEquals(expected, actual);
		expected = 15;
		actual = board.calcIndex(3, 3);
		Assert.assertEquals(expected, actual);
		expected = 10;
		actual = board.calcIndex(2, 2);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testAdjacency0()
	{
		LinkedList testList = board.getAdjList(0);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency15()
	{
		LinkedList testList = board.getAdjList(15);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(14));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency7()
	{
		LinkedList testList = board.getAdjList(7);
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(3));
		Assert.assertTrue(testList.contains(6));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency8()
	{
		LinkedList testList = board.getAdjList(8);
		Assert.assertTrue(testList.contains(12));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(4));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency5()
	{
		LinkedList testList = board.getAdjList(5);
		Assert.assertTrue(testList.contains(4));
		Assert.assertTrue(testList.contains(9));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(1));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency10()
	{
		LinkedList testList = board.getAdjList(10);
		Assert.assertTrue(testList.contains(14));
		Assert.assertTrue(testList.contains(11));
		Assert.assertTrue(testList.contains(6));
		Assert.assertTrue(testList.contains(9));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testTargets0_3()
	{
		board.calcTargets(0, 3);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(4));
	}
	
	@Test
	public void testTargets4_2()
	{
		board.calcTargets(4, 2);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(12));
	}
	
	@Test
	public void testTargets10_1()
	{
		board.calcTargets(10, 1);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(6));
		Assert.assertTrue(targets.contains(11));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(9));
	}
	
	@Test
	public void testTargets5_4()
	{
		board.calcTargets(5, 4);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(8));
	}
	
	@Test
	public void testTargets9_5()
	{
		board.calcTargets(9, 5);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(7));
		Assert.assertTrue(targets.contains(10));
		Assert.assertTrue(targets.contains(2));
		Assert.assertTrue(targets.contains(15));
		Assert.assertTrue(targets.contains(8));
		Assert.assertTrue(targets.contains(0));
		Assert.assertTrue(targets.contains(13));
		Assert.assertTrue(targets.contains(5));
	}
	
	@Test
	public void testTargets6_6()
	{
		board.calcTargets(6, 6);
		TreeSet targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(12));
		Assert.assertTrue(targets.contains(9));
		Assert.assertTrue(targets.contains(1));
		Assert.assertTrue(targets.contains(4));
		Assert.assertTrue(targets.contains(14));
		Assert.assertTrue(targets.contains(3));
		Assert.assertTrue(targets.contains(11));
	}
	
}
