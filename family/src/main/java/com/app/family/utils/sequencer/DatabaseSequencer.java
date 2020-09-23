package com.app.family.utils.sequencer;

import javax.persistence.Transient;

public class DatabaseSequencer {
	@Transient 
	public static final String SEQUENCE_NAME = "token_sequence";
	
	@Transient 
	public static final String FAMILY_CODE_SEQUENCE = "family_code_sequence";

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	public static String getFamilyCodeSequence() {
		return FAMILY_CODE_SEQUENCE;
	}

	
}
