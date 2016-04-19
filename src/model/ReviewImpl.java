package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Review is composed by 2 field, the first is a vote and the second is a text.
 *
 * @author Edoardo
 *
 */
public class ReviewImpl implements Review {

    static final int MAX = 5;
    static final int MIN = 0;
    private int vote;
    private List<String> note = new LinkedList<String>();

    /**
     * Review Constructor.
     *
     * @param vote
     * @param note
     */
    public ReviewImpl(final int vote, final List<String> note) {
        this.setVote(vote);
        this.setNote(note);
    }

    @Override
    public boolean setVote(final int vote) {
        if (this.checkVote(vote)) {
            this.vote = vote;
            return true;
        }
        return false;
    }

    @Override
    public void setNote(final List<String> note) {
        this.note = note;

    }

    @Override
    public int getVote() {
        return this.vote;
    }

    @Override
    public List<String> getNote() {
        return this.note;
    }

    @Override
    public boolean checkVote(final int vote) {
        if ((vote >= ReviewImpl.MIN) && (vote <= ReviewImpl.MAX)) {
            return true;
        } else {
            return false;
        }
    }

}
