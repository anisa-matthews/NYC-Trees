package homework1;

/**
 * This class represents every Tree or 'Tree object' to be stored in a data structure
 * 
 * @author Anisa Matthews
 */

public class Tree implements Comparable<Tree> {

	int ID;
	int diam;
	int zip;
	String status;
	String health;
	String spc_common;
	String boro;
	double x;
	double y;

	/**Tree()
	 * Tree Constructor method that creates a tree with an unique ID and other
	 * data fields
	 * 
	 * @implements Comparable
	 * @param ID
	 *            = tree identification number diam = diameter of body status =
	 *            status of tree. can only be alive, dead, stump, empty or null.
	 *            health = health of tree. can be good, fair, poor, empty, or
	 *            null. spc_common = common species name of the tree, as opposed
	 *            to its latin name. zip = zip code of tree's location boro =
	 *            borough in which the tree is x = x coordinate y = y coordinate
	 *
	 */
	public Tree(int ID, int diam, String status, String health, String spc_common, int zip, String boro, double x,
			double y) throws IllegalArgumentException {
		// ID must be positive integer
		if (ID < 0)
			throw new IllegalArgumentException("ID must be a positive number");
		else
			setID(ID);
		// Diameter must be positive integer
		if (diam < 0)
			throw new IllegalArgumentException("DBH cannot be negative");
		else
			setDiam(diam);
		// ZIP must be up to 5 digits and a positive integer
		if ((zip < 0) || zip > 99999)
			throw new IllegalArgumentException("This zip is invalid");
		else
			setZip(zip);
		// status of tree can only be alive, dead, stump, empty or null
		if (status != null) {
			if (!(status.equalsIgnoreCase("Alive")) && !(status.equalsIgnoreCase("Dead"))
					&& !(status.equalsIgnoreCase("Stump")) && !(status.equalsIgnoreCase("")))
				throw new IllegalArgumentException("This status is invalid");
			else
				setStatus(status);
		} else
			setStatus("");
		// health of tree can only be good, fair, poor, empty or null
		if (health != null) {
			if (!(health.equalsIgnoreCase("Good")) && !(health.equalsIgnoreCase("Fair"))
					&& !(health.equalsIgnoreCase("Poor")) && !(health.equalsIgnoreCase("")))
				throw new IllegalArgumentException("This health is invalid");
			else
				setHealth(health);
		} else
			setHealth("");
		// species CAN be null
		if (spc_common == null)
			setSpecies("");
		else
			setSpecies(spc_common);
		// Borough can only be one of the five boroughs in NYC
		if (boro != null) {
			if (boro.equalsIgnoreCase("Manhattan") || boro.equalsIgnoreCase("Brooklyn")
					|| boro.equalsIgnoreCase("Queens") || boro.equalsIgnoreCase("Bronx")
					|| boro.equalsIgnoreCase("Staten Island"))
				setBoro(boro);
			else
				throw new IllegalArgumentException("This borough does not exist");
		} else
			throw new IllegalArgumentException("This borough does not exist");
		setX(x);
		setY(y);
	}

	/**
	 * getID Grabs and @returns ID
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

	/**
	 * setID Sets id to given id
	 * 
	 * @param ID
	 *            - new tree ID
	 */
	private void setID(int iD) {
		this.ID = iD;
	}

	/**
	 * getDiam Grabs and @returns diam
	 */
	public int getDiam() {
		return diam;
	}

	/**
	 * setDiam Sets diam to given diam
	 * 
	 * @param diam
	 *            - new tree diam
	 */
	private void setDiam(int diam) {
		this.diam = diam;
	}

	/**
	 * getZip Grabs and @returns zip
	 */
	public int getZip() {
		return zip;
	}

	/**
	 * setZip Sets zip to given zip
	 * 
	 * @param zip
	 *            - new tree zip
	 */
	private void setZip(int zip) {
		this.zip = zip;
	}

	/**
	 * getStatus Grabs and @returns status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * setStatus Sets status to given status
	 * 
	 * @param status
	 *            - new tree status
	 */
	private void setStatus(String status) {
		this.status = status;
	}

	/**
	 * getHealth Grabs and @returns health
	 */
	public String getHealth() {
		return health;
	}

	/**
	 * setHealth Sets health to given health
	 * 
	 * @param health
	 *            - new tree health
	 */
	private void setHealth(String health) {
		this.health = health;
	}

	/**
	 * getSpecies Grabs and @returns species
	 */
	public String getSpecies() {
		return spc_common;
	}

	/**
	 * setSpecies Sets species to given species
	 * 
	 * @param species
	 *            - new tree species
	 */
	private void setSpecies(String spc_common) {
		this.spc_common = spc_common;
	}

	/**
	 * getBoro Grabs and @returns borough
	 */
	public String getBoro() {
		return boro;
	}

	/**
	 * setBoro Sets borough to given borough
	 * 
	 * @param boro
	 *            - new tree borough
	 */
	private void setBoro(String boro) {
		this.boro = boro;
	}

	/**
	 * getX Grabs and @returns x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * setX Sets x to given x coordinate
	 * 
	 * @param x
	 *            - new x coordinate
	 */
	private void setX(double x) {
		this.x = x;
	}

	/**
	 * getY Grabs and @returns Y
	 */
	public double getY() {
		return y;
	}

	/**
	 * setY Sets y to given y coordinate
	 * 
	 * @param y
	 *            - new tree y coordinate
	 */
	private void setY(double y) {
		this.y = y;
	}

	/**sameName
	 * checks to see if trees are of the same species
	 * @param t - another tree to compare to
	 * @return true if they are of the same species and false if they aren't
	 */
	public boolean sameName(Tree t) {
		if (this.getSpecies().equalsIgnoreCase(t.getSpecies()))
			return true;
		else
			return false;
	}

	/** equals
	 * check to see if two trees are equal based on ID and species
	 * @param other - tree to compare to
	 * @return true if they have the same ID and species, and false if they don't
	 */
	public boolean equals(Tree other) {
		if ((this.getID() == other.getID()) && (this.spc_common.equalsIgnoreCase(other.spc_common)))
			return true;
		if ((this.getID() == other.getID()) && !(this.spc_common.equalsIgnoreCase(other.spc_common)))
			throw new IllegalArgumentException("Error: Same tree ID but different species");
		else
			return false;
	}

	/** compareTo
	 * compare trees by their species and ID in order to order them in alphabetical order later on
	 * @param other - other tree to compare to
	 * @return a number greater than 0 if this ID is greater, less than 0 if this ID is smaller, and 0 if they are equal
	 */
	public int compareTo(Tree other) {
		int speciesCheck = this.spc_common.compareToIgnoreCase(other.spc_common);
		if (speciesCheck == 0) {
			if (this.getID() > other.getID())
				return 1;
			else if (this.getID() == other.getID())
				return 0;
			else
				return -1;
		}

		else
			return speciesCheck;
	}

	/**compareName
	 * compares two trees by their species name
	 * @param t - other tree to compare to
	 * @return a number greater than 0 if this species is greater, less than 0 if this species is smaller, and 0 if they are equal
	 */
	public int compareName(Tree t) {
		if (this.getSpecies().equalsIgnoreCase(t.getSpecies())) {
			return 0;
		} else if (this.getSpecies().compareToIgnoreCase(t.getSpecies()) > 0) {
			return 1;
		} else
			return -1;

	}

	/**toString
	 * Overriding toString to print out simple tree facts
	 */
	public String toString() {
		return "Tree ID: " + this.ID + " Species: " + this.spc_common + " Location: " + this.boro + " " + this.zip
				+ " Status: " + this.status;
	}
}