package cs3500.music.util;

import cs3500.music.model.*;

/**
 * Allows for a composition to be created using a few functions offered by the model.
 * This makes it possible to read in text files to make songs.
 * <p>
 * Created by Sam Letcher on 3/23/2016.
 */
public class CompositionBuilder implements ICompositionBuilder<IPiece> {
    private final Piece piece;

    public CompositionBuilder() {
        this.piece = new Piece();
    }

    public IPiece build() {
        return piece;
    }

    public ICompositionBuilder<IPiece> setTempo(int tempo) {
        piece.setTempo(tempo);
        return this;
    }

    public ICompositionBuilder<IPiece> addNote(int start, int end, int instrument, int pitch,
        int volume) {
        pitch = pitch - 12;
        Octave octave = new Octave(pitch / 12);
        Pitch charPitch = Pitch.values()[pitch % 12];
        int duration = end - start;
        if (duration < 1) {
            duration = 1;
            //support files that accidentally put an invalid duration.
        }

        piece.addNote(new Note(charPitch, octave, start, duration, instrument, volume));
        return this;
    }

    /**
     * Add a repeat to the piece.
     *
     * @param repeat to add
     */
    @Override
    public ICompositionBuilder<IPiece> addRepeat(IRepeat repeat) {
        piece.addRepeat(repeat);
        return this;
    }
}
