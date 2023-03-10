package telran.io;


public class DisplayResult {
long fileSize;
long copyTime;

public DisplayResult (long fileSize, long copyTime) {
	this.fileSize = fileSize;
	this.copyTime = copyTime;
}

public String toString() {
	String res = "fileSize - " + Long.toString(fileSize / 1_048_576) + " MB, copyTime - " + Long.toString(copyTime / 1_000_000) + " ms";
	return res;
}

}
