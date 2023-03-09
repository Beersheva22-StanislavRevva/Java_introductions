package telran.io;

public class DisplayResultBuffer extends DisplayResult {

	long bufferSize;

	public DisplayResultBuffer(long fileSize, long copyTime, long bufferSize) {
		super(fileSize, copyTime);
		this.bufferSize = bufferSize;
	}
	public String toString() {
		String res = "fileSize - " + Long.toString(fileSize) + ", copyTime" + Long.toString(copyTime) + ", bufferSize" + Long.toString(bufferSize);
		return res;
	}

}
