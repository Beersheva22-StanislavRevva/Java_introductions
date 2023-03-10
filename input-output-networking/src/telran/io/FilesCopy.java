package telran.io;

import java.io.*;
import java.nio.file.*;

import org.junit.jupiter.api.DisplayNameGenerator.Standard;


public class FilesCopy extends Copy {

	public FilesCopy(String srcFilePass, String destFilePass, boolean overwrite) {
		super(srcFilePass, destFilePass, overwrite);
	}

	@Override
	long copy()	{
		long res = 0;
		try {Files.copy(Path.of(srcFilePass), Path.of(destFilePass), StandardCopyOption.REPLACE_EXISTING);
		}  catch (IOException e) {
			e.printStackTrace();
			}
		try {
			res = Files.size(Path.of(destFilePass));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return res;
	}

	@Override
	DisplayResult getDisplayResult(long fileSize, long copyTime) {
		DisplayResult displayResult = new DisplayResult(copyTime, fileSize);
		return displayResult;
	}
	
}
