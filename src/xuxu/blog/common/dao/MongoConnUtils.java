package xuxu.blog.common.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnUtils {
	public static MongoClient mongoClient = null;

	public static MongoClient getMongoClient() {
		if (mongoClient == null) {
			mongoClient = new MongoClient("localhost");
		}
		return mongoClient;
	}

	public static MongoDatabase getDefaultDb() {
		return getMongoClient().getDatabase("xuxu");
	}
}
