package idv.heimlich.readproperties.common.codegenerate;

public enum DBsetting {

	DRIVER_CLASS_NAME("jdbc.driverClassName"), //
	URL("jdbc.url"), //
	USERNAME("jdbc.username"), //
	PASSWORD("jdbc.password"), //
	;
	
	final String name;
	final String code;

	private DBsetting(String code) {
		this.name = name();
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String toText() {
		return code;
	}
	
}
