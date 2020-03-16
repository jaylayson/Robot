/**
 * 
 */
package package0;
import java.util.Scanner;
import package0.robot;
/**
 * @author Jahn
 *
 */
public class Main {
	

	//Initialise variables
	private static String input;
	private static final int MAX_XY_POS = 4;
	private static final int MIN_XY_POS = 0;
	static String[] direction_array = {"NORTH", "EAST", "SOUTH", "WEST"};
	static String[] commands = {"PLACE", "MOVE", "LEFT", "RIGHT", "REPORT"};
	public static robot myRobot = new robot(-1, -1, null); //made public for testcases.java access
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for(;;) {
			if(myRobot.getxPosition() == -1 && 			//if robot position is still -1 -1 (means robot not on board)
					myRobot.getyPosition() == -1) {
				System.out.println("Enter a PLACE command: "); //ask player to PLACE the robot
				input = scanner.nextLine();
				if(input.contains(commands[0])) { 		//if command contains "PLACE", try to execute place()
					try {
						place();						
					}catch(Exception e) {			
						continue;						
					}
				}else{
					System.out.println("Invalid command: You must first place the Robot!"); //any command that isn't PLACE
					continue;
				}
			}
			
			System.out.println("Enter a command: ");
			input = scanner.nextLine();
			
			//nested if statements to match input command to command array
			if(input.equals(commands[1])) {
				move();
			}else if(input.equals(commands[2])) {
				turnLeft();
			}else if(input.contains(commands[0])) {			//if command contains "PLACE", try to execute place()
				try {
					place();						//if the information with PLACE command is invalid
				}catch(Exception e) {				//handle the exception error
					System.out.println("Invalid PLACE arguments"); //prompt player for a new command
					continue;
				}
			}else if(input.equals(commands[3])) {
				turnRight();
			}else if(input.equals(commands[4])){
				report();
			}else{
				System.out.println("Invalid command.");
				continue;
			}
		}
	}
	
	/**
	 * Function to handle place commands.
	 * Checks if each input is valid first.
	 */
	public static void place() throws Exception{
		int x;
		int y;
		String direction = null;
		
		for(int i = 0; i < direction_array.length; ) {
			if(input.substring(input.lastIndexOf(",") + 1).toString().equals(direction_array[i])){
				direction = input.substring(input.lastIndexOf(",") + 1);
				break;
			}else{
				System.out.println("Invalid Direction.");
				throw new Exception("Invalid Direction.");
			}
		}
		
		x = Integer.parseInt(input.substring(input.indexOf(",") - 1, input.indexOf(",")));
		y = Integer.parseInt(input.substring(input.lastIndexOf(",") - 1, input.lastIndexOf(",")));
		if(x > MAX_XY_POS && y > MAX_XY_POS) {
			System.out.println("Invalid Coordinates.");
		}else if(x <= MAX_XY_POS && y <= MAX_XY_POS ) {
			myRobot.setxPosition(x);
			myRobot.setyPosition(y);
			myRobot.setDirection(direction);
		}
	}
	
	
	/**
	 * Prints the robot's fields.
	 * Takes advantage of overridden toString() in Robot.java
	 * Format: OUTPUT: X,Y,DIRECTION
	 */
	public static void report() {		
		System.out.println(myRobot.toString());
	}
	
	
	/**
	 * Moves the robot in the direction it's facing.
	 * Get direction, add or subtract from X and Y appropriately.
	 * Uses a boolean to enable/disable one step movement.
	 * Moving past X/Y:4 axis, shows appropriate error message.
	 */
	public static void move() {
		String dir = myRobot.getDirection();
		boolean moved = false;

		while(!moved) {				
				switch(dir) {
					case "NORTH":
						if(myRobot.getyPosition() < MAX_XY_POS) {
							myRobot.setyPosition(myRobot.getyPosition() + 1);
						}else if(myRobot.getyPosition() == MAX_XY_POS){
							System.out.println("You are at the edge of the board. Cannot move further NORTH!");
						}
						moved = !moved;
						break;
					case "EAST":
						if(myRobot.getxPosition() < MAX_XY_POS) {
							myRobot.setxPosition(myRobot.getxPosition() + 1);
						}else if(myRobot.getxPosition() == MAX_XY_POS){
							System.out.println("You are at the edge of the board. Cannot move further EAST!");
						}
						moved = !moved;
						break;
					case "SOUTH":
						if(myRobot.getyPosition() <= MAX_XY_POS && myRobot.getyPosition() >= 1) {
							myRobot.setyPosition(myRobot.getyPosition() - 1);
						}else if(myRobot.getyPosition() == MIN_XY_POS){
							System.out.println("You are at the edge of the board. Cannot move further SOUTH!");
						}
						moved = !moved;
						break;
					case "WEST":
						if(myRobot.getxPosition() <= MAX_XY_POS && myRobot.getxPosition() >= 1) {
							myRobot.setxPosition(myRobot.getxPosition() - 1);
						}else if(myRobot.getxPosition() == MIN_XY_POS){
							System.out.println("You are at the edge of the board. Cannot move further WEST!");
						}
						moved = !moved;
						break;
				}
			}
		}
	
	
	/**
	 * Matches the current direction in the array and then decrements index.
	 * Try-Catch block for when it goes out of index,
	 * the direction is set to the other end of the array.
	 */
	public static void turnLeft() {
		for(int i = 0; i<direction_array.length; i++) {
			if(direction_array[i].equals(myRobot.getDirection())) {
				try {
					myRobot.setDirection(direction_array[i-1]);
				}catch (Exception e){
					myRobot.setDirection(direction_array[3]);
					break;
				}
			}
		}
	}
	
	/**
	 * Matches the current direction in the array and then increments index.
	 * Try-Catch block for when it goes out of index,
	 * the direction is set to the other end of the array.
	 */
	public static void turnRight() {
		for(int i = 0; i<direction_array.length; i++) {
			if(direction_array[i].equals(myRobot.getDirection())) {
				try {
					myRobot.setDirection(direction_array[i+1]);
					break; //prevents a full loop. If i start north, the direction updates to east
							//BUT, this makes the next iteration true, therefore does another turn
							//to the right.
				}catch(Exception e){
					myRobot.setDirection(direction_array[0]);
					break;
				}
			}
		}
	}
}
