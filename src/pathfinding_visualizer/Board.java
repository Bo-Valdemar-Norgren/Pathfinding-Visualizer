package pathfinding_visualizer;

import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.Node;
import pathfinding_visualizer.nodes.NodeFactory;
import pathfinding_visualizer.nodes.NodeType;
import pathfinding_visualizer.nodes.WallNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

public class Board extends JPanel
{
    private Timer timer;
    private Iterator<DefaultNode> resultIterator;
    private Iterator<DefaultNode> pathIterator;
    public Node[][] nodeGrid;
    public static final int SQUARESIZE = 16;
    private NodeFactory nodeFactory;
    private EnumMap<NodeType, Color> nodeColors;
    private int boardWidth, boardHeight;
    private DefaultNode startNode;
    private DefaultNode endNode;

    public Board() {
        this.timer = new Timer(this);
        this.boardWidth = 40;
	this.boardHeight = 40;
	this.nodeGrid = new Node[boardWidth][boardHeight];
	this.nodeFactory = new NodeFactory();
	this.nodeColors = new EnumMap<>(NodeType.class);
	nodeColors.put(NodeType.UNVISITED, Color.LIGHT_GRAY);
	nodeColors.put(NodeType.VISITED, Color.YELLOW);
	nodeColors.put(NodeType.WALL, Color.BLACK);
	nodeColors.put(NodeType.START, Color.BLUE);
	nodeColors.put(NodeType.END, Color.RED);
	nodeColors.put(NodeType.PATH, Color.GREEN);

	resetBoard();
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

    @Override
    public Dimension getPreferredSize() {
        int width = boardWidth*SQUARESIZE;
        int height = boardHeight*SQUARESIZE;

	return new Dimension(width, height);
    }

    public Node getNodeAt(Point coordinates) {
        int x = coordinates.x;
        int y = coordinates.y;

        return nodeGrid[x][y];
    }

    public void setNode(Point coordinates, NodeType nodeType) {
        Node currentNode = getNodeAt(coordinates);
        NodeType currentNodeType = currentNode.getNodeType();
        if (currentNodeType == NodeType.START) {
            startNode = null;
	} else if (currentNodeType == NodeType.END) {
            endNode = null;
	}
        switch (nodeType) {
	    case START:
	        removeNodesOfType(NodeType.START); // Remove old start node
	        DefaultNode startNode = (DefaultNode) nodeFactory.createNode(coordinates, nodeType);
	        setStartNode(startNode);
		nodeGrid[coordinates.x][coordinates.y] = startNode;
		break;
	    case END:
		removeNodesOfType(NodeType.END); // Remove old end node
		DefaultNode endNode = (DefaultNode) nodeFactory.createNode(coordinates, nodeType);
		setEndNode(endNode);
		nodeGrid[coordinates.x][coordinates.y] = endNode;
		break;
	    case WALL:
		WallNode wallNode = (WallNode) nodeFactory.createNode(coordinates, nodeType);
		nodeGrid[coordinates.x][coordinates.y] = wallNode;
		break;
	    default:
	        throw new IllegalArgumentException("No such node exists.");
	}
        boardChanged();
    }


    private void removeNodesOfType(NodeType nodeType) {
	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
		if (nodeGrid[x][y].getNodeType() == nodeType) {
		    Point coordinates = new Point(x, y);
		    nodeGrid[x][y] = nodeFactory.createNode(coordinates, NodeType.UNVISITED);
		}
	    }
	}
	boardChanged();
    }

    public int getBoardWidth() {
	return boardWidth;
    }

    public int getBoardHeight() {
	return boardHeight;
    }
    public void setStartNode(DefaultNode startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(DefaultNode endNode) {
	this.endNode = endNode;
    }

    public void resetBoard() {
        startNode = null;
        endNode = null;

	for (int y = 0; y < boardHeight; y++) {
	    for (int x = 0; x < boardWidth; x++) {
		Point coordinates = new Point(x, y);
		nodeGrid[x][y] = nodeFactory.createNode(coordinates, NodeType.UNVISITED);
	    }
	}
	boardChanged();
    }

    public DefaultNode getStartNode() {
        return startNode;
    }

    public DefaultNode getEndNode() {
        return endNode;
    }

    public void showResults(ArrayList<DefaultNode> visitedNodes) {
        updateIterators(visitedNodes);
	timer.start();
    }

    private void updateIterators(ArrayList<DefaultNode> visitedNodes) {
	resultIterator = visitedNodes.iterator();


	ArrayList<DefaultNode> path = new ArrayList<>();
	DefaultNode endNodeCandidate = visitedNodes.get(visitedNodes.size() - 1);
	if (endNodeCandidate.getNodeType() == NodeType.END) {
	    DefaultNode currentNode = endNodeCandidate.getParent();
	    while (currentNode.getParent() != null && currentNode.getNodeType() != NodeType.START) {
		path.add(currentNode);
		currentNode = currentNode.getParent();
	    }
	    System.out.println("success");
	    pathIterator = path.iterator();
	}
    }

    public void boardChanged() {
        repaint();
    }

    public void nextResultNode() {
        if (resultIterator.hasNext()) {
            DefaultNode currentNode = resultIterator.next();
	    if (currentNode.getNodeType() != NodeType.END) {
		currentNode.setNodeType(NodeType.VISITED);
	    }
	}  else if (pathIterator.hasNext()) {
	    DefaultNode currentNode = pathIterator.next();
	    if (currentNode.getNodeType() != NodeType.START) {
		currentNode.setNodeType(NodeType.PATH);
	    }
	} else {
	    System.out.println("Stopped timer");
	    timer.stop();
	}
	boardChanged();
    }

    public void setTimer(int delay) {
        timer.changeTimerDelay(delay);
    }
}
