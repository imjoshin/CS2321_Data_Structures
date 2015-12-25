/**
 * @author Josh Johnson
 */

public class MyPriorityQueue<Item> implements MyPriorityQueueInterface{

	private int size = 0;
	private Node head = null; //lowest value
	private Node tail = null; //highest value
	/**
	 * Stores an item into the priority queue in order.
	 * @param Item to put into the queue.
	 */
	public void put(Comparable item) {
		Node cur = head;
		if(size > 0){
			for(int i = 0; i < size; i++){
				int result = compare(cur.getValue(), item);
				if(result >= 0){
					Node temp = new Node((Item) item);
					if(cur.hasPrev()){
						cur.getPrev().setNext(temp);
						temp.setPrev(cur.getPrev());
					}
					cur.setPrev(temp);
					temp.setNext(cur);
					
					if(i == 0)
						head = temp;
					break;
				}else if(i == size - 1){
					Node temp = new Node((Item) item);
					cur.setNext(temp);
					temp.setPrev(cur);
					tail = temp;
				}else{
					cur = cur.getNext();
				}
			}			
		}else{
			Node temp = new Node((Item) item);
			head = temp;
			tail = temp;
		}

		size++;
		
	}
	/**
	 * Get and remove the greatest valued item.
	 * @return An Item that contains the greatest value.
	 */
	public Comparable getMax() throws EmptyQueueException {
		if(size == 0)
			throw new EmptyQueueException();
		
		Item value = tail.getValue();
		if(tail.hasPrev()){
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
		}else{
			tail = null;
			head = null;
		}
		size--;
		return (Comparable) value;
	}
	/**
	 * Get and remove the least valued item.
	 * @return An Item that contains the least value.
	 */
	public Comparable getMin() throws EmptyQueueException {
		if(size == 0)
			throw new EmptyQueueException();
		
		Item value = head.getValue();
		if(head.hasNext()){
			head.getNext().setPrev(null);
			head = head.getNext();
		}else{
			tail = null;
			head = null;
		}
		size--;
		return (Comparable) value;
	}
	/**
	 * Create a list object from the sorted priority queue.
	 * @return A list object with the priority queue.
	 */
	public ListInterface toList() {
		List<Item> list = new List<Item>();
		Node cur = head;
		for(int i = 0; i < size; i++){
			list.add(cur.getValue());
			cur = cur.getNext();
		}
		return list;
	}
	/**
	 * @return The size of the priority queue.
	 */
	public int size() {
		return size;
	}
	/**
	 * @return A boolean for if the queue is empty.
	 */
	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}
	/**
	 * A helper method for comparing two objects when sorting.
	 */
	private int compare(Object obj1, Object obj2){
		if(obj1 == null && obj2 == null)
			return 0;
		else if(obj2 == null)
			return 1;
		else if(obj1 == null)
			return -1;
		else{
			return ((Comparable) obj1).compareTo(obj2);
		}
	}
	
	//debugging purposes
	/*public String toString(){
		String str = "{";
		Node cur = head;
		for(int i = 0; i < size; i++){
			if(cur.getValue() == null)
				str += "\"null\", ";
			else
				str += "\"" + cur.getValue().toString() + "\", ";
			cur = cur.getNext();
		}
		return str.substring(0, str.length() - 2) + "}";
	}
	*/
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
		 * Check to see if the node has a next node
		 * @return A boolean to see if the next exists.
		 */
		public boolean hasPrev(){
			if(prev != null)
				return true;
			else
				return false;
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
		 * Check to see if the node has a next node
		 * @return A boolean to see if the next exists.
		 */
		public boolean hasNext(){
			if(next != null)
				return true;
			else
				return false;
		}
		/**
		 * Set the Node object above the current node.
		 */
		public void setNext(Node n){
			next = n;
		}	
		
	}
}
