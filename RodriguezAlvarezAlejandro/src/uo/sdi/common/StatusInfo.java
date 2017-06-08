package uo.sdi.common;

public class StatusInfo {
	
	protected String value;

	public StatusInfo() {
		value = null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "StatusInfo [value=" + value + "]";
	}

}
