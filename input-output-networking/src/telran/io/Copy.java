package telran.io;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Copy  {
	String srcFilePass;
	String destFilePass;
	boolean overwrite;
	
	public Copy (String srcFilePass,String destFilePass, boolean overwrite) {
	this.srcFilePass = srcFilePass;
	this.destFilePass = destFilePass;
	this.overwrite = overwrite;
	}
	
	abstract long copy() throws FileAlreadyExistsException;
	
	abstract DisplayResult getDisplayResult(long copyTime, long fileSize);
	
	void copyRun() throws FileAlreadyExistsException {
		long copyTime = this.copy();
		long fileSize = 0;
		try {
			fileSize = Files.size(Path.of(destFilePass));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String res = getDisplayResult(copyTime, fileSize).toString();
		System.out.println(res);
	}
	
	
	
}
