package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements a StudyRoom, it is Serializable and it has one field
 * that is a map which has key the date and the value a ArrayList of UserId( 0
 * means that the sit is free, another number means that it is busy).
 *
 * @author Edoardo
 *
 */
public class StudyRoom implements Serializable {

    private static final long serialVersionUID = -6559792291462784732L;
    private static final int N = 100;

    // true == busy, false == free
    private Map<GregorianCalendar, ArrayList<Integer>> mapStudyRoom = new HashMap<>();

    /**
     * Empty constructor.
     */
    public StudyRoom() {
    }

    /**
     * This method add a day into the archive of StudyRoom.
     *
     * @param day
     *            to add.
     */
    public void addDate(final GregorianCalendar day) {
        if (!this.mapStudyRoom.containsKey(day) || this.mapStudyRoom.isEmpty()) {
            ArrayList<Integer> al = new ArrayList<Integer>(StudyRoom.N);
            for (int i = 0; i < StudyRoom.N; i++) {
                al.add(i, 0);
            }
            this.mapStudyRoom.put(day, al);
        } else {
            System.out.println("Day is in the archive");
        }
    }

    /**
     * This method return a list of integer which represent the user list that
     * have taken a sit. In the case which the day is not in the map, it will be
     * add.
     *
     * @param day
     *            to search.
     * @return the list of free sit.
     */
    public List<Integer> getAllSit(final GregorianCalendar day) {
        if (!this.mapStudyRoom.containsKey(day)) {
            this.addDate(day);
        }
        return this.mapStudyRoom.get(day);
    }

    /**
     * This method add a userId to a specific sit into the StudyRoom. In the
     * case which the day is not in the map, it will be add.
     *
     * @param day
     *            to search.
     * @param sit
     *            required.
     * @param userId
     *            user's identifier.
     * @throws Exception
     *             in the case which the sit is a number < 0 || >= StudyRoom.N
     *             && int he case of busy sit.
     */
    public void takeSit(final GregorianCalendar day, final Integer sit, final Integer userId) throws Exception {
        if (!this.mapStudyRoom.containsKey(day)) {
            this.addDate(day);
        }
        if ((sit <= StudyRoom.N) && (sit > 0)) {
            if (this.mapStudyRoom.get(day).get(sit) != 0) {
                this.mapStudyRoom.get(day).set(sit, userId);
                System.out.println("Day " + day.getTimeInMillis() + " User " + userId + " in position " + sit);
            } else {
                throw new Exception(+sit + " busy sit.");
            }
        } else {
            throw new Exception(+sit + "not valid sit position.");
        }
    }

    /**
     * This method remove a userId from a specific sit into the study room. In
     * the case which the day is not into the mail, it will be anything.
     *
     * @param day
     *            to search.
     * @param sit
     *            required to cancel.
     * @throws Exception
     *             in the case which the sit is a number < 0 || >= StudyRoom.N.
     */
    public void cancelSit(final GregorianCalendar day, final Integer sit) throws Exception {
        if (this.mapStudyRoom.containsKey(day)) {
            if ((sit <= StudyRoom.N) && (sit > 0)) {
                this.mapStudyRoom.get(day).add(sit, 0);
            } else {
                throw new Exception(+sit + " not valid position.");
            }
        }
    }
}
