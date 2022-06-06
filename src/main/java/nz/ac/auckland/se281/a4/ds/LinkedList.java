package nz.ac.auckland.se281.a4.ds;

//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//THIS CLASS ALSO HAS TO BE MADE 
//GENERIC
//*******************************

/**
 * The Linked List Class Has only one head pointer to the start node (head)
 * Nodes are indexed starting from 0. The list goes from 0 to size-1.
 *
 * @author Partha Roop
 */
public class LinkedList<T> {
	// the head of the linked list
	private Node<T> head;

	/**
	 * Constructor for LinkedList
	 */
	public LinkedList() {
		head = null;
	}

	/**
	 * This method returns a reference to a node whose position is at pos TODO:
	 * Complete this method
	 * 
	 * @param pos: an integer specifying the position of the node to be located
	 * @return Node: the reference to the Node at position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	private Node<T> locateNode(int pos) throws InvalidPositionException {

		Node<T> temp = head;
		Node<T> node = head;

		// checks the validity of the position input
		if (pos < 0 || pos > (size() - 1)) {
			throw new InvalidPositionException("Position is invalid");
		} else {

			// initialises index as 0
			int i = 0;

			// loops through the nodes
			while (temp != null) {
				// checks if the index is equal to the position
				if (i == pos) {
					// assigns the node temp node
					node = temp;
					break;
				} else {
					// if position is not correct it moves to the next node and increments index
					temp = temp.getNext();
					i++;
				}
			}
		}
		return node;
	}

	/**
	 * This method adds a node with specified data as the start node of the list
	 * TODO: Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be prepended
	 */
	public void prepend(T element) {
		Node<T> n = new Node<T>(element);
		// node points to current and makes current head the input element
		n.setNext(head);
		head = n;
	}

	/**
	 * This method adds a node with specified data as the end node of the list TODO:
	 * Complete this method
	 *
	 * @param element a parameter, which is the value of the node to be appended
	 */

	// Note this method has been refactored using the helper methods
	// I will do this as a small ACP exercise in class
	public void append(T element) {
		Node<T> n = new Node<T>(element);

		// checks if the size is 0 then it appends by making the node head
		if (size() == 0) {
			prepend(element);
		} else {
			// calls the locate node method and finds the last node then sets note
			locateNode(size() - 1).setNext(n);
		}

	}

	/**
	 * This method gets the value of a node at a given position TODO: Complete this
	 * method
	 *
	 * @param pos an integer, which is the position
	 * @return the value at the position pos
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size-1
	 */
	public T get(int pos) throws InvalidPositionException {

		// calls locate node then gets the value stored in the node
		return locateNode(pos).getValue();
	}

	/**
	 * This method adds an node at a given position in the List TODO: Complete this
	 * method
	 * 
	 * @param pos     an integer, which is the position
	 * @param element the element to insert
	 * @throws InvalidPositionException if position is less than 0 or greater than
	 *                                  size
	 */
	public void insert(int pos, T element) throws InvalidPositionException {
		// creates a new node instance of the input element
		Node<T> n = new Node<T>(element);

		if (pos == 0) {
			prepend(element);
		} else if (pos == size()) {
			append(element);
		} else {
			// sets the node in the current position as the next node
			n.setNext(locateNode(pos));

			// points the node before the input position to the new node
			locateNode(pos - 1).setNext(n);
		}

	}

	/**
	 * This method removes an node at a given position TODO: Complete this method
	 *
	 * @param pos : an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {

		// checks if the position is 0 then makes it head
		if (pos == 0) {
			head = head.getNext();
			// if last position then resets the last one to null
		} else if (pos == (size() - 1)) {
			locateNode(pos - 1).setNext(null);
			// else it connects the position before to the position after (hence removing
			// the node)
		} else {
			locateNode(pos - 1).setNext(locateNode(pos + 1));
		}
	}

	/**
	 * This method returns the size of the Linked list TODO: Complete this method
	 *
	 * @return the size of the list
	 */
	public int size() {

		// initialises size as zero
		int size = 0;
		// initialise temporary node as head
		Node<T> temp = head;

		// while node is not null
		while (temp != null) {
			// size increases
			++size;
			// temporary node becomes the next node
			temp = temp.getNext();
		}

		return size;

	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 */
	public void print() {
		Node<T> node = head;
		while (node != null) {
			System.out.println(node);
			node = node.getNext();
		}
	}
}