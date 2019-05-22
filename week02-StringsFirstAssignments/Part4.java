
/**
 * 여기에 Part4 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
import edu.duke.*;

public class Part4 {
	
	public void youtubeLinks (String url){
		
		URLResource src = new URLResource(url);
		
		for (String line: src.lines()) {
		
			if (hasYoutube(line) == true) {
				System.out.println(extractURL(line));
			}
		}
	}
	
	
	public boolean hasYoutube (String line) {
		
		if (line.indexOf("youtube.com") == -1) {
			return false;
		}
		
		return true;
	}
	
	
	public String extractURL(String line) {
		
		String result;
		String lineLowerCase = line.toLowerCase();
		int posYoutubeCom = lineLowerCase.indexOf("www.youtube.com");
		
		int leftQuotation = lineLowerCase.lastIndexOf("\"", posYoutubeCom);
		int rightQuotation = lineLowerCase.indexOf("\"", leftQuotation + 1);
		
		result = line.substring(leftQuotation + 1, rightQuotation);
		
		return result;
		
	}
	
	
	public static void main (String[] args) {
		
		String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        Part4 webPage = new Part4 ();
		webPage.youtubeLinks (url);
		
	}
	
}







