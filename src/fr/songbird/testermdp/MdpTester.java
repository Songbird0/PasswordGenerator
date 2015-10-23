package fr.songbird.testermdp;

import java.util.HashMap;


import fr.songbird.generator.PasswordGenerated;

import fr.songbird.generator.SolidityFlags;
import fr.songbird.survivalDevKit.CheckEntry;

import net.wytrem.logging.BasicLogger;
import net.wytrem.logging.LogLevel;
import net.wytrem.logging.LoggerFactory;

/**
 * MdpTest attributes a tag to generated passwords by Generator class.
 * @author songbird
 * @version 0.1_1-ALPHA
 * @see{@link{fr.songbird.generator.Generator}}
 *
 */
public class MdpTester {
	
	//###### PRIVATE VARIABLE  ######
	private static final BasicLogger logger = LoggerFactory.getLogger(MdpTester.class);
	private PasswordGenerated pwg = null;
	private HashMap<SolidityFlags, byte[]> difficultyArray = new HashMap<SolidityFlags, byte[]>();
	private SolidityFlags[] flagsArray = SolidityFlags.values();
	
	//###### Constructor ######
	
	public MdpTester(String testTarget){
		pwg = testProcedure(testTarget);
		logger.log(LogLevel.SUCCESS, "Mot de passe: "+pwg.getPasswordGenerated()+"\n Flag de sécurité: ["+pwg.getSolidityFlags()+"]");
	}
	
	//###### PRIVATE METHODS ######
	
	private PasswordGenerated testProcedure(String password){
		getAppropriateFlagsInString((byte)10);
		DiffcultyArrayinitialization();
		CheckEntry checker = new CheckEntry();
		checker.entryChecking(password, true);
		for(HashMap.Entry<SolidityFlags, byte[]>DA : difficultyArray.entrySet()){
			if(standardPassword(password, DA.getValue())){
				logger.log(LogLevel.SUCCESS, "Félicitations ! Votre mot de passe respecte les 'standards' de sécurité du générateur.");
				Runtime.getRuntime().exit(0);
			}
		}
		logger.log(LogLevel.WARNING, "Votre mot de passe ne respecte pas les 'standards' de sécurité du générateur, mais reste solide malgré tout.");
		return new PasswordGenerated(password, getAppropriateFlags(checker.getOccurrenceNumberSum()));
	}
	private boolean standardPassword(String password, byte[] occurrenceArray){
		CheckEntry checker = new CheckEntry();
		
		checker.entryChecking(password, false);
		for(byte i = 0x0; i<occurrenceArray.length; i++){
			if(checker.getOccurrenceNumberSum() == getOccurrenceArraySum(occurrenceArray)){
				if(checker.compairToOccurrenceArray(occurrenceArray)){
					
					return true;
				}
			}
		}
		return false;
	}
	private byte getOccurrenceArraySum(byte[] occurrenceArray){
		byte sum = 0;
		for(int i = 0; i<occurrenceArray.length; i++){
			sum += occurrenceArray[i];
		}
		return sum;
	}
	/**
	 * 
	 * @param passwordLength
	 * @return SolidityFlags.[flag].getID();
	 * @see SolidityFlags
	 */
	private String getAppropriateFlagsInString(byte passwordLength){
		switch(passwordLength){
			case 3: return SolidityFlags.mediocre.getID();
			case 6: return SolidityFlags.passable.getID();
			case 9: return SolidityFlags.correct.getID();
			case 12: return SolidityFlags.strong.getID();
			case 15: return SolidityFlags.complex.getID();
			default:
				System.out.println("Standard not respected."
						+"\n State of password: "+SolidityFlags.unknown.getID());
				break;
		}
		return null;
		
	}
	private SolidityFlags getAppropriateFlags(byte passwordLenght){
		switch(passwordLenght){
			case 3: return SolidityFlags.mediocre;
			case 6: return SolidityFlags.passable;
			case 9: return SolidityFlags.correct;
			case 12: return SolidityFlags.strong;
			case 15: return SolidityFlags.complex;
			default:
				return SolidityFlags.unknown;
		}
	}
	/**
	 * Initializes the difficulty array
	 */
	private void DiffcultyArrayinitialization(){
		byte exponent3 = 0x3;
		for(int i = 0; i<flagsArray.length; i++){
			String flagID = new String(""+flagsArray[i]);
			switch(flagID){
				case "complex": difficultyArray.put(SolidityFlags.complex, new byte[]{(byte)0x2, (byte)0x3, exponent3, (byte)Math.pow(0x3, exponent3)}); break;//3, 3, 9
				case "strong": difficultyArray.put(SolidityFlags.strong, new byte[]{(byte)0x2, (byte)0x1, (byte)Math.pow(0x3, exponent3)}); break; //2, 1, 9
				case "correct": difficultyArray.put(SolidityFlags.correct, new byte[]{(byte)0x4, (byte)0x3, (byte)0x1}); break; //4, 3, 2
				case "passable": difficultyArray.put(SolidityFlags.passable, new byte[]{(byte)0x2, (byte)0x3, (byte)0x3}); break; //2, 3, 1
				case "mediore": difficultyArray.put(SolidityFlags.mediocre, new byte[]{(byte)0x1, (byte)0x1, (byte)0x1}); break; //1, 1, 1
			}
		}
	}
}
