import java.awt.*;

public class EndNode extends AbstractNode
{
    private boolean hasBeenVisited;

    protected EndNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
	this.hasBeenVisited = false;
    }

    @Override public NodeType getNodeType() {
	return null;
    }

    @Override public Point getCartesianCoordinates() {
	return null;
    }
}
