package telran.io;


public class DisplayResult {
long fileSize;
long copyTime;

public DisplayResult (long fileSize, long copyTime) {
	this.fileSize = fileSize;
	this.copyTime = copyTime;
}

public String toString() {
	String res = "fileSize - " + Long.toString(fileSize) + ", copyTime" + Long.toString(copyTime);
	return res;
}

}
