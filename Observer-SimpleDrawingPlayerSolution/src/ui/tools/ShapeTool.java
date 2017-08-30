package ui.tools;


import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ShapeTool extends Tool {
	private Shape shape;

    public ShapeTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		shape = null;
	}

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Shape");
		button = customizeButton(button);
	}

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ShapeToolClickHandler());
	}

	// MODIFIES: this
    // EFFECTS:  the shape field is instantiated where the MouseEvent occurs, and played and
    //           added to the editor's drawing
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		shape = new Shape(e.getPoint(), editor.getMidiSynth());
		shape.selectAndPlay();
		shape.setBounds(e.getPoint());
		editor.addToDrawing(shape);
	}

	// MODIFIES: this
    // EFFECTS:  unselects this shape, and set it to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
        shape.unselectAndStopPlaying();
	    shape = null;
	}

	// MODIFIES: this
    // EFFECTS:  invokes the setBounds(Point p), where p is the location of the given MouseEvent
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		shape.setBounds(e.getPoint());
	}

	private class ShapeToolClickHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(ShapeTool.this);
		}
	}
}

