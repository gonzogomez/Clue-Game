package clueGame;

public abstract class BoardCell {
	private int row;
	private int column;
	private char cellType;
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	//TODO add abstract method name draw
}
