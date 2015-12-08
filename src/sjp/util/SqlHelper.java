package sjp.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

	public static void generateClass(String str) {
		generateClass(handle(str));
	}

	public static void generateClass(List<TableField> tableFieldList) {
		String result = "";

		for (TableField field : tableFieldList) {
			String s = field.fieldName;
			if (s.trim().length() > 0) {
				String property = turnToCamelCase(s);
				String classType = getClassByDbFieldType(field.fieldType);
				result += "private " + classType + " " + property + ";\n";
			}
		}
		for (TableField field : tableFieldList) {
			String s = field.fieldName;
			if (s.trim().length() > 0) {

				String property = turnToCamelCase(s);
				String classType = getClassByDbFieldType(field.fieldType);

				String setMethod = getNameOfSetMethod(property);
				String getMethod = getNameOfGetMethod(property);
				result += "public " + classType + " " + getMethod + "(){\n " + " return this." + property + ";\n   }\n";
				result += "public void " + setMethod + "(" + classType + " " + property + "){\n setChangedFlag(\""
						+ property + "\");\nthis." + property + "=" + property + ";\n   }\n";
			}
		}
		System.out.println(result);
	}

	private static String getClassByDbFieldType(String fieldType) {
		if (fieldType.equalsIgnoreCase("date")) {
			return "Date";
		}
		if (fieldType.equalsIgnoreCase("number")) {
			return "Double";

		}
		return "String";
	}

	public static String getNameOfSetMethod(String property) {
		return "set" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	public static String getNameOfGetMethod(String property) {
		return "get" + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	public static List<TableField> handle(String str) {
		str = str.replaceAll("\n", "");
		str = str.replaceAll(" +", " ");
		String[] arr = str.split(",");
		String regex = "\\s";
		List<TableField> tableFieldList = new ArrayList<TableField>();
		SqlHelper sqlHelper = new SqlHelper();
		for (String string : arr) {
			TableField field = sqlHelper.new TableField();
			string = string.trim();
			String[] arr1 = string.split(" ");
			field.fieldName = arr1[0];
			field.fieldType = arr1[1];
			tableFieldList.add(field);

			// System.out.println(field);
		}
		return tableFieldList;
	}

	class TableField {
		public String fieldName;
		public String fieldType;

		@Override
		public String toString() {
			return "TableField [fieldName=" + fieldName + ", filedType=" + fieldType + "]";
		}
	}

	public static void main(String[] args) {
		// System.out.println(turnToCamelCase("CHECKLIST_RSP"));
		// System.out.println(turnToCamelCase("T_DECL_GOODS"));

		// System.out.println(releaseCamelCase("TDeclGoodsF"));
		//
		// System.out.println(getFieldsStr(User.class));

		String str = "RSP_ID       VARCHAR2(32) not null,\n" + "  ORDER_ID     NUMBER,\n"
				+ "  ORDER_NO     VARCHAR2(60),\n" + "  COMPANY_NAME VARCHAR2(200),\n"
				+ "  COMPANY_CODE VARCHAR2(20),\n" + "  MESSAGE_TYPE VARCHAR2(4),\n"
				+ "  MESSAGE_DESC VARCHAR2(500),\n" + "  MESSAGE_TIME DATE";

		generateClass(str);

		// test1(str);
		// genAlterScript("RE_BUYER", str);
	}
}
