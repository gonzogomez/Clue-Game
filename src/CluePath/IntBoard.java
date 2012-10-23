package CluePath;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class IntBoard {
	public static int ROWS;
	public static int COLS;
	public static int GRID_PIECES;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	public TreeSet targets;
	
	public IntBoard() {
		super();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		targets = new TreeSet<Integer>();
		ROWS = 4;
		COLS = 4;
		GRID_PIECES = 16;
		visited = new boolean[GRID_PIECES];
		calcAdjacencies();
	}
	
	public void calcAdjacencies(){
		for(int x = 0; x < ROWS; x++){
			for(int y =0; y < COLS; y++){
				int index = calcIndex(x,y);
				LinkedList<Integer> list = new LinkedList<Integer>();
				if(x != 0){
					int value = calcIndex(x-1, y);
					list.add(value);
				}
				if(y != 0){
					int value = calcIndex(x, y-1);
					list.add(value);
				}
				if(x != ROWS-1){
					int value = calcIndex(x+1, y);
					list.add(value);
				}
				if(y != COLS-1){
					int value = calcIndex(x, y+1);
					list.add(value);
				}
				adjMtx.put(index, list);
			}
		}
	}
	
	public void calcTargets(int startLocation, int steps){
		for(int i = 0; i < GRID_PIECES; i++){
			visited[i] = false;
		}
		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		visited[startLocation] = true;
		list = getAdjList(startLocation);
		calcTargetsHelper(path, list, steps);
	}
	
	public void calcTargetsHelper(LinkedList<Integer> path, LinkedList<Integer> list, int steps){
		for(int vertex: list){
			if(visited[vertex] == false){
				visited[vertex] = true;
				path.addLast(vertex);
				if(path.size() == steps){
					targets.add(vertex);
				}
				else{
					LinkedList<Integer> adjacent = new LinkedList<Integer>();
					adjacent = getAdjList(vertex);
					calcTargetsHelper(path, adjacent, steps);
				}
				path.removeLast();
				visited[vertex] = false;
			}
		}
	}
	
	public TreeSet getTargets(){
		return targets;
	}
	
	public LinkedList getAdjList(int cell){
		LinkedList list = new LinkedList();
		list = adjMtx.get(cell);
		return list;
	}
	
	public int calcIndex(int row, int column){
		int index;
		index = ROWS*row + column;
		return index;
	}
	
}
