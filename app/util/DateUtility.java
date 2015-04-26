/**
 * 
 */
package util;

import java.util.concurrent.TimeUnit;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
public class DateUtility {

	public static long millisecondsToSeconds(long milliseconds) {
		return TimeUnit.MILLISECONDS.toSeconds(milliseconds);
	}
}
