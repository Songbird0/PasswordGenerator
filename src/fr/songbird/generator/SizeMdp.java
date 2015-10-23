package fr.songbird.generator;

public interface SizeMdp {
	/**
	 * Standard password sizes.
	 * @see Generator#Generator(CharacteristicsFlags, byte)
	 */
	public final byte THREE = 0x3, SIX = 0x6, NINE = 0x9, TWENTY = 0xC, EIGHTEEN = 0x12;
	/**
	 * Limit of generation given in parameter to class Random
	 * @see java.util.Random#nextInt(int)
	 */
	public final int LIMITOFGENERATION = 0x9;
}
