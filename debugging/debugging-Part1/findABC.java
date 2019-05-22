
/**
 * 여기에 findABC 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class findABC {
	
	public void findAbc(String input) {
    
		int index = input.indexOf("abc");
		while (true) {
			if (index == -1 || (index + 4) > input.length()) {
				break;
			}
			
			String found = input.substring(index+1, index+4);
			System.out.println(found);
			index = input.indexOf("abc", index+1);
			
			
		}
	}
   public void test() {
		//findAbc("abcdabc");
		//findAbc("abcbbbabcdddabc");
		//findAbc("aaaaabc");
		//findAbc("yabcyabc");
		//findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		findAbc("abcabcabcabca"); 
		
	}
	
	public static void main (String[] args) {
        findABC find = new findABC();
		find.test();
    }
}
