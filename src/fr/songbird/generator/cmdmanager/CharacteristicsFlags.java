package fr.songbird.generator.cmdmanager;
/**
 * <p>They are differents states that commands can have</p>
 * @author songbird
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
