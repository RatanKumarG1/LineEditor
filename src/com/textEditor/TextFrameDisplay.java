package com.textEditor;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
/**
 * Java Swing class to display the input file text
 */
public class TextFrameDisplay extends JFrame {
	private final JFrame jFrame;;
	private final JEditorPane editPane;
	
	
	public TextFrameDisplay() {
		jFrame = new JFrame();
		jFrame.setTitle("Line Editor");
		editPane = new JEditorPane();
		
		// configure display
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(800, 400);
		editPane.setContentType("text/plain");
		editPane.setEditable(Boolean.FALSE);

		// Added vertical scroll
		JScrollPane editorScrollPane = new JScrollPane(editPane);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		jFrame.setContentPane(editorScrollPane);
		jFrame.setVisible(true);
		
	}
	
	/**
	 * dispose the running frame
	 */
	public void dispose() {
		jFrame.dispose();
	}

	/**
	 * set text to the editor pane
	 * @param text - input text
	 */
	public void setText(String text) {
		editPane.setText(text);
	}
	
	
}