
/**
 * 여기에 Part2 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
import edu.duke.*;
 
public class Part2 {


	// This method returns an integer indicating how many times stringa appears in stringb, where each occurrence of stringa must not overlap with another occurrence of it. 
	
	// For example, the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times. The call howMany(“AA”, “ATAAAA”) returns 2. Note that the AA’s found cannot overlap.
	
	public int howMany (String stringa, String stringb) {
		
		int currentPos = 0;
		int count = 0;
		int straLength = stringa.length();
		
		currentPos = stringb.indexOf(stringa, currentPos);
		
		while (currentPos != -1) {
			count += 1;
			currentPos = stringb.indexOf(stringa, currentPos + straLength);
		}
		return count;
	}
	
	
	
	// Write the void method testHowMany has no parameters. Add code in here to call howMany with several examples and print the results. Think carefully about what types of examples would be good to test to make sure your method works correctly.
	
	public void testHowMany () {
	
		String stra = "AA";
		String strb = "ATAAAA";
		
		String str01 = "A";
		String str02 = "ATAAAA";
		
		String strA = "foo";
		String strB = "ATAAAA";
		
		int result01 = howMany(stra, strb);
		System.out.println(stra + " occurs " + result01 + " times in " + strb);
		
		int result02 = howMany(str01, str02);
		System.out.println(str01 + " occurs " + result02 + " times in " + str02);
		
		int result03 = howMany(strA, strB);
		System.out.println(strA + " occurs " + result03 + " times in " + strB);
		
		
	}
	
	
	
	public static void main (String[] args) {
        Part2 howManyTimes = new Part2 ();
		howManyTimes.testHowMany();
		
	}

}
