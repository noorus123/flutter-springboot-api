package com.app.family.utils;

public class IdGenerator {	
	    private static final long twepoch = 1288834974657L;
	    private static final long sequenceBits = 17;
	    private static final long sequenceMax = 65536;
	    private static volatile long lastTimestamp = -1L;
	    private static volatile long sequence = 0L;
	    
	    public static String getUniqueId(){
	    	Long generatedId = generateLongId();
	    	String uniqueId = generatedId.toString();	    	
			return uniqueId;	    	
	    }

	    private static synchronized Long generateLongId() {
	        long timestamp = System.currentTimeMillis();
	        if (lastTimestamp == timestamp) {
	            sequence = (sequence + 1) % sequenceMax;
	            if (sequence == 0) {
	                timestamp = tilNextMillis(lastTimestamp);
	            }
	        } else {
	            sequence = 0;
	        }
	        lastTimestamp = timestamp;
	        Long id = ((timestamp - twepoch) << sequenceBits) | sequence;
	        return id;
	    }

	    private static long tilNextMillis(long lastTimestamp) {
	        long timestamp = System.currentTimeMillis();
	        while (timestamp <= lastTimestamp) {
	            timestamp = System.currentTimeMillis();
	        }
	        return timestamp;
	    }
}


