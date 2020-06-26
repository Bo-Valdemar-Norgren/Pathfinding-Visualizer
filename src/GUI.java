import Nodes.NodeType;
import Nodes.WallNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
    private Board board;
    private int mx;
    private int my;

    public GUI() {
        this.setTitle("Pathfinding Visualizer");
        this.setSize(658,680);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.board = new Board();
        this.setContentPane(board);
        this.setVisible(true);

        MouseMoves move = new MouseMoves();
        this.addMouseMotionListener(move);

        MouseClick click = new MouseClick();
        this.addMouseListener(click);

    }
    public class MouseMoves implements MouseMotionListener
    {

        @Override public void mouseDragged(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
            updateSelectedNode();
        }

        @Override public void mouseMoved(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
        }
    }

    public class MouseClick implements MouseListener
    {

        @Override public void mouseClicked(final MouseEvent mouseEvent) {
            updateSelectedNode();
        }

        @Override public void mousePressed(final MouseEvent mouseEvent) {

        }

        @Override public void mouseReleased(final MouseEvent mouseEvent) {

        }

        @Override public void mouseEntered(final MouseEvent mouseEvent) {

        }

        @Override public void mouseExited(final MouseEvent mouseEvent) {

        }
    }
    private void updateSelectedNode() {
        if (cursorOnNode()) {
            Point coordinates = getSelectedNodeCoordinates();
            WallNode wallNode = new WallNode(coordinates, NodeType.WALL);
            board.setNode(coordinates, wallNode);
        }
    }

    private boolean cursorOnNode() {
        Point point = getSelectedNodeCoordinates();
        return point.x != -1 && point.y != -1;
    }

    private Point getSelectedNodeCoordinates() {
        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();
        int squareSize = Board.SQUARESIZE;

        for (int x = 0; x < boardHeight; x++) {
            for (int y = 0; y < boardWidth; y++) {
                if (mx >= x*squareSize && mx < x*squareSize+squareSize+8 && my >= y*squareSize+8 && my < y*squareSize+squareSize+30) { //Magic numbers represent the pixels covered by the program frame.
                    return new Point(x,y);
                }
            }
        }
        return new Point(-1,-1);
    }
    public void updateCursorCoordinates(MouseEvent mouseEvent) {
        mx = mouseEvent.getX();
        my = mouseEvent.getY();
    }
}
