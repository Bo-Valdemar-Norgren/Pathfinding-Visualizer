import nodes.AbstractNode;
import nodes.NodeFactory;
import nodes.NodeType;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class Board extends JPanel
{
    public AbstractNode[][] nodeGrid;
    public static final int SQUARESIZE = 16;
    private NodeFactory nodeFactory;
    private EnumMap<NodeType, Color> nodeColors;
    private int boardWidth, boardHeight;

    public Board() {
        this.boardWidth = 40;
	this.boardHeight = 40;
	this.nodeGrid = new AbstractNode[boardWidth][boardHeight];
	this.nodeFactory = new NodeFactory();
	this.nodeColors = new EnumMap<>(NodeType.class);
	nodeColors.put(NodeType.DEFAULT_UNVISITED, Color.LIGHT_GRAY);
	nodeColors.put(NodeType.DEFAULT_VISITED, Color.YELLOW);
	nodeColors.put(NodeType.WALL, Color.BLACK);
	nodeColors.put(NodeType.START, Color.BLUE);
	nodeColors.put(NodeType.END, Color.RED);

	fillBoardWithDefaultNodes();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                Point coordinates = new Point(x,y);
                AbstractNode node = getNodeAt(coordinates);
		g2d.setColor(nodeColors.get(node.getNodeType()));
		g2d.fillRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
		g2d.setColor(Color.GRAY);
		g2d.drawRect(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
	    }
	}
    }

    public AbstractNode getNodeAt(Point coordinates) {
        int x = coordinates.x;
        int y = coordinates.y;

        return nodeGrid[x][y];
    }

    public void setNode(Point coordinates, NodeType nodeType) {
        removeSingularNodes(nodeType);

        AbstractNode node = nodeFactory.createNode(coordinates, nodeType);
	nodeGrid[coordinates.x][coordinates.y] = node;
        repaint();
    }

    private void removeSingularNodes(NodeType nodeType) {
	switch (nodeType) {
	    case START:
		removeNodesOfType(NodeType.START);
		break;
	    case END:
		removeNodesOfType(NodeType.END);
		break;
	}
	repaint();
    }

    private void removeNodesOfType(NodeType nodeType) {
	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
		if (nodeGrid[x][y].getNodeType() == nodeType) {
		    Point coordinates = new Point(x, y);
		    nodeGrid[x][y] = nodeFactory.createNode(coordinates, NodeType.DEFAULT_UNVISITED);
		}
	    }
	}
    }

    public int getBoardWidth() {
	return boardWidth;
    }

    public int getBoardHeight() {
	return boardHeight;
    }

    public void fillBoardWithDefaultNodes() {
	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
		Point coordinates = new Point(x, y);
		nodeGrid[x][y] = nodeFactory.createNode(coordinates, NodeType.DEFAULT_UNVISITED);
	    }
	}
	repaint();
    }
}
