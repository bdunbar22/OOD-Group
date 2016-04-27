package cs3500.music.model;

/**
 * Represent repeats.
 * The end of a repeat is the last beat contained in the repeat.
 * A song will play to the end of a repeat, then go back to the start of the repeat and
 * play past the end of the repeat. Therefor, the beats between the start and end will be
 * played twice.
 *
 * Created by Sam Letcher on 4/26/2016.
 */
public interface IRepeat {
    /**
     * Return a copy of the repeat object.
     *
     * @return new repeat.
     */
    IRepeat copy();

    /**
     * Get the start.
     * @return int start
     */
    int getStart();

    /**
     * Set the start.
     *
     * @param start to set
     */
    void setStart(int start);

    /**
     * Get the end.
     *
     * @return end.
     */
    int getEnd();

    /**
     * Set the end beat.
     *
     * @param end to set.
     */
    void setEnd(int end);

    /**
     * Check to see if a repeat has been completed.
     *
     * @return
     */
    boolean isCompleted();

    /**
     * Set the repeat to completed.
     * @param completed
     */
    void setCompleted(boolean completed);
}
