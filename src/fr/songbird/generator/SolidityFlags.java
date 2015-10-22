package fr.songbird.generator;

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
