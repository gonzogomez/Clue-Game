package clueGame;

public class BadConfigFormatException extends Exception {
	
	private String name;
	//1- parameter constructor
	public BadConfigFormatException(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " invalid format";
	}
}
