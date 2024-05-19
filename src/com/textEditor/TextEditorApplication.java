package com.textEditor;

/**
 * Java main class to run the application
 * Need to pass the text file path while running the application as command line argument
 */
public class TextEditorApplication {

	public static void main(String[] args) {
		if(args.length <= 0) {
			System.err.println("Error: Please Provide file name along with the path !!!");
		}
		CommandLineOperations commandLine = new CommandLineOperations();
		commandLine.processInput(args[0]);
	}

}
