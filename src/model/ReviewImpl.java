package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Review is composed by 2 field, the first is a vote and the second is a text.
 *
 * @author Edoardo
 *
 */
public class ReviewImpl implements Review, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8843824865824137318L;
    private static final int MAX = 5;
    private static final int MIN = 0;
    private int vote;
    private List<String> note = new LinkedList<String>();

    /**
     * Review Constructor.
     *
     * @param initVote
     *            item's vote
     * @param initNote
     *            item's text note.
     */
    public ReviewImpl(final int initVote, final List<String> initNote) {
        this.setVote(initVote);
        this.setNote(initNote);
    }

    @Override
    public boolean setVote(final int initVote) {
        if (this.checkVote(initVote)) {
            this.vote = initVote;
            return true;
        }
        return false;
    }

    @Override
    public void setNote(final List<String> initNote) {
        this.note = initNote;

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
    public boolean checkVote(final int initVote) {
        return ((initVote >= ReviewImpl.MIN) && (initVote <= ReviewImpl.MAX));
    }

}
