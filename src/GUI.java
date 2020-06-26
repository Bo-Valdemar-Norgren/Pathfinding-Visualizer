import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
    Board board;
    int mx;
    int my;

    public GUI() {
        this.setTitle("Pathfinding Visualizer");
        this.setSize(658,680);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.board = new Board();
        this.setContentPane(board);

        MouseMoves move = new MouseMoves();
        this.addMouseMotionListener(move);

        MouseClick click = new MouseClick();
        this.addMouseListener(click);

    }
    public class MouseMoves implements MouseMotionListener
    {

        @Override public void mouseDragged(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);

            if (cursorOnNode()) {
                Point nodePoint = selectedNodePoint();
                int x = nodePoint.x;
                int y = nodePoint.y;
                board.setSquareType(x, y, SquareType.BLOCKED);
            }
        }

        @Override public void mouseMoved(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
        }
    }

    public class MouseClick implements MouseListener
    {

        @Override public void mouseClicked(final MouseEvent mouseEvent) {
            if (cursorOnNode()) {
                Point nodePoint = selectedNodePoint();
                int x = nodePoint.x;
                int y = nodePoint.y;
                board.setSquareType(x, y, SquareType.BLOCKED);
            }
            else {
                System.out.println("Cursor not on any node!");
            }
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

    private boolean cursorOnNode() {
        Point point = selectedNodePoint();
        return point.x != -1 && point.y != -1;
    }

    private Point selectedNodePoint() {
        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();
        int squareSize = Board.SQUARESIZE;

        for (int x = 0; x < boardHeight; x++) {
            for (int y = 0; y < boardWidth; y++) {
                if (mx >= x*squareSize && mx < x*squareSize+squareSize+8 && my >= y*squareSize+8 && my < y*squareSize+squareSize+30) {
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
