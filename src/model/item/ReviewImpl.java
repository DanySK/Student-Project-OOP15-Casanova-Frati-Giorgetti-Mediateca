package model.item;

import java.io.Serializable;

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
    private String note;

    /**
     * Review Constructor.
     *
     * @param initVote
     *            item's vote
     * @param initNote
     *            item's text note.
     */
    public ReviewImpl(final int initVote, final String initNote) {
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
    public void setNote(final String initNote) {
        this.note = initNote;

    }

    @Override
    public int getVote() {
        return this.vote;
    }

    @Override
    public String getNote() {
        return this.note;
    }

    @Override
    public String toString() {
        return "ReviewImpl [vote=" + this.vote + ", note=" + this.note + "]";
    }

    @Override
    public boolean checkVote(final int initVote) {
        return ((initVote >= ReviewImpl.MIN) && (initVote <= ReviewImpl.MAX));
    }

}
