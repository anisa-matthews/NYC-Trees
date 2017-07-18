package homework1;

import java.util.*;

/**MyBST
 * This class is my implementation of a generic Binary Search Tree
 * 
 * @author anisa.matthews
 */
public class MyBST<E extends Comparable<E>> {

	protected BSTNode<E> root; 
	protected int size;

	/**MyBST
	 * Constructor that creates a BST of trees with a null root
	 */
	public MyBST() {
		root = null;
	}

	/**getRoot
	 * returns root node
	 * @return root data field
	 */
	public E getRoot() {
		return root.getData();
	}

	/**add
	 * makes sure the object to be added is of a proper type before calling helper method
	 * @param e - element/object to be added
	 * @return true if the tree did not already exist in the BST, and false if it did
	 * @throws ClassCastException if an object of a different type is added
	 * @throws NullPointerException if a null object is added
	 */
	public boolean add(E e) throws ClassCastException, NullPointerException {
		if(e == null)
			throw new NullPointerException("Cannot add given object");
		if (contains(e)) {
			return false;
		}
		else if (e instanceof Comparable <?>) {
			// to make sure it’s not an empty tree
			if (root == null) {
				this.root = new BSTNode<E>(e);
				size = 1;
				return true;
			} else {
				BSTNode<E> newLeaf = new BSTNode<E>(e);
				addHelper(root, newLeaf);
				size++;
				return true;
			}
		}
		else
			throw new ClassCastException("Cannot add given object");
	}

	/**addHelper
	 * helper method for add. finds where to add the node within the tree
	 * @param newRoot - node currently being evaluated
	 * @param leaf - new node to be added
	 */
	private void addHelper(BSTNode<E> newRoot, BSTNode<E> leaf) {

		if (newRoot.getData().compareTo(leaf.getData()) > 0) {
			if (newRoot.getLeft() == null) {
				newRoot.setLeft(leaf);
				return;
			} else
				addHelper(newRoot.getLeft(), leaf);
		}

		// new leaf is to the right
		else {
			if (newRoot.getRight() == null) {
				newRoot.setRight(leaf);
				return;
			} else
				addHelper(newRoot.getRight(), leaf);
		}
	}

	/**remove
	 * makes sure the object to be removed is of a proper type before calling helper method
	 * @param o - object to be removed
	 * @return true if the tree exist in the BST, and false if it does not
	 * @throws ClassCastException if an object of a different type is added
	 * @throws NullPointerException if a null object is added
	 */
	public boolean remove(Object o) throws ClassCastException, NullPointerException {
		if(o ==null)
			throw new NullPointerException("Cannot remove given object");
		if (!contains(o))
			return false;
		else if (o instanceof Comparable <?>){
			removeHelper(null, root, o);
			return true;
		}
		else
			throw new ClassCastException("Cannot remove given object");
	}

	/**removeHelper
	 * helper method for remove. finds where to remove the node within the tree
	 * @param newRoot - node currently being evaluated
	 * @param leaf - node to be removed
	 */
	private void removeHelper(BSTNode<E> parent, BSTNode<E> child, Object key) {
		// found the thing to remove
		if (child.getData().equals(key)) {

			// case 1 - no children
			if (child.getLeft() == null && child.getRight() == null) {
				if (parent.getLeft().equals(child))
					parent.setLeft(null);
				else
					parent.setRight(null);
			}

			// case 2 - one child
			else if (child.getLeft() == null) // child's right child
			{
				if (parent == null) {
					root = child.getRight();
				} else {
					BSTNode<E> temp = child.getRight();
					child.setData(temp.getData());
					child.setLeft(temp.getLeft());
					child.setRight(temp.getRight());
				}
			} else if (child.getRight() == null) // child's left child
			{
				if (parent == null) {
					root = child.getLeft();
				} else {
					BSTNode<E> temp = child.getLeft();
					child.setData(temp.getData());
					child.setLeft(temp.getLeft());
					child.setRight(temp.getRight());
				}
			}

			// case 3 - two children
			else {
				// find minimum within right subtree
				BSTNode<E> successor = findSuccessor(child.getRight());
				// set parent to successor
				child.setData(successor.getData());
				// remove the original successor
				removeHelper(child.getRight(), child.getRight().getLeft(), successor);

			}
		}

		// it's smaller than the current node (child)
		else if (child.getData().compareTo((E) key) > 0) {
			parent = child;
			child = child.getLeft();
			removeHelper(parent, child, key);
		}

		// it's greater than the current node (child)
		else
			parent = child;
		child = child.getRight();
		removeHelper(parent, child.getRight(), key);

	}

	/**contains
	 * makes sure object is of propert type before calling helper method
	 * @param o
	 * @return
	 * @throws ClassCastException
	 * @throws NullPointerException
	 */
	public boolean contains(Object o) throws ClassCastException, NullPointerException {
		if (o == null)
			throw new NullPointerException("Given object is invalid for search");
		else if (!(o instanceof Comparable <?>))
			throw new ClassCastException("");
		else{
			BSTNode<E> target = new BSTNode<E>((E) o);
			return containsHelper(target, root);
		}
	}

	/**containsHelper
	 * helper method for contains. searches BST for node.
	 * @param target
	 * @param current
	 * @return
	 */
	private boolean containsHelper(BSTNode<E> target, BSTNode<E> current) {
		if (current == null)
			return false;
		else if (current.getData().compareTo(target.getData()) > 0) {
			return containsHelper(target, current.getLeft());
		} else if (current.getData().compareTo(target.getData()) == 0) {
			return true;
		} else
			return containsHelper(target, current.getRight());
	}

	/**first
	 * finds the smallest node in the BST
	 * @return data of the smallest node
	 * @throws NoSuchElementException
	 */
	public E first() throws NoSuchElementException {
		BSTNode<E> temp = root;
		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp.getData();

	}

	/**findSuccessor
	 * finds the next largest node of the one being removed
	 * @param miniRoot - root of subTree
	 * @return successor node
	 */
	private BSTNode<E> findSuccessor(BSTNode<E> miniRoot) {
		BSTNode<E> temp = miniRoot;
		while (temp.getLeft() != null) {
			temp = temp.getLeft();
		}
		return temp;
	}

	/**last
	 * finds the largest node in the BST
	 * @return
	 */
	public E last() {
		BSTNode<E> temp = root;
		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp.getData();

	}

	/**toString
	 * prints each node's data
	 * @return a string
	 */
	public String toString() {
		// an inorder traversal
		printInOrder(root);
		return "";

	}

	/**printInOrder
	 * helper method for toString
	 * @param node - current node being evaluated
	 */
	private void printInOrder(BSTNode<E> node) {
		if (node != null) {
			printInOrder(node.getLeft());
			System.out.println(node.getData());
			printInOrder(node.getRight());
		}
	}
	
	
}