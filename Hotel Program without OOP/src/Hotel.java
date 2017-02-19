import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel{
	private Scanner input = new Scanner(System.in);
	// Array declaration for 10 rooms
	String[][] roomArray = new String[10][3];
	
	//Displays the menu and controls user choice
	public void displayMenu(){
		//obj.loadFromFile();
		boolean repeatFlag = true;
		while (repeatFlag == true) {
			// The main menu
			System.out.println("_______________________________");
			System.out.println("|  Welcome to Paradise Hotel  |");
			System.out.println("|             Menu            |");
			System.out.println("===============================\n\n");
			System.out.println("V: Views All rooms");
			System.out.println("A: Add customer to a room");
			System.out.println("E: Display empty rooms");
			System.out.println("D: Delete customer from room");
			System.out.println("F: Find room from customer name");
			System.out.println("S: Store program array data into a plain text file");
			System.out.println("L: Load program data back from the file into the array");
			System.out.println("O: View rooms Ordered alphabetically by name");
			System.out.println("X: Exit \n\n");
			System.out.print("Please enter your selection: ");
			try {
				String usrInput = input.nextLine();
				if (usrInput.length() == 1) {
					// Getting the first character in the user input
					char userInput = usrInput.charAt(0);
					// Both upper and lower case characters are allowed for a
					// particular selection
					switch (userInput) {
					case 'v':
					case 'V':
						System.out.println("---View Rooms---\n");
						viewRooms();
						break;
					case 'a':
					case 'A':
						System.out.println("---Add Customer---\n");
						addCustomer();
						break;
					case 'e':
					case 'E':
						System.out.println("---Display Empty Rooms---\n");
						displayEmptyRooms();
						break;
					case 'd':
					case 'D':
						System.out.println("---Delete Customer---\n");
						deleteCustomer();
						break;
					case 'f':
					case 'F':
						System.out.println("---Find room by name---\n");
						findRoomByName();
						break;
					case 's':
					case 'S':
						storeInFile();
						break;
					case 'l':
					case 'L':
						loadFromFile();
						break;
					case 'o':
					case 'O':
						System.out.println("---Sort rooms by name---\n");
						alphabaticSort();
						break;
					case 'x':
					case 'X':
						System.out.println("---Program End---");
						System.exit(0);
						break;
					default:
						System.out.println("Invalid input");
						break;
					}
				} else {
					System.out.println("!!! Invalid Input. Input should contain only one character !!!");
				}

			} catch (Exception e) {
				System.out.println();
				e.printStackTrace();
			}

		}
	}
	//initializing roomArray
	public void initialiseArray() {
		for (int i = 0; i < roomArray.length; i++) {
			roomArray[i][0]=""+i;
			roomArray[i][1]="empty";
			roomArray[i][2]="empty";
		}
	}

	// method which display room status
	public void viewRooms() {
		for (int i = 0; i < roomArray.length; i++) {
			if (roomArray[i][1].equals("empty")) {
				System.out.println("Room " + (i + 1) + " is empty");
			} else {
				System.out.println("Room " + (i + 1) + " is occupied by " + roomArray[i][1] + " "
						+ roomArray[i][2]);
			}
		}
	}

	// method which validates the room number
	public int rooomNoValidator() {
		int roomNum = 0;
		boolean flag1 = true;
		while (flag1 == true) {
			try {
				System.out.print("Enter room number (1-10): ");
				roomNum = Integer.parseInt(input.nextLine());
				System.out.println();
				if (roomNum > 0 && roomNum < roomArray.length) {
					flag1 = false;
				} else {
					System.out.println("Room number should be between 1-10");
				}
			} catch (Exception e1) {
				// e1.printStackTrace();
				System.out.println("Please enter a valid room number");
			}
		}
		return roomNum;
	}

	// method which validates the user entered name
		public String nameValidator(String namePart) {
			String name = "0";
			boolean flag2 = true;
			Pattern p = Pattern.compile("[a-zA-Z]+$"); //("[a-zA-Z]*$") ("[a-zA-Z]*$") ^[a-zA-Z]+[\s][a-zA-Z]+$

			while (flag2 == true) {
				try {
					System.out.print("Enter Customer's " + namePart + " name:");
					name = input.nextLine();
					System.out.println();
					Matcher m = p.matcher(name);
					if (m.matches()) {
						flag2 = false;
					} else {
						System.out.println("Please enter a valid " + namePart + " name");
					}

				} catch (Exception e2) {
					// e2.printStackTrace();
					System.out.println("Please enter a valid " + namePart + " name");
				}
			}
			return name;
		}

	// method which adds a customer to a room
	public void addCustomer() {
		int roomNum = rooomNoValidator();
		if (roomArray[roomNum - 1][1].equalsIgnoreCase("empty")) {
			String fName = nameValidator("first");
			String lName = nameValidator("last");
			roomArray[roomNum - 1][1]=fName;
			roomArray[roomNum - 1][2]=lName;
			System.out.println("Customer successfully added to room " + roomNum);
		} else {
			System.out.println("A customer already occupied in this room");
		}
	}

	// method which displays empty rooms
	public void displayEmptyRooms() {
		for (int i = 0; i < roomArray.length; i++) {
			if (roomArray[i][1].equalsIgnoreCase("empty"))
				System.out.println("room " + (i + 1) + " is empty");
		}
	}

	// method which removes a customer from a room
	public void deleteCustomer() {
		int roomNum = rooomNoValidator();
		if (roomArray[roomNum - 1][1].equals("empty")) {
			System.out.println("Room " + (roomNum) + " is already empty");
		} else {
			roomArray[roomNum - 1][1]="empty";
			roomArray[roomNum - 1][2]="empty";
			System.out.println("Successfully deleted Customer from Room " + (roomNum));
		}
	}

	// method to find a room by a given name
	public void findRoomByName() {
		String fName = nameValidator("first");
		String lName = nameValidator("last");
		String tempName = fName + lName;
		boolean found = false;
		for (int i = 0; i < roomArray.length; i++) {

			if ((roomArray[i][1] + roomArray[i][2]).equalsIgnoreCase(tempName)) {
				found = true;
				System.out.println("Customer " + fName + " " + lName + " is occupied in room " + (i + 1));
			} else {
			}
		}
		if (found == false) {
			System.out.println("!!! No match found !!!");
		} else {
		}
	}

	// storing room details in hotelDetails.txt file
	public void storeInFile() {
		try {
			PrintWriter opf = new PrintWriter("hotelDetails.txt");
			for (int i = 0; i < roomArray.length; i++) {
				opf.println((i + 1) + "\t" + roomArray[i][1] + "\t" + roomArray[i][2]);
			}
			opf.close();
			System.out.println("Successfully saved data to the file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// loading room details from hotelDetails.txt file
	public void loadFromFile() {
		try {
			FileReader fr = new FileReader("hotelDetails.txt");
			Scanner sc = new Scanner(fr);
			int count = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] data = line.split("\t");
				roomArray[count][1]=data[1];
				roomArray[count][2]=data[2];
				count += 1;
			}
			fr.close();
			sc.close();
			System.out.println("Successfully loaded data from file");
		} catch (IOException e) {
			System.out.println("!!! File not found !!!");
		}
	}

	public void alphabaticSort() {
		String[][] sortArray=new String[10][3];
		sortArray=roomArray;
		for (int i = 0; i < sortArray.length- 1; i++) {
			boolean isSorted=true;
			//System.out.println("Traversal " + (i + 1));
			for (int j = 0; j < (sortArray.length - 1) - i; j++) {
				// comparing first name of customers in the array list
				if ((sortArray[j][1]).compareToIgnoreCase(sortArray[j+1][1]) > 0) {
					String temp1 = sortArray[j][1];
					sortArray[j][1]=sortArray[j+1][1];
					sortArray[j+1][1]=temp1;

					String temp2 = sortArray[j][2];
					sortArray[j][2]=sortArray[j+1][2];
					sortArray[j+1][2]=temp2;
					
					String temp3 = sortArray[j][0];
					sortArray[j][0]=sortArray[j+1][0];
					sortArray[j+1][0]=temp3;
					
					isSorted=false;

				} else if ((sortArray[j][1]).compareToIgnoreCase(sortArray[j+1][1]) == 0) {
					// comparing last name of customers in the array list if
					// first names are same
					if ((sortArray[j][2]).compareToIgnoreCase(sortArray[j+1][2]) > 0) {
						String temp1 = sortArray[j][1];
						sortArray[j][1]=sortArray[j+1][1];
						sortArray[j+1][1]=temp1;

						String temp2 = sortArray[j][2];
						sortArray[j][2]=sortArray[j+1][2];
						sortArray[j+1][2]=temp2;
						
						String temp3 = sortArray[j][0];
						sortArray[j][0]=sortArray[j+1][0];
						sortArray[j+1][0]=temp3;
						
						isSorted=false;
					} else {
					}
				} else {
				}
			}
			if(isSorted) break;
		}
		System.out.println("Rooms Ordered alphabetically by name");
		System.out.println("Room number   Customer Name");
		for (int i = 0; i < sortArray.length; i++) {
			if(!(roomArray[i][1].equalsIgnoreCase("empty"))){
				System.out.println(
						"     " + (Integer.parseInt(roomArray[i][0])+1) + "         " + roomArray[i][1] + " " + roomArray[i][2]);
			}else{}
		}
	}
}
