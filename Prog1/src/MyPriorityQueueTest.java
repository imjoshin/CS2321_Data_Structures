import static org.junit.Assert.*;

import org.junit.Test;
/**
 * @author Josh Johnson
 */

public class MyPriorityQueueTest {

	@Test
	public void putTest() {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		pq.put(9);
		pq.put(7);
		pq.put(8);
		if(pq.size() != 3)
			fail("put failed.");
		
		MyPriorityQueue<String> pq1 = new MyPriorityQueue<String>();
		pq1.put("Test");
		pq1.put("apple");
		pq1.put("Zoo");
		pq1.put(null);
		pq1.put("Map");
		if(pq1.size() != 5)
			fail("put failed.");
	}
	
	@Test
	public void getMaxTest() throws EmptyQueueException {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		pq.put(9);
		pq.put(7);
		pq.put(10);
		pq.put(8);
		if((Integer) pq.getMax() != 10)
			fail("getMax did not return the correct value.");
		if((Integer) pq.getMax() != 9)
			fail("getMax did not return the correct value.");
		
		pq.getMax();
		pq.getMax();
		
		try{
			pq.getMax();
			fail("getMax did not throw EmptyQueueException.");
		}catch (EmptyQueueException e){
			
		}catch ( Exception e ){
			throw e;
		}
	}
	
	@Test
	public void getMinTest() throws EmptyQueueException {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		pq.put(9);
		pq.put(7);
		pq.put(10);
		pq.put(8);
		if((Integer) pq.getMin() != 7)
			fail("getMin did not return the correct value.");
		if((Integer) pq.getMin() != 8)
			fail("getMin did not return the correct value.");
		
		pq.getMin();
		pq.getMin();
		
		try{
			pq.getMin();
			fail("getMin did not throw EmptyQueueException.");
		}catch (EmptyQueueException e){
			
		}catch ( Exception e ){
			throw e;
		}
	}
	
	@Test
	public void toListTest() {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		pq.put(9);
		pq.put(7);
		pq.put(10);
		pq.put(8);
		List<Integer> list = (List<Integer>) pq.toList();
		if(list.size() != pq.size())
			fail("toList did not return the correct list.");
	}
	
	@Test
	public void sizeTest() {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		pq.put(9);
		pq.put(7);
		pq.put(10);
		pq.put(8);
		if((Integer) pq.size() != 4)
			fail("size did not return the correct value.");
	}
	
	@Test
	public void isEmptyTest() throws EmptyQueueException {
		MyPriorityQueue<Integer> pq = new MyPriorityQueue<Integer>();
		if(pq.size() != 0)
			fail("isEmpty did not return the correct value.");
		pq.put(7);
		pq.getMax();
		if(pq.size() != 0)
			fail("isEmpty did not return the correct value.");
	}

}
