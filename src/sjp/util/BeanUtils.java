package sjp.util;

import java.lang.reflect.Method;

public class BeanUtils {
	public static String getGetMethodName(String property) {
		return "get" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	public static String getSetMethodName(String property) {
		return "set" + property.substring(0, 1).toUpperCase()
				+ property.substring(1);
	}

	public static Object getPropertyVal(Object obj, String property) {
		String methodName = getGetMethodName(property);
		try {
			Method method = obj.getClass().getMethod(methodName, null);
			return method.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
