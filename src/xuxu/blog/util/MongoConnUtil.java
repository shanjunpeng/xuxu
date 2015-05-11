package xuxu.blog.util;

import com.mongodb.MongoClient;

public class MongoConnUtil {
	public static MongoClient mongoClient = null;

	public static MongoClient getInstance() {
		if (mongoClient == null) {
			mongoClient = new MongoClient("localhost");
		}
		return mongoClient;
	}
}
