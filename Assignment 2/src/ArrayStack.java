/** CS 1027 Assignment 2
 *  @author Edward Cheng
 *  251024979
 *  Defines the methods needed to implement a stack using an array
 */

public class ArrayStack<T> implements ArrayStackADT<T> {

    // Array to store data items of the stack
    private T[] stack;

    // Stores the position of the last data item in the stack
    private int top;

    // Initial size of the array stack
    private int initialCapacity;

    // Amount to increase the stack by if its full
    private int sizeIncrease;

    // Amount to decrease the stack
    private int sizeDecrease;


    // Constructor
    public ArrayStack(int initialCap, int sizeInc, int sizeDec){
        top = -1;
        stack = (T[]) new Object[initialCap];
        initialCapacity = initialCap;
        sizeIncrease = sizeInc;
        sizeDecrease = sizeDec;
    }

    // Adds dataItem to the top of the stack and updates the value of top
    public void push (T dataItem){
        // Update the value of top
        top++;

        // If the stack has reached it capacity then increase the capacity of the stack
        if(size() == length()){
            // Create a new stack with the increased capacity
            T[] newStack = (T[]) new Object [length()+sizeIncrease];
            //Copy the old stack into the new one
            for (int i = 0; i < length(); i++){
                newStack[i] = stack[i];
            }
            // Make the new stack the stack
            stack = newStack;
        }
        // Add the item to the top of the stack
        stack[top] = dataItem;
    }

    // Removes and returns the data item at the top of the stack and updates the value of top
    public T pop() throws EmptyStackException{
        // If the stack is empty then throw the EmptyStackException
        if(isEmpty()){
            throw new EmptyStackException("There is nothing to pop, the stack is empty");
        }
        // Get the item at the top of the stack
        T item = stack[top];
        // Make the item at the top of the stack null
        stack[top] = null;
        // Decrement the position of top
        top--;
        // If the size of the stack is less than 1/4 of the length AND
        // The stack was increased larger tha the initialCapacity
        // Then decrease the capacity
        if((size() < (length() / 4)) && (length() > initialCapacity)){
            // Create a new stack with the decreased size
            T[] newStack = (T[]) new Object[length()-sizeDecrease];
            // Copy the old stack into the new
            for(int i = 0; i < newStack.length; i++){
                newStack[i] = stack[i];
            }
            // Make the new stack the stack
            stack = newStack;
        }
        // Return the popped item
        return item;
    }

    // Returns the data item at the top of the stack without removing it
    public T peek() throws EmptyStackException{
        // If its empty throw and EmptyStackException
        if(isEmpty()){
            throw new EmptyStackException("There is nothing to peek, the stack is empty");
        }
        // Return the item at the top of the stack
        return stack[top];
    }

    // Returns true if the stack is empty
    public boolean isEmpty(){
        return (top == -1);
    }

    // Returns number of data items in the stack
    public int size(){
        return top + 1;
    }

    // Returns capacity of the array stack
    public int length(){
        return stack.length;
    }

    // Returns string representation of the stack
    public String toString(){
        String s = "Stack: " + stack[0];
        for (int i = 1; i < size(); i++){
            s += ", " + stack[i].toString();
        }
        return s;
    }
}
