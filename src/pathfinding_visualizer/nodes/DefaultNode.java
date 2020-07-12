package pathfinding_visualizer.nodes;

import java.awt.*;

public class DefaultNode extends AbstractNode implements Comparable<DefaultNode>
{
    private int gCost;
    private int hCost;
    private int fCost;

    public DefaultNode(Point cartesianCoordinates, NodeType nodeType) {
        super(cartesianCoordinates, nodeType);
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
    }

    @Override public int compareTo(final DefaultNode node) {
        return this.fCost - node.fCost;
    }

    public void setNodeType(NodeType nodeType) {
       this.nodeType = nodeType;
    }
}
