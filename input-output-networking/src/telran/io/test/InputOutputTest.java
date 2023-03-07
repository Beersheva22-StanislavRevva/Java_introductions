package telran.io.test;
import java.io.*;
import java.nio.file.*;

import javax.imageio.IIOException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class InputOutputTest {
String fileName = "myFile";	
String directoryName = "myDirectory1/myDirectory2";
	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	void test() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1.getAbsolutePath());
		System.out.println(f1.getCanonicalPath());
	}
	@Test
	void printDirectoryFileTest() {
		printDirectoryFile("C:\\Users\\revva\\git\\input-output-networking\\input-output-networking",4);
	}
	
	void printDirectoryFile(String path, int maxLevel) {
		File folder = new File (path);
		System.out.println(folder.getName() + " - root"); 
		printDirectoryFile(path, maxLevel, 1);
	}
	
	void printDirectoryFile(String path, int maxLevel, int currentLevel) {
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel <1, all levels should be printed
		//output format
		//	<directory name (no points, no full absolute path)>
		//		<node name> - dir | file
		//			<node_name> ...
		//		<node name> - 
		File folder = new File (path);
		if (folder.isDirectory()) {
			for (File file : folder.listFiles()) {
				printFileName(file, currentLevel);
				if (file.isDirectory() && currentLevel < maxLevel) {
					printDirectoryFile(file.getAbsolutePath(), maxLevel,currentLevel + 1);
				}
			}
		} 
	}
	private void printFileName(File folder, int currentLevel) {
		if(folder.isDirectory()) { 
		 System.out.printf("%s%s - dir \n", "	".repeat(currentLevel), folder.getName());
		} else {
			System.out.printf("%s%s - file \n", "	".repeat(currentLevel), folder.getName());
		}
	}

	
	@Test
	void testFiles() {
		Path path = Path.of(".");
		System.out.println(path.toAbsolutePath().getNameCount());
	}
	@Test
	void printDirectoryFilesTest() {
		printDirectoryFiles("C:\\Users\\revva\\git\\input-output-networking\\input-output-networking",4);
	}
	void printDirectoryFiles(String path, int maxLevel) {
		System.out.println(Path.of(path).getFileName() + " - root");
		printDirectoryFiles(path, maxLevel, 1);
	}
	void printDirectoryFiles(String path, int maxLevel,int currentLevel) {
		//path -directory path
		//maxLevel - maximal level of printing, if maxLevel <1, all levels should be printed
		//output format
		//	<directory name (no points, no full absolute path)>
		//		<node name> - dir | filev
		//			<node_name> ...
		//		<node name> -
		//File folder = new File (path);
		if (Files.isDirectory(Path.of(path))) {
			Object[] ar = null;
			try {
				ar = Files.list(Path.of(path)).toArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			    for (int i = 0; i < ar.length; i++) {
			    	printFilesName(ar[i], currentLevel);
			    	if (Files.isDirectory(Path.of(ar[i].toString())) && currentLevel < maxLevel) {
			    		printDirectoryFiles(ar[i].toString(), maxLevel,currentLevel + 1);
			    	}
			    }
		} 
	}
	
	private void printFilesName(Object file, int currentLevel) {
		if(Files.isDirectory(Path.of(file.toString()))) {
		System.out.printf("%s%s - dir \n", "	".repeat(currentLevel), Path.of(file.toString()).getFileName());
		} else {
			System.out.printf("%s%s - file \n", "	".repeat(currentLevel), Path.of(file.toString()).getFileName());	
		}
	}
	
}
