package telran.io;

public class BufferCopyAppl {
	private static final String type = "BufferCopy";

	public static void main (String[] args) {
		FilesCopyBuilder builder = new FilesCopyBuilder();
		try {
			Copy copy = builder.build(type, args);
			copy.copyRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
