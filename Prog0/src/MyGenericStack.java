/**
 * @author Josh Johnson
 */
public class MyGenericStack<Item> implements MyGenericStackInterface{
	private Node top = null; //top of the stack
	private int count = 0; //count of how many items are in the stack
	
	/**
	 * Return and remove the item from the top of the stack.
	 * @return The item from the top of the stack.
	 */
	@Override
	public Object pop() {
		Item item;
		if(count > 1){ //shift top up and remove
			item = top.getValue();
			top = top.getPrev();
			top.setNext(null);
			count--;
		}else if(count == 1){ //if only one item, clear the stack
			item = top.getValue();
			top = null;
			count--;
		}else{ //if no items
			throw new NullPointerException("Stack is empty.");
		}
		return item;
	}
	/**
	 * Peek at the item on the top of the stack.
	 * @return The item from the top of the stack.
	 */
	@Override
	public Object peek() {
		if(count == 0)
			throw new NullPointerException("Stack is empty.");
		else
			return top.getValue();
	}
	/**
	 * Push a new item onto the stack.
	 * @param Item to push onto the stack.
	 */
	@Override
	public void push(Object item) {
		if(count == 0){ //if no items, create top of stack
			top = new Node((Item) item);
		}else{	//shift top down and add item
			Node temp = new Node((Item) item);
			temp.setPrev(top);
			top.setNext(temp);
			top = temp;
		}
		count++;
	}
	/**
	 * Check to see if the stack is empty
	 * @return A boolean in response to if the stack is empty.
	 */
	@Override
	public boolean isEmpty() {
		if(count == 0)
			return true;
		else
			return false;
	}
	/**
	 * Get the size of the stack.
	 * @return An integer that contains the size of the stack.
	 */
	@Override
	public int size() {
		return count;
	}

	//Node class that holds items for stack
	/**
	 * Private inner "Node" class that contains each Item in the stack.
	 */
	private class Node{
		private Node prev = null; //pointer to previous node
		private Item value = null;
		private Node next = null; //pointer to next node
		
		/**
		 * Creates a new node with the given item.
		 */
		public Node(Item item){
			value = item;
		}
		/**
		 * Get the item value stored in the node.
		 * @return An Item object that contains the value.
		 */
		public Item getValue(){
			return value;
		}
		/**
		 * Set the value of the current node.
		 */
		public void setValue(Item i){
			value = i;
		}
		/**
		 * Get the Node object below the current node.
		 * @return The previous Node.
		 */
		public Node getPrev(){
			return prev;
		}
		/**
		 * Set the Node object below the current node.
		 */
		public void setPrev(Node n){
			prev = n;
		}
		/**
		 * Get the Node object above the current node.
		 * @return The next Node.
		 */
		public Node getNext(){
			return next;
		}
		/**
		 * Set the Node object above the current node.
		 */
		public void setNext(Node n){
			next = n;
		}	
		
	}

}
