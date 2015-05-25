package xuxu.blog.common.dao;

import java.util.Properties;

import xuxu.blog.common.utils.ConfigUtil;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnUtils {

	private static ThreadLocal<MongoClient> mcLocal = new ThreadLocal<MongoClient>();
	private static Properties dbConfig = ConfigUtil
			.getProperties("dbconfig.properties");

	public static MongoClient getMongoClient() {
		MongoClient mongoClient = mcLocal.get();
		if (mongoClient == null) {
			mongoClient = new MongoClient(dbConfig.getProperty("url"));
			mcLocal.set(mongoClient);
		}
		return mongoClient;
	}

	public static MongoDatabase getDefaultDb() {
		return getMongoClient().getDatabase(dbConfig.getProperty("dbname"));
	}
}
