package model;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class ItemInfo {

    private Integer quantity;
    private List<Pair<Integer, GregorianCalendar>> userList;

    public ItemInfo(final int initQuantity) {
        this.quantity = initQuantity;
        this.userList = new LinkedList<Pair<Integer, GregorianCalendar>>();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void addQuantity(final Integer initQuantity) {
        this.quantity = this.quantity + initQuantity;
    }

    public List<Pair<Integer, GregorianCalendar>> getUserList() {
        return this.userList;
    }

    public void setUserList(final List<Pair<Integer, GregorianCalendar>> userList) {
        this.userList = userList;
    }

}
