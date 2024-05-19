package com.textEditor;

import java.io.IOException;
import java.util.List;

/**
 * Interface defining read, list, save, insert, delete, quit functionalities for the line editor
 */
public interface LineEditorOperations {
	/**
	 * Read the text file from the input path which is provided as command line argument
	 * @param path
	 * @return List<String>
	 */
	List<String> readFile(String path);
	
	/**
	 * List all the text file lines with numbering
	 * @param allLines
	 * @return List<String>
	 */
	List<String> listAllLines(List<String> allLines);
	
	/**
	 * delete a line at particular index from the text editor
	 * @param allLines
	 * @param lineNum
	 * @return List<String>
	 */
	void delLine(List<String> allLines, int lineNum);
	
	/**
	 * insert a line at the particular index in the text editor
	 * @param allLines
	 * @param lineNum
	 * @param text
	 * @return List<String>
	 */
	void insLine(List<String> allLines, int lineNum, String text);
	
	/**
	 * save file to disk - text appearing on the prompt will be saved
	 * @param content
	 * @param path
	 * @throws IOException
	 */
	void saveFile(String content, String path) throws IOException;
	
	/**
	 * quit the running application
	 */
	void quit();
	
	
}
