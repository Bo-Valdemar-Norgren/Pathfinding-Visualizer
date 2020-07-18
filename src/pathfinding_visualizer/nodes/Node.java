package pathfinding_visualizer.nodes;

import java.awt.*;

public abstract class Node
{
    protected Point coordinates;
    protected NodeType nodeType;

    public Node(Point coordinates, NodeType nodeType) {
	this.coordinates = coordinates;
	this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
	return nodeType;
    }

    public Point getCoordinates() {
	return coordinates;
    }
}
