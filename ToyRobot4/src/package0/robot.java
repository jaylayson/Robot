package package0;
/**
 * 
 */

/**
 * @author Jahn
 *
 */
public class robot {
	
	//Initialise class variables
	private int xPosition;
	private int yPosition;
	private String direction;
	
	/**
	 * Robot object constructor. 
	 */
	public robot(int xPos, int yPos, String direction) {
		this.xPosition = xPos;
		this.yPosition = yPos;
		this.direction = direction;
	}
	
	
	/*
	 * get X axis position of the robot
	 */
	public int getxPosition() {
		return xPosition;
	}
	
	/*
	 * set X axis position of the robot
	 */
	public void setxPosition(int xPos) {
		this.xPosition = xPos;
	}
	
	/*
	 * get Y axis position of the robot
	 */
	public int getyPosition() {
		return yPosition;
	}
	
	/*
	 * set Y axis position of the robot
	 */
	public void setyPosition(int yPos) {
		this.yPosition = yPos;
	}
	
	/*
	 * get direction of the robot.
	 */
	public String getDirection() {
		return direction;
	}
	
	/*
	 * set direction of the robot.
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	/*
	 * collect robot attributes for a clean formatted printing.
	 */
	@Override
	public String toString() {
		String robotLocation = "OUTPUT: " + getxPosition() + "," +getyPosition()+ "," + getDirection();
		return robotLocation;
	}
	
}
