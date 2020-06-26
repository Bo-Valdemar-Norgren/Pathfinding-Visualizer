import java.awt.*;

public abstract class AbstractNode implements Node
{
    protected Point cartesianCoordinates;
    protected NodeType nodeType;

    protected AbstractNode(Point cartesianCoordinates, NodeType nodeType) {
	this.cartesianCoordinates = cartesianCoordinates;
	this.nodeType = nodeType;
    }

}
