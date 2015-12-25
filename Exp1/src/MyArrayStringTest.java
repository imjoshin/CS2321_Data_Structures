import static org.junit.Assert.*;

import org.junit.Test;


public class MyArrayStringTest {


	//Only used for creating test cases
	//Creates a MyArrayString object with character array 'str'
	public MyArrayString createArrayString(String str){
		if(str == null || str.length() == 0) return null;
		MyArrayString arrayString = new MyArrayString(str.charAt(0));
		for(int i = 1; i < str.length(); i++)
			arrayString = (MyArrayString) arrayString.myConcat(new MyArrayString(str.charAt(i)));
		return arrayString;
	}

	@Test
	public void testDefaultConstructor( ) {
		MyArrayString obj = new MyArrayString( );
		if ( obj.myLength( ) != 0 ) {
			fail( "Length of DefaultConstructor array != 0." );
		}
	}

	@Test
	public void testCharacterConstructor( ) {
		MyArrayString obj = new MyArrayString( 'a' );
		if ( obj.myCharAt(0) != 'a' ) {
			fail( "CharacterConstructor element != input." );
		}
	}

	@Test
	public void testMyArrayStringConstructor( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( obj1 );
		if ( obj1.myCharAt(0) != obj2.myCharAt(0) ) {
			fail( "MyArrayStringConstructor elements not equal." );
		}
	}

	@Test
	public void testEquals( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		try {
			obj1.myConcat( null );
			fail( "obj1.myConcat( null ) did not throw bad Arf." );
		} catch ( IllegalArgumentException e ) {
			// Expected Error - Test Succeeded
		} catch ( Exception e ) {
			// Unexpected Error - re throw;
			throw e;
		}
	}
	
	@Test
	public void testMyEquals(){
		MyArrayString obj = createArrayString("This is a test.");
		try {
			obj.myEquals(10);
			fail( "obj.myEquals( 10 ) did not throw bad Arf." );
		}catch(IllegalArgumentException e){
			
		}catch(Exception e){
			
		}
		
		if(!obj.myEquals(createArrayString("This is a test").myConcat(new MyArrayString('.')))){
			fail("obj.myEquals failed.");
		}
	}

	@Test
	public void testMyCharAt( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3 ) );
		if ( obj4.myCharAt(1) != 'b' ) {
			fail( "MyConcat wrong 1 element." );
		}
	}

	@Test
	public void testMyConcat( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3 ) );
		if ( obj4.myLength( ) != 3 ) {
			fail( "MyConcat wrong length." );
		}
	}

	@Test
	public void testMyIndexOf( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3.myConcat( obj2 ) ) );
		if ( obj4.myIndexOf( 'b' ) != 1 ) {
			fail( "myIndexOf incorrect position." );
		}
	}

	@Test
	public void testMyLength( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3.myConcat( obj2 ) ) );
		if ( obj4.myLength() != 4 ) {
			fail( "myLength incorrect" );
		}
	}

	@Test
	public void testMySetAt( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3 ) );
		obj4.mySetAt(1, 'z');
		if ( obj4.myCharAt(1) != 'z' ) {
			fail( "setAt wrong 1 element." );
		}
	}

	@Test
	public void testReturnArray( ) {
		MyArrayString obj1 = new MyArrayString( 'a' );
		MyArrayString obj2 = new MyArrayString( 'b' );
		MyArrayString obj3 = new MyArrayString( 'c' );
		MyArrayString obj4 = (MyArrayString) obj1.myConcat( obj2.myConcat( obj3 ) );
		if ( obj4.getArrayString( )[1] != 'b' ) {
			fail( "returnArray wrong element value." );
		}
	}

	@Test
	public void testMySubString(){
		MyArrayString obj = createArrayString("This is a test.");
		MyArrayString test = createArrayString("test");
		if(!obj.mySubString(10, 14).myEquals(test)){
			fail("mySubString did not return \"test\"");
		}
	}
}
