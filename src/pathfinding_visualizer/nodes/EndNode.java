package pathfinding_visualizer.nodes;
import java.awt.*;

public class EndNode extends AbstractTraversableNode
{
    public EndNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
    }
}
