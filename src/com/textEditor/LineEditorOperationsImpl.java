package com.textEditor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Line Editor implementation for read, list, save, insert, delete, quit operations
 */
public class LineEditorOperationsImpl implements LineEditorOperations {

	@Override
	public List<String> readFile(String path) {
		try {
			return Files.readAllLines(Paths.get(path));
		} catch (IOException e) {
			System.err.println("Error while reading file from the path - " + path);
			quit();
		}
		return null;
	}
	
	@Override
	public List<String> listAllLines(List<String> allLines) {
		return IntStream.range(0, allLines.size()).mapToObj(num -> (num + 1) + " : " + allLines.get(num))
				.collect(Collectors.toList());
	}

	@Override
	public void delLine(List<String> allLines, int lineNum) {
		allLines.remove(lineNum - 1);
	}

	@Override
	public void insLine(List<String> allLines, int lineNum, String text) {
		allLines.add(lineNum - 1, text);
	}

	@Override
	public void saveFile(String content, String path) throws IOException {
		FileWriter myWriter = new FileWriter(path);
		myWriter.write(content);
		myWriter.close();
	}

	@Override
	public void quit() {
		System.exit(0);
	}

}
