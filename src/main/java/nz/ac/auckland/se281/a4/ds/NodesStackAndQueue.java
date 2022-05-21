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
		// creates a generic node of the input element
		Node<T> n = new Node<T>(element);
		// pushes the current head to the next node
		n.setNext(head);
		// makes the new element the head (adds to the top)
		head = n;
	}

	/**
	 * pop an element from the top of the stack (removes and returns the top
	 * element) TODO: Complete this method (Note: You may have to change the return
	 * type)
	 * 
	 * @return object of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T pop() throws EmptyStackException {

		// checks if stack is empty or not
		if (!isEmpty()) {
			// creates a variable to hold the value of top element
			T top = head.getValue();
			// changes the head to the the element below the head (removes top)
			head = head.getNext();
			// returns the value that was the top
			return top;
		} else {
			// throws exception to say it is empty
			throw new EmptyStackException();
		}

	}

	/**
	 * get the element from the top of the stack without removing it TODO: Complete
	 * this method (Note: You may have to change the return type)
	 *
	 * @return the value of the top element
	 * @throws EmptyStackException if the stack is empty
	 */
	public T peek() throws EmptyStackException {

		// checks if stack is empty or not
		if (!isEmpty()) {
			// returns the value of the top element
			return head.getValue();
		} else {
			// throws exception to say it is empty
			throw new EmptyStackException();
		}

	}

	/**
	 * append an element at the end of the queue TODO: Complete this method
	 *
	 * @param element the element to be appended
	 */
	public void append(T element) {
		Node<T> n = new Node<T>(element);

		if (!isEmpty()) {
			// initialise last node to head
			Node<T> last = head;

			// loops until the last node is found i.e. next node would be null
			while (last.getNext() != null) {
				// if there is a next node then that becomes the "last" node
				last = last.getNext();
			}

			// once it finds the last node it appends the input element to it
			last.setNext(n);
		} else {
			// queue is empty appending means it becomes the head
			head = n;
		}

	}
}
