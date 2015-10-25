package fr.songbird.generator.cmdmanager;

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
 * 
 *  
 *  
 *  
 * Different states that commands can have
 * </p>
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
