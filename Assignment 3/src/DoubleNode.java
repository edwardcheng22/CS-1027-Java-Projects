/**
 * CS 1027 Assignment 3
 * @author Edward Cheng
 * Student Number: 251024979
 * DoubleNode.java represents the nodes in a doubly linked list
 */

/**
 * Instance variables are declared in this constructor
 * @param <T>
 */
public class DoubleNode<T> {

	private DoubleNode<T>next;
	private DoubleNode<T>prev;
	private T data;

// Creates empty node
public DoubleNode() {
	next = null;
	prev = null;
	data = null;
}

// Creates a node storing the given data in which next and prev are null
public DoubleNode(T newData) {
	next = null;
	prev = null;
	data = newData;
}

// Returns the value of next
public DoubleNode<T>getNext(){
	return next;
}

public DoubleNode<T>getPrev(){
	return prev;
}

public T getData() {
	return data;
}

// Stores nextNode in next
public void setNext(DoubleNode<T>nextNode) {
	next = nextNode;
}

public void setPrev(DoubleNode<T>prevNode) {
	prev = prevNode;
}

public void setData(T newData) {
	data = newData;
}

}
