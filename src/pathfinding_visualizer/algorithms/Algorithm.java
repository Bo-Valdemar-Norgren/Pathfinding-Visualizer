package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;

public interface Algorithm
{
    ArrayList<DefaultNode> startSearch();
    void setTraversalStrategy(TraversalStrategy traversalStrategy);
    TraversalStrategy getTraversalStrategy();
}
