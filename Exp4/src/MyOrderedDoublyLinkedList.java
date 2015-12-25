/**
 * @author Josh Johnson
 * Date: 10/8/14
 * Program 4
 */

public class MyOrderedDoublyLinkedList<Item extends Comparable<Item>> extends List<Item> implements ListInterface<Item> {
	Node tail = null;
	Node head = null;
	Node cur = null;

	protected class Node extends ListNode {
		private Item value = null;
		private Node next = null;
		private Node prev = null;

		//create node with given value
		public Node(Item newValue) {
			value = newValue;
		}

		//get item value
		public Item getValue( ) {
			return value;
		}

		//get next node
		public Node getNext( ) {
			return next;
		}

		//set next node
		public void setNext( Node nextNode ) {
			next = nextNode;
		}

		//get previous node
		public Node getPrev( ) {
			return prev;
		}

		//set previous node
		public void setPrev( Node prevNode ) {
			prev = prevNode;
		}

		//print node with next and previous
		//debugging use
		/*public String toString() {
			String nextValue = "null";
			String prevValue = "null";
			String thisValue = "null";
			
			if (value != null)
				thisValue = value.toString();
			if (next != null && next.getValue() != null)
				nextValue = next.getValue().toString();
			if (prev != null && prev.getValue() != null)
				prevValue = prev.getValue().toString();

			return " { Value: \"" + thisValue + "\", Prev: \"" + prevValue + "\", Next: \"" + nextValue + "\" }";
		 }*/
	}

	//add item to list
	public void add(Item value) {
		Node newNode = new Node(value);
		//if empty, set head and tail to the new node
		if (size == 0) {
			head = newNode;
			tail = newNode;
			cur = newNode;
		//if not empty, find insertion and insert
		}else {
			cur = head;

			//iterate through list
			for (int i = 0; i < size; i++) {
				//find first value that is greater than insertion
				if (myCompare(value, cur.getValue()) <= 0) {
					//once found, set next/previous of node and surrounding nodes
					if (cur.getPrev() != null)
						cur.getPrev().setNext(newNode);

					newNode.setNext(cur);
					newNode.setPrev(cur.getPrev());
					cur.setPrev(newNode);

					if (cur == head)
						head = newNode;

					break;
				}else {
					//if value is never found, set new node to the tail
					if (cur == tail) {
						cur.setNext(newNode);
						newNode.setPrev(cur);
						tail = newNode;
					}else
						//move to next node
						cur = cur.getNext();
				}
			}

			cur = newNode;
		}
		//increment size
		size++;
	}

	//insert just calls add
	public void insert(Item value) {
		add(value);
	}

	//remove item from list
	public Item remove() throws EmptyListException {
		Item result = null;
		errWhenEmpty( );
		//check if the node to remove is the head
		if (head == cur) {
			//set head to the next node
			Node temp = head;
			head = head.getNext( );
			cur.setNext( null );
			result = cur.getValue( );
			cur = head;

			if (head != null)
				head.setPrev(null);
			
			temp.setNext(null);
			temp.setPrev(null);
		} else {
			//else, remove all references to current node
			if (cur.getNext() != null)
				cur.getNext().setPrev(cur.getPrev());
			cur.getPrev().setNext(cur.getNext());
			//set cur back one node
			Node temp = cur;
			cur = cur.getPrev();
			//clear removed references
			temp.setNext(null);
			temp.setPrev(null);
			
		}
		//decrement size
		size--;
		return result;
	}

	// True if the list empty; False otherwise
	@Override
	public boolean isEmpty( ) {
		return( head == null );
	}

	// Helper Method - Throws EmptyListException when list is empty
	private void errWhenEmpty( ) throws EmptyListException {
		if (isEmpty( )) {
			throw new EmptyListException( );
		}
	}

	// get current item
	@Override
	public Item get( ) throws EmptyListException {
		errWhenEmpty( );
		return cur.getValue( );
	}


	// Returns true if there exists a next node in the list
	public boolean hasNext( ) {
		return( ( !isEmpty( ) ) && ( cur.getNext( ) != null ) );
	}

	// get next item, moving cur
	// return that item
	@Override
	public Item next( ) throws EmptyListException, EndOfListException {
		errWhenEmpty( );
		if (!hasNext( )) {
			throw new EndOfListException( );
		}
		cur = cur.getNext( );
		return cur.getValue( );
	}

	// get previous item, moving cur
	// return that item
	@Override
	public Item prev( ) throws EmptyListException, EndOfListException {
		errWhenEmpty( );
		if (!hasPrev( )) {
			throw new EndOfListException( );
		}
		cur = cur.getPrev( );
		return cur.getValue( );
	}

	// Returns true if there exists a previous node in the list
	public boolean hasPrev( ) {
		return( ( !isEmpty( ) ) && ( cur != head ) );
	}

	// move cur to head
	@Override
	public void reset( ) {
		cur = head;
	}
	
	public int myCompare(Item item1, Item item2) {
		if (item1 == null && item2 == null)
			return 0;
		else if (item1 == null)
			return -1;
		else if (item2 == null)
			return 1;
		else
			return item1.compareTo(item2);
	}
	//print list
	//debugging use
	/*public String toString() {
		Node temp = cur;
		if (size == 0) return " {}";
		String str = "\tHead: " + head + "\n\tTail: " + tail + "\n\t {";
		cur = head;

		while (true) {
			if (cur == null)
				break;
			else {
				str += "\n\t\t" + cur + ", ";
				cur = cur.getNext();
			}
		}

		cur = temp;
		return str + "\n\t}";

	}*/

}
