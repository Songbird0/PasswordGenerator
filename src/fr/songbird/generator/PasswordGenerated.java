package fr.songbird.generator;



public class PasswordGenerated {
	
	//###### PRIVATE VARIABLES ######
	
	
	private String password = null;
	private SolidityFlags flags = null;
	
	
	//###### PUBLIC VARIABLES ######
	
	//######Constructor######
	
	public PasswordGenerated(String password, SolidityFlags flags){
		this.password = password;
		this.flags = flags;
	}
	
	
	//###### PRIVATE METHODS ######
	
	
	//###### PUBLIC METHODS ######
	
	

	
	public SolidityFlags getSolidityFlags(){
		return this.flags;
	}
	
	public String getPasswordGenerated(){
		return this.password;
	}
	
	
}
