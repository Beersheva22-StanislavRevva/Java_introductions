package telran.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferCopy extends Copy {

	private int bufferSize;

	public BufferCopy(String srcFilePass, String destFilePass, boolean overwrite, int bufferSize) {
		super(srcFilePass, destFilePass, overwrite);
		this.bufferSize = bufferSize;
		
	}

	@Override
	long copy() {
		long res = 0;
		byte[] buffer = new byte[bufferSize];
		int count;
		try (InputStream input = new FileInputStream(srcFilePass); 
				OutputStream output = new FileOutputStream(destFilePass);
			)
			{while((count=input.read(buffer))!=-1) {
				output.write(buffer, 0 , count);
			}
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
	DisplayResult getDisplayResult(long copyTime, long fileSize) {
		DisplayResultBuffer displayResultBuffer = new DisplayResultBuffer(copyTime, fileSize, bufferSize);
		return displayResultBuffer;
	}
			
}
