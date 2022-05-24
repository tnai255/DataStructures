package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	protected Set<Edge<Node<String>>> edges;
	protected Set<Node<String>> nodes;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 * 
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {

		// Creates an adjacency map with the input relation
		Graph g = new Graph(relation);
		// creates a set of nodes and edges
		nodes = getAllNodes();
		edges = getAllEdges();

		// First checks if all the node in the set are in the adjacency map
		for (String element : set) {
			Node<String> n = new Node<String>(element);

			// if they aren't then it is automatically not reflexive
			if (!nodes.contains(n)) {
				return false;
			}

		}

		// If all elements are present it loops through the nodes
		for (Node<String> node : nodes) {
			// creates the desired reflexive edge
			Edge<Node<String>> reflexive = new Edge<Node<String>>(node, node);

			// checks if the edges set don't contain the desired reflexive edge
			if (!edges.contains(reflexive)) {
				// then returns false
				return false;
			}
		}

		// if it hasn't returned yet then it is reflexive in which case it returns true
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {

		// Creates an adjacency map with the input relation
		Graph g = new Graph(relation);

		// creates a set of nodes and edges
		nodes = getAllNodes();
		edges = getAllEdges();

		// loops through all the edges
		for (Edge<Node<String>> edge : edges) {
			// creates the desired symmetric edge by switching the current edge's source and
			// target
			Edge<Node<String>> symmetric = new Edge<Node<String>>(edge.getTarget(), edge.getSource());

			// if the desired symmetric edge is not in the edges set then return false
			if (!edges.contains(symmetric)) {
				System.out.println("For the graph to be symmetric tuple: " + symmetric + " MUST be present");
				return false;
			}

		}

		// if the condition was never met then the graph must be symmetric in which case
		// return true
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * 
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {

		// Creates an adjacency map with the input relation
		Graph g = new Graph(relation);

		// creates a set of nodes and edges
		nodes = getAllNodes();
		edges = getAllEdges();

		// loops through all the edges
		for (Edge<Node<String>> edge1 : edges) {

			// takes out the source and target of the edge
			Node<String> source = edge1.getSource();
			Node<String> target = edge1.getTarget();

			// loops through the edges again
			for (Edge<Node<String>> edge2 : edges) {
				// checks if there's an edge thats of the form (y, z) given the edge1 = (x, y)
				if (edge2.getSource().equals(target)) {
					// if that is true then it must have the desired transitive edge of (x, z)
					Edge<Node<String>> transitive = new Edge<Node<String>>(source, edge2.getTarget());

					// if that is not contained in the edge set then return false
					if (!edges.contains(transitive)) {
						System.out.println("For the graph to be transitive tuple: " + transitive + " MUST be present");
						return false;
					}
				}
			}
		}

		// if the following conditions aren't met then it must be the case that the
		// graph is transitive so return true
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 * 
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {

		// checks if the set is reflexive, symmetric and transitive in which case it is
		// equivalent so return true
		if (isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation)) {
			return true;
		}

		// if the above condition isn't met it must be the case it is not equivalent
		// hence return false
		return false;
	}

	/**
	 * This method returns a List of the equivalence class
	 * 
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * 
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 * 
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {

		// Creates an adjacency map with the input relation
		Graph g = new Graph(relation);

		// creates a set of nodes and edges
		nodes = getAllNodes();
		edges = getAllEdges();

		// initialise the output class list
		List<String> eqClass = new ArrayList<String>();

		// check if the graph is equivalent return null if it is
		if (!isEquivalence(set, relation)) {
			return null;
		}

		// loop through the edges
		for (Edge<Node<String>> edge : edges) {
			// find the neighbours of the input node and put that into the class
			if (edge.getSource().getValue().equals(node)) {
				eqClass.add(edge.getTarget().getValue());
			}
		}

		// return the class list
		return eqClass;
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start) {// name to breadthFirstSearch
		throw new java.lang.UnsupportedOperationException("Not supported yet.");

	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 * 
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start) {
		throw new java.lang.UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
