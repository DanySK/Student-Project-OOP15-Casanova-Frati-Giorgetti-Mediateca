package view;

import view.ViewImpl.UserInfo;

public interface UserModify {

	void setField(String name, String surname, String username,
			String password, String birthDate, String email, String telephone);

	String getInfo(UserInfo info);

}
