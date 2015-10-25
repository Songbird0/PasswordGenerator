package fr.songbird.tools;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * <p>
 * PasswordGenerator<br/>
 * Copyright (C) 2015 Songbird<br/>
 * Ce programme est libre, vous pouvez le redistribuer et/ou le modifier selon les termes de la Licence Publique Générale GNU publiée par la Free Software Foundation (version 2 ou bien toute autre version ultérieure choisie par vous).
 * Ce programme est distribué car potentiellement utile, mais SANS AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de commercialisation ou d'adaptation dans un but spécifique. 
 * Reportez-vous à la Licence Publique Générale GNU pour plus de détails.
 * Vous devez avoir reçu une copie de la Licence Publique Générale GNU en même temps que ce programme ; 
 * si ce n'est pas le cas, écrivez à la Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, États-Unis.<br/><br/>
 * 
 * </p>
 *	A Scanner class home made.
 * @author songbird
 * @version 0.1_0-ALPHA
 */
public class ScannerHM {
	private BufferedReader reader = null;
	
	/**
	 * CONSTRUCTORS
	 */
	/**
	 * This constructor allowing preparation for to read the input stream.
	 * @param inputstream
	 */
	public ScannerHM(InputStream inputstream){
		if(inputstream.equals(System.in)){
			reader = new BufferedReader(new InputStreamReader(inputstream));
		}
	}
	
	
	/**
	 * 
	 * @return the input keyboard.
	 */
	public String ReadInputKeyboard(){
		String iKeyBoard = null;
		try{
			iKeyBoard = reader.readLine();
		}catch(IOException exception0){
			exception0.toString();
		}
		return iKeyBoard;
	}
	/**
	 * 
	 * @return an integer. Else, catch an NumberFormatException.
	 */
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
	
	/**
	 * 
	 * @return current instance.
	 */
	public ScannerHM getCurrentInstanceSHM(){
		return this;
	}
	
}
