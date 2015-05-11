package sjp.util;

import java.lang.reflect.Field;

public class SqlHelper {
	public static String getFieldsStr(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		String result = "";
		for (Field field : fields) {
			String name = field.getName();
			result += releaseCamelCase(name) + ",";
		}
		return result.substring(0, result.lastIndexOf(","));
	}

	public static String releaseCamelCase(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		String result = "";
		int index = 0;
		for (int i = 0; i < str.length(); i++) {
			if (Character.isUpperCase(str.charAt(i))) {
				String s = str.substring(index, i);
				index = i;
				if (s.equals("")) {
					continue;
				}
				result += s + "_";
			}
		}
		result += str.substring(index);
		return result.toUpperCase();
	}

	public static String turnToCamelCase(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		str = str.trim().toLowerCase();
		String[] arr = str.split("_");
		String result = "";
		int i = 0;
		for (String s : arr) {
			if (i++ != 0) {
				s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
			}
			result += s;

		}
		return result;
	}

	public static void test1(String str) {
		String[] arr = str.split(" ");
		String result = "";

		for (String s : arr) {
			if (s.trim().length() > 0) {
				String property = turnToCamelCase(s);
				result += "private String " + property + ";\n";
			}
		}
		for (String s : arr) {
			if (s.trim().length() > 0) {
				String property = turnToCamelCase(s);

				String setMethod = getNameOfSetMethod(property);
				String getMethod = getNameOfGetMethod(property);
				result += "public String " + getMethod + "(){\n "
						+ " return this." + property + ";\n   }\n";
				result += "public void " + setMethod + "(String " + property
						+ "){\n setChangedFlag(\"" + property + "\");\nthis."
						+ property + "=" + property + ";\n   }\n";
			}
		}
		System.out.println(result);
	}

	public static String getNameOfSetMethod(String property) {
		return "set" + Character.toUpperCase(property.charAt(0))
				+ property.substring(1);
	}

	public static String getNameOfGetMethod(String property) {
		return "get" + Character.toUpperCase(property.charAt(0))
				+ property.substring(1);
	}

	public static void main(String[] args) {
		// System.out.println(turnToCamelCase("CHECKLIST_RSP"));
		// System.out.println(turnToCamelCase("T_DECL_GOODS"));
		//
		// System.out.println(releaseCamelCase("TDeclGoodsF"));
		//
		// System.out.println(getFieldsStr(User.class));

		String str = "ENT_FILING_INFO_ID " + "  ENT_FILING_MAG_ID  "
				+ "  AUDIT_ORG_CODE     " + "  AUDIT_DEPT_CODE    "
				+ "  AUDIT_PERSON_CODE  " + "  ALLOCATION_DATE    "
				+ "  RECEIVE_DATE       " + "  AUDIT_STATUS       "
				+ "  AUDIT_MESSAGE      " + "  AUDIT_SEQ_NO      "
				+ "  AUDIT_DATE        ";

		test1(str);
	}
}
