/**
 * CS 1027 Assignment 3
 * @author Edward Cheng
 * Student Number: 251024979
 * DoubleList.java represents a doubly linked list of nodes of the class DoubleNode
 */


public class DoubleList<T>{

	private DoubleNode<T> head;
	private DoubleNode<T> rear;
	private int numDataItems;
	public int length;
	
	public DoubleList() {
		head = null;
		rear = null;
		numDataItems = 0;
		
	}
	
	public void addData(int index, T newData) throws InvalidPositionException {
		// Index is out of range
		if(index < 0 || index > numDataItems){
		      throw new InvalidPositionException("Invalid position");
		} 
		if (this.head==null) {	// There is no head so just put the new node as the head
			this.head = new DoubleNode<T>(newData);
			this.rear = this.head;
			numDataItems++; // increment numDataItems
			return;
		} else if(index == 0) {		// Insert new head
			// Save the current head
			DoubleNode<T>node = this.head;
			// Set the new head
			this.head = new DoubleNode<T>(newData);
			// Set the new head's next node to be the old head
			this.head.setNext(node);
			numDataItems++;
			return;
		} else if(index == numDataItems){
			// Save the current tail
			this.rear.setNext(new DoubleNode<T>(newData));
			this.rear = this.rear.getNext();
			// Set the new tail's next node to be the head
			numDataItems++;
			return;
		}
		int counter = 1; // Counter variable
		DoubleNode<T> previous = this.head; 
		DoubleNode<T> node = this.head.getNext();
		while(node!=null){
			if(counter == index){
				previous.setNext(new DoubleNode<T>(newData));
				previous.getNext().setNext(node);
				numDataItems++;
				return;
			}
			previous = node;
			node = node.getNext();
			counter++;
		}
		previous.setNext(new DoubleNode<T>(newData));
		previous.getNext().setNext(node);
	}
	
	public DoubleNode<T> getNode(int index) throws InvalidPositionException {
		if(index < 0 || index > numDataItems){
		      throw new InvalidPositionException("Invalid position");
		}
		// Efficiency
		// Return head
		if(index == 0){
			return head;
		} else if(index == numDataItems - 1){
			// Return tail
			return rear;
		}
		DoubleNode<T> current = this.head.getNext();
		int count = 1; 

		// Loop through the linked list until we reach the index
		while (current != null) {
			// If we have reached the index, return the node
			if(count == index) {
				return current;
			} else{
				// Else, increment the count and set the current node as the next node
				count++;
				current = current.getNext();
			}
		}
		return current;
	}
	
	public void removeData(int index) throws InvalidPositionException{
		if(index < 0 || index >= numDataItems){
		      throw new InvalidPositionException("Invalid position");
		}

		// If there is no head, then just return nothing
		if (head == null) {
			return;
		}

		// Remove head;
		if(index == 0){
			// Set the head to be the old head's next
			this.head = this.head.getNext();
			return;
		} else if(index == numDataItems - 1){	// Remove tail
			DoubleNode<T> previous = this.head;
			DoubleNode<T> node = this.head.getNext();
			// Loop through until node reaches the tail
			while(node.getNext() != null){
				previous = node;
				node = node.getNext();
			}
			// Once the loop finishes previous should be the node before the tail
			// So set the next node for the node before the tail to be null;
			previous.setNext(null);
			return;
		}

		// Remove a node in between the head and tail
		DoubleNode<T> previous = this.getNode(index-1);
		DoubleNode<T> node = this.getNode(index);
		previous.setNext(node.getNext());

		numDataItems--;
	}
	
	public T getData(int index) throws InvalidPositionException {
		if(index < 0 || index >= numDataItems){
		      throw new InvalidPositionException("Invalid position");
		}
		return getNode(index).getData();
	}
	
	public void setData(int index, T newData) throws InvalidPositionException{
		if(index < 0 || index >= numDataItems){
		      throw new InvalidPositionException("Invalid position");
		}
		getNode(index).setData(newData);
	}
	
}

