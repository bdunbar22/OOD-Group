package cs3500.music.model;

/**
 * Object to represent repeats.
 *
 * Created by Sam Letcher on 4/26/2016.
 */
public class Repeat implements IRepeat {
    private int start;
    private int end;
    private boolean completed;

    public Repeat(int start, int end) {
        if(start < 0) {
            start = 0;
        }
        this.start = start;
        this.end = end;
        this.completed = false;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Make a copy of the repeat.
     *
     * @return new repeat.
     */
    @Override
    public IRepeat copy() {
        IRepeat copy = new Repeat(this.getStart(), this.getEnd());
        copy.setCompleted(this.isCompleted());
        return copy;
    }
}
