package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;

public interface Algorithm
{
    int startSearch();
    void setTraversalStrategy(TraversalStrategy traversalStrategy);
}
