package com.textEditor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Read input from user and process
 */
public class CommandLineOperations {
	private LineEditorOperations lineEditor = new LineEditorOperationsImpl();
	private TextFrameDisplay textFrame = new TextFrameDisplay();
	private boolean IS_LISTED = false;
	private List<String> RAW_CONTENT;

	/**
	 * Read input and route accordingly to process
	 * 
	 * @param path - input file
	 */
	public void processInput(String path) {
		RAW_CONTENT = lineEditor.readFile(path);
		textFrame.setText(convertListToString(RAW_CONTENT, Boolean.FALSE));
		System.out.println("Enter your input command or Type \"F1\" for help");
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				String commandInput = scanner.nextLine();
				commandInput = null != commandInput ? commandInput.trim() : null;
				// validate input commands
				if (!validateInput(commandInput)) {
					System.err.println("Invalid Input");
				} else if (commandInput.startsWith("del") || commandInput.startsWith("ins")) {
					executeCommand(commandInput);
				} else {
					executeCommand(commandInput, path);
				}
			}
		} catch (Exception e) {
			System.err.println("An error occured !!! ");
		}
	}

	public boolean validateInput(String commandInput) {
		if(commandInput == null)
			return false;
		List<String> allowed = List.of("list", "save", "quit", "^del \\d$", "^ins \\d .*", "F1");
		return allowed.stream().anyMatch(value -> Pattern.matches(value, commandInput));
	}

	/**
	 * Execute delete and insert commands for the files
	 * 
	 * @param commandInput - user input
	 */
	private void executeCommand(String commandInput) {
		String[] command = commandInput.split(" ");
		int lineNum = Integer.parseInt(command[1]);
		if ("del".equalsIgnoreCase(command[0])) {
			lineEditor.delLine(RAW_CONTENT, lineNum);
			textFrame.setText(convertListToString(RAW_CONTENT, IS_LISTED));
			System.out.println(String.format("Deleted line number %s Successfully !!! ", lineNum));

		} else if ("ins".equalsIgnoreCase(command[0])) {
			lineEditor.insLine(RAW_CONTENT, lineNum, Arrays.asList(command).stream().skip(2).collect(Collectors.joining(" ")));
			textFrame.setText(convertListToString(RAW_CONTENT, IS_LISTED));
			System.out.println(String.format("Inserted line at line number %s Successfully !!! ", lineNum));
		}
	}

	/**
	 * Execute listing lines, save and quit operations Added "F1" functionality to
	 * help users to use commands
	 * 
	 * @param commandInput - user input
	 * @param path         - input file
	 */
	private void executeCommand(String commandInput, String path) {
		switch (commandInput) {
		case "F1":
			printHelpCommands();
			break;
		case "list":
			IS_LISTED = true;
			textFrame.setText(convertListToString(RAW_CONTENT, Boolean.TRUE));
			System.out.println("All the lines listed in the order");
			break;
		case "save":
			try {
				lineEditor.saveFile(convertListToString(RAW_CONTENT, Boolean.FALSE), path);
				System.out.println("File Saved Successfully !!!");
			} catch (IOException e) {
				System.out.println("An error occurred while saving File !!!");
			}
			break;
		case "quit":
			System.out.println("Quitting Line Editor");
			lineEditor.quit();
			break;
		default:
			System.err.println("Invalid Input");
			break;
		}
	}

	/**
	 * Print all the available help commands
	 */
	private void printHelpCommands() {
		System.out.println("\"list\" - to list all the text file lines in the order");
		System.out.println(
				"\"del n\" - delete line at n, n should be a number in the range 1 to number of lines present in the file");
		System.out.println(
				"\"ins n\" - insert a line at nth position, n should be a number in the range 1 to number of lines present in the file");
		System.out.println("\"save\" - save file to disk");
		System.out.println("\"quit\" - quits the editor and returns to the command line");
	}

	/**
	 * Convert List of String to String separated by next line character
	 * 
	 * @param allLines
	 * @param isListed
	 * @return String
	 */
	private String convertListToString(List<String> allLines, boolean isListed) {
		allLines = isListed ? lineEditor.listAllLines(allLines) : allLines;
		return allLines.stream().collect(Collectors.joining("\n"));
	}
}
