package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransferCopy extends Copy {

	public TransferCopy(String srcFilePass, String destFilePass, boolean overwrite) {
		super(srcFilePass, destFilePass, overwrite);
		}

	@Override
	long copy() throws FileAlreadyExistsException {
		long start = System.nanoTime();
		if (Files.exists(Path.of(destFilePass)) && !overwrite) {
			throw new FileAlreadyExistsException(destFilePass);
		}
		try (InputStream input = new FileInputStream(srcFilePass); 
				OutputStream output = new FileOutputStream(destFilePass);) {
			input.transferTo(output);
		} catch (IOException e) {
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
