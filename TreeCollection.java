package homework1;

import java.util.*;

/**
 * This class extends MyBST to create a Binary Search Tree of 'Tree objects'
 * 
 * @author Anisa Matthews
 */
public class TreeCollection extends MyBST<Tree> {

	protected ArrayList<String> uniqueSpecies = new ArrayList<String>();
	protected HashMap<String, Integer> boroughCount = new HashMap<String, Integer>();

	/**TreeCollection()
	 * Constructor that creates a BST of trees with a null root
	 */
	public TreeCollection() {
		root = null;
	}

	/**add
	 * adds a new tree object to the Binary Search Tree
	 * @param tree - new tree to be added
	 * @return true if the tree did not already exist in the BST, and false if it did
	 * @throws
	 * ClassCastException if an object of a different type is added
	 * NullPointerException if a null object is added
	 */
	@Override
	public boolean add(Tree tree) throws ClassCastException, NullPointerException {
		super.add(tree);
		String boro = tree.getBoro().toLowerCase();
		if (!uniqueSpecies.contains(tree.getSpecies())) {
			uniqueSpecies.add(tree.getSpecies());
		}
		if (!boroughCount.containsKey(boro)) {
			boroughCount.put(boro, 1);
		} else {
			Integer count = boroughCount.get(boro);
			count += 1;
			boroughCount.put(boro, count);
		}
		if (super.add(tree) == true)
			return true;
		return false;
	}
	
	/**getCountByTreeSpecies
	 * calls getMatchingSpecies to create a list of species to then search for using a helper method
	 * @param speciesName
	 * @return number of specific species in total
	 */
	public int getCountByTreeSpecies(String speciesName) {
		ArrayList<String> matching = (ArrayList<String>) getMatchingSpecies(speciesName);
		int counter = 0;
		for (String species : matching){
			int newCount = countTreeSpeciesHelper(root, species);
			counter += newCount;
		}
		return counter;
	}

	/**countTreeSpeciesHelper
	 * helper method for getCountByTreeSpecies 
	 * performs actual search in BST for each species of the matching species list
	 * @param subRoot - node the method is currently look at
	 * @param spc - species to look for
	 * @return - count for that specific species from the matching species list
	 */
	private int countTreeSpeciesHelper(BSTNode<Tree> subRoot, String spc) {
		if (subRoot == null)
			return 0;
		else {
			if (subRoot.getData().getSpecies().compareToIgnoreCase(spc) > 0){
				return countTreeSpeciesHelper(subRoot.getLeft(), spc);
			}
			else if (subRoot.getData().getSpecies().compareToIgnoreCase(spc) < 0){
				return countTreeSpeciesHelper(subRoot.getRight(), spc);
			}
			else
				return 1 + countTreeSpeciesHelper(subRoot.getLeft(), spc) + countTreeSpeciesHelper(subRoot.getRight(), spc);
		}
	}

	/**getCountByBorough
	 * returns the number of trees in a specific borough
	 * @param boroName - borough
	 * @return the integer value associated with the boroName key in the hash map
	 */
	public int getCountByBorough(String boroName) {
		String lowerCasedBoro = boroName.toLowerCase();
		if (!boroughCount.containsKey(lowerCasedBoro))
			return 0;
		else
			return boroughCount.get(lowerCasedBoro);
	}

	/**getCountByTreeSpeciesBorough
	 * calls getMatchingSpecies to create a list of species to then search for using a helper method
	 * @param speciesName - species to look for
	 * @param boroName - borough to search for
	 * @return number of specific species in a specific borough
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName) {
		Collection<String> matching = (ArrayList<String>) getMatchingSpecies(speciesName);
		int counter = 0;
		for (String species : matching) {
			int newCount = countTreeSpeciesBoroughHelper(root, species, boroName);
			counter += newCount;
		}
		return counter;

	}

	/**countTreeSpeciesBoroughHelper
	 * helper method for getCountByTreeSpeciesBorough
	 * performs actual search in BST for each species of the matching species list, and whether it is in the given borough
	 * @param subRoot - node the method is currently looking at
	 * @param spc - species to look for
	 * @param boro - borough to look for
	 * @return - count for that specific species from the matching list in the specified borough
	 */
	public int countTreeSpeciesBoroughHelper(BSTNode<Tree> subRoot, String spc, String boro) {
		// base case
		if (subRoot == null)
			return 0;
		else{
			if (subRoot.getData().getSpecies().compareToIgnoreCase(spc) > 0){
				return countTreeSpeciesBoroughHelper(subRoot.getLeft(), spc, boro);
			}
			else if (subRoot.getData().getSpecies().compareToIgnoreCase(spc) < 0){
				return countTreeSpeciesBoroughHelper(subRoot.getRight(), spc, boro);
			}
			else{
				if (subRoot.getData().getBoro().equalsIgnoreCase(boro))
					return 1 + countTreeSpeciesBoroughHelper(subRoot.getLeft(), spc, boro) + countTreeSpeciesBoroughHelper(subRoot.getRight(), spc, boro);
				else
					return countTreeSpeciesBoroughHelper(subRoot.getLeft(), spc, boro) + countTreeSpeciesBoroughHelper(subRoot.getRight(), spc, boro);
			}				
		}
	}

	/**getMatchingSpecies
	 * returns a collection of species names containing the given species string
	 * @param speciesName - species name, or part of one
	 * @return a collection of species names
	 */
	public Collection<String> getMatchingSpecies(String speciesName) {
		Collections.sort(uniqueSpecies);
		Collection<String> matches = new ArrayList<String>();
		for (String species : uniqueSpecies) {
			if (species.toLowerCase().contains(speciesName.toLowerCase()))
				matches.add(species);
		}
		return matches;
	}

	/**getTotalNumberOfTrees
	 * returns the number of trees total in the tree collection
	 * @return the size of the tree collection
	 */
	public int getTotalNumberOfTrees() {
		return size;
	}
	
	public String toString() {
		super.toString();
		return "";
	}
}
