package demo.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBTest {
	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase db = mongoClient.getDatabase("sjp");
		MongoCollection<Document> cols = db.getCollection("imooc_collection");
		MongoCursor<Document> cursor = cols.find().iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
		mongoClient.close();

	}
}
