package team000;

public enum SenseBoolean {
	TRUE,
	FALSE,
	CANNOT_SENSE,
	;

	public static SenseBoolean create(boolean b) {
		return b ? TRUE : FALSE;
	}
	
	public static SenseBoolean create(Boolean b) {
		if (b == null) return CANNOT_SENSE;
		return create(b.booleanValue());
	}
}
