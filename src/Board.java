import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class Board extends JPanel
{
    public SquareType[][] squares;
    public static final int SQUARESIZE = 16;
    private EnumMap<SquareType, Color> squareColors;
    private int boardWidth, boardHeight;

    public Board() {
        this.boardWidth = 40;
	this.boardHeight = 40;
	this.squares = new SquareType[boardWidth][boardHeight];
	this.squareColors = new EnumMap<>(SquareType.class);
	squareColors.put(SquareType.EMPTY, Color.LIGHT_GRAY);
	squareColors.put(SquareType.VISITED, Color.GREEN);
	squareColors.put(SquareType.BLOCKED, Color.BLACK);
	squareColors.put(SquareType.START, Color.BLUE);
	squareColors.put(SquareType.END, Color.RED);

	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
	        squares[x][y] = SquareType.EMPTY;
	    }
	}

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
		g2d.setColor(squareColors.get(getSquareAt(x,y)));
		g2d.fillRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
	    }
	}
    }

    public SquareType getSquareAt(int x, int y) {
        return squares[x][y];
    }

    public void setSquareType(int x, int y, SquareType squareType) {
        squares[x][y] = squareType;
        repaint();
    }

    public int getBoardWidth() {
	return boardWidth;
    }

    public int getBoardHeight() {
	return boardHeight;
    }
}
