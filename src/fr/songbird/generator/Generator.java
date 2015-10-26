package fr.songbird.generator;
import java.lang.reflect.Constructor;
import java.util.Random;


import fr.songbird.generator.cmdmanager.CharacteristicsFlags;
import fr.songbird.testermdp.MdpTester;





/**
 * 
 * <p>
 *   PasswordGenerator<br/>
 *   Copyright (C) 2015  Songbird<br/>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.<br/><br/><br/>
 * 
 * 
 * 
 * 
 * 
 *  Chief class of the program.
 *  </p>
 * @author songbird
 * @version 1.4_5-ALPHA
 * @see Generator#Generator(CharacteristicsFlags, byte)
 */
public class Generator implements SizeMdp{
	
	//###### PRIVATE VARIABLES ######
	
	private static final String FATALERROR = new String("La taille proposée n'est pas conforme aux attentes du générateur.");
	
	private String[] alphbLowerCase = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"o","p","q","r","s","t","u","v","w","x","y","z"};
	private String[] alphbUpperCase = alphbUpperCase();
	
	//###### PUBLIC VARIABLES ######
	
	
	
	//###### CONSTRUCTOR(S) ######
	/**
	 * Generator generates so many of characters that number of iterations given in parameter.
	 * @param sizeMDP Number of iteration == Number of characters.
	 * @param state State of flag.
	 */
	public Generator(CharacteristicsFlags state, byte sizeMDP){
		new MdpTester(passwordFactory(sizeMDP, state));
	}
	
	
	
	//###### PRIVATE METHODS ######
	/**
	 * The alphbUpperCase method converts alphbLowerCase array in upper case array, obviously.
	 * @return
	 */
	private String[] alphbUpperCase(){
		String[] alphbUpperCase = new String[0x1A];
		
		for(int i = 0x0; i<alphbLowerCase.length; i++){
			alphbUpperCase[i] = alphbLowerCase[i].toUpperCase();
		}
		return alphbUpperCase;
	}
	/**
	 * 
	 * @param sizeMDP the password size.
	 * @param state State of flag.
	 * @return String a warning message.
	 * @see Generator#Generator(CharacteristicsFlags, byte)
	 */
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
					return unknownCommand();
			}
		}
		return FATALERROR;
	}
	
	/**
	 * 
	 * @param sizeMDP
	 * @return true if the password size have the expected size by interface SizeMdp.
	 * @see SizeMdp
	 */
	private boolean itsConsistent(byte sizeMDP){
		switch(sizeMDP){
			case THREE: return true;
			case SIX: return true;
			case NINE: return true;
			case TWENTY: return true;
			case FIFTEEN: return true;
			default:
				return false;
		}
	}
	
	/**
	 * 
	 * @param sizeMDP
	 * @return a compound password of integers only.
	 * @see {@link Generator#withLowerCaseOnly(byte)}
	 * {@link Generator#withIntandUpperCaseOnly(byte)}
	 * {@link Generator#withIntandLowerCaseOnly(byte)}
	 * {@link Generator#withIntandUpperCaseOnly(byte)}
	 * {@link Generator#withLowerCaseandUpperCase(byte)}
	 * {@link Generator#EveryFlags(byte)}
	 */
	private String withIntOnly(byte sizeMDP){ 
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			password += String.valueOf(random.nextInt(LIMITOFGENERATION));
			i++;
		}
		return password;
	}
	/**
	 * 
	 * @param sizeMDP
	 * @return a compound password of lower cases only;
	 */
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
	/**
	 * 
	 * @param wLCOreturn
	 * @return a compound password of upper cases only;
	 */
	private String withUpperCaseOnly(String wLCOreturn){
		return wLCOreturn.toUpperCase();
	}
	/**
	 * 
	 * @param sizeMDP
	 * @return a compound password of integers and lower cases.
	 */
	private String withIntandLowerCaseOnly(byte sizeMDP){
		Random random = new Random();
		String password = new String("");
		byte i = 0x0;
		while(i<sizeMDP){
			if(random.nextInt(0x2) == 0x0){
				password += String.valueOf(random.nextInt(LIMITOFGENERATION));
				i++;
			}
			else{
				password += alphbLowerCase[random.nextInt(0x19)];
				i++;
			}
		}
		
		return password;
		
	}
	/**
	 * 
	 * @param sizeMDP
	 * @return a compound password of integers and upper cases.
	 */
	private String withIntandUpperCaseOnly(byte sizeMDP){
		Random random = new Random();
		String password = new String("");
		byte i = 0x0;
		while(i<sizeMDP){
			if(random.nextInt(0x2) == 0x0){
				password += String.valueOf(random.nextInt(LIMITOFGENERATION));
				i++;
			}
			else{
				password += alphbUpperCase[random.nextInt(0x19)];
				i++;
			}
		}
		
		return password;
	}
	/**
	 * 
	 * @param sizeMDP
	 * @return a compound password of lower cases and upper cases.
	 */
	private String withLowerCaseandUpperCase(byte sizeMDP){
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			if(random.nextInt(0x2) == 0x0){
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
	/**
	 * 
	 * @param sizeMDP
	 * @return a alphanumeric password that supports upper and lower cases.
	 */
	private String EveryFlags(byte sizeMDP){
		Random random = new Random();
		byte i = 0x0;
		String password = new String("");
		while(i<sizeMDP){
			if(random.nextInt(0x3) == 0x0){
				password += String.valueOf(random.nextInt(LIMITOFGENERATION));
				i++;
			}
			else if(random.nextInt(0x3) == 0x1){
				password += alphbLowerCase[random.nextInt(0x19)];
				i++;
			}
			else if(random.nextInt(0x3) == 0x2){
				password += alphbUpperCase[random.nextInt(0x19)];
				i++;
			}
			
		}
		return password;
	}
	/**
	 * 
	 * 
	 */
	private String unknownCommand(){
		return "L'ordre d'apparition des arguments n'a pas été respecté, fermeture de l'application...";
		
	}

	
	
	//###### PUBLIC METHODS ######
	
	
	/**
	 * For getInstanceOfGenerator doc, cf {@link Generator#Generator(CharacteristicsFlags)}
	 * @param command state.
	 * @return  instance of the generator.
	 */
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
	}
	
	public static String getFatalError(){
		return Generator.FATALERROR;
	}
	

}

