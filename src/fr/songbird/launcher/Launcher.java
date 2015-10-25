package fr.songbird.launcher;

import fr.songbird.generator.cmdmanager.CmdManager;
import fr.songbird.survivalDevKit.Downloader;
import fr.songbird.tools.ScannerHM;

/**
 * 
 * @author songbird
 * @version 0.1_0-ALPHA
 */
public class Launcher {

	private static String[] argsOfMethodMain = null;
	private  Downloader dl = null;
	/**
	 * Reference of current instance.
	 */
	private final ScannerHM readerKeyBoard = new ScannerHM (System.in);
	
	public Launcher(){
		aLittleFormality();
		if(CmdManager.argDetection(getArgsOfMethodMain())){
			new CmdManager();
		}
		else{
			Runtime.getRuntime().exit(1);
		}
	}
	//###### PRIVATE METHODS ######
	
	
	
	/**
	 * This method downloads and reads the documents.
	 * @param filename 
	 * @see Downloader
	 */
	private void downloadInformationUser(String filename, String fileURL){
		dl= new Downloader(fileURL, filename);
	}
	private static String[] getArgsOfMethodMain(){
		return Launcher.argsOfMethodMain;
	}
	/**
	 * Downloads necessaries files.
	 */
	private void aLittleFormality(){
		String lang = null;
		String response = new String("");
		System.out.println("French or english speaker ? [FR/EN]:");
		lang = readerKeyBoard.ReadInputKeyboard();
		//TODO Structure conditionnelle provisoire 
		if(lang.equals("FR")){
			downloadInformationUser((lang.equals("FR")? "LISEZ_MOI_FR": "READ_ME_EN"),
					lang.equals("FR")? "https://www.dropbox.com/s/khocq519rsyuzj0/LISEZ_MOI_FR.html?&dl=1" : "sorry, but the 'readme' in english isn't  yet avalable. Soon ;-)");
			
			while(!response.equals("y") || !response.equals("Y")){
				System.out.println("Avez-vous lu la documentation téléchargée ? y/n");
				response = readerKeyBoard.ReadInputKeyboard();
				if(response.equals("n") || response.equals("N")){
					System.out.println("Alors allez-y ! :-)\nPour rappel, elle se trouve ici: "+dl.getDefinitivePath()+"\n\n");
					continue;
				}
				else if(response.equals("y") || response.equals("Y")){
					break;
				}
			}
		}
		else{
			System.out.println("sorry, but the 'readme' in english isn't  yet avalable. Soon ;-)\n\n");
		}

	}
	
	
	//###### PUBLIC METHODS ######
	
	
	
	public static void main(String[] args) {
		Launcher.argsOfMethodMain = args;
		new Launcher();
	}

}
