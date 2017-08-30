package ui.tools;

import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class MoveTool extends Tool {

	private Shape shapeToMove;
	private Point start;

    public MoveTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		shapeToMove = null;
		start = null;
	}

    // MODIFIES: this
    // EFFECTS:  constructs a move button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Move");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton field
	@Override
	protected void addListener() {
		button.addActionListener(new moveToolButtonPressedHandler());
	}

    // MODIFIES: this
    // EFFECTS: sets figureToMove to the shape at the location of the MouseEvent e.
    //          Return if shapeToMove == null, else invoke select() on shapeToMove and initialize
    //          the Point (start) with the current location of the MouseEvent
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		shapeToMove = editor.getShapeInDrawing(e.getPoint());
		if (shapeToMove != null) {
			shapeToMove.selectAndPlay();
			start = e.getPoint();
		}
	}

    // MODIFIES: this
    // EFFECTS:  if shapeToMove == null, return. Else, invoke unselect() on shapeToMove, and set it to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (shapeToMove != null) {
			shapeToMove.unselectAndStopPlaying();
			shapeToMove = null;
		}
	}

    // MODIFIES: this
    // EFFECTS:  if shapeToMove != null, we compute the change in x,y (dx,dy), and
    //           invoke the move() method on shapeToMove
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (shapeToMove != null) {
			int dx = (int) (e.getPoint().getX() - start.getX());
			int dy = (int) (e.getPoint().getY() - start.getY());
			start = e.getPoint();
			shapeToMove.move(dx, dy);
		}
	}

	//TODO: explain that this is the handler that listens for mouse presses
	private class moveToolButtonPressedHandler implements ActionListener {

    	//TODO: explain that this is the method that is called when the MoveTookClickHandler "hears" something.
		//TODO: it is called by the framework.
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(MoveTool.this);
		}

	}
}
