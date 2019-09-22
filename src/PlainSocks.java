
/*Nina DeSouza
 * May 19, 2017
 * This program lets the user create a custom sock pattern.
 * Input: Information about yarn, needles, gauge, and foot measurements
 * Output: A new .txt file containing the customized sock pattern.*/
import java.util.HashMap;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;

public class PlainSocks {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to the sock pattern generator");
		System.out.println("Please enter the following information:");
		//TODO: Sort out how to ask the user where to save the file
		System.out.print("Output File Name:");
		PrintStream fileToWrite = new PrintStream(new File(console.nextLine()));
		Foot person = new Foot();
		printHeader(fileToWrite, console, person);
		File[] styleChoices = getStyleChoices(fileToWrite, console);
		printPattern(fileToWrite, person, styleChoices);
		System.out.print("Would you like to view the pattern now? Type Y for yes, or any letter for no: ");
		if (console.hasNext() && console.next().equalsIgnoreCase("Y")) {
			// open the file
		}
		console.close();
	}

	//This method gathers initial information from the user and prints it to the custom pattern file
	//Variable values are stored in a foot object called "person"
	public static void printHeader(PrintStream fileToWrite, Scanner console, Foot person) {
		// change this to read from the file and print to the console when it
		// comes across a variable,
		// then print that to the new file and save the value if necessary
		// possibly eliminate the foot class entirely and store all the variables in the hashmap
		System.out.print("Recipient of socks: ");
		fileToWrite.println(console.nextLine() + "'s Socks");
		fileToWrite.println();
		System.out.print("Yarn name, brand, and weight: ");
		fileToWrite.println("Yarn: " + console.nextLine());
		System.out.println("Please enter the following values as single decimal numbers greater than 0. E.g., 4.5");
		System.out.print("Needle Size: ");
		person.needleSize = getNextDouble(console);
		fileToWrite.println("Needle Size: " + person.needleSize);
		System.out.print("Gauge (sts/in): ");
		person.gaugeSts = getNextDouble(console);
		fileToWrite.println("Gauge (sts/in): " + person.gaugeSts);
		System.out.print("Gauge (rows/in): ");
		person.gaugeRows = getNextDouble(console);
		fileToWrite.println("Gauge (rows/in): " + person.gaugeRows);
		System.out.print("Foot Circumference (in): ");
		person.circumference = getNextDouble(console);
		fileToWrite.println("Foot Circumference: " + person.circumference);
		System.out.print("Foot Length (in): ");
		person.length = getNextDouble(console);
		fileToWrite.println("Foot Length: " + person.length);
		System.out.print("Instep (in): ");
		person.instep = getNextDouble(console);
		fileToWrite.println("Instep: " + person.instep);
		fileToWrite.println();
	}

	//This method makes an array of all the sock component pattern files
	public static File[] getStyleChoices(PrintStream fileToWrite, Scanner console) {
		File[] styleChoices = new File[7];
		styleChoices[0] = getStyle(fileToWrite, console, "Cuff style", "defaultCuff.txt");
		styleChoices[1] = getStyle(fileToWrite, console, "Leg pattern", "defaultLeg.txt");
		styleChoices[2] = getStyle(fileToWrite, console, "Heel flap", "defaultHeelFlap.txt");
		styleChoices[3] = getStyle(fileToWrite, console, "Heel turn", "defaultHeelTurn.txt");
		styleChoices[4] = getStyle(fileToWrite, console, "Gusset", "defaultGusset.txt");
		styleChoices[5] = getStyle(fileToWrite, console, "Foot", "defaultFoot.txt");
		styleChoices[6] = getStyle(fileToWrite, console, "Toe", "defaultToe.txt");
		return styleChoices;
	}

	//This method returns a pattern file for a given sock part. The user can specify either the default pattern or a custom one
	public static File getStyle(PrintStream fileToWrite, Scanner console, String sockPart, String defaultFileName) {
		File styleChoice;
		String fileName;
		System.out.println("Please enter a file name for " + sockPart + "or \"default\" for standard sock pattern: ");
		//Make sure the file is valid; convert "default" to the specific default file if applicable
		do {
			fileName = console.nextLine();
			if (fileName.equalsIgnoreCase("default")) {
				styleChoice = new File(defaultFileName);
			} else {
				styleChoice = new File(fileName);
				if (!styleChoice.exists() || !styleChoice.canRead()) {
					System.out.print("File not found. Please try again: ");
				}
			}
		} while (!styleChoice.exists() || !styleChoice.canRead());
		//Document which modular pattern files are used for this custom pattern
		fileToWrite.println(sockPart + ": " + fileName);
		return styleChoice;
	}

	//This method ensures that values are entered as doubles, so that they can be used later
	public static double getNextDouble(Scanner console) {
		double nextDouble = 0;
		do {
			//First make sure the user typed a double
			if (console.hasNextDouble()) {
				double intermediate = console.nextDouble();
				//Then make sure it's not zero
				if (intermediate != 0) {
					nextDouble = intermediate;
				} else {
					//Prompt for a new value if it is zero
					System.out.print("Please enter a non-zero value: ");
				}
			} else {
				//Prompt for a double if it's not a double
				System.out.print("Please enter a single decimal number. E.g., 4.5: ");
			}
		} while (nextDouble == 0);
		return nextDouble;
	}

	//This method takes all the modular sock patterns and combines them into a custom pattern with proper variable values
	public static void printPattern(PrintStream fileToWrite, Foot person, File[] styleChoices) throws FileNotFoundException {
		HashMap<String, Double> variables = mapVariables(person);
		substituteVariables(variables, fileToWrite, styleChoices);
	}

	//This method will take the basic user input from the foot object and calculate all other variables required for a pattern
	public static HashMap<String, Double> mapVariables(Foot person) {
		// TODO
		//Read in the variables file line by line
		//For each line
			//Calculate the variable value
			//With the values defined by the foot class
			//And the definition of the new variable from ??Somewhere??
			//Maybe they should be defined as methods in the foot class
		HashMap<String, Double> variables = new HashMap<String, Double>();
		//I don't know where I want to get these definitions from
		//int castOn = (int) Math.round(person.gaugeSts * person.circEase);
		//if (castOn % 2 != 0) {
		//	castOn += 1;
		//}
		//int heelFlap = castOn / 2;
		//int heelTurn = heelFlap / 2 - 1;
		return variables;
	}
	
	//This method takes an input file with a piece of the sock pattern and prints it in the custom file, substituting the variables with their values as it goes
	public static void substituteVariables(HashMap<String, Double> variables, PrintStream fileToWrite, File[] styleChoices) throws FileNotFoundException {
		for(File styleChoiceFile : styleChoices){
			Scanner fileReader = new Scanner(styleChoiceFile);
			// Line-based processing of the input file
			while (fileReader.hasNextLine()) {
				Scanner lineReader = new Scanner(fileReader.nextLine());
				/* Now we're looking at the tokens in the lines and writing them to
				 * the output file or replacing them with the appropriate value in the output file */
				while (lineReader.hasNext()) {
					String token = lineReader.next();
					// Find the placeholders
					if (token.startsWith("<") && token.endsWith(">")) {
						String placeholder = token.substring(1, token.length() - 1);
						//get the value
						double value = variables.get(placeholder);
						fileToWrite.print(value + " ");
					} else {
						fileToWrite.print(token + " ");
					}
				}
				fileToWrite.println();
				lineReader.close();
			}
			fileToWrite.println();
			fileReader.close();
		}
	}
	
}
