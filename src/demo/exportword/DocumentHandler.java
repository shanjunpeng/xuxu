package demo.exportword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	private String realPath = null;
	// FrameworkRegistry.getRealContextPath();

	private Configuration configuration = null;

	public DocumentHandler() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}

	public String createDoc(String fileName, String templateFileName,
			Map<String, Object> dataMap) {
		realPath = realPath + "/documentOutput/";
		// String templateFileName = "grantRegTemplate.ftl";
		// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		// 这里我们的模板是放在com.havenliu.document.template包下面
		configuration.setClassForTemplateLoading(this.getClass(),
				"/com/itown/ies/common/document/template");
		Template t = null;
		try {
			// test.ftl为要装载的模板
			t = configuration.getTemplate(templateFileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// 输出文档路径及名称
		File outFile = new File(realPath + fileName);
		Writer out = null;
		try {

			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "utf-8"));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return realPath + fileName;
	}

	public static void main(String[] args) {
		DocumentHandler dh = new DocumentHandler();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		dataMap.put("countryName", "1111111111111");
		dataMap.put("supplierEn", "22222222222222");
		dataMap.put("supplierCh", "33333333333333");
		String filePath = dh.createDoc("abc.doc", "grantRegTemplate.ftl",
				dataMap);
	}

}
