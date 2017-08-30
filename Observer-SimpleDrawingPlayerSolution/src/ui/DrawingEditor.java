package ui;


import model.Drawing;
import model.Shape;
import sound.MidiSynth;
import ui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class DrawingEditor extends JFrame {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	private MidiSynth midiSynth;

	private List<Tool> tools;
	private Tool activeTool;

	private Drawing currentDrawing;

	public DrawingEditor() {
		super("Drawing Player");
        initializeFields();
        setUpWindowForListeningAndDisplay();
        initializeSound();
        initializeInteraction();
	}

	// getters
	public Drawing getCurrentDrawing(){ return currentDrawing; }
	public MidiSynth getMidiSynth() { return midiSynth; }


	// MODIFIES: this
	// EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        DrawingMouseListener dml = new DrawingMouseListener();
        addMouseListener(dml);
        addMouseMotionListener(dml);
    }

    // MODIFIES: this
    // EFFECTS:  initializes this DrawingEditor's midisynth field, then calls open() on it
    private void initializeSound() {
        midiSynth = new MidiSynth();
        midiSynth.open();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate
    //           populates the tools to be used to manipulate this drawing
    //
    private void setUpWindowForListeningAndDisplay() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createAndAddTools();
        addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

	// MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        activeTool = null;
        currentDrawing = null;
        tools = new ArrayList<Tool>();
    }

	// MODIFIES: this
	// EFFECTS:  declares and instantiates a Drawing (newDrawing), and adds it to drawings
	private void addNewDrawing() {
		Drawing newDrawing = new Drawing();
		currentDrawing = newDrawing;
		add(newDrawing, BorderLayout.CENTER);
		validate();
	}

	// MODIFIES: this
	// EFFECTS:  adds given Shape to currentDrawing
	public void addToDrawing(Shape f) {
		currentDrawing.addShape(f);
	}

	// MODIFIES: this
	// EFFECTS:  removes given Shape from currentDrawing
	public void removeFromDrawing(Shape f) {
		currentDrawing.removeShape(f);
	}

	// EFFECTS: if activeTool != null, then mousePressedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
	private void handleMousePressed(MouseEvent e)  {
		if (activeTool != null)
			activeTool.mousePressedInDrawingArea(e);
		repaint();
	}

    // EFFECTS: if activeTool != null, then mouseReleasedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
	private void handleMouseReleased(MouseEvent e) {
		if (activeTool != null)
			activeTool.mouseReleasedInDrawingArea(e);
		repaint();
	}

    // EFFECTS: if activeTool != null, then mouseClickedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
	private void handleMouseClicked(MouseEvent e) {
		if (activeTool != null)
			activeTool.mouseClickedInDrawingArea(e);
		repaint();
	}

    // EFFECTS: if activeTool != null, then mouseDraggedInDrawingArea is invoked on activeTool, depends on the
    //          type of the tool which is currently activeTool
	private void handleMouseDragged(MouseEvent e) {
		if (activeTool != null)
			activeTool.mouseDraggedInDrawingArea(e);
		repaint();
	}

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
	public void setActiveTool(Tool aTool) {
		if (activeTool != null)
			activeTool.deactivate();
		aTool.activate();
		activeTool = aTool;
	}

	// EFFECTS: return shapes at given point at the currentDrawing
	public Shape getShapeInDrawing(Point point) {
		return currentDrawing.getShapesAtPoint(point);
	}

	// MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
	private void createAndAddTools() {
		JPanel toolArea = new JPanel();
		toolArea.setLayout(new GridLayout(0,1));
		toolArea.setSize(new Dimension(0, 0));
		add(toolArea, BorderLayout.SOUTH);

        ShapeTool rectTool = new ShapeTool(this, toolArea);
        tools.add(rectTool);

        MoveTool moveTool = new MoveTool(this, toolArea);
        tools.add(moveTool);

        ResizeTool resizeTool = new ResizeTool(this, toolArea);
        tools.add(resizeTool);

        DeleteTool deleteTool = new DeleteTool(this, toolArea);
        tools.add(deleteTool);

        PlayShapeTool playShapeTool = new PlayShapeTool(this, toolArea);
		tools.add(playShapeTool);

        PlayDrawingTool playDrawingTool = new PlayDrawingTool(this, toolArea);
        tools.add(playDrawingTool);

        setActiveTool(rectTool);
	}

	public static void main(String args[]) {
		new DrawingEditor();
	}

	// Represents a mouse listener for this drawing
	// Note to Software Construction students: this is an example of an inner class - it is defined
    // within the scope of another class (in this case Drawing) which is referred
    // to as the outer class. Notice how the methods in this inner class have
    // access to the private fields of the outer class (in this case 'editor').
    //
    private class DrawingMouseListener extends MouseAdapter {

		// EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }

		// EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

		// EFFECTS:Forward mouse dragged event to the active tool
        public void mouseDragged(MouseEvent e) {
            handleMouseDragged(translateEvent(e));
        }

        // TODO: Effects
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentDrawing);
        }
    }
}

