package idv.heimlich.readproperties.common.context;

import idv.heimlich.readproperties.common.codegenerate.SystemDefine;
import idv.heimlich.readproperties.common.exception.TxBusinessException;
import idv.heimlich.readproperties.common.file.FileLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.xwork.StringUtils;

public class AppContext {
	
	private String config = "config.properties";
	private Properties properties = System.getProperties();
	final static AppContext CONTEXT = new AppContext();
	private boolean load = false;
	final String systemCode;

	public AppContext() {
		super();
		this.systemCode = System.getProperty("SystemDefine");
		if (StringUtils.isBlank(systemCode)) {
			throw new TxBusinessException("系統代碼不得空白");
		}
		SystemDefine systemDefine = SystemDefine.valueOf(this.systemCode);
		this.config = systemDefine.getConfigFile();
	}

	public void loadConfig() {
		try {
			File file = FileLoader.getResourcesFile(AppContext.class, this.config);
			this.properties.load(new FileInputStream(file));
			this.load = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public String getValue(String key, String defaultValue) {
		return init().getProperty(key, defaultValue);
	}

	public String getValue(String key) {
		return init().getProperty(key);
	}

	private Properties init() {
		if (!this.load) {
			this.loadConfig();
		}
		return this.properties;
	}

	public static AppContext get() {
		return CONTEXT;
	}

}
