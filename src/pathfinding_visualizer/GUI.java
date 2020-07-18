package pathfinding_visualizer;

import pathfinding_visualizer.algorithms.AStar;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.AlgorithmFactory;
import pathfinding_visualizer.algorithms.AlgorithmType;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame {
    private Board board;
    private AlgorithmFactory algorithmFactory;
    private NodeType selectedNodeType;
    private AlgorithmType selectedAlgorithmType;
    private int mx;
    private int my;

    public GUI() {
        this.setTitle("Pathfinding Visualizer");
        this.setSize(642,684);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.selectedNodeType = NodeType.WALL;
        this.board = new Board();
        this.algorithmFactory = new AlgorithmFactory();
        this.setContentPane(board);

        JMenuBar menuBar = createMenu();
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        MouseMoves move = new MouseMoves();
        this.addMouseMotionListener(move);

        MouseClick click = new MouseClick();
        this.addMouseListener(click);

    }
    public class MouseMoves implements MouseMotionListener
    {

        @Override public void mouseDragged(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
            if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                updateSelectedNode();
            }
            else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                replaceSelectedNodeWithDefaultNode();
            }

        }

        @Override public void mouseMoved(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
        }
    }

    public class MouseClick implements MouseListener
    {

        @Override public void mouseClicked(final MouseEvent mouseEvent) {
            if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                updateSelectedNode();
            }
            else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                replaceSelectedNodeWithDefaultNode();
            }
        }

        @Override public void mousePressed(final MouseEvent mouseEvent) {

        }

        @Override public void mouseReleased(final MouseEvent mouseEvent) {

        }

        @Override public void mouseEntered(final MouseEvent mouseEvent) {

        }

        @Override public void mouseExited(final MouseEvent mouseEvent) {

        }
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu nodeMenu = new JMenu("Nodes"), algoMenu = new JMenu("Algorithms"), startMenu = new JMenu("Start");
        JMenuItem wallNode = new JMenuItem("Wall node"), startNode = new JMenuItem("Start node"), endNode = new JMenuItem("End node");

        wallNode.addActionListener(e->setSelectedNodeType(NodeType.WALL));
        startNode.addActionListener(e->setSelectedNodeType(NodeType.START));
        endNode.addActionListener(e->setSelectedNodeType(NodeType.END));

        nodeMenu.add(wallNode);
        nodeMenu.add(startNode);
        nodeMenu.add(endNode);

        JMenuItem start = new JMenuItem("Start"), reset = new JMenuItem("Reset");

        reset.addActionListener(actionEvent -> board.fillBoardWithDefaultNodes());
        start.addActionListener(actionEvent -> {
            try {
                Algorithm selectedAlgorithm = algorithmFactory.createAlgorithm(board, selectedAlgorithmType);
                selectedAlgorithm.startSearch();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Couldn't run algorithm: " + selectedAlgorithmType);
            }

        });

        startMenu.add(start);
        startMenu.add(reset);

        JMenuItem AStar = new JMenuItem("A* Search"), dijkstra = new JMenuItem("Dijkstra's algorithm");

        AStar.addActionListener(actionEvent-> setSelectedAlgorithmType(AlgorithmType.ASTAR));
        dijkstra.addActionListener(actionEvent-> setSelectedAlgorithmType(AlgorithmType.DIJKSTRA));

        algoMenu.add(AStar);
        algoMenu.add(dijkstra);

        menuBar.add(startMenu);
        menuBar.add(nodeMenu);
        menuBar.add(algoMenu);
        return menuBar;
    }

    private void setSelectedAlgorithmType(AlgorithmType algoType) {
        selectedAlgorithmType = algoType;
    }

    private void updateSelectedNode() {
        if (cursorOnNode()) {
            Point coordinates = getSelectedNodeCoordinates();
            board.setNode(coordinates, selectedNodeType);
        }
    }

    private void replaceSelectedNodeWithDefaultNode() {
        if (cursorOnNode()) {
            Point coordinates = getSelectedNodeCoordinates();
            board.setNode(coordinates, NodeType.DEFAULT_UNVISITED);
        }
    }

    private boolean cursorOnNode() {
        Point point = getSelectedNodeCoordinates();
        return point.x != -1 && point.y != -1;
    }

    private Point getSelectedNodeCoordinates() {
        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();
        int squareSize = Board.SQUARESIZE;

        for (int x = 0; x < boardHeight; x++) {
            for (int y = 0; y < boardWidth; y++) {
                if (mx >= x*squareSize+getInsets().left && mx < x*squareSize+squareSize+getInsets().right && my >= y*squareSize+getInsets().bottom && my < y*squareSize+squareSize+getInsets().top+22) { //Magic number represents the height of the menu bar.
                    return new Point(x,y);
                }
            }
        }
        return new Point(-1,-1);
    }
    public void updateCursorCoordinates(MouseEvent mouseEvent) {
        mx = mouseEvent.getX();
        my = mouseEvent.getY();
    }
    private void setSelectedNodeType(NodeType nodeType) {
        selectedNodeType = nodeType;
    }
}
