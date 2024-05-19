package com.textEditor.test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.textEditor.LineEditorOperations;
import com.textEditor.LineEditorOperationsImpl;

public class LineEditorTest {
	LineEditorOperations lineEditor = new LineEditorOperationsImpl();
	List<String> readText_expected = null;
	public final static String FILE_NAME = "SampleTest.txt";
	
	public LineEditorTest() throws IOException {
		lineEditor.saveFile(sampleText_actual, FILE_NAME);
		readText_expected = lineEditor.readFile(FILE_NAME);
	}
	
	public static void main(String[] args) throws IOException {
		LineEditorTest test = new LineEditorTest();
		test.readFileTest();
		test.listAllLinesTest();
		test.delLineTest();
		test.insLineTest();
		test.saveFileTest();
	}
	
	String sampleText_actual = "Angular lets you start small and supports you as your team and apps grow.\n"
			+ "Join the millions of developers building with Angular in a thriving and friendly community.\n"
			+ "Rely on Angular's internationalization tools, security, and accessibility to build for everyone around the world.";
	String listText_actual = "1 : Angular lets you start small and supports you as your team and apps grow.\n"
			+ "2 : Join the millions of developers building with Angular in a thriving and friendly community.\n"
			+ "3 : Rely on Angular's internationalization tools, security, and accessibility to build for everyone around the world.";
	String delText_actual = "Angular lets you start small and supports you as your team and apps grow.\n"
			+ "Rely on Angular's internationalization tools, security, and accessibility to build for everyone around the world.";
	String insText_actual = "Angular lets you start small and supports you as your team and apps grow.\n"
			+ "Some random text.\n"
			+ "Join the millions of developers building with Angular in a thriving and friendly community.\n"
			+ "Rely on Angular's internationalization tools, security, and accessibility to build for everyone around the world.";

	public void readFileTest() {
		if(!readText_expected.stream().collect(Collectors.joining("\n")).equals(sampleText_actual))
			System.err.println("Error: Read file test case failed");
		 else
			System.out.println("Read file test case passed");
	}
	
	public void listAllLinesTest() {
		List<String> listText_expected = lineEditor.listAllLines(readText_expected);
		
		if(!convertListToString(listText_expected).equals(listText_actual))
			System.err.println("Error: List each line test case failed");
		else
			System.out.println("List each line test case passed");
	}
	
	
	public void delLineTest() {
		readText_expected = lineEditor.readFile(FILE_NAME);
		lineEditor.delLine(readText_expected, 2);
		
		if(!convertListToString(readText_expected).equals(delText_actual))
			System.err.println("Error: Delete line test case failed");
		else
			System.out.println("Delete line  test case passed");
	}
	
	public void insLineTest() {
		readText_expected = lineEditor.readFile(FILE_NAME);
		lineEditor.insLine(readText_expected, 2, "Some random text.");
		if(!convertListToString(readText_expected).equals(insText_actual))
			System.err.println("Error: Insert line test case failed");
		else
			System.out.println("Insert line  test case passed");
	}
	
	public void saveFileTest() throws IOException {
		readText_expected = lineEditor.readFile(FILE_NAME);
		lineEditor.insLine(readText_expected, 2, "Some random text.");
		
		lineEditor.saveFile(convertListToString(readText_expected), FILE_NAME);
		List<String> readText_next = lineEditor.readFile(FILE_NAME);
		
		if(!convertListToString(readText_next).equals(convertListToString(readText_expected)))
			System.err.println("Error: Save file test case failed");
		else
			System.out.println("Save file  test case passed");
	}
	
	private String convertListToString(List<String> allLines) {
		return allLines.stream().collect(Collectors.joining("\n"));
	}
	
}
