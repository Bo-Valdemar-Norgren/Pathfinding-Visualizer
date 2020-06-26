import java.awt.*;

public class DefaultNode extends AbstractNode
{
    private boolean hasBeenVisited;

    protected DefaultNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
	this.hasBeenVisited = false;
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
}
