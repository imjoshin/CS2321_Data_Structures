
public class CopyOfMyOrderedDoublyLinkedList<Item extends Comparable<Item>> extends List<Item> implements ListInterface<Item>{
   protected class ListNode {
	      private Item value = null;
	      private ListNode next = null;
	      private ListNode prev = null;

	      public Item getValue( ) {
	         return value;
	      }

	      public void setValue( Item newValue ) {
	         value = newValue;
	      }


	      public ListNode getNext( ) {
	         return next;
	      }

	      public void setNext( ListNode nextNode ) {
	         next = nextNode;
	      }
	      
	      public ListNode getPrev( ) {
	         return prev;
	      }

	      public void setPrev( ListNode prevNode ) {
	         prev = prevNode;
	      }
	   }
	   
	public void add(Item value) {
		if(size == 0){
			super.add(value);
		}else{
			try {
				if(value.compareTo(get()) > 0){
					while(hasNext()){
						try {
							super.next();
							if(value.compareTo(get()) <= 0){
								super.insert(value);
								break;
							}
						} catch (EndOfListException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(!hasNext()){
						super.add(value);
					}
				}else if(value.compareTo((Item) get()) < 0){
					while(hasPrev()){
						try {
							super.prev();
							if(value.compareTo(get()) > 0){
								super.add(value);
								break;
							}
						} catch (EndOfListException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					if(!hasPrev()){
						super.insert(value);
					}
				}else{
					super.add(value);
				}
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println("Added " + value);
	}
	
	public void insert(Item value) {
		add(value);
	}
/*
	public String toString() {
		ListNode temp = cur;
		
		if(size == 0)
			return "{}";
		
		String str = "{";
		reset();
		while(true){
			try {
				str += "\"" + get().toString() + "\", ";
				if(hasNext())
					next();
				else
					break;
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EndOfListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		cur = temp;
		return str.substring(0, str.length() - 2) + "}";

	}
	*/
}
