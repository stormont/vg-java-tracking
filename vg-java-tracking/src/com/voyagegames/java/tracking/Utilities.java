package com.voyagegames.java.tracking;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.zip.Deflater;


/**
 * A general purpose helper class
 */
public class Utilities {

	/**
	 * Converts an exception stack track to a String
	 * @param exception The exception to convert to a readable String
	 * @return The readable String representation of the exception
	 */
	public static String exceptionToString(final Exception exception) {
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
	/**
	 * Basic compression algorithm for fastest available speed
	 * @param bytes The serialized data to compress
	 * @return The resulting compressed data
	 * @throws IOException
	 */
	public static byte[] compress(final byte[] bytes) throws IOException {
		final Deflater compressor = new Deflater();
		compressor.setLevel(Deflater.BEST_SPEED);
	    compressor.setInput(bytes);
	    compressor.finish();

	    /*
	     * Create an expandable byte array to hold the compressed data.
	     * You cannot use an array that's the same size as the orginal because
	     * there is no guarantee that the compressed data will be smaller than
	     * the uncompressed data.
	     */
		final ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
	    final byte[] buf = new byte[1024];
	    
	    while (!compressor.finished()) {
	        final int count = compressor.deflate(buf);
	        bos.write(buf, 0, count);
	    }
	
	    return bos.toByteArray();
	}

}
