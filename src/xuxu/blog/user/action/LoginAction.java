package xuxu.blog.user.action;

import xuxu.blog.common.action.BaseAction;
import xuxu.blog.entity.User;

public class LoginAction extends BaseAction {

	private User user = new User();

	public String execute() {
		if ("monster".equalsIgnoreCase(user.getName())) {
			return "fail";
		}
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
