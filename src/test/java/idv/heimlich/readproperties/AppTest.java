package idv.heimlich.readproperties;

import idv.heimlich.readproperties.common.codegenerate.CodeParm;
import idv.heimlich.readproperties.common.codegenerate.DBsetting;
import idv.heimlich.readproperties.common.codegenerate.SystemDefine;
import idv.heimlich.readproperties.common.context.AppContext;
import idv.heimlich.readproperties.common.file.FileLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppTest {

	public static void main(String[] args) {
		System.setProperty("SystemDefine", SystemDefine.CLMS_AP.name());

		// 取SystemDefine指定pclms/ap/config.properties內的DB_SETTING =
		// pclms/db.properties
		final String dbProperties = AppContext.get().getValue(
				CodeParm.DB_SETTING.name());
		System.out.println(dbProperties);

		final File f = FileLoader.getResourcesFile(AppTest.class, dbProperties);
		final Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(f));
			// pclms/db.properties內的jdbc.username = PCLMSMGR
			System.out.println(properties.getProperty(DBsetting.USERNAME
					.toText()));
		} catch (final FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}

		// 取SystemDefine指定pclms/ap/config.properties內的ZZZZ，沒有ZZZ，就帶入預設值 =
		// pclms/db.properties
		final String dbPropertiesNoKey = AppContext.get().getValue("ZZZZ",
				"db.properties");
		System.out.println(dbPropertiesNoKey);

		final File fNoKey = FileLoader.getResourcesFile(AppTest.class,
				dbPropertiesNoKey);
		final Properties propertiesNoKey = new Properties();
		try {
			propertiesNoKey.load(new FileInputStream(fNoKey));
			// 預設值db.properties內的jdbc.username = pftzcmgr
			System.out.println(propertiesNoKey.getProperty(DBsetting.USERNAME
					.toText()));
		} catch (final FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
	}
}
