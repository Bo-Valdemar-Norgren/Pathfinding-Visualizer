package pathfinding_visualizer.nodes;

import org.w3c.dom.Node;

import java.awt.*;

public abstract class AbstractNode
{
    protected Point cartesianCoordinates;
    protected NodeType nodeType;

    public AbstractNode(Point cartesianCoordinates, NodeType nodeType) {
	this.cartesianCoordinates = cartesianCoordinates;
	this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
	return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }

}
