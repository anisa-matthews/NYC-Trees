package homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**This class is responsible for opening and reading the data files, obtaining user input, performing some data validation
 * 
 * @author anisa.matthews
 *
 */
public class NYCStreetTrees {

	static TreeCollection NYCTrees = new TreeCollection();

	/**main method
	 * first checks to see if given file exists and is readable, then calls the makeTrees method.
	 * User can then enter a species name for program to find information on
	 * User can type 'quit' to close the program
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File test = new File(args[0]);
		// check if it exists
		if (!test.exists())
			// throw "File does not exist"
			throw new FileNotFoundException();
		else
			makeTrees(test);

		// User enters a species name and program finds all the info on it
		System.out.println("Enter a tree species to learn more about it (type 'quit' to end the program):");
		Scanner scanSpecies = new Scanner(System.in);
		String speciesInput;
		do {
			speciesInput = scanSpecies.next();
			// find what trees contain 'input' in their species name. Print out
			// names.
			Collection<String> matchResult = NYCTrees.getMatchingSpecies(speciesInput);
			for (String s : matchResult) {
				System.out.println(s);
			}
			
			// find the popularity of these species in each borough and make references for them
			int speciesTotalNYC = NYCTrees.getCountByTreeSpecies(speciesInput);
			int totalTreesNYC = NYCTrees.getTotalNumberOfTrees();
			float percentNYC = (float)speciesTotalNYC / totalTreesNYC;
			
			int speciesTotalManhattan = NYCTrees.getCountByTreeSpeciesBorough(speciesInput, "Manhattan");
			int totalTreesManhattan = NYCTrees.getCountByBorough("Manhattan");
			float percentManhattan = 0;
			if (totalTreesManhattan != 0)
				percentManhattan = (float)speciesTotalManhattan / totalTreesManhattan;
			
			int speciesTotalBrooklyn = NYCTrees.getCountByTreeSpeciesBorough(speciesInput, "Brooklyn");
			int totalTreesBrooklyn = NYCTrees.getCountByBorough("Brooklyn");
			float percentBrooklyn = 0;
			if (totalTreesBrooklyn != 0)
				percentBrooklyn = (float)speciesTotalBrooklyn / totalTreesBrooklyn;
			
			int speciesTotalQueens = NYCTrees.getCountByTreeSpeciesBorough(speciesInput, "Queens");
			int totalTreesQueens = NYCTrees.getCountByBorough("Queens");
			float percentQueens = 0;
			if (totalTreesQueens != 0)
				percentQueens = (float)speciesTotalQueens / totalTreesQueens;
			
			int speciesTotalBronx = NYCTrees.getCountByTreeSpeciesBorough(speciesInput, "Bronx");
			int totalTreesBronx = NYCTrees.getCountByBorough("Bronx");
			float percentBronx = 0;
			if (totalTreesBronx != 0)
				percentBronx = (float)speciesTotalBronx / totalTreesBronx;
			
			int speciesTotalStaten = NYCTrees.getCountByTreeSpeciesBorough(speciesInput, "Staten Island");
			int totalTreesStaten = NYCTrees.getCountByBorough("Staten Island");
			float percentStaten = 0;
			if (totalTreesStaten != 0)
				percentStaten = (float)speciesTotalStaten / totalTreesStaten;
			
			if ((speciesTotalNYC == 0) && (speciesTotalBrooklyn == 0) && (speciesTotalQueens == 0) && (speciesTotalBronx == 0) && (speciesTotalStaten == 0)){
				System.out.println("There are no records of" + speciesInput + "in NYC");
			}
			else{
			System.out.println("\nPopularity in the city:\n");
			
			//NYC
			System.out.printf("\t %-15s", "NYC: ");
			System.out.printf("\t %,10d", speciesTotalNYC);
			System.out.printf("(%,d", totalTreesNYC);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentNYC*100);
			System.out.println("");
			
			//Manhattan
			System.out.printf("\t %-15s", "Manhattan: ");
			System.out.printf("\t %,10d", speciesTotalManhattan);
			System.out.printf("(%,d", totalTreesManhattan);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentManhattan*100);
			System.out.println("");
			
			//Brooklyn
			System.out.printf("\t %-15s", "Brooklyn: ");
			System.out.printf("\t %,10d", speciesTotalBrooklyn);
			System.out.printf("(%,d", totalTreesBrooklyn);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentBrooklyn*100);
			System.out.println("");
			
			//Queens
			System.out.printf("\t %-15s", "Queens: ");
			System.out.printf("\t %,10d", speciesTotalQueens);
			System.out.printf("(%,d", totalTreesQueens);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentQueens*100);
			System.out.println("");
			
			//Bronx
			System.out.printf("\t %-15s", "Bronx: ");
			System.out.printf("\t %,10d", speciesTotalBronx);
			System.out.printf("(%,d", totalTreesBronx);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentBronx*100);
			System.out.println("");
			
			//Staten Island
			System.out.printf("\t %-15s", "Staten Island: ");
			System.out.printf("\t %,10d", speciesTotalStaten);
			System.out.printf("(%,d", totalTreesStaten);
			System.out.print(")");
			System.out.printf("\t %15f%%", percentStaten*100);
			System.out.println("");
			}
			System.out.println("\nEnter a tree species to learn more about it (type 'quit' to end the program):");
		} while (!speciesInput.equals("quit"));
		System.out.println("Goodbye!");
		scanSpecies.close();
	}

	/**splitCSVLine
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries that may contain
	 * commas). //makes it a list!
	 * 
	 * @param textLine
	 *            line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line.
	 */
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					// add it to the current entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries
					continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) // comma inside an entry
					nextWord.append(nextChar);
				else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}

		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}
	// this returns an array list of tree info for each tree

	/**makeTrees
	 * create a TreeCollection, and then populates the collection with lines from the CSV file
	 * @param treeFile - file with tree information
	 * @throws FileNotFoundException if file cannot be found
	 */
	public static void makeTrees(File treeFile) throws FileNotFoundException {
		Scanner treeInput = new Scanner(treeFile);

		// skip the first line cause it's just headers
		treeInput.nextLine();
		/*
		 * for every line grab the parameters and put into an array to make a
		 * tree, then add tree to a tree collection.
		 */
		while (treeInput.hasNextLine()) {
			ArrayList<String> tempTreeInfo = new ArrayList<String>();
			tempTreeInfo = splitCSVLine(treeInput.nextLine());
			// index 0, 3, 6, 7, 9, 35, 39, 40,
			try {
				Tree newTree = new Tree(Integer.parseInt(tempTreeInfo.get(0)), Integer.parseInt(tempTreeInfo.get(3)),
						tempTreeInfo.get(6), tempTreeInfo.get(7), tempTreeInfo.get(9),
						Integer.parseInt(tempTreeInfo.get(25)), tempTreeInfo.get(29),
						Double.parseDouble(tempTreeInfo.get(39)), Double.parseDouble(tempTreeInfo.get(40)));
				if (NYCTrees.contains(newTree))
					throw new IllegalArgumentException("This tree is not unique");
				else if (newTree.getSpecies() != null && newTree.getHealth() != null)
					NYCTrees.add(newTree);

			} catch (IllegalArgumentException iae) {
				continue;
			}
		}
		treeInput.close();
	}
}