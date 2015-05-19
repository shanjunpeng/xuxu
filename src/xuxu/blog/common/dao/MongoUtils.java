package xuxu.blog.common.dao;

import java.lang.reflect.Field;

import org.bson.Document;

import sjp.util.BeanUtils;

public class MongoUtils {
	public static Document obj2Document(Object obj) {
		Document document = new Document();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			document.put(fieldName, BeanUtils.getPropertyVal(obj, fieldName));
		}
		return document;
	}
}
