
/**
	* 여기에 Part3 클래스 설명을 작성하십시오.
	* 
	* @author (작성자 이름) 
	* @version (버전번호나 날짜)
*/
import edu.duke.*;

public class Part3 {
	
	public boolean twoOccurrences(String stringa, String stringb) {
	    
		int count = 0;
		int currentIndex = 0;
		int length_stra = stringa.length();
		boolean result = false;
		
		currentIndex = stringb.indexOf(stringa, currentIndex);
		
		while (currentIndex != -1) {
			count += 1;
			currentIndex += length_stra;
			currentIndex = stringb.indexOf(stringa, currentIndex);
		}
		
		if (count >= 2) {
			result = true;
		}
		
		System.out.println(stringa + " count: " + count);
		System.out.println(stringa +  " result: " + result);
		
		return result;
	}
	
	public String lastPart (String stringa, String stringb) {
		
		String result;
		int length_stra = stringa.length();
		int length_strb = stringb.length();
		int index = stringb.indexOf(stringa);
		
		if (index == -1) {
			result = stringb;
		} else {
			index += length_stra;
			result = stringb.substring(index);
			
			/*if (index > length_strb) {
				result = ""; // the curren value of index is out of range.  
			} else {
				result = stringb.substring(index);
			}*/
		}
		return result;
	}
	
	
	public void testing () {
		
		// test twoOccurrences method
		String test_str01 = "it";
		String test_str02 = "ig";
		String test_str03 = "origins";
		String test_str04 = "al";
		String target_str = "From its medieval origins to the digital era al";
		
		System.out.println("Target String: " + target_str);
		System.out.println("");
		
		System.out.println("Does " + test_str01 + " occur more than twice? : " + twoOccurrences(test_str01, target_str));
		System.out.println("");
		
		System.out.println("Does " + test_str02 + " occur more than twice? : " + twoOccurrences(test_str02, target_str));
		System.out.println("");
		
		System.out.println("Does " + test_str03 + " occur more than twice? : " + twoOccurrences(test_str03, target_str));
		System.out.println("");
		
		System.out.println("Does " + test_str04 + " occur more than twice? : " + twoOccurrences(test_str04, target_str));
		System.out.println("");
		
		
		// lastPart method method
		
		String test_str = "an";
		
		String targetStr01 = "banana";
		String targetStr02 = "foonan";
		String targetStr03 = "darkness";
		
		// For each call print the strings passed in and the result. For example, the output for the two calls above might be:
		// The part of the string after an in banana is ana.
		// The part of the string after zoo in forest is forest.
		
		System.out.println("The part of the string after " + test_str + " in " + targetStr01 + " is " + lastPart(test_str, targetStr01) );
		System.out.println("The part of the string after " + test_str + " in " + targetStr02 + " is " + lastPart(test_str, targetStr02) );
		System.out.println("The part of the string after " + test_str + " in " + targetStr03 + " is " + lastPart(test_str, targetStr03) );
		
	}
	
	public static void main (String[] args) {
        Part3 Is_twoOccurrences = new Part3 ();
		Is_twoOccurrences.testing ();
	}
}
