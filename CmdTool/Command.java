package PA6;

public interface Command {
	// Takes a String[] that contains the initial data
    // and returns a String[] that contains the 
    // data that results from executing a command
    String[] execute(String[] data);
}
