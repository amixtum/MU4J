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
	private static final double HalfStepCo = Math.pow(2, 1 / 12);
	private static final double CFreq = 440.00 * Math.pow(HalfStepCo, 3);

	// Note quality enumerations
	public static enum Quality {
		Major, Minor, Perfect, Augmented, Diminished, Invalid
	}
	
	//String constants for ease of use
	public static final String C = "C";
	public static final String Cs = "C#";
	public static final String Db = "Db";
	public static final String D = "D";
	public static final String Ds = "D#";
	public static final String Eb = "Eb";
	public static final String E = "E";
	public static final String Es = "E#";
	public static final String Fb = "Fb";
	public static final String F = "F";
	public static final String Fs = "F#";
	public static final String Gb = "Gb";
	public static final String G = "G";
	public static final String Gs = "G#";
	public static final String Ab = "Ab";
	public static final String A = "A";
	public static final String As = "A#";
	public static final String Bb = "Bb";
	public static final String B = "B";
	public static final String Bs = "B#";
	public static final String Cb = "Cb";
	
	public static Quality getRelationship(Note baseNote, Note compareNote) {
		return getRelationship(baseNote.getNoteName(), compareNote.getNoteName());
	}
	
	/**
	 * Gets the quality relationship between the two notes with respect to the
	 * first note
	 * 
	 * @param baseNote
	 *            The note used as the starting point
	 * @param compareNote
	 *            The note to compare to
	 * @return The quality of the relationship
	 */
	public static Quality getRelationship(String baseNote, String compareNote) {
		int baseInt = noteStringToInt(baseNote);
		int compareInt = noteStringToInt(compareNote);

		switch (compareInt - baseInt) {
		case 0:
			return Quality.Perfect;
		case -1:
		case 1:
			return Quality.Minor;
		case -2:
		case 2:
			return Quality.Major;
		case -3:
		case 3:
			return Quality.Minor;
		case -4:
		case 4:
			return Quality.Major;
		case -5:
		case 5:
			return Quality.Perfect;
		case -6:
		case 6:
			return Quality.Diminished;
		case -7:
		case 7:
			return Quality.Perfect;
		case -8:
		case 8:
			return Quality.Minor;
		case -9:
		case 9:
			return Quality.Major;
		case -10:
		case 10:
			return Quality.Minor;
		case -11:
		case 11:
			return Quality.Major;
		case -12:
		case 12:
			return Quality.Perfect;
		default:
			return Quality.Invalid;
		}
	}

	/**
	 * Gets the quality relationship between the two specified notes with
	 * respect to the the first note
	 * 
	 * @param baseInt
	 *            The base note. Must be a value in [0 , 12]
	 * @param compareInt
	 *            The note to compare to the base note. Must be a value in [0 ,
	 *            12]
	 * @return The quality of the relationship between the notes
	 */
	public static Quality getRelationship(int baseInt, int compareInt) {
		switch (compareInt - baseInt) {
		case 0:
			return Quality.Perfect;
		case -1:
		case 1:
			return Quality.Minor;
		case -2:
		case 2:
			return Quality.Major;
		case -3:
		case 3:
			return Quality.Minor;
		case -4:
		case 4:
			return Quality.Major;
		case -5:
		case 5:
			return Quality.Perfect;
		case -6:
		case 6:
			return Quality.Diminished;
		case -7:
		case 7:
			return Quality.Perfect;
		case -8:
		case 8:
			return Quality.Minor;
		case -9:
		case 9:
			return Quality.Major;
		case -10:
		case 10:
			return Quality.Minor;
		case -11:
		case 11:
			return Quality.Major;
		case -12:
		case 12:
			return Quality.Perfect;
		default:
			return Quality.Invalid;
		}
	}

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

	/**
	 * Gets the frequency of specified note
	 * 
	 * @param intNote
	 *            The integer code for the note. C is 0 and increments by one
	 *            for each half step up to B# which is 12
	 * @param octave
	 *            The octave of the note. E.g. intNote = 0 and octave = 5 would
	 *            give C5
	 * @return The frequency of the note specified by the parameters
	 */
	public static double getFrequency(int intNote, int octave) {
		int coPow = ((octave - 5) * 12) + intNote;

		return CFreq * (Math.pow(HalfStepCo, coPow));
	}

	/**
	 * Gets the frequency of the specified note
	 * 
	 * @param halfStepsFromC5
	 *            The number of half steps from C5
	 * @return The frequency of the note specified by the parameters
	 */
	public static double getFrequency(int halfStepsFromC5) {
		return CFreq * (Math.pow(HalfStepCo, halfStepsFromC5));
	}
}
