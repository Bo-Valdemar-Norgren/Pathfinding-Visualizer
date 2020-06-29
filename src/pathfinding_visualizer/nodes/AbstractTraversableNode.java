package pathfinding_visualizer.nodes;

import java.awt.*;

public abstract class AbstractTraversableNode extends AbstractNode implements Comparable<AbstractTraversableNode>
{
    protected Point cartesianCoordinates;
    protected NodeType nodeType;
    protected int costFromStart;

    public AbstractTraversableNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
	this.costFromStart = 0;
    }

    public int getCostFromStart() {
	return costFromStart;
    }
    public void setCostFromStart(int costFromStart) {
        this.costFromStart = costFromStart;
    }

    public boolean equals(AbstractTraversableNode node) {
	return this.getCostFromStart() == node.getCostFromStart();
    }

   public int compareTo(final AbstractTraversableNode node) {
	if (this.equals(node)) {
	    return 0;
	}
	else if (this.getCostFromStart() > node.getCostFromStart()) {
	    return 1;
	}
	else {
	    return -1;
	}
    }

}
