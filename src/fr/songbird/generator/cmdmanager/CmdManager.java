package fr.songbird.generator.cmdmanager;


import fr.songbird.generator.Generator;
import fr.songbird.tools.ScannerHM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.songbird.survivalDevKit.CheckInt;
import fr.songbird.survivalDevKit.Downloader;
import net.wytrem.logging.BasicLogger;
import net.wytrem.logging.LogLevel;
import net.wytrem.logging.LoggerFactory;

public class CmdManager {

	//###### PRIVATE VARIABLES ######
	
	private final static BasicLogger logger = LoggerFactory.getLogger(CmdManager.class);
	/**
	 * Reference of current instance.
	 */
	private final ScannerHM readerKeyBoard = new ScannerHM (System.in);
	
	
	/**
	 * Character number given in parameter by the user.
	 */
	private static byte characterNumber;

	
	/**
	private String[] stateOfFlagArray = new String[]{CharacteristicsFlags.WithUpperCaseOnly.getStateOfFlag(),
			CharacteristicsFlags.WithLowerCaseOnly.getStateOfFlag(),
			CharacteristicsFlags.WithLowerCaseandUpperCase.getStateOfFlag(),
			CharacteristicsFlags.WithIntandLowerCaseOnly.getStateOfFlag(),
			CharacteristicsFlags.WithIntOnly.getStateOfFlag(),
			CharacteristicsFlags.WithIntandUpperCaseOnly.getStateOfFlag(),
			CharacteristicsFlags.EveryFlags.getStateOfFlag()};
	*/
	
	//###### PUBLIC VARIABLES #####
	
	
	//###### CONSTRUCTOR ######
	
	
	public CmdManager(){
		String inputKeyBoardCatcher = null, command = null;
		inputKeyBoardCatcher= readerKeyBoard.ReadInputKeyboard();
		logger.log(LogLevel.INFO, "Vous avez saisi: "+inputKeyBoardCatcher+"\n "
				+" ===>"+inputKeyBoardCatcher.substring(inputKeyBoardCatcher.indexOf("-")+1, 2)+"\n");
		if(respectedOrder(inputKeyBoardCatcher.substring(inputKeyBoardCatcher.indexOf("-")+1, 2))){
			command = inputKeyBoardCatcher.substring(inputKeyBoardCatcher.indexOf("-")+1, 2);
			System.out.println("Demarrage du generateur... ");
			//TODO Bug a fixer ici, le generateur s'arrete dans son elan et ne fait pas son boulot.
			logger.log(LogLevel.WARNING, "Taille du mot de passe a generer: "+characterNumber);
			new Generator(getAppropriateFlagsState(command), characterNumber);
		}
		else{
			logger.log(LogLevel.ERROR, "Les arguments passés ne sont pas dans l'ordre demandé.");
		}
	}
	
	
	//###### PRIVATE METHODS ######
	
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
	@SuppressWarnings({ "unused" })
	/**
	 * Unused method for good work of the program, but useful.
	 * @param stateFlag
	 * @return Generator instance
	 *
	private final Generator generatorInvocation(final CharacteristicsFlags stateFlag){
		return new Generator(stateFlag);
	}*/
	
	private void pushInformationUser(){
		Downloader getInformationText = new Downloader("https://www.dropbox.com/s/6fvcbi6y4ktidjt/version?&dl=1", "MOUAHAHAHAHAH");
		String line = null;
		BufferedReader buffer = null;
		try{
			logger.log(LogLevel.INFO, "Préparation à la lecture du fichier en cours...");
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getInformationText.getDefinitivePath()+File.separator+getInformationText.getFNW()))));
			try{
				while((line =buffer.readLine()) != null){
					System.out.println(line);
				}
			}catch(IOException ioexception1){
				ioexception1.toString();
			}
		}catch(IOException ioexception0){
			ioexception0.printStackTrace();
		}finally{
			try{
				buffer.close();
			}catch(IOException ioexception2){
				ioexception2.printStackTrace();
			}
		}
	}
	
	
	//###### PUBLIC METHODS ######
	
	
	public static void main(String[] args){
		logger.log(LogLevel.INFO, "Detection des potentiels arguments...");
		CheckInt checker = new CheckInt();
		for(int i = 0; i<args.length; i++){
			if(checker.isInt(args[i])){
				try{
					characterNumber += (byte)Integer.parseInt(args[i]);
				}catch(NumberFormatException e0){
					e0.printStackTrace();
				}catch(Exception e1){
					e1.printStackTrace();
				}
				break;
			}
			else{
				logger.log(LogLevel.ERROR, "L'argument n'est pas un entier, fermeture de l'application...");
				Runtime.getRuntime().exit(1);
			}
		}
		
		new CmdManager();
	}
	

}
