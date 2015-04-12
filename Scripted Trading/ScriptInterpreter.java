import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/***
 * @author YuWu
 * last update:2015/4/12 12:55
 */

public class ScriptInterpreter {
	
	public static String lineNumberFormat(int num){
		String result="";
		int digit=Integer.toString(num).length();
		while(digit<3){
			result+="0";
			digit++;
		}
		result= result + Integer.toString(num);
		return result;
	}
	
	public static String printFormat(String src){  //modify format of space
		String result=src;
		while(result.indexOf("  ")>=0){
			int uselessSpace=result.indexOf("  ");
			result = result.substring(0,uselessSpace)+result.substring(uselessSpace+1);
		}
		return result;
	}
	
	public static boolean containsValidInfo(String src){
		boolean result=false;
		char[] srcArray=new char[src.length()];
		srcArray=src.toCharArray();
		for(int i=0;i<srcArray.length;i++){
			if      (srcArray[i]>='a'&&srcArray[i]<='z') {
				result=true;
				break;
			}
			else if (srcArray[i]>='A'&&srcArray[i]<='Z') {
				result=true;
				break;
			}
			else if (srcArray[i]>='0'&&srcArray[i]<='9') {
				result=true;
				break;
			}
		}
		return result;
	}
	
	
	
	
	
	//contains sensitive word, then catch condition, create operation and evoke action
	public static String catchConditionForKeyWord(String originalLine, String key){
		String result="";
		
		int keyPosition=originalLine.indexOf(key);
		int startIndex=keyPosition+key.length(); //need modify

		if (originalLine.contains("price")){ 
			int i=startIndex+1;
			while(originalLine.charAt(i)>='0'&&originalLine.charAt(i)<='9'){
			result = result+originalLine.charAt(i);
			i++;
			if(i>=originalLine.length()) break;
			} }
		else if ((key.equals("increase")||key.equals("decrease"))&&originalLine.contains("%")){ 
			//result = originalLine.substring(startIndex, endIndex);
			} //m
		//else if (originalLine.contains("SensitiveWords"))
		
		if(!result.equals("")) return result;
		
		return "INVALID COMMAND";
	}
	
	
	
	
	
	
	
	public static void printValidInfo(){
		BufferedReader reader = null;
        int lineNumber=0;
        
		try {
		    File file = new File("sample.txt");  //HOW TO LOAD FILE FROM CERTAIN PATH AT DATABASE??
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != (null)&&ScriptInterpreter.containsValidInfo(line)){
		    	//System.out.print(ScriptInterpreter.lineNumberFormat(lineNumber++)+"-");
		    	line=printFormat(line);
		    	System.out.println(catchConditionForKeyWord(line,"price"));
		    	
		        //System.out.println(line);
		    }
		    	
		    	

		} catch (IOException e) {
		    e.printStackTrace();
		    
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	} 


	
	public static void main(String[] args){
		
		printValidInfo(); //argument of this method relate to user's information
	}

}
