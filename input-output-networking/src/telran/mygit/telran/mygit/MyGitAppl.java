package telran.mygit;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import telran.view.*;

public class MyGitAppl {

	public static void main (String[] args) {
		InputOutput io = new StandardInputOutput();
		GitRepositoryImpl git = GitRepositoryImpl.init();
		git.addIgnoredFileNameExp(".mygit");
		GitControlItems gitControlItems = new GitControlItems(git);
		Menu menu = gitControlItems.getMenu();
		menu.perform(io);

	}

}
