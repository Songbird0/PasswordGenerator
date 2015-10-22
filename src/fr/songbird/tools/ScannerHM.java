package fr.songbird.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 
 * @author songbird
 *	A Scanner class home made.
 */
public class ScannerHM {
	private BufferedReader reader = null;
	private ScannerHM readerKeyBoard = null;
	
	/**
	 * CONSTRUCTORS
	 */
	
	public ScannerHM(InputStream inputstream){
		if(inputstream.equals(System.in)){
			reader = new BufferedReader(new InputStreamReader(inputstream));
		}
	}
	
	
	
	public String ReadInputKeyboard(){
		String iKeyBoard = null;
		try{
			iKeyBoard = reader.readLine();
		}catch(IOException exception0){
			exception0.toString();
		}
		return iKeyBoard;
	}
	public int ReadAndParseInt(){
		String iKeyBoard = null;
		try{
			iKeyBoard = reader.readLine();
		}catch(IOException exception1){
			exception1.getMessage();
		}
		char[] tabOfVerif = iKeyBoard.toCharArray();
		for(int i = 0; i<tabOfVerif.length; i++){
			if(!Character.isDigit(tabOfVerif[i])){
				throw new NumberFormatException();
			}
		}
		return Integer.parseInt(iKeyBoard);
	}
	
	public ScannerHM getCurrentInstanceSHM(){
		return this;
	}
	
}
