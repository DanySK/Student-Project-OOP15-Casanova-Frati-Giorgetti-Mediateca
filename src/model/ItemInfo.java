package model;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;

public class ItemInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7549869898253407439L;
    private Integer quantity;
    private Map<Integer, GregorianCalendar> userList;

    public ItemInfo(final int initQuantity) {
        this.quantity = initQuantity;
        this.userList = new HashMap<>();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void addQuantity(final Integer initQuantity) {
        this.quantity = this.quantity + initQuantity;
    }

    public Map<Integer, GregorianCalendar> getUserList() {
        return this.userList;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.quantity, this.userList);
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof ItemInfo)) {
            return false;
        }
        final ItemInfo item = (ItemInfo) obj;
        return Objects.equal(this.quantity, item.quantity) && Objects.equal(this.userList, item.userList);
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public void setUserList(final Map<Integer, GregorianCalendar> userList) {
        this.userList = userList;
    }

    public boolean isAvailable() {
        if ((this.quantity - this.userList.size()) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
