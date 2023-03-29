package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
String readString(String prompt);
void writeString(Object obj);
default void writeLine(Object obj) {
		writeString(obj.toString() + "\n");
}
default <R> R readObjet(String prompt, String errorPrompt, Function<String, R> mapper) {
	boolean running = true;
	R result = null;
	while(running) {
		try {
			String str = readString(prompt);
			result = mapper.apply(str);
			running= false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
			}
	}
	return result;
	}
default String readStringPredicate (String prompt, String errorPrompt,
		Predicate<String> predicate) {
		boolean running = true;
		String str = null;
		while(running) {
			try {
				str = readString(prompt);
				if (!predicate.test(str)) 
				{
					throw new Exception("string mismatches to predicate's conditon");
				};
				running = false;
			} catch (Exception e) {
				writeLine(errorPrompt + " - " + e.getMessage());
			}
		}
	
			return str;
	}

default String readStringOptions(String prompt, String errorPrompt,
		Set<String> options) {
	boolean running = true;
	String str = null;
	while(running) {
		try {
			str = readString(prompt);
			if (!options.contains(str)) {
				throw new Exception("string doesn't exis in set options");
			}
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage() + "\n");
		}
	}
		return str;
}
	
default int readInt(String prompt, String errorPrompt) {
	boolean running = true;
	String str = null;
	int res = -1;
	while(running) {
		try {
			str = readString(prompt);
			res = Integer.parseInt(str);
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
		}
	}
	return res;
}
default int readInt(String prompt, String errorPrompt, int min, int max) {
	boolean running = true;
	String str = null;
	int res = -1;
	while(running) {
		try {
			str = readString(prompt);
			res = Integer.parseInt(str);
			if (res < min || res > max) {
				throw new Exception("number is out of range");
			}
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
		}
	}
	return res;
}
default long readLong(String prompt, String errorPrompt, long min, long max) {
	boolean running = true;
	String str = null;
	long res = -1;
	while(running) {
		try {
			str = readString(prompt);
			res = Long.parseLong(str);
			if (res < min || res > max) {
				throw new Exception("number is out of range");
			}
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
		}
	}
	return res;
}
default LocalDate readDateISO(String prompt, String errorPrompt) {
	boolean running = true;
	String str = null;
	while(running) {
		try {
			str = readString(prompt);
			LocalDate.parse(str);
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
		}
	}
		return LocalDate.parse(str);
	
}

default LocalDate readDate(String prompt, String errorPrompt, String format, LocalDate min, LocalDate max){
		boolean running = true;
		String str = null;
		LocalDate res = null;
		DateTimeFormatter dtf = null;
		while(running) {
			try {
				str = readString(prompt);
				dtf = DateTimeFormatter.ofPattern(format);
				res = LocalDate.parse(str, dtf);
				if (res.isBefore(min) || res.isAfter(max)) {
					throw new Exception("date is out of range");
				}
				running = false;
			} catch (Exception e) {
				writeLine(errorPrompt + " - " + e.getMessage());
			}
		}
		return res;
	}

default double readNumber(String prompt, String errorPrompt, double min, double max) {
	boolean running = true;
	String str = null;
	double res = -1;
	while(running) {
		try {
			str = readString(prompt);
			res = Double.parseDouble(str);
			if (res < min || res > max) {
				throw new Exception("number is out of range");
			}
			running = false;
		} catch (Exception e) {
			writeLine(errorPrompt + " - " + e.getMessage());
		}
	}
	return res;
}

}
