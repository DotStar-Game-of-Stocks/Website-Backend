import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/***
 * @author YuWu
 * last update:2015/4/12 23:36
 */


public class BasicInterpreter {
	
	private static int getValue(String src, int index){
		String valueString="";

		while(src.charAt(index)==' ')index++;
		while(src.charAt(index)>='0'&&src.charAt(index)<='9'){
			valueString = valueString+src.charAt(index);
			index++;
			if(src.charAt(index)==';'||src.charAt(index)==' ') break;    //first ;
		}
		if(!valueString.equals("")){
			return Integer.valueOf(valueString);
		}
		return -999999;
	}
	
	private static String[] collectScoop(){ //single scoop

		String[] text=new String[10];
		text=basic(); 
		int textIndex=0;
		while(!text[textIndex].contains("{")){
			textIndex++;
		}
		textIndex++;
		String[] scoop=new String[10];
		int scoopIndex=0;
		while(!text[textIndex].contains("}")){
			if (!text[textIndex].equals("")){
				scoop[scoopIndex++]=text[textIndex++];
			}else{ textIndex++; }
			
		}
		int index=0;
		while(scoop[index]!=null){
			System.out.println(scoop[index]);
			index++;
		}
		return scoop;
	}
	
	
	
	public static boolean isFOR(String src){  // integer ; single variable ;ignore space; recognize invalid thing; basic increment
		if (!src.contains("for")) return false;
		//get initial value
		
		int index=src.indexOf("=")+1;
		int initial=getValue(src,index);
		if(initial==-999999){
			System.out.println("INVALID INITIAL VALUE");
			return false;
		}
		else{
			System.out.println("initial = "+initial);
		}
		/*
		String initialString="";
		while(src.charAt(index)==' ')index++;
		while(src.charAt(index)>='0'&&src.charAt(index)<='9'){
			initialString = initialString+src.charAt(index);
			index++;
			if(src.charAt(index)==';'||src.charAt(index)==' ') break;    //first ;
		}
		if(!initialString.equals("")){
			int initial=Integer.valueOf(initialString);
			System.out.println("initial = "+initial);
		}
		*/
		
		// get condition
		String condition="";
		if     (src.contains(">=")) condition=">=";
		else if(src.contains("<=")) condition="<=";
		else if(src.contains(">"))  condition=">";
		else if(src.contains("<"))  condition="<";
		else {
			System.out.println("INVALID CONDITION");
			return false;
		}
		
		// get condition value
		if(condition.length()==1){
			index=src.indexOf(condition)+1;			
		}else{
			index=src.indexOf(condition)+2;
		}
		int conditionValue=getValue(src,index);
		if(conditionValue==-999999){
			System.out.println("INVALID CONDITION VALUE");
			return false;
		}
		else{
			System.out.println("condition value = "+conditionValue);
		}
		
		// get increment
		String incre="";
		if (src.contains("++")){
			incre="++";
			System.out.println("increment = "+incre);
		}else{
			System.out.println("INVALID INCREMENT");
			return false;
		}
		//operate
		String[] statement=new String[10];
		statement= collectScoop();
		
		
		System.out.println("YES!");
		return true;
	}
	
	public static String[] basic(){
		BufferedReader reader = null;
		String[] store=new String[10];
		
		try {
		    File file = new File("basic.txt");  //HOW TO LOAD FILE FROM CERTAIN PATH AT DATABASE??
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    
		    int storeIndex=0;
		    while ((line = reader.readLine()) != (null)){
		    	//System.out.print(ScriptInterpreter.lineNumberFormat(lineNumber++)+"-");
		    	//line=printFormat(line);
		    	//System.out.println(line);
		    	store[storeIndex++]=line;   	    	
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
		return store;
	} 


	
	public static void main(String[] args){
		String[] text=new String[10];
		text=basic();
		for(int i=0;i<10&&text[i]!=null;i++){
			isFOR(text[i]);
			}
		
	}
}
