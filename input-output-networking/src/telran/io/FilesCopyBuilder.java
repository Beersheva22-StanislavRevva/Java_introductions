package telran.io;

public class FilesCopyBuilder {

	final String filesCopy = "FilesCopy";
	final String transferCopy = "TransferCopy";
	final String bufferCopy = "BufferCopy";
	
	
	public Copy build (String type, String[] args) throws Exception {
		if (args.length < 2) {
			throw new Exception ("Not enough args");
		}
		String srcFilePass = args [0];
		String destFilePass = args [1];
		boolean overwrite = false;
		int bufferSize = 1_000_000;
		if (args.length > 2 && args[2].equalsIgnoreCase("true")) {
			overwrite = true;
		}
		if (args.length > 3) {
			bufferSize = Integer.parseInt(args[3]);
		}
		return switch (type) {
			case filesCopy -> new FilesCopy(srcFilePass, destFilePass, overwrite);
			case transferCopy -> new FilesCopy(srcFilePass, destFilePass, overwrite);
			case bufferCopy -> new BufferCopy(srcFilePass, destFilePass, overwrite, bufferSize);
			default -> throw new IllegalArgumentException("Unexpected value: " + type);
		};
	}
	
}
