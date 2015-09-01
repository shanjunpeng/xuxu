package sjp.util;

public class ScriptHelper {

	public static String generateSysroleScript(int[] roleIdArr,
			String[] sysServiceIdArr) {
		StringBuilder sb = new StringBuilder();
		String str = "insert into t_s_sys_roleservice (ROLE_ID, SERVICE_ID) values (%d, '%s');";
		for (int i = 0; i < roleIdArr.length; i++) {
			for (int j = 0; j < sysServiceIdArr.length; j++) {
				sb.append(String.format(str, roleIdArr[i], sysServiceIdArr[j]));
				sb.append("\n");
			}
			sb.append("\n");
		}
		sb.append("commit;");
		return sb.toString();
	}

	public static void main(String[] args) {
		int[] roleIdArr = new int[] { 5002, 5102, 5202, 5302, 5402, 6102, 6202,
				6302, 6402, 6502 };
		String[] sysServiceIdArr = new String[] {
				"REGISTRY_PRO_ACCESS_SEARCH_SERVICE",
				"REGISTRY_PRO_ACCESS_VISIT_SERVICE",
				"REGISTRY_PRO_ACCESS_UPDATE_SERVICE" };
		System.out.println(generateSysroleScript(roleIdArr, sysServiceIdArr));
	}

}
