package telran.mygit;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;

public class CommitMessage {

	String commitName;
	LocalDateTime date;
	
	public CommitMessage(String commitName, LocalDateTime date) {
		this.commitName = commitName;
		this.date = date;
		}
	
}
