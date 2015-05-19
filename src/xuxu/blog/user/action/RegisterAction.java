package xuxu.blog.user.action;

import org.bson.Document;

import xuxu.blog.common.action.BaseAction;
import xuxu.blog.common.dao.MongoConnUtils;
import xuxu.blog.common.dao.MongoUtils;
import xuxu.blog.entity.User;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class RegisterAction extends BaseAction {

	private User user = new User();

	public String execute() {
		MongoDatabase db = MongoConnUtils.getDefaultDb();
		MongoCollection<Document> collection = db.getCollection("user");

		Document document = MongoUtils.obj2Document(user);
		collection.insertOne(document);

		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
