package telran.io;

import java.io.*;
import java.nio.file.*;


public class FilesCopy extends Copy {

	public FilesCopy(String srcFilePass, String destFilePass, boolean overwrite) {
		super(srcFilePass, destFilePass, overwrite);
	}

	@Override
	long copy() throws FileAlreadyExistsException {
		long start = System.nanoTime();
		if (Files.exists(Path.of(destFilePass)) && !overwrite) {
			throw new FileAlreadyExistsException(destFilePass);
		} try {Files.copy(Path.of(srcFilePass), Path.of(destFilePass));
					}  catch (IOException e) {
							e.printStackTrace();
						}
			
	long stop = System.nanoTime() - start;
	return stop;
	}

	@Override
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		DisplayResult displayResult = new DisplayResult(copyTime, fileSize);
		return displayResult;
	}
	
}
