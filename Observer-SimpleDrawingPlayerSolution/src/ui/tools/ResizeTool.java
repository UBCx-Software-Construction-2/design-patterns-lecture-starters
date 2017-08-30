package ui.tools;

import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ResizeTool extends Tool {

	private Shape shapeToResize;

    // EFFECTS: creates a new ResizeTool with the given editor and parent. Sets shapeToResize to null
	public ResizeTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		shapeToResize = null;
	}

    // MODIFIES: this
    // EFFECTS: creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Resize");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS: associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ResizeToolClickHandler());
	}

    // MODIFIES: this
    // EFFECTS: if shapeToResize exists, selects it
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		shapeToResize = editor.getShapeInDrawing(e.getPoint());
		if (shapeToResize != null) {
			shapeToResize.selectAndPlay();
		}
	}

    // MODIFIES: this
    // EFFECTS: deselects shapeToResize and sets field to null (i.e. done resizing)
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (shapeToResize != null) {
			shapeToResize.unselectAndStopPlaying();
			shapeToResize = null;
		}
    }

    // MODIFIES: this
    // EFFECTS: resizes figureToResize to drag release point
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (shapeToResize != null) {
			shapeToResize.setBounds(e.getPoint());
		}
	}

	/**
	 * Listener for click events on this move tool
	 */
	private class ResizeToolClickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(ResizeTool.this);
		}
	}
}
