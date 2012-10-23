package clueGame;

public class RoomCell extends BoardCell {
	public enum DoorDirection { UP, DOWN, LEFT, RIGHT, NONE };
	private DoorDirection doorDirection;
	private char roomInitial;
	
	
	public RoomCell() {
		super();
	}

	public RoomCell(String room) {
		super();
		roomInitial = room.charAt(0);
		if(room.length() == 2){
			char direction = room.charAt(1);
			if(direction == 'U'){
				doorDirection = DoorDirection.UP;
			}
			else if(direction == 'D'){
				doorDirection = DoorDirection.DOWN;
			}
			else if(direction == 'L'){
				doorDirection = DoorDirection.LEFT;
			}
			else{
				doorDirection = DoorDirection.RIGHT;
			}
		}
		else{
			doorDirection = DoorDirection.NONE;
		}
	}

	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		if(doorDirection == DoorDirection.NONE){
			return false;
		}
		
		else{
			return true;
		}
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public void setDoorDirection(DoorDirection doorDirection) {
		this.doorDirection = doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}

	public void setRoomInitial(char roomInitial) {
		this.roomInitial = roomInitial;
	}
	
	//TODO override the draw method
}
