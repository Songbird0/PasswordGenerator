package fr.songbird.generator.cmdmanager;


import fr.songbird.generator.Generator;
import fr.songbird.tools.ScannerHM;

import fr.songbird.survivalDevKit.CheckInt;
import fr.songbird.survivalDevKit.Downloader;
import net.wytrem.logging.BasicLogger;
import net.wytrem.logging.LogLevel;
import net.wytrem.logging.LoggerFactory;
/**
 * 
 * @author songbird
 * @version 0.3_4-ALPHA
 * @since PasswordGenerator-0.1_0-ALPHA
 * @see CmdManager#CmdManager()
 */
public class CmdManager {

	//###### PRIVATE VARIABLES ######
	/**
	 * A unique logger, for all govern.
	 */
	private final static BasicLogger logger = LoggerFactory.getLogger(CmdManager.class);
	/**
	 * Reference of current instance.
	 */
	private final ScannerHM readerKeyBoard = new ScannerHM (System.in);
	
	
	/**
	 * Character number given in parameter by the user.
	 */
	private static byte characterNumber;

	
	
	//###### PUBLIC VARIABLES #####
	
	
	//###### CONSTRUCTOR ######
	
	/**
	 * <p>
	 * The command manager checks the argument prompt entered by the user,
	 * and ensures the smooth operation between different classes.<br/>
	 * His main job is to send the goods informations to Generator. 
	 * </p>
	 * 
	 * 
	 * 
	 */
	public CmdManager(){
		String inputKeyBoardCatcher = null, command = null, lang = null;
		System.out.println("French or english speaker ? [FR/EN]:");
		lang = readerKeyBoard.ReadInputKeyboard();
		//TODO Structure conditionnelle provisoire 
		if(lang.equals("FR")){
			downloadInformationUser((lang.equals("FR")? "LISEZ_MOI_FR": "READ_ME_EN"),
					lang.equals("FR")? "https://www.dropbox.com/s/khocq519rsyuzj0/LISEZ_MOI_FR.html?&dl=1" : "sorry, but the 'readme' in english isn't  yet avalable. Soon ;-)");
		}
		else{
			System.out.println("sorry, but the 'readme' in english isn't  yet avalable. Soon ;-)\n\n");
		}
		System.out.print("Choose your flags (i ,l, M, il, iM, lM, or ilM): ");
		inputKeyBoardCatcher= readerKeyBoard.ReadInputKeyboard();
		int indexOfFlags = inputKeyBoardCatcher.indexOf("-")+1;
		if(respectedOrder(inputKeyBoardCatcher.substring(indexOfFlags, indexOfFlags+getLimit(inputKeyBoardCatcher)))){
			command = inputKeyBoardCatcher.substring(indexOfFlags, indexOfFlags+getLimit(inputKeyBoardCatcher));
			System.out.println("Demarrage du generateur... ");
			logger.log(LogLevel.WARNING, "Taille du mot de passe a generer: "+characterNumber);
			new Generator(getAppropriateFlagsState(command), characterNumber);
		}
		else{
			logger.log(LogLevel.ERROR, "Les arguments passés ne sont pas dans l'ordre demandé.");
		}
	}
	
	//###### PRIVATE METHODS ######
	public int getLimit(String flag){
		if(heExists("ilM", flag) && heExists("-",flag.indexOf("ilM")-1, flag)){
			return 3; //indexOfFlags+3
		}
		else if(heExists("il", flag) && heExists("-", flag.indexOf("il")-1, flag)){
			return 2;
		}
		else if(heExists("iM", flag) && heExists("-", flag.indexOf("iM")-1, flag)){
			return 2;
		}
		else if(heExists("lM", flag) && heExists("-", flag.indexOf("lM")-1, flag)){
			return 2;
		}
		else if(heExists("i", flag) && heExists("-", flag.indexOf("i")-1, flag)){
			return 1;
		}
		else if(heExists("l", flag) && heExists("-", flag.indexOf("l")-1, flag)){
			return 1;
		}
		else if(heExists("M", flag) && heExists("-", flag.indexOf("M")-1, flag)){
			return 1;
		}
		return 0;
	}
	private boolean heExists(String targetCharacter, int targetIndex, String initialString){
		if(targetIndex == initialString.indexOf(targetCharacter)){
			return true;
		}
		return false;
	}
	private boolean heExists(String target, String initialString){
		if(initialString.indexOf(target)!=-1){
			return true;
		}
		return false;
	}
	private boolean respectedOrder(String readerKeyBoardReturn){
		String flags = readerKeyBoardReturn;
		switch(flags){
			case "i": return true;
			case "l": return true;
			case "M": return true;
			case "il": return true;
			case "iM": return true;
			case "lM": return true;
			case "ilM": return true;
			default:
				return false;
		}
	}
	
	public CharacteristicsFlags getAppropriateFlagsState(String command){
		switch(command){
			case "i": return CharacteristicsFlags.WithIntOnly;
			case "l": return CharacteristicsFlags.WithLowerCaseOnly;
			case "M": return CharacteristicsFlags.WithUpperCaseOnly;
			case "il": return CharacteristicsFlags.WithIntandLowerCaseOnly;
			case "iM": return CharacteristicsFlags.WithIntandUpperCaseOnly;
			case "lM": return CharacteristicsFlags.WithLowerCaseandUpperCase;
			case "ilM": return CharacteristicsFlags.EveryFlags;
			default:
				return CharacteristicsFlags.UnknownCommand;
		}
	}
	/**
	 * This method downloads and reads the documents.
	 * @param filename 
	 * @see Downloader
	 */
	private void downloadInformationUser(String filename, String fileURL){
		new Downloader(fileURL, filename);
	}
	
	
	//###### PUBLIC METHODS ######
	
	public static boolean argDetection(String[] args){
		boolean isIntOrNot = false;
		logger.log(LogLevel.INFO, "Detection des potentiels arguments...");
		CheckInt checker = new CheckInt();
		int i = 0x0;
		if(args.length == 1 && checker.isInt(args[i])){
			try{
				characterNumber += (byte)Integer.parseInt(args[i]);
				System.out.println("characterNumber == "+characterNumber);
			}catch(NumberFormatException e0){
				e0.printStackTrace();
				Runtime.getRuntime().exit(1);
			}catch(Exception e1){
				e1.printStackTrace();
				Runtime.getRuntime().exit(1);
			}
			return isIntOrNot = true;
				
		}
		logger.log(LogLevel.ERROR, "L'argument n'est pas un entier, fermeture de l'application...");
		return isIntOrNot;
		
	}
	public static void main(String[] args){

		if(CmdManager.argDetection(args)){
			new CmdManager();
		}else{
			Runtime.getRuntime().exit(1);
		}
	}
	

}
