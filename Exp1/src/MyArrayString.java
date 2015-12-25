
public class MyArrayString implements MyArrayStringInterface{

	private char[] str;
	/*
	 * initializes a newly created MyArrayString object so that it represents 
	 * an empty character sequence; i.e, the empty string.
	 */
	public MyArrayString() {
		str = new char[0];
	}

	/*
	 * initializes a newly created MyArrayString object so that
	 * it represents a string consisting of just ch.
	 */
	public MyArrayString(char ch) {
		str = new char[1];
		str[0] = ch;
	}

	/*
	 * initializes a newly created MyArrayString object so that
	 * it represents the given char array.
	 */
	public MyArrayString(char[] ch) {
		str = ch;
	}
	
	/*
	 * initializes a newly created MyArrayString
	 * object so that it represents the same string as otherMyArrayString.
	 */
	public MyArrayString(MyArrayString otherMyArrayString) {
		str = otherMyArrayString.getArrayString();
	}

	public boolean myEquals(Object o) {
		if(o != null){ //if null, return false
			if(o instanceof MyArrayString){ //if not a MyArrayString, throw exception
				if(((MyArrayString) o).myLength() != str.length) //if they aren't the same length, they can't be equal
					return false;
				else{
					for(int i = 0; i < str.length; i++){ //once a mismatch is found, return false
						if(((MyArrayString) o).myCharAt(i) != str[i])
							return false;
					}
					return true; //if all tests pass, return true
				}
			}else{
				throw new IllegalArgumentException();
			}
		}
		return false;
		
	}

	public char myCharAt(int index) throws IndexOutOfBoundsException {
		if(index >= str.length || index < 0) //check if index is in bounds
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		return str[index];
			
	}

	public MyArrayStringInterface myConcat(MyArrayStringInterface otherMyArrayString) throws IllegalArgumentException {
		if(otherMyArrayString == null) //throw exception if arg is null
			throw new IllegalArgumentException("Null is not a valid argument!");
		
		char[] temp = new char[str.length + otherMyArrayString.myLength()]; //create array of appropriate length
		for(int i = 0; i < str.length; i++) //copy current str into first part of the new array
			temp[i] = str[i];
		
		for(int i = str.length; i < str.length + otherMyArrayString.myLength(); i++) //copy arg into second part of the new array
			temp[i] = otherMyArrayString.myCharAt(i - str.length);			
		
		return new MyArrayString(temp);
	}

	public int myIndexOf(char ch) throws IndexOutOfBoundsException {
		for(int i = 0; i < str.length; i++) //search until ch is found
			if(str[i] == ch) return i;
		return -1;
	}

	public int myLength() {
		return str.length;
	}

	public void mySetAt(int index, char ch) throws IndexOutOfBoundsException {
		if(index >= str.length || index < 0) //check if index is in bounds
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		
		str[index] = ch;
	}

	public MyArrayStringInterface mySubString(int low, int high) throws IndexOutOfBoundsException {
		if(low >= str.length || low < 0){ //appropriate index checking
			throw new IndexOutOfBoundsException("Low index " + low + " is out of bounds!");
		}else if(high >= str.length || high < 0){
			throw new IndexOutOfBoundsException("High index " + high + " is out of bounds!");
		}else if(low > high){
			throw new IndexOutOfBoundsException("Low index " + low + " is greater than high index " + high + "!");
		}else{
			char[] temp = new char[high-low]; //create new array of correct size
			for(int i = low; i < high; i++) //copy low up to high index into new array
				temp[i - low] = str[i];
			
			return new MyArrayString(temp);
		}
	}

	public char[] getArrayString() {
		return str;
	}
	
	/*
	//debugging only
	public String toString(){
		String temp = "";
		for(int i = 0; i < str.length; i++){
			temp += str[i];
		}
		return temp;
	}
	*/

}
