package fr.songbird.generator;

import java.lang.reflect.Constructor;
import java.util.Random;


import fr.songbird.generator.cmdmanager.CharacteristicsFlags;
import fr.songbird.tools.ScannerHM;
import fr.songbird.testermdp.MdpTester;

import fr.songbird.survivalDevKit.*;

import net.wytrem.logging.*;


/**
 * Chief class of the program. cf. constructor javadoc.
 * @author songbird
 * @version 0.1_0-ALPHA This development phase is attributed to all classes of the program.
 */
public class Generator implements SizeMdp{
	
	//###### PRIVATE VARIABLES ######
	
	private static final BasicLogger logger = LoggerFactory.getLogger(Generator.class);
	
	private ScannerHM readerKeyBoard = null;
	

	
	private String[] alphbLowerCase = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"o","p","q","r","s","t","u","v","w","x","y","z"};
	private String[] alphbUpperCase = alphbUpperCase();
	
	//###### PUBLIC VARIABLES ######
	
	
	
	//###### CONSTRUCTOR(S) ######
	/**
	 * Generator generates so many of characters that number of iterations given in parameter.
	 * @param The current reader 
	 * @param Number of iteration == Number of characters
	 * @param State of flag
	 */
	public Generator(CharacteristicsFlags state, byte sizeMDP){
		new MdpTester(passwordFactory(sizeMDP, state));
	}
	
	
	
	//###### PRIVATE METHODS ######
	private String[] alphbUpperCase(){
		String[] alphbUpperCase = new String[0x1A];
		
		for(int i = 0x0; i<alphbLowerCase.length; i++){
			alphbUpperCase[i] = alphbLowerCase[i].toUpperCase();
		}
		return alphbUpperCase;
	}
	
	private String passwordFactory(byte sizeMDP, CharacteristicsFlags state){
		if(itsConsistent(sizeMDP)){
			switch(state.getStateOfFlag()){
				case "i": return withIntOnly(sizeMDP);
				case "l": return withLowerCaseOnly(sizeMDP);
				case "M": return withUpperCaseOnly(withLowerCaseOnly(sizeMDP));
				case "il": return withIntandLowerCaseOnly(sizeMDP);
				case "iM": return withIntandUpperCaseOnly(sizeMDP);
				case "lM": return withLowerCaseandUpperCase(sizeMDP);
				case "ilM": return EveryFlags(sizeMDP);
				default:
					return "L'ordre d'apparition des arguments n'est pas respecté.";
			}
		}
		return "La taille proposée n'est pas conforme aux attentes du générateur.";
	}
	
	private boolean itsConsistent(byte sizeMDP){
		switch(sizeMDP){
			case THREE: return true;
			case SIX: return true;
			case NINE: return true;
			case TWENTY: return true;
			case EIGHTEEN: return true;
			default:
				return false;
		}
	}
	
	
	private String withIntOnly(byte sizeMDP){ 
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			password += String.valueOf(random.nextInt(LIMITOFGENERATION));
			logger.log(LogLevel.WARNING, "Generator.password == "+password);
			i++;
		}
		return password;
	}
	private String withLowerCaseOnly(byte sizeMDP){
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			password += alphbLowerCase[random.nextInt(0x19)];
			i++;
		}
		return password;
	}
	
	private String withUpperCaseOnly(String wLCOreturn){
		return wLCOreturn.toUpperCase();
	}
	private String withIntandLowerCaseOnly(byte sizeMDP){
		Random random = new Random();
		String password = new String("");
		return zeroOrOne(password, sizeMDP, random);
		
	}
	private String withIntandUpperCaseOnly(byte sizeMDP){
		Random random = new Random();
		String password = new String("");
		return zeroOrOne(password, sizeMDP, random);
	}
	private String withLowerCaseandUpperCase(byte sizeMDP){
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			if(random.nextInt(1) == 0){
				password += alphbUpperCase[random.nextInt(0x19)];
				i++;
			}
			else{
				password += alphbLowerCase[random.nextInt(0x19)];
				i++;
			}
		}
		return password;
	}
	private String EveryFlags(byte sizeMDP){
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			if(random.nextInt(0x2) == 0x0){
				password += String.valueOf(random.nextInt(LIMITOFGENERATION));
				i++;
			}
			else if(random.nextInt(0x2) == 0x1){
				password += alphbLowerCase[random.nextInt(0x19)];
				i++;
			}
			else if(random.nextInt(0x2) == 0x2){
				password += alphbUpperCase[random.nextInt(0x19)];
				i++;
			}
			
		}
		return password;
	}
	
	private void unknownCommand(){
		logger.log(LogLevel.ERROR, "L'ordre d'apparition des arguments n'a pas été respecté, fermeture de l'application...");
		
	}
	
	private String zeroOrOne(String password, byte sizeMDP, Random random){
		byte i = 0x0;
		while(i<sizeMDP){
			if(random.nextInt(0x1) == 0x0){
				password += String.valueOf((byte)random.nextInt());
			}
			else{
				password += alphbLowerCase[random.nextInt(0x19)];
			}
		}
		
		return password;
	}
	
	
	//###### PUBLIC METHODS ######
	
	
	/**
	 * For getInstanceOfGenerator doc, cf {@link Generator#Generator(CharacteristicsFlags)}
	 * @param command state.
	 * @return  instance of the generator.
	 *
	public static final Generator getInstanceOfGenerator(CharacteristicsFlags state){
		String className = Generator.class.getName();
		Class<?> generatorClass = null;
		Class<CharacteristicsFlags> parameter = null;
		Constructor<?> constructor = null;
		Object instanceOfGenerator = null;
		try{
			generatorClass = Class.forName(className);
			parameter = CharacteristicsFlags.class;
			constructor = generatorClass.getConstructor(parameter);
			instanceOfGenerator = constructor.newInstance(state);
		}catch(Exception exception0){
			exception0.printStackTrace();
			System.exit(1);
		}
		return (Generator)instanceOfGenerator;
	}*/
	

}

