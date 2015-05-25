package xuxu.blog.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {

	private static Map<String, Properties> propMap = new HashMap<String, Properties>();

	public static Properties getProperties(String fileName) {
		if (propMap.containsKey(fileName)) {
			return propMap.get(fileName);
		}
		Properties prop = new Properties();
		try {
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream(
					fileName));
			propMap.put(fileName, prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
