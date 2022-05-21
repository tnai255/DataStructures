package nz.ac.auckland.se281.a4.ds;

import java.util.EmptyStackException;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class NodesStackAndQueue<T> {

	private Node<T> head; // You should use this variable in your methods

	public NodesStackAndQueue() {
		head = null;
	}

	/**
	 * Checks if the stack / queue is empty
	 * 
	 * @return true if the stack / queue is empty
	 */
	public boolean isEmpty() {
		// if head is null then stack/queue is empty
		if (head == null) {
			return true;
		}
		// if condition is not true it must be the case that stack/queue is not empty
		return false;
	}

	/**
	 * Push operation refers to inserting an element in the Top of the stack. TODO:
	 * Complete this method
	 * 
	 * @param element the element to be "pushed"
	 */
	public void push(T element) {
		// creates a generic node of the inputted element
		Node<T> n = new Node<T>(element);
		// pushes the current head to the next node
		n.setNext(head);
		// makes the new element the head (adds to the top)
		head = n;
	}

	/**
	 * pop an element from the top of the stack (removes and returns the tope
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public Node pop() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public Node peek() throws EmptyStackException {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node n = new Node(element);
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}
}
