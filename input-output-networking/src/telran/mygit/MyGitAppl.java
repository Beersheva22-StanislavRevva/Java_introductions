package telran.mygit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import telran.view.*;

public class MyGitAppl {

	public static void main (String[] args) {
		InputOutput io = new StandardInputOutput();
		GitRepository git = new GitRepositoryImpl();
		/*if (Files.exists(Path.of(GitRepository.GIT_FILE))) {
			git.init();	
		} else { try {
			Files.createFile(Path.of(GitRepository.GIT_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		}*/
		git.addIgnoredFileNameExp(".mygit");
		GitControlItems gitControlItems = new GitControlItems(git);
		Menu menu = gitControlItems.getMenu();
		menu.perform(io);

	}

}
