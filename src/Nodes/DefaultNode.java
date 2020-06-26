package Nodes;

import java.awt.*;

public class DefaultNode extends AbstractNode
{
    private boolean hasBeenVisited;

    public DefaultNode(final Point cartesianCoordinates, final NodeType nodeType, boolean hasBeenVisited) {
	super(cartesianCoordinates, nodeType);
	this.hasBeenVisited = hasBeenVisited;
    }

    @Override public NodeType getNodeType() {
	return nodeType;
    }

    @Override public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }

    public boolean hasBeenVisited() {
	return hasBeenVisited;
    }

    public void markAsVisited() {
        hasBeenVisited = true;
    }
}
