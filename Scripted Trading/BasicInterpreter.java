import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/***
 * @author YuWu
 * last update:2015/4/14 00:50
 */


public class BasicInterpreter {
	private static boolean printScoop=true;
	
	//get value (positive)
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
	public static int getValue(String src){
		if (src.length()==0) return -999999;
		String valueString="";
		int index=0;
		while(src.charAt(index)<'0'||src.charAt(index)>'9'){
			index++;
			if(index>=src.length()) {
				return -999999;
			}
						
		}
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
		if(printScoop){
			int index=0;
			while(scoop[index]!=null){
				System.out.println(scoop[index]);
				index++;
			}
			printScoop=false;
		}
		
		
		int countLength=0;
		for(int i=0;i<10;i++){ if (scoop[i]!=null) countLength++; }
		String[] result=new String[countLength];
		for(int i=0;i<result.length;i++){ result[i]=scoop[i]; }
		

		
		return result;
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
		//operate statement
		String[] statement=new String[collectScoop().length];
		statement= collectScoop();
		if (condition.equals("<")){
			for(int i=initial;i<conditionValue;i++){
				isPRINT(statement,i);
			}
		}else if(condition.equals("<=")){
			for(int i=initial;i<=conditionValue;i++){
				isPRINT(statement,i);
			}
		}else{
			System.out.println("OPERATE NEED IMPROMENT WITH INCREMENT!=3=");
			return false;
		}
		
		
		
		
		
		
		
		
		System.out.println("FINISH FOR");
		return true;
	}
	
	// PRINT 
	public static boolean isPRINT(String[] scoop,int num){
		int index=0;
		boolean result=false;
		while(scoop[index]!=null){
			if (scoop[index].contains("print")) {
				System.out.println(num);
				result=true;
			}
			index++;
			if(index>=scoop.length) break;
		}
		return result;		
	}
	
	public static boolean isPRINT(String src,int num){
		boolean result=false;
		if (src.contains("print")) {
			result=true;
			if(num!=-999999) System.out.println(num);			
		}
		return result;
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
			if(isFOR(text[i]));
			else isPRINT(text[i],getValue(text[i]));
			}
		
	}
}
