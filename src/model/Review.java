package model;

import java.util.List;

/**
 * Each user can leave a review on an item.
 *
 * @author Edoardo
 *
 */
public interface Review {

    /**
     * Setting value's vote, it must be 0 <= vote <= 5.
     *
     * @param vote
     *            0 <= vote <= 5
     * @return result of setting true if it is ok, false it isn't ok
     */
    boolean setVote(final int vote);

    /**
     * Setting the note text.
     *
     * @param note
     *            composed by List<String>
     *
     */
    void setNote(final List<String> note);

    /**
     * Getter vote.
     *
     * @return value's vote
     */
    int getVote();

    /**
     * Getter note.
     *
     * @return text's note
     */
    List<String> getNote();

    /**
     * Check if the input parameter is correct.
     *
     * @param vote
     *            0 <= vote <= 5
     * @return true if is in the range, else false
     */
    boolean checkVote(final int vote);
}
