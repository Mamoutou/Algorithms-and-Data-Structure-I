/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Mamoutou
 */

/*
 * University of Victoria
 * CSC 225 - Fall 2016
 * Code template for assignment 4
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import static javafx.scene.input.KeyCode.T;

// DO NOT CHANGE THE CLASS NAME OR PACKAGE
public class Graph225 {

    /**
     * Simple representation of an undirected graph, using a square, symmetric
     * adjacency matrix.
     * <p>
     * An adjacency matrix M represents a graph G=(V,E) where V is a set of n
     * vertices and E is a set of m edges. The size of the matrix is {@code n},
     * where {@code n} is in the range {@code [4, 15]} only. Thus, the rows and
     * columns of the matrix are in the range {@code [0, n-1]} representing
     * vertices. The elements of the matrix are 1 if the edge exists in the
     * graph and 0 otherwise. Since the graph is undirected, the matrix is
     * symmetric and contains 2m 1’s.
     */
    public static class Graph {

        private int[][] adjacencyMatrix;
        private int[] verticesNodes;
        private int edges;

        /*
         * You are free to add constructors, but the empty constructor is the
         * only one invoked during marking.
         */
        public Graph() {
            // YOUR CODE HERE (if needed)   
        }

        /**
         * Generate a random graph as specified in the assignment statement.
         *
         * @param n The size of the graph
         * @param density The density of the graph
         */
        public void generate(int n, int density) {
			//throw new UnsupportedOperationException("This method has not been implemented yet.");
            // int [] randomNum;

            //     int nVert = n;
            ArrayList<Integer> vertices = new ArrayList<Integer>();
            if (n < 4 || n > 15) {
                System.out.println("Error, number of elements must be in the range of 4 to 15");
                exit(0);
            }
            if (density < 1 || density > 3) {
                System.out.println("Error DENSITY, Density must be 1 or 2 or 3");
                exit(0);
            }
            int value = n;
            final int MAX_SIZE = n;
            final int MAX = 15;
            final int MIN = 4;
            edges = 0;

            if (density == 1) {
                edges = (7 * n) / 5;
            } else if (density == 2) {
                edges = n * n / 4;
            } else if (density == 3) {
                edges = 2 * (n * n) / 5;
            }

            System.out.println("nodes= " + n + "   Edges=" + edges);

            adjacencyMatrix = new int[MAX_SIZE][MAX_SIZE];
            verticesNodes = new int[MAX_SIZE];
            Random random = new Random();
            int row = 0;
            int col = 0;
            int count = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    adjacencyMatrix[i][j] = 0;
                }
            }

            int counter = 0; //keeep track the number of edges
            int c = 0;  // keep track the number of the nodes
            while (counter < edges) {
                row = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
                col = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
                if (c < n) {
                    vertices.add(row);
                    vertices.add(col);
                    c += 2;
                }
                if (row >= MAX_SIZE) {
                    row %= MAX_SIZE;
                }

                if (col >= MAX_SIZE) {
                    col %= MAX_SIZE;
                }
                if (row == col) {
                    continue;
                }
                adjacencyMatrix[row][col] = 1;
                adjacencyMatrix[col][row] = 1;
                counter++;
                //   edges--;
            }

            int co = 0;
            for (int i = 0; i < MAX_SIZE; ++i) {
                for (int j = 0; j < MAX_SIZE; ++j) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                    if (adjacencyMatrix[i][j] == 1) {
                        co++;
                    }
                }
                System.out.println();
            }
            //   System.out.println("count: " + co);
            for (int k = 0; k < vertices.size(); ++k) {
                verticesNodes[k] = vertices.get(k);
            }
        }

        /**
         * Reads an adjacency matrix from the specified file, and updates this
         * graph's data. For the file structure please refer to the sample input
         * file {@code testadjmat.txt}).
         *
         * @param file The input file
         * @throws IOException If something bad happens while reading the input
         * file.
         */
        public int[] getVertices() {
            return verticesNodes;
        }

        private int getNumberofEges() {
            return edges;
        }

        public void read(String file) throws IOException {
            int[][] array = create2DMatrix(file);
            this.setAdjacencyMatrix(array);
        }

        private int[][] create2DMatrix(String filename) throws IOException {
            int[][] matrix = {{1}, {2}};
            File inFile = new File(filename);
            Scanner in = new Scanner(inFile);
            int intLength = 0;
            String[] length = in.nextLine().trim().split("\\s+");
            for (int i = 0; i < length.length; i++) {
                intLength++;
            }
            in.close();
            matrix = new int[intLength][intLength];
            in = new Scanner(inFile);
            int lineCount = 0;
            while (in.hasNextLine()) {
                String[] currentLine = in.nextLine().trim().split("\\s+");
                for (int i = 0; i < currentLine.length; i++) {
                    matrix[lineCount][i] = Integer.parseInt(currentLine[i]);
                }
                lineCount++;
            }
            return matrix;
        }

        private static int[][] create2DIntMatrixFromFile(String filename) throws Exception {
            int[][] matrix = {{1}, {2}};
            File inFile = new File(filename);
            Scanner in = new Scanner(inFile);
            in.useDelimiter("[/n]");

            String line = "";
            int lineCount = 0;

            while (in.hasNextLine()) {
                line = in.nextLine().trim();
                Scanner lineIn = new Scanner(line);
                lineIn.useDelimiter("");

                for (int i = 0; lineIn.hasNext(); i++) {
                    matrix[lineCount][i] = Integer.parseInt(lineIn.next());
                    lineIn.next();
                }

                lineCount++;
            }

            return matrix;
        }
        /* * Writes the adjacency matrix representing this graph in the specified
         * file.
         * 
         * @param file
         *            The path of the output file
         * @throws IOException
         *             If something bad happens while writing the file.
         */

        public void write(String file) throws IOException {
            int[][] arr = getAdjacencyMatrix();
            try (FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw); //fw
                    PrintWriter out = new PrintWriter(bw)) {
                for (int i = 0; i < arr.length; ++i) {
                    for (int j = 0; j < arr.length; ++j) {
                        out.print(adjacencyMatrix[i][j] + " ");
                    }
                    out.println();
                }
                out.println();
            } catch (IOException e) {
                System.out.printf("Unable to write %s\n", "fn.txt");
                return;
            }
        }

        /**
         * @return an adjacency matrix representation of this graph
         */
        public int[][] getAdjacencyMatrix() {
            return this.adjacencyMatrix;
        }

        /**
         * Updates this graph's adjacency matrix
         *
         * @param m The adjacency matrix representing the new graph
         */
        public void setAdjacencyMatrix(int[][] m) {
            this.adjacencyMatrix = m;
        }

        //  }
        /**
         * Traverses the given graph starting at the specified vertex, using the
         * depth first search graph traversal algorithm.
         * <p>
         * <b>NOTICE</b>: adjacent vertices must be visited in strictly
         * increasing order (for automated marking)
         *
         * @param graph The graph to traverse
         * @param vertex The starting vertex (as per its position in the
         * adjacency matrix)
         * @return a vector R of n elements where R[j] is 1 if vertex j can be
         * reached from {@code vertex} and 0 otherwise
         */
        public int[] reach(Graph graph, int vertex) {

            Stack<Integer> stack = new Stack<Integer>();
            ArrayList<Integer> setElem = new ArrayList<Integer>();
            int[][] arr = graph.getAdjacencyMatrix();
            int number_of_nodes = arr[vertex].length - 1;
            int visited[] = new int[number_of_nodes + 1];
            int element = vertex;
            int i = vertex;
            //  System.out.print(element + " ");
            setElem.add(element);
            visited[vertex] = 1;
            stack.push(vertex);

            while (!stack.isEmpty()) {
                element = stack.peek();
                i = element;
                while (i <= number_of_nodes) {
                    if (arr[element][i] == 1 && visited[i] == 0) {
                        stack.push(i);
                        visited[i] = 1;
                        element = i;
                        i = 1;
                        //  System.out.print(element + " ");
                        setElem.add(element);
                        continue;
                    }
                    i++;
                }
                stack.pop();
            }
            int[] myList = new int[setElem.size()];
            for (int j = 0; j < myList.length; ++j) {
                myList[j] = setElem.get(j);
            }
            return myList;
        }

        public int connectedComponents(Graph graph) {
            int[][] array = graph.getAdjacencyMatrix();
            int length = array[0].length;
            int count = countComponents(length, array);
            //   System.out.println("count: "  + count);
            return count;
        }

        public int countComponents(int n, int[][] edges) {
            int[] roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }

            for (int[] e : edges) {
                int root1 = find(roots, e[0]);
                int root2 = find(roots, e[1]);
                if (root1 != root2) {
                    roots[root1] = root2;  // union
                    n--;
                }
            }
            return n;
        }

        private int find(int[] roots, int id) {
            while (roots[id] != id) {
                id = roots[id];
            }
            return id;
        }

        public int countConnect(Graph g) {
            boolean[] visit;
            int[][] array = g.getAdjacencyMatrix();
            int node = array[0].length;
            visit = new boolean[node];
            for (boolean i : visit) {
                i = false;
            }

            int countNum = 0;

            return 1;
        }

        /**
         * Computes the pre-order for each vertex in the given graph.
         * <p>
         * <b>NOTICE</b>: adjacent vertices must be visited in strictly
         * increasing order (for automated marking)
         *
         * @param graph The graph
         * @return a vector R of n elements, representing the pre-order of
         * {@code graph}
         *
         *
         */
        private int[] depthFirst(int vFirst, int n) {
            int v, i;
            int[] arr = new int[n]; // max size 
            int[] isVisited = new int[n];
            int[][] adjMatrix = getAdjacencyMatrix();
            Stack st = new Stack();
            st.push(vFirst);

            while (!st.isEmpty()) {
                v = (int) st.pop();
                int k = 0;
                if (isVisited[v] == 0) {
                    System.out.print("\n" + (v + 1));
                    isVisited[v] = 1;
                    arr[k] = v;
                    k += 1;
                }
                for (i = 0; i < n; i++) {
                    if ((adjMatrix[v][i] == 1) && (isVisited[i] == 0)) {
                        st.push(v);
                        isVisited[i] = 1;
                        System.out.print(" " + (i + 1));
                        v = i;
                        arr[k] = v;
                        k += 1;
                    }
                }
            }
            return arr;
        }

        public int[] preOrder(Graph graph) {

            Stack<Integer> stack = new Stack<Integer>();
            ArrayList<Integer> setElem = new ArrayList<Integer>();
            int s = getVertices().length;
            int[] arr = graph.getVertices();
            depthFirst(arr[0], s);
            return arr;
        }

        /**
         * Computes the post-order for each vertex in the given graph.
         * <p>
         * <b>NOTICE</b>: adjacent vertices must be visited in strictly
         * increasing order (for automated marking)
         *
         * @param graph The graph
         * @return a vector R of n elements, representing the post-order of
         * {@code graph}
         */
        public int[] postOrder(Graph graph) {
            Stack<Integer> stack = new Stack<Integer>();
            ArrayList<Integer> setElem = new ArrayList<Integer>();
            int s = getVertices().length;
            int[] arr = graph.getVertices();
            depthFirst(arr[0], s);
            return arr;
            //  throw new UnsupportedOperationException("This method has not been implemented yet.");
        }

        // if number of edges if greater than the number of nodes then
        // there is a cycle
        public boolean hasCycle(Graph graph) {
            // cycle in subgraph reachable from vertex v.
            int nodesize = graph.getVertices().length;
            int edgeSize = graph.getNumberofEges();
            int[][] array = graph.getAdjacencyMatrix();

            //remove 1 from v every time a vertex is found without any edges
            for (int i = 0; i < nodesize; ++i) {
                if (array[0][i] == 0) {
                    nodesize--;
                }
            }
            if (edgeSize > nodesize - 1) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * test and exercise the algorithms and data structures developed for
         * the first five parts of this assignment extensively. The output
         * generated by this method must convince the marker that the algorithms
         * and data structures are implemented as specified. For example:
         * <ul>
         * <li>Generate graphs of different sizes and densities
         * <li>Test the algorithms for different graphs
         * <li>Test your algorithms using the sample input file testadjmat.txt
         *
         * @throws Exception if something bad happens!
         */
        public void test() throws Exception {
            // throw new UnsupportedOperationException("This method has not been implemented yet.");
            Graph g = new Graph();
            g.generate(10, 3);
            g.write("fn.txt");
            g.read("testadjmat.txt");
            boolean cycle = hasCycle(g);
            System.out.println("check for cycle: " + cycle);
           
            ///   int count =   connectedComponents(g);
            // System.out.println("connected Components: " + count);

        }

    }
}
