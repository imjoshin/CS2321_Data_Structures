import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Josh Johnson
 * Date: 10/8/14
 * Program 4
 */

public class MyOrderedDoublyLinkedListTest {

	@Test
	public void addTest() throws EmptyListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test first add
		list.add(14);
		if (list.size() != 1 || list.get() != 14) {
			fail("Add failed.");
		}
		//test several adds
		list.add(18);
		list.add(160);
		list.add(3);
		//test null
		list.add(null);
		list.add(8);
		list.add(4);
		//System.out.println(list);
		if (list.size() != 7 || list.get() != 4) {
			fail("Add failed.");
		}
	}
	
	@Test
	public void insertTest() throws EmptyListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test first add
		list.insert(14);
		if (list.size() != 1 || list.get() != 14) {
			fail("Add failed.");
		}
		//test several adds
		list.insert(18);
		list.insert(160);
		list.insert(3);
		//test null
		list.add(null);
		list.insert(8);
		list.insert(4);
		if (list.size() != 7 || list.get() != 4) {
			fail("Add failed.");
		}
	}

	@Test
	public void removeTest() throws EmptyListException, EndOfListException {
		MyOrderedDoublyLinkedList<String> list = new MyOrderedDoublyLinkedList<String>();
		
		//test removing a single element
		list.add("Cat");
		list.remove();
		if (list.size() != 0 || !list.isEmpty()) {
			fail("Remove failed.");
		}
		
		//test removing multiple elements
		list.add(null);
		list.add("Zebra");
		list.add("test");
		list.add(null);
		//System.out.println(list);
		
		list.next();
		list.next();
		list.remove();
		list.remove();
		
		if (list.size() != 2 || list.get() != null) {
			fail("Remove failed.");
		}

		//test removing on empty list
		list = new MyOrderedDoublyLinkedList<String>();
		try {
			list.remove();
			fail("Remove did not throw EmptyQueueException.");
		}catch (EmptyListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
	}
		
	@Test
	public void getTest() throws EmptyListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		
		//test get on list of size one
		list.insert(14);
		if (list.get() != 14) {
			fail("Get failed.");
		}
		
		//test get on multiple-length list
		list.insert(18);
		list.insert(160);
		list.insert(3);
		if (list.get() != 3) {
			fail("Get failed.");
		}
		
		//test get on empty list
		list = new MyOrderedDoublyLinkedList<Integer>();
		try {
			list.get();
			fail("Get did not throw EmptyQueueException.");
		}catch (EmptyListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
	}

	@Test
	public void nextTest() throws EmptyListException, EndOfListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		
		//test on multiple list
		list.insert(14);
		list.insert(18);
		list.insert(160);
		list.insert(3);
		
		if (list.next() != 14) {
			fail("Next failed.");
		}
		if (list.next() != 18) {
			fail("Next failed.");
		}
		
		//test on end of list
		try {
			list.next();
			list.next();
			fail("Next did not throw EndOfListException.");
		}catch (EndOfListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
		
		//test next on empty list
		list = new MyOrderedDoublyLinkedList<Integer>();
		try {
			list.prev();
			fail("Next did not throw EmptyListException.");
		}catch (EndOfListException e) {
			
		}catch (EmptyListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
	}

	@Test
	public void hasNextTest() throws EmptyListException, EndOfListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test hasNext on empty list
		if (list.hasNext()) {
			fail("hasNext failed.");
		}
		
		//test multiple times on populated list
		list.insert(14);
		list.insert(3);
		
		if (!list.hasNext()) {
			fail("hasNext failed.");
		}
		list.next();
		if (list.hasNext()) {
			fail("hasNext failed.");
		}
	}
	
	@Test
	public void prevTest() throws EmptyListException, EndOfListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		
		//test on multiple list
		list.insert(14);
		list.insert(18);
		list.insert(3);
		list.insert(160);
		
		if (list.prev() != 18) {
			fail("Prev failed.");
		}
		if (list.prev() != 14) {
			fail("Prev failed.");
		}
		
		//test on beginning of list
		try {
			list.prev();
			list.prev();
			fail("Prev did not throw EndOfListException.");
		}catch (EndOfListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
		
		//test prev on empty list
		list = new MyOrderedDoublyLinkedList<Integer>();
		try {
			list.prev();
			fail("Prev did not throw EmptyListException.");
		}catch (EndOfListException e) {
			
		}catch (EmptyListException e) {
			
		}catch ( Exception e ) {
			throw e;
		}
	}

	@Test
	public void hasPrevTest() throws EmptyListException, EndOfListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test hasPrev on empty list
		if (list.hasPrev()) {
			fail("hasPrev failed.");
		}
		
		//test multiple times on populated list
		list.insert(3);
		list.insert(14);
		
		if (!list.hasPrev()) {
			fail("hasPrev failed.");
		}
		list.prev();
		if (list.hasPrev()) {
			fail("hasPrev failed.");
		}		
	}
	@Test
	public void resetTest() throws EmptyListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		list.insert(14);
		list.insert(3);	
		list.insert(18);
		list.insert(160);
		list.reset();
		if (list.get() != 3) {
			fail("Reset failed.");
		}
	}

	@Test
	public void sizeTest() {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test on empty list
		if (list.size() != 0) {
			fail("Size failed.");
		}
		
		//test on populated list
		list.insert(14);
		list.insert(3);	
		list.insert(18);
		list.insert(160);
		
		if (list.size() != 4) {
			fail("Size failed.");
		}
	}

	@Test
	public void isEmptyTest() throws EmptyListException {
		MyOrderedDoublyLinkedList<Integer> list = new MyOrderedDoublyLinkedList<Integer>();
		//test on empty list
		if (!list.isEmpty()) {
			fail("isEmpty Failed.");
		}
		
		//test on populated
		list.insert(14);
		if (list.isEmpty()) {
			fail("isEmpty Failed.");
		}
		
		//populate, remove, and test
		list.insert(3);
		list.remove();
		list.remove();
		
		if (!list.isEmpty()) {
			fail("isEmpty Failed.");
		}
		
	}
}
