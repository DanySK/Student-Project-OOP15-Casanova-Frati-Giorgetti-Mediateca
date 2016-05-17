package model;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ItemInfo {

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
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        result = (prime * result) + ((this.userList == null) ? 0 : this.userList.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ItemInfo other = (ItemInfo) obj;
        if (this.quantity == null) {
            if (other.quantity != null) {
                return false;
            }
        } else if (!this.quantity.equals(other.quantity)) {
            return false;
        }
        if (this.userList == null) {
            if (other.userList != null) {
                return false;
            }
        } else if (!this.userList.equals(other.userList)) {
            return false;
        }
        return true;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public void setUserList(final Map<Integer, GregorianCalendar> userList) {
        this.userList = userList;
    }

}
