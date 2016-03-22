package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.IPiece;
import cs3500.music.model.Note;
import cs3500.music.model.NoteList;

import javax.sound.midi.*;
import java.util.List;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements IMusicView {
  private final Synthesizer synth;
  private final Receiver receiver;

  public MidiViewImpl() {
    Synthesizer trySynth;
    Receiver tryReceive;
    try {
      trySynth = MidiSystem.getSynthesizer();
      tryReceive = trySynth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      trySynth = null;
      tryReceive = null;
    }
    this.synth = trySynth;
    this.receiver = tryReceive;
    try {
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  public void playNote(INote note) throws InvalidMidiDataException {
    long startTime = (note.getStart() * 1000000) + this.synth.getMicrosecondPosition();
    long endTime = (startTime + (note.getDuration() * 1000000));
    int pitch = note.getMidiPitch();
    int instrument = note.getInstrument();
    int volume = note.getVolume();
    System.out.print(volume);

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
    this.receiver.send(start, startTime);
    this.receiver.send(stop, endTime);
  }

  @Override
  public void viewMusic(IPiece music) {
    for (INote note : music.getNotes()) {
      try {
        playNote(note);
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    try {
      Thread.sleep((music.getLastBeat()+1) * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.print("DONE");
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }
}