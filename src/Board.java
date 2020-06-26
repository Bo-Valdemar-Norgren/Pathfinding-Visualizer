import Nodes.DefaultNode;
import Nodes.Node;
import Nodes.NodeType;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class Board extends JPanel
{
    public Node[][] nodes;
    public static final int SQUARESIZE = 16;
    private EnumMap<NodeType, Color> nodeColors;
    private int boardWidth, boardHeight;

    public Board() {
        this.boardWidth = 40;
	this.boardHeight = 40;
	this.nodes = new Node[boardWidth][boardHeight];
	this.nodeColors = new EnumMap<>(NodeType.class);
	nodeColors.put(NodeType.UNVISITED, Color.LIGHT_GRAY);
	nodeColors.put(NodeType.VISITED, Color.GREEN);
	nodeColors.put(NodeType.WALL, Color.BLACK);
	nodeColors.put(NodeType.START, Color.BLUE);
	nodeColors.put(NodeType.END, Color.RED);

	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
	        Point coordinates = new Point(x, y);
		nodes[x][y] = new DefaultNode(coordinates, NodeType.UNVISITED);
	    }
	}
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Point coordinates = new Point(x,y);
                Node node = getNodeAt(coordinates);
		g2d.setColor(nodeColors.get(node.getNodeType()));
		g2d.fillRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
	    }
	}
    }

    public Node getNodeAt(Point coordinates) {
        int x = coordinates.x;
        int y = coordinates.y;

        return nodes[x][y];
    }

    public void setNode(Point coordinates, Node node) {
	nodes[coordinates.x][coordinates.y] = node;
        repaint();
    }

    public int getBoardWidth() {
	return boardWidth;
    }

    public int getBoardHeight() {
	return boardHeight;
    }
}
