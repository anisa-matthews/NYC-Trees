package homework1;

public class TestsOfTests {

	public static void main(String [] args){
		TreeCollection tree = new TreeCollection();
		tree.add(new Tree(37, 3, null, null, "Red Oak", 0, "brooklyn", 0, 0));
		tree.add(new Tree(24, 3, null, null, "Red Oak", 0, "brooklyn", 0, 0));
		tree.add(new Tree(37, 3, null, null, "White Oak", 0, "brooklyn", 0, 0));
		tree.add(new Tree(56, 3, null, null, "Linden", 0, "brooklyn", 0, 0));
		tree.add(new Tree(14, 3, null, null, "Maple", 0, "brooklyn", 0, 0));
		
		tree.add(new Tree(37, 3, null, null, "Pine Oak", 0, "bronx", 0, 0));
		tree.add(new Tree(24, 3, null, null, "Red Maple", 0, "queens", 0, 0));
		tree.add(new Tree(37, 3, null, null, "Honeylocust", 0, "brooklyn", 0, 0));
		tree.add(new Tree(56, 3, null, null, "American linden", 0, "manhattan", 0, 0));
		tree.add(new Tree(14, 3, null, null, "Maple", 0, "brooklyn", 0, 0));
		
		System.out.println(tree.getCountByTreeSpecies("oak"));
		System.out.println(tree.getCountByBorough("Brooklyn"));
		System.out.println(tree.getCountByTreeSpeciesBorough("oak", "Brooklyn"));
	}
}
