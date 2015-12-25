import static org.junit.Assert.*;

import org.junit.Test;

/*
 * This is an example JUnit test suite for Program 0.
 * NOTE: This is by no means a complete test suite.
 *       More test cases need to be added.
 */
public class MyGenericStackTest {
   public String reverseString( MyGenericStack<Character> stack, String string ) {
      String result = "";
      for( int index = 0; index < string.length( ); index++ ) {
         stack.push( string.charAt( index ) );
      } // for
      while ( !stack.isEmpty( ) ) {
         result += stack.pop( );
      } // while
      return result;
   }
   
   @Test
   public void testReverseStringWithStack( ) {
      MyGenericStack<Character> stack = new MyGenericStack<Character>( );
      String source = "Hello World!";
      String target = "!dlroW olleH";
      if ( !target.equals( reverseString( stack, source ) ) ) {
         fail( "testReverseStringWithStack Failed" );
      }
   }

   @Test
   public void testPush( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      if ( (int) stack.peek( ) != 5 ) {
         fail( "Push Error" );
      }
   }

   @Test
   public void testPop( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      int result = (int) stack.pop( );
      if ( result != 5 ) {
         fail( "Pop Error" );
      }
   }

   @Test
   public void testNullPop( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      try{
          int result1 = (int) stack.pop( );
          int result2 = (int) stack.pop( );
          fail("Popping an empty stack did not throw an error.");
      }catch (NullPointerException e){
    	  
      }catch ( Exception e ){
    	  throw e;
      }
   }
   
   @Test
   public void testPeek( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      if ( (int) stack.peek( ) != 5 ) {
         fail( "Peek Error" );
      }
   }

   @Test
   public void testNullPeek( ) {
	      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
	      stack.push( 5 );
	      try{
	          int result1 = (int) stack.pop( );
	          int result2 = (int) stack.pop( );
	          fail("Peeking at an empty stack did not throw an error.");
	      }catch (NullPointerException e){
	    	  
	      }catch ( Exception e ){
	    	  throw e;
	      }
   }
   
   @Test
   public void testSize( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      if ( stack.size( ) != 1 ) {
         fail( "Size Error" );
      }
   }

   @Test
   public void testIsEmpty( ) {
      MyGenericStack<Integer> stack = new MyGenericStack<Integer>( );
      stack.push( 5 );
      if ( stack.isEmpty( ) ) {
         fail( "IsEmpty Error" );
      }
   }

}