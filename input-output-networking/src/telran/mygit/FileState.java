package telran.mygit;


public class FileState {
	
	String filePath;
	FileStatus fileStatus;
	
	public FileState (String fileName, FileStatus fileStatus) {
		this.filePath = fileName;
		this.fileStatus = fileStatus;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public FileStatus getFileStatus() {
		return fileStatus;
	}
	
	public String toString () {
		return String.format(filePath + "  " + fileStatus.toString());
		
	}

	
}
	


