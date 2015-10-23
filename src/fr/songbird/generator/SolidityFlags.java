package fr.songbird.generator;

/**
 * This enumeration contains all solidity flags.
 * @author songbird
 * @version 0.2_0-ALPHA
 */
public enum SolidityFlags {
	complex("[complex]"),
	strong("[strong]"),
	correct("[correct]"),
	passable("[passable]"),
	mediocre("[mediocre]"),
	unknown("[unknown]");
	
	private String id = null;
	
	private SolidityFlags(String id){
		this.id = id;
	}
	
	public String getID(){
		return this.id;
	}
}
