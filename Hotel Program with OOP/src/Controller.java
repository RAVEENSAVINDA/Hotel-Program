import java.io.Serializable;
import java.util.Scanner;

public class Controller implements Serializable{
	
	public void displayMenu() {
		// creating a Model object
		Model objM = new Model();
		
		Scanner input = new Scanner(System.in);
		//obj.loadFromFile();
		boolean repeatFlag = true;
		while (repeatFlag == true) {
			// The main menu
			System.out.println("____________________________________________");
			System.out.println("|       Welcome to Paradise Eve Hotel      |");
			System.out.println("|                   Menu                   |");
			System.out.println("============================================\n\n");
			System.out.println("V: Views All rooms");
			System.out.println("A: Add a customer to a room");
			System.out.println("D: Delete a customer from room");
			System.out.println("S: Store program array data into a plain text file");
			System.out.println("L: Load program data back from the file into the array");
			System.out.println("3: Display the names of the first 3 customers");
			System.out.println("   who have been allocated to a room");
			System.out.println("X: Exit \n\n");
			System.out.print("Please enter your selection: ");
			try {
				String usrInput = input.nextLine();
				if (usrInput.length() == 1) {
					// Getting first character from user input
					char userInput = usrInput.charAt(0);
					// Both upper and lower case characters are 
					// allowed for a particular selection
					switch (userInput) {
					case 'v':
					case 'V':
						System.out.println("----View Rooms----\n");
						objM.viewRooms();
						break;
					case 'a':
					case 'A':
						objM.addCustomer();
						break;
					case 'd':
					case 'D':
						objM.deleteCustomer();
						break;
					case 's':
					case 'S':
						objM.storeInFile();
						break;
					case 'l':
					case 'L':
						objM.loadFromFile();
						break;
					case '3':
						objM.displayQueue();
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
					System.out.println("! Invalid Input. Input should contain only one character !");
				}

			} catch (Exception e) {
				System.out.println();
				e.printStackTrace();
			}
		}
	}
}
