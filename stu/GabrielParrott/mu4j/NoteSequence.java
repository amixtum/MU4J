package stu.GabrielParrott.mu4j;

import java.util.ArrayDeque;
import java.util.Iterator;

public class NoteSequence {
	private int BPM = 120;
	
	public static enum NoteDurations {
		Whole, Half, Quarter, Eighth, Sixteenth, ThirtySecond
	}
	
	ArrayDeque<TimeNote> notes = new ArrayDeque<>();
	
	public void setBPM(int bpm) {
		BPM = Math.abs(bpm);
	}
	
	public void add(Note note, NoteDurations dur) {
		notes.addLast(new TimeNote(note, durationEnumToInt(dur)));
	}
	
	public void add(Note note, int dur) {
		notes.addLast(new TimeNote(note, dur));
	}
	
	public void clear() {
		notes.clear();
	}
	
	public TimeNote popFront() {
		if (notes.isEmpty()) {
			return null;
		}
		
		return notes.removeFirst();
	}
	
	public Iterator<TimeNote> getIterator() {
		return notes.iterator();
	}
	
	private int durationEnumToInt(NoteDurations dur) {
		switch (dur) {
		case Whole:
			return 32;
		case Half:
			return 16;
		case Quarter:
			return 8;
		case Eighth:
			return 4;
		case Sixteenth:
			return 2;
		case ThirtySecond:
			return 1;
		default:
			return 0;
		}
	}
	
	public class TimeNote {
		private Note note;
		private int duration;
		
		private TimeNote(Note n, int d) {
			note = n;
			duration = d;
		}
		
		public Note getNote() {
			return note;
		}
		public double getFrequency() {
			return note.getFrequency();
		}
		public int getDuration() {
			return duration;
		}
		public long toNanoseconds() {
			return (long)(((BPM / 60.00) / 0.000000001) * ((double)duration / 32.00));
		}
	}
}
