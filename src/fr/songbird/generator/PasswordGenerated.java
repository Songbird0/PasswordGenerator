package fr.songbird.generator;

/**
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
 * </p>
* 
* @author songbird
* @version 0.1_0-ALPHA
* @since PasswordGenerator-0.1_0-ALPHA
* */
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
