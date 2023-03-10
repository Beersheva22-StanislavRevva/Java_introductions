package telran.io;

public class TransferCopyAppl {
	private static final String type = "TransferCopy";

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
