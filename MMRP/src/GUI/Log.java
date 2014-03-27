package GUI;


/**
 * @author Dan Miller
 * Log class is used for logging all actions done by MMRP.  This allows us to pull the logs 
 * for review
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.sql.Timestamp;
import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class Log {
	private final static Logger logg = Logger.getLogger(Log.class.getName());
	private static FileHandler file = null;

	public static void createLogg() {
		try {
			file = new FileHandler(generateFileName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Logger l = Logger.getLogger("");
		file.setFormatter(new SimpleFormatter());
		l.addHandler(file);
		l.setLevel(Level.CONFIG);
	}
	
	private static String generateFileName() {
		DateFormat doItLikeThis = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
		doItLikeThis.setTimeZone(TimeZone.getTimeZone("EST"));

		String returnString = new String("MMRP_" + doItLikeThis.format(new Date()) + ".log");
		return returnString;
		
	}
	
	public static void writeLogInfo(String message) {
		logg.log(Level.INFO, message);
	}

	public static void writeLogSevere(String message) {
		logg.log(Level.SEVERE, message);
	}
	public static void writeLogWarning(String message) {
		logg.log(Level.WARNING, message);
	}
	public static void writeLogFine(String message) {
		logg.log(Level.FINE, message);
	}
	
}


