package ui.tools;

import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class DeleteTool extends Tool {

	private Shape shapeToDelete;

	public DeleteTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		shapeToDelete = null;
	}

    // MODIFIES: this
    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Delete");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton field
	@Override
	protected void addListener() {
		button.addActionListener(new DeleteToolClickHandler());
	}

    // MODIFIES: this
    // EFFECTS:  sets this DeleteTool's shapeToDelete field to a Shape that may exist in the location covered by
    //           the MouseEvent e. Return if shapeToDelete == null, else invoke select
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		shapeToDelete = editor.getShapeInDrawing(e.getPoint());
		if (shapeToDelete != null) {
			shapeToDelete.selectAndPlay();
		}
	}

    // MODIFIES: this
    // EFFECTS:  if figureToDelete == null, return. Else, invoke unselect() on figureToDelete, remove it from the
    //           drawing, and set it to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (shapeToDelete != null) {
		    shapeToDelete.unselectAndStopPlaying();
			editor.removeFromDrawing(shapeToDelete);
			shapeToDelete = null;
		}
	}

    // EFFECTS: shapeToDelete is set to shape at the location of the MouseEvent
    //          if shapeToDelete != null, invoke selectAndPlay() is invoked on it
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
        shapeToDelete = editor.getShapeInDrawing(e.getPoint());
        if (shapeToDelete != null) {
            shapeToDelete.selectAndPlay();
        }
	}

	private class DeleteToolClickHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(DeleteTool.this);
		}
	}
}
