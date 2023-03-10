package telran.io;

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
	
	abstract long copy();
	
	abstract DisplayResult getDisplayResult(long copyTime, long fileSize);
	
	void copyRun() {
		try {
			isAlowedToCopy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long copyTime;
		long fileSize = 0;
		long start = System.nanoTime();
		fileSize = copy();
		copyTime = System.nanoTime() - start;
		String res = getDisplayResult(copyTime, fileSize).toString();
		System.out.println(res);
	}

	private void isAlowedToCopy() throws Exception {
		if (Files.exists(Path.of(destFilePass)) && !overwrite) {
			throw new Exception (destFilePass +  " - not allowed to overwrite file");
		}
		
	}
	
	
	
}
