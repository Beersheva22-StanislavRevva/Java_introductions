package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TransferCopy extends Copy {

	public TransferCopy(String srcFilePass, String destFilePass, boolean overwrite) {
		super(srcFilePass, destFilePass, overwrite);
		}

	@Override
	long copy() {
		long res = 0;
		try (InputStream input = new FileInputStream(srcFilePass); 
			OutputStream output = new FileOutputStream(destFilePass);) {
				input.transferTo(output);
		} catch (IOException e) {
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
