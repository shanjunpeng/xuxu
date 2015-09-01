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
				String classType = "String";
				if (field.filedType.equalsIgnoreCase("date")) {
					classType = "Date";

				}
				result += "private " + classType + " " + property + ";\n";
			}
		}
		for (TableField field : tableFieldList) {
			String s = field.fieldName;
			if (s.trim().length() > 0) {

				String property = turnToCamelCase(s);
				String classType = "String";
				if (field.filedType.equalsIgnoreCase("date")) {
					classType = "Date";
				}

				String setMethod = getNameOfSetMethod(property);
				String getMethod = getNameOfGetMethod(property);
				result += "public " + classType + " " + getMethod + "(){\n "
						+ " return this." + property + ";\n   }\n";
				result += "public void " + setMethod + "(" + classType + " "
						+ property + "){\n setChangedFlag(\"" + property
						+ "\");\nthis." + property + "=" + property
						+ ";\n   }\n";
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
			field.filedType = arr1[1];
			tableFieldList.add(field);

			// System.out.println(field);
		}
		return tableFieldList;
	}

	class TableField {
		public String fieldName;
		public String filedType;

		@Override
		public String toString() {
			return "TableField [fieldName=" + fieldName + ", filedType="
					+ filedType + "]";
		}
	}

	public static void main(String[] args) {
		// System.out.println(turnToCamelCase("CHECKLIST_RSP"));
		// System.out.println(turnToCamelCase("T_DECL_GOODS"));
		//
		// System.out.println(releaseCamelCase("TDeclGoodsF"));
		//
		// System.out.println(getFieldsStr(User.class));

		String str = "TP_GUID             VARCHAR2(128) not null,\n"
				+ "  APPROVAL_NO_KEY     VARCHAR2(200),\n"
				+ "  APPROVAL_NO         VARCHAR2(200),\n"
				+ "  G_TYPE              VARCHAR2(100),\n"
				+ "  NAME_OF_PLANT       VARCHAR2(400),\n"
				+ "  COUNTRY_CODE        VARCHAR2(6),\n"
				+ "  COUNTRY             VARCHAR2(440),\n"
				+ "  PREFECTURE          VARCHAR2(100),\n"
				+ "  CITY                VARCHAR2(100),\n"
				+ "  ADDRESS             VARCHAR2(400),\n"
				+ "  APPROVAL_TYPE       VARCHAR2(255),\n"
				+ "  REMARK              VARCHAR2(4000),\n"
				+ "  STATE               VARCHAR2(50),\n"
				+ "  CREATED             DATE,\n"
				+ "  MODIFIED            DATE,\n"
				+ "  CHECKSTATE          VARCHAR2(50),\n"
				+ "  CHECKTIME           DATE,\n"
				+ "  IS_DELETE           VARCHAR2(50),\n"
				+ "  OPER_FLAG           CHAR(1),\n"
				+ "  PROCESS_STATUS_FOOD CHAR(1),\n"
				+ "  PROCESS_DATE_FOOD   DATE,\n"
				+ "  PROCESS_STATUS      CHAR(1),\n"
				+ "  PROCESS_DATE        DATE,\n"
				+ "  REMARK1             VARCHAR2(100),\n"
				+ "  REMARK2             VARCHAR2(100)";
		generateClass(str);

		// test1(str);
		// genAlterScript("RE_BUYER", str);
	}
}
