package com.textEditor.test;

import java.io.IOException;
import java.util.List;

import com.textEditor.CommandLineOperations;

public class InputCommandValidatorTest {
	CommandLineOperations cmdLineOperations = new CommandLineOperations();
	
	public static void main(String[] args) throws IOException {
		InputCommandValidatorTest test = new InputCommandValidatorTest();
		test.validateCorrectInputTest();
		test.validateWrongInputTest();
		test.validateInputTestWithoutText();
		test.validateInputTestEmptyQuotes();
		test.validateInputTestNullValue();
		
	}

	public void validateCorrectInputTest() {
		List<String> testInputs = List.of("list", "save", "quit", "del 5", "ins 3 Some text inserted.", "F1");
		testInputs.forEach(input -> {
			boolean isValid = cmdLineOperations.validateInput(input);
			if(isValid)
				System.out.println("Validate Input test case passed for - " + input);
			else 
				System.err.println("Error: Validate test case failed for - " + input);
		});
	}
	
	public void validateWrongInputTest() {
		List<String> testInputs = List.of("list itr", "saves", "quit terminal", "del 5 some ss", "ins 3.", "F3");
		testInputs.forEach(input -> {
			boolean isValid = cmdLineOperations.validateInput(input);
			if(!isValid)
				System.out.println("Validate wrong input test case passed for - " + input);
			else 
				System.err.println("Error: Validate wrong input test case failed for - " + input);
		});
	}
	
	public void validateInputTestWithoutText() {
		boolean isValid = cmdLineOperations.validateInput("ins 1");
		if(!isValid)
			System.out.println("Validate Input without text test case passed");
		else 
			System.err.println("Error: Validate Input test case failed");
	}
	
	public void validateInputTestEmptyQuotes() {
		boolean isValid = cmdLineOperations.validateInput("");
		if(!isValid)
			System.out.println("Validate Input empty string test case passed");
		else 
			System.err.println("Error: Validate Input empty string test case failed");
	}
	

	public void validateInputTestNullValue() {
		boolean isValid = cmdLineOperations.validateInput(null);
		if(!isValid)
			System.out.println("Validate Input null value test case passed");
		else 
			System.err.println("Error: Validate Input null value test case failed");
		
	}
	
}
