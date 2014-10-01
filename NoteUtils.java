/**
 * Utility class for dealing with musical notes and frequencies
 * 
 * TODO: Deal with harmony e.g. note relationships
 * 
 * @author Gabriel Parrott
 *
 */
public class NoteUtils {
	// return value if a note string is invalid
	public static final int INVALID_STRING = Integer.MAX_VALUE;

	// frequency constants
	private static final double HalfStepCo = Math.pow(2, -12);
	private static final double CFreq = 440.00 * Math.pow(HalfStepCo, 3);

	/**
	 * Takes a String in the form of a captial letter and either '#' or 'b' for
	 * a sharp or flat respectively and returns the integer value of the note,
	 * starting at 0 for A
	 * 
	 * @param noteName
	 *            The note string
	 * @return The integer corresponding to the note, starting at A with integer
	 *         0
	 */
	public static int noteStringToInt(String noteName) {
		switch (noteName) {
		case "C":
			return 0;
		case "C#":
			return 1;
		case "Db":
			return 1;
		case "D":
			return 2;
		case "D#":
			return 3;
		case "Eb":
			return 3;
		case "E":
			return 4;
		case "E#":
			return 5;
		case "Fb":
			return 4;
		case "F":
			return 5;
		case "F#":
			return 6;
		case "Gb":
			return 6;
		case "G":
			return 7;
		case "G#":
			return 8;
		case "Ab":
			return 8;
		case "A":
			return 9;
		case "A#":
			return 10;
		case "Bb":
			return 10;
		case "B":
			return 11;
		case "B#":
			return 12;
		case "Cb":
			return -1;
		default:
			return INVALID_STRING;
		}
	}

	/**
	 * Gets the frequency of the given note in the given octave.
	 * 
	 * @param noteName
	 *            The name of the note
	 * @param octave
	 *            The octave of the note. E.g. A4 would be the fourth octave
	 * @return The frequency of the note
	 */
	public static double getFrequency(String noteName, int octave) {
		int intNote = noteStringToInt(noteName);

		int coPow = ((octave - 5) * 12) + intNote;

		return CFreq * (Math.pow(HalfStepCo, coPow));
	}
}