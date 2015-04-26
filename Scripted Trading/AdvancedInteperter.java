import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/***
 * @author YuWu
 * last update:2015/4/25 0:31
 * 
 */

public class AdvancedInteperter {

	public static String[] fileToArray(){
		BufferedReader reader = null;
		String[] store=new String[10];
		
		try {
		    File file = new File("advancedSample.txt");  //HOW TO LOAD FILE FROM CERTAIN PATH AT DATABASE??
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    
		    int storeIndex=0;
		    while ((line = reader.readLine()) != (null)){
		    	//System.out.print(ScriptInterpreter.lineNumberFormat(lineNumber++)+"-");
		    	//line=printFormat(line);
		    	//System.out.println(line);
		    	
		    	store[storeIndex++]=line;   	    	
		    }} catch (IOException e) {
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
	
	
	public static String getUser(String src){
		
		//get User's name
		String userName="";
		int index=6;
		String range="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
		while(range.indexOf(src.charAt(index))>=0){
			userName = userName+src.charAt(index);
			index++;
			if(index>=src.length()) break;    
		}
		System.out.println(userName);
		return userName;
	}
	
	public static String getTime(String src){
		String updateTime=src.substring(13, 27);
		System.out.println(updateTime);
		return updateTime;
	}
	
	public static void identify(String line){
		//get STOCK
		String stock=line.substring(0,4);
		System.out.print(stock);
		
		//get ACTION
		String action="";
		if(line.contains("buy")) action="buy";
		if(line.contains("sell")) action="sell";
		if(action.length()==0) return;
		System.out.print(" "+action);
		
		//get AMOUNT
		String amount="";
		int num=0;
		int index=6+action.length();
		while(line.charAt(index)==' ')index++;
		while(line.charAt(index)>='0'&&line.charAt(index)<='9'){
			amount = amount+line.charAt(index);
			index++;
			if(line.charAt(index)==' ') break;   
		}
		if(amount.equals("")){return;}
		num=Integer.valueOf(amount);
		System.out.print(" "+num);
		
		//get Critic
		String critic="";
		double ctc=0;
		index=line.indexOf("$")+1;
		while(line.charAt(index)==' ')index++;
		while(line.charAt(index)>='0'&&line.charAt(index)<='9'){
			critic = critic+line.charAt(index);
			index++;
			
			if(index>=line.length()||line.charAt(index)==' ') break;   
		}
		if(critic.equals("")){return;}
		ctc=Double.valueOf(critic);
		System.out.println(" "+ctc);
		
		//money change
		double change=((double)num)*ctc;
		System.out.println("money change"+" "+(action.equals("buy")?"+":"-")+change);
		
	}
	public static void main(String[] args){
		String[] text=new String[10];
		text=fileToArray();
		getUser(text[0]);
		getTime(text[1]);
		for(int i=2;i<10&&text[i]!=null;i++){
			if(text[i].length()!=0){
				if(text[i].contains("END")) break;
				identify(text[i]);
			}
			
		}
	}
}
