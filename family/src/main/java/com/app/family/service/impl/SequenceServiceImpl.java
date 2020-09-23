package com.app.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.family.service.SequenceService;
import com.app.family.utils.sequencer.DatabaseSequencer;
import com.app.family.utils.sequencer.SequenceGeneratorService;

@Service
public class SequenceServiceImpl implements SequenceService {
	
	@Autowired
	SequenceGeneratorService sequenceService;

	@Override
	public String getFamilyNextSequence() {
		long seqNum = sequenceService.generateSequence(DatabaseSequencer.getFamilyCodeSequence());
        return String.valueOf(seqNum);
	}

}
