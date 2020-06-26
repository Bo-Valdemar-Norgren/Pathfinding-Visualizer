package Nodes;
import java.awt.*;

public class EndNode extends AbstractNode
{
    private boolean hasBeenVisited;

    public EndNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
	this.hasBeenVisited = false;
    }

    @Override public NodeType getNodeType() {
	return nodeType;
    }

    @Override public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }
}
