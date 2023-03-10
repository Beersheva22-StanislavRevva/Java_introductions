package telran.io;

public class DisplayResultBuffer extends DisplayResult {

	long bufferSize;

	public DisplayResultBuffer(long fileSize, long copyTime, int bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
	}
	public String toString() {
		String res = "fileSize - " + Long.toString(fileSize / 1_048_576) + " MB, copyTime - " + Long.toString(copyTime / 1_000_000) +
				" ms" + ", bufferSize - " + bufferSize / 1024 + " KB" ;
		return res;
	}

}
