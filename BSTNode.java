package homework1;

public class BSTNode <E extends Comparable <E>> implements Comparable <BSTNode <E>> {
    private E data;
    private BSTNode <E> left;
    private BSTNode <E> right;
    
    public BSTNode (E data) {
    	this.data = data;
    	this.left = left;
    	this.right = right;
    }
    
    public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public BSTNode<E> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<E> left) {
		this.left = left;
	}

	public BSTNode<E> getRight() {
		return right;
	}

	public void setRight(BSTNode<E> right) {
		this.right = right;
	}

	public int compareTo (BSTNode <E> other){
    	return this.data.compareTo(other.data);
    }
}
