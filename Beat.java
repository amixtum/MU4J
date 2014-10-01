/**
 * Representation of a single "beat" in a measure. E.g. In 4/4 one quarter of
 * the measure is one "beat".
 * 
 * Smallest supported precision is 1/8th of a beat so far, or a 32nd note.
 * 
 * Right now, a rest is denoted by a null values
 * 
 * @author Gabriel Parrott
 *
 */
public class Beat {
	private Note[] beat = new Note[8]; // array representation of a beat

	/**
	 * This is here to simplify the process of specifying the division of a
	 * measure with less error-checking
	 * 
	 * @author Gabriel Parrott
	 *
	 */
	public static enum Division {
		Quarter, Eighth, Sixteenth, ThirtySecond
	}

	/**
	 * Fills the beat field from the given array.
	 * 
	 * @param notes
	 *            The array to copy from.
	 * @return True if the length of the notes parameter is equal to the length
	 *         of the beat array. False otherwise
	 */
	public boolean fromArray(Note[] notes) {
		if (notes.length != beat.length) {
			return false;
		}

		for (int i = 0; i < notes.length; ++i) {
			beat[i] = notes[i];
		}

		return true;
	}

	/**
	 * Fills the beat array with elements of the array passed into this method.
	 * This overload allows the user to specify a number of elements less than
	 * the length of the beat array and space them out evenly over the beat.
	 * 
	 * E.g. {Note1, Note2} passed into this method would fill the beat array
	 * with {Note1, Note1, Note1, Note1, Note2, Note2, Note2, Note2}
	 * 
	 * @param notes
	 *            The array to take notes from
	 * @param equalSpace
	 *            Whether the user would like the array passed into the method
	 *            to fill multiple spaces of the beat array with its elements
	 * @return Whether the operation was successful or not
	 */
	public boolean fromArray(Note[] notes, boolean equalSpace) {
		if (equalSpace) {
			if (notes.length > beat.length) {
				return false;
			}

			int distanceBetween = (int) (beat.length / notes.length);

			distanceBetween = (distanceBetween % 2 == 0) ? distanceBetween
					: distanceBetween + 1;

			for (int i = 0, k = 0; i < beat.length; i += distanceBetween, k++) {
				beat[i] = notes[k];
			}

			return true;
		} else {
			return fromArray(notes);
		}
	}

	/**
	 * Fills the beat with one note
	 * 
	 * @param note
	 *            The note to fill the beat with
	 */
	public void fill(Note note) {
		for (int i = 0; i < beat.length; ++i) {
			beat[i] = note;
		}
	}

	/**
	 * Adds a note at the starting index for the specified length in the beat
	 * 
	 * @param note
	 *            The note to add
	 * @param startIndex
	 *            The starting index to be filled
	 * @param length
	 *            The number of elements of the beat to be filled by the note
	 * @return Whether the add was successful or not
	 */
	public boolean add(Note note, int startIndex, int length) {
		if (startIndex < 0 || startIndex >= beat.length
				|| (length + startIndex) > beat.length) {
			return false;
		}

		for (int i = 0; i < (startIndex + length); ++i) {
			beat[i] = note;
		}

		return true;
	}

	/**
	 * Removes all notes from the beat
	 */
	public void clear() {
		for (int i = 0; i < beat.length; ++i) {
			beat[i] = null;
		}
	}

	/**
	 * Adds an eighth note in the given section
	 * 
	 * @param note
	 *            The note to be added
	 * @param section
	 *            The section of the beat to be added to. In this case, there
	 *            are two sections because one eighth note takes half of a beat.
	 *            This is for convenience. See addAt for more control.
	 * @return Whether the addition was successful or not
	 */
	public boolean addEighth(Note note, int section) {
		switch (section) {
		case 0:
			for (int i = 0; i < beat.length / 2; ++i) {
				beat[i] = note;
			}
			return true;
		case 1:
			for (int i = beat.length / 2; i < beat.length; ++i) {
				beat[i] = note;
			}
			return true;
		default:
			return false;
		}
	}

	/**
	 * Adds a sixteenth note in the given section of the beat.
	 * 
	 * @param note
	 *            The note to be added.
	 * @param section
	 *            The section of the beat to be added to. Acceptable values are
	 *            [0,3] because 4 16th notes make a beat. See addAt for more
	 *            control
	 * @return Whether the add was successful or not
	 */
	public boolean addSixteenth(Note note, int section) {
		switch (section) {
		case 0:
			for (int i = 0; i < beat.length / 4; ++i) {
				beat[i] = note;
			}
			return true;
		case 1:
			for (int i = beat.length / 4; i < (2 * (beat.length / 4)); ++i) {
				beat[i] = note;
			}
			return true;
		case 2:
			for (int i = (beat.length / 2); i < 3 * (beat.length / 4); ++i) {
				beat[i] = note;
			}
			return true;
		case 3:
			for (int i = (3 * (beat.length / 4)); i < beat.length; ++i) {
				beat[i] = note;
			}
			return true;
		default:
			return false;
		}
	}

	/**
	 * Adds a single Note at the given position
	 * 
	 * @param note
	 *            The note to be added
	 * @param position
	 *            The position to add the note
	 * @return True if the position was valid. False otherwise.
	 */
	public boolean addAt(Note note, int position) {
		if (position < 0 || position >= beat.length) {
			return false;
		}

		beat[position] = note;

		return true;
	}

	/**
	 * Adds a Note at the given position with a specified Division enumeration.
	 * 
	 * @param note
	 *            The note to be added.
	 * @param position
	 *            The position to add the note.
	 * @param div
	 *            The division of the note. See Beat.Division.
	 * @return Whether the add was successful.
	 */
	public boolean addAt(Note note, int position, Division div) {
		switch (div) {
		case Quarter:
			if (position != 0) {
				System.err.println("Consider using Beat.fill()");
				return false;
			}
			fill(note);
			return true;
		case Eighth:
			if (position < 0 || position > beat.length / 2) {
				return false;
			}
			for (int i = position; i < position + 4; ++i) {
				beat[i] = note;
			}
			return true;
		case Sixteenth:
			if (position < 0 || position > beat.length - (beat.length / 4)) {
				return false;
			}
			for (int i = position; i < position + 2; ++i) {
				beat[i] = note;
			}
			return true;
		case ThirtySecond:
			return addAt(note, position);
		default:
			return false;
		}
	}
}
