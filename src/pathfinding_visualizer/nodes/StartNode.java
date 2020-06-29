package pathfinding_visualizer.nodes;
import java.awt.*;

public class StartNode extends AbstractTraversableNode
{
    public StartNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
    }
}
