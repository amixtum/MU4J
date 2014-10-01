/**
 * Data representation for a musical note
 * 
 * @author Gabriel Parrott
 *
 */
public class Note {
	private String name; // name of the note
	private int numberRepresentation; // numerical representation of the note.
										// Based on C = 0
	private int octave; // The octave the note is in e.g. C5 is C in octave 5

	/**
	 * Sets the fields of the note.
	 * 
	 * @param name
	 *            The String representation of the note e.g. C, C#, Cb
	 * @param octave
	 *            The octave of the note. C5 is C in octave 5
	 */
	public Note(String name, int octave) {
		this.name = name;
		this.numberRepresentation = NoteUtils.noteStringToInt(name);
		this.octave = octave;
	}

	/**
	 * Sets the note to a different note specified by the name parameter.
	 * 
	 * @param name
	 *            The name of the note. Must be a valid name. See NoteUtils for
	 *            examples of valid note names or use the constants written into
	 *            that class
	 * @return True if setting the note was successful. False if an invalid
	 *         String was supplied
	 */
	public boolean setNote(String name) {
		int newNote = NoteUtils.noteStringToInt(name);

		if (newNote == (NoteUtils.INVALID_STRING)) {
			return false;
		}

		this.name = name;
		this.numberRepresentation = newNote;

		return true;
	}

	/**
	 * Sets the octave of the note
	 * 
	 * @param newOctave
	 *            The octave to be set
	 */
	public void setOctave(int newOctave) {
		this.octave = newOctave;
	}

	/**
	 * Gets the name of the note
	 * 
	 * @return The Note's name
	 */
	public String getNoteName() {
		return name;
	}

	/**
	 * Gets the number representation of the note.
	 * 
	 * @return A value between [-1,12]. See NoteUtils
	 */
	public int getNoteNumber() {
		return numberRepresentation;
	}

	/**
	 * Gets the octave of the note
	 * 
	 * @return The octave of the note
	 */
	public int getOctave() {
		return octave;
	}

	/**
	 * 
	 * @return The frequency of the note in Hertz
	 */
	public double getFrequency() {
		return NoteUtils.getFrequency(numberRepresentation, octave);
	}

	/**
	 * The relationship between this note and another note
	 * 
	 * @param otherNote
	 *            The other note to compare with
	 * @return The harmonic quality of the relationship between the notes. See
	 *         NoteUtils
	 */
	public NoteUtils.Quality getRelationship(Note otherNote) {
		return NoteUtils.getRelationship(this, otherNote);
	}
}
