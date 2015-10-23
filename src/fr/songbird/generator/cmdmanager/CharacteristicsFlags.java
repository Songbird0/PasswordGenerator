package fr.songbird.generator.cmdmanager;
/**
 * Different states that commands can have
 * @author songbird
 * @version 0.2_0-ALPHA
 */
public enum CharacteristicsFlags{
	WithIntOnly("i"),
	WithLowerCaseOnly("l"),
	WithUpperCaseOnly("M"),
	WithIntandLowerCaseOnly("il"),
	WithIntandUpperCaseOnly("iM"),
	WithLowerCaseandUpperCase("lM"),
	EveryFlags("ilM"),
	UnknownCommand("?");
	
	private String cFlag = null;
	private CharacteristicsFlags(String cFlag){
		this.cFlag = cFlag;
	}
	
	public String getStateOfFlag(){
		return this.cFlag;
	}
}
