package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import clueGame.RoomCell.DoorDirection;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	public static int GRID_PIECES;
	private Set<BoardCell> targets;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	
	public Board() {
		super();
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		rooms = new TreeMap<Character, String>();
		cells = new ArrayList<BoardCell>();
		targets = new HashSet<BoardCell>();
		loadConfigFiles();
		GRID_PIECES = numRows * numColumns;
		visited = new boolean[GRID_PIECES];
		calcAdjacencies();
	}

	public void loadConfigFiles(){
		try{
			loadConfigFilesHelperLegend("room_legend_file.txt");
			loadConfigFilesHelperFormat("config_file.csv");
		}
		catch(BadConfigFormatException e){
			System.out.println(e);
		}
		
	}
	
	public void loadConfigFilesHelperLegend(String filename) throws BadConfigFormatException{
		try{
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);
			while (in.hasNextLine()){
				String line = in.nextLine();
				char letterRoom = line.charAt(0);
				if(line.indexOf(',') == -1){
					throw new BadConfigFormatException(line);
				}
				if(letterRoom != 'C' && letterRoom != 'K' && letterRoom != 'B' && letterRoom != 'R' && letterRoom != 'L' && letterRoom != 'S' && letterRoom != 'D' && letterRoom != 'O' && letterRoom != 'H' && letterRoom != 'X' && letterRoom != 'W'){
					throw new BadConfigFormatException(line);
				}
				String room = line.substring(3);
				rooms.put(letterRoom, room);
			}
			in.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Can't open file: " + e.getMessage());
		}
	}
	
	public void loadConfigFilesHelperFormat(String filename) throws BadConfigFormatException{
		try{
			int columns = 0;
			int rows = 0;
			FileReader reader = new FileReader(filename);
			Scanner in = new Scanner(reader);
			while(in.hasNextLine()){
				String[] roomCells;
				String line = in.nextLine();
				roomCells = line.split(",");
				columns = roomCells.length;
				rows++;
				for(int i=0; i < roomCells.length; i++){
					if(roomCells[i].equals("W")){
						WalkwayCell walk = new WalkwayCell();
						cells.add(walk);
					}
					else{
						RoomCell room = new RoomCell(roomCells[i]);
						cells.add(room);
					}
				}
			}
			numColumns = columns;
			numRows = rows;
		}
		catch(FileNotFoundException e){
			System.out.println("Can't open file: " + e.getMessage());
		}
	}
	
	public int calcIndex(int rowNum, int columnNum){
		int index;
		index = numColumns*rowNum + columnNum;
		return index;
	}
	
	public void calcAdjacencies(){
		for(int x = 0; x < numRows; x++){
			for(int y =0; y < numColumns; y++){
				int index = calcIndex(x,y);
				LinkedList<Integer> list = new LinkedList<Integer>();
				//If board cell is a doorway
				if(cells.get(index).isDoorway() == true){
					RoomCell roomCell = (RoomCell) cells.get(index);
					//UP
					if(roomCell.getDoorDirection() == DoorDirection.UP){
						int value = calcIndex(x-1, y);
						list.add(value);
					}
					//DOWN
					else if(roomCell.getDoorDirection() == DoorDirection.DOWN){
						int value = calcIndex(x+1, y);
						list.add(value);
					}
					//LEFT
					else if(roomCell.getDoorDirection() == DoorDirection.LEFT){
						int value = calcIndex(x, y-1);
						list.add(value);
					}
					//RIGHT
					else{
						int value = calcIndex(x, y+1);
						list.add(value);
					}
				}
				//If board cell is a walkway
				if(cells.get(index).isWalkway() == true){
					//Not on edge of board
					if(x != 0){
						//If the board cell above it is a walkway
						if(cells.get(calcIndex(x-1, y)).isWalkway() == true){
							int value = calcIndex(x-1, y);
							list.add(value);
						}
						//If the board cell above it is a doorway with direction down
						if(cells.get(calcIndex(x-1, y)).isDoorway() == true){
							RoomCell roomcell = (RoomCell) cells.get(calcIndex(x-1, y));
							if(roomcell.getDoorDirection() == DoorDirection.DOWN){
								int value = calcIndex(x-1, y);
								list.add(value);
							}
						}
					}
					//Not on edge of board
					if(y != 0){
						//If the board cell to the left of it is a walkway
						if(cells.get(calcIndex(x, y-1)).isWalkway() == true){
							int value = calcIndex(x, y-1);
							list.add(value);
						}
						//If the board cell to the left of it is a doorway with direction right
						if(cells.get(calcIndex(x, y-1)).isDoorway() == true){
							RoomCell roomcell = (RoomCell) cells.get(calcIndex(x, y-1));
							if(roomcell.getDoorDirection() == DoorDirection.RIGHT){
								int value = calcIndex(x, y-1);
								list.add(value);
							}
						}
					}
					//Not on the edge of the board
					if(x != numRows -1){
						//If the board cell below it is a walkway
						if(cells.get(calcIndex(x+1, y)).isWalkway() == true){
							int value = calcIndex(x+1, y);
							list.add(value);
						}
						//If the board cell below it is a doorway with direction up
						if(cells.get(calcIndex(x+1, y)).isDoorway() == true){
							RoomCell roomcell = (RoomCell) cells.get(calcIndex(x+1, y));
							if(roomcell.getDoorDirection() == DoorDirection.UP){
								int value = calcIndex(x+1, y);
								list.add(value);
							}
						}
					}
					//Not on the edge of the board
					if(y != numColumns -1){
						//If the board cell to the right of it is a walkway
						if(cells.get(calcIndex(x, y+1)).isWalkway() == true){
							int value = calcIndex(x, y+1);
							list.add(value);
						}
						//If the board cell to the right of it is a doorway with direction left
						if(cells.get(calcIndex(x, y+1)).isDoorway() == true){
							RoomCell roomcell = (RoomCell) cells.get(calcIndex(x, y+1));
							if(roomcell.getDoorDirection() == DoorDirection.LEFT){
								int value = calcIndex(x, y+1);
								list.add(value);
							}
						}
					}
				}
				adjMtx.put(index, list);
			}
		}
	}
	
	public void calcTargets(int startLocation, int steps){
		targets = new HashSet<BoardCell>();
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
				if(path.size() == steps || cells.get(vertex).isDoorway() == true){
					targets.add(cells.get(vertex));
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
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(int cell){
		LinkedList<Integer> list = new LinkedList<Integer>();
		list = adjMtx.get(cell);
		return list;
	}
	
	public RoomCell getRoomCellAt(int row, int col){
		int index = calcIndex(row, col);
		RoomCell room = new RoomCell();
		room = (RoomCell) cells.get(index);
		return room;
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<BoardCell> cells) {
		this.cells = cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public void setRooms(Map<Character, String> rooms) {
		this.rooms = rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}
}
