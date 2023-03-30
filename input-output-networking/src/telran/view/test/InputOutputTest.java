package telran.view.test;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import org.junit.jupiter.api.*;
import telran.view.StandardInputOutput;

class InputOutputTest extends StandardInputOutput {
	
	StandardInputOutput standartInputOutput; 
	String errorPrompt = "Wrong value \n"; 

	@Test
	@Disabled
	void readStringPredicateTest() {
		String prompt = "readStringPredicateTest, type the string";
		Predicate<String> predicate = s -> s.contains("@");
		writeString(readStringPredicate(prompt, errorPrompt, predicate));
		
	}
	
	@Test
	@Disabled
	void readStringOptionsTest() {
		String prompt = "readStringOptionsTest, type the string";
		Set<String> options = new HashSet <String> ();
		options.add("test string 1");
		options.add("test string 2");
		options.add("test string 3");
		writeString(readStringOptions(prompt, errorPrompt, options));
		
	}
	
	@Test
	@Disabled
	void readIntTest() {
		String prompt = "readIntTest1, type the number";
		writeString(readInt(prompt, errorPrompt));
		
	}
	
	@Test
	@Disabled
	void readIntTest2() {
		String prompt = "readIntTest2, type the number";
		int min = 0;
		int max = 100;
		writeString(readInt(prompt, errorPrompt, min, max));
		
	}
	
	@Test
	@Disabled
	void readLongTest() {
		String prompt = "readLongTest, type the number";
		long min = 0l;
		long max = 2_200_000_000l;
		writeString(readLong(prompt, errorPrompt, min, max));
		
	}
	
	@Test
	@Disabled
	void readDateISOTest() {
		String prompt = "readDateISOTest, type the date (YYYY-MM-DD)";
		writeString(readDateISO(prompt, errorPrompt));
		
	}
	
	@Test
	
	void readDateTest() {
		String prompt = "readDateTest, type the date (yyyy-MM-dd)";
		LocalDate min = LocalDate.of(2000, 01, 01);
		LocalDate max = LocalDate.of(2023, 01, 01);
		String format = "yyyy mm dd";
		writeString(readDate(prompt, errorPrompt, "yyyy-MM-dd", min, max));
		
	}
	
	@Test
	@Disabled
	void readNumberTest() {
		String prompt = "readNumberTest, type the number";
		double min = 0.1;
		double max = 1;
		writeString(readNumber(prompt, errorPrompt, min, max));
		
	}
	

}
