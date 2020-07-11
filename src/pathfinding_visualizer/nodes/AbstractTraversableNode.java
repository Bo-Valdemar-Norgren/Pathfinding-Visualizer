package pathfinding_visualizer.nodes;

import java.awt.*;

public abstract class AbstractTraversableNode extends AbstractNode implements Comparable<AbstractTraversableNode>
{
    protected int gCost;
    protected int hCost;
    protected int fCost;

    public AbstractTraversableNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
	this.gCost = 0;
	this.hCost = 0;
	this.fCost = 0;
    }


   public int compareTo(final AbstractTraversableNode node) {
	return this.fCost - node.fCost;
    }

}
