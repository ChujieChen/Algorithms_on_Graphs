package week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.FindProperty;

public class ConnectingPoints {
	// TODO Subset class for Union-Find
	// 01/03/2020
	// ref: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
	// each Subset (tree) is a group of vertices
	// all Subsets form a forest
	private static class Subset{
		int parent;
		int rank;
	}
	private static class Edge implements Comparable<Edge>{
		int u;    // src
		int v;    // des
		double weight;    // dist
		public Edge(int u, int v, double weight) {
			// TODO Auto-generated constructor stub
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge that) {
			// TODO Auto-generated method stub
			return this.weight - that.weight > 0? 1: -1;
		}
	}
	private static int find(Subset[] subsets, int i) {
		// TODO path compression
		if(subsets[i].parent != i) {
			subsets[i].parent = find(subsets, subsets[i].parent);
		}
		return subsets[i].parent;
	}
	private static void union(Subset[] subsets, int u, int v) {
		// TODO union by rank
		// check Data_Structures/week2/MergingTables.java
		int uRoot = find(subsets, u);
		int vRoot = find(subsets, v);
		if(subsets[uRoot].rank < subsets[vRoot].rank) {
			subsets[uRoot].parent = vRoot;
		}
		else if (subsets[uRoot].rank > subsets[vRoot].rank) {
			subsets[vRoot].parent = uRoot;
		}
		else {
			subsets[uRoot].parent = vRoot;
			subsets[vRoot].rank++;
		}
		
	}
	private static double computeDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        // TODO by LCC on 12/29/2019
//        result = Kruskal(x, y);
        result = Prim(x, y);
        return result;
    }
    private static double Kruskal(int[] x, int[] y) {
    	// TODO implement Kruskal's Algorithm
    	double result = 0;
    	int V = x.length;
    	// make all vertices as subsets
    	Subset[] subsets = new Subset[x.length];
    	for(int u = 0; u < V; ++u) {
    		subsets[u] = new Subset();
    		subsets[u].parent = u;
    		subsets[u].rank = 0;
    	}
    	// TODO create Edges based on inputs
    	// sort them by weights
    	ArrayList<Edge> edges = new ArrayList<>();
    	for(int u = 0; u < V; ++u) {
    		for(int v = u + 1; v < V; ++v) {
    			edges.add(new Edge(u, v, computeDist(x[u], y[u], x[v], y[v])));
    		}
    	}
    	Collections.sort(edges);
    	for(Edge edge: edges) {
    		if(find(subsets, edge.u) != find(subsets, edge.v)) {
    			result = result + edge.weight;
    			union(subsets, edge.u, edge.v);
    		}
    	}
    	return result;
    }
    private static double Prim(int[] x, int[] y) {
    	// TODO implement Prim's Algorithm
    	// similar to week4/Dijkstra.java
    	double result = 0;
    	int V = x.length;
    	// store i-j edges in a 2-D array [full adjacency list]
    	double[][] w = new double[V][V];
    	for(int i = 0; i < V; ++i) {
    		for(int j = 0; j < V; ++j) {
    			w[i][j] = computeDist(x[i], y[i], x[j], y[j]); 
    		}
    	}
    	// cost: min cost to add current Vertex in tree
    	// parent: src Vertex to current Vertex
    	double[] cost = new double[V];
        int[] parent = new int[V];
        for(int u = 0; u < V; ++u) {
        	cost[u] = Integer.MAX_VALUE;
        	parent[u] = -1; 
        }
        // pick index 0 as initial vertex u0
        cost[0] = 0;
        // TODO Java do not support ChangePriority in PriorityQueue
    	// we use a HashSet to record incomplete Vertices
    	PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
    	HashSet<Integer> set = new HashSet<>();
    	for(int u = 0; u < V; ++u) {
    		// Although it is not necessary to add all Vertices into pQueue
    		// at the beginning, to prevent consistency, I still add them all.
    		pQueue.offer(new Vertex(u, cost[u]));
    		set.add(u);
    	}
    	// instead of calling while(!pQueue.isEmpty())
    	// here I call
    	while(!set.isEmpty()) {
    		Vertex vertex = pQueue.poll();
    		int v = vertex.index;
    		set.remove(v);
    		for(int z = 0; z < V; ++z) {
    			if(set.contains(z) && cost[z] > w[v][z]) {
    				cost[z] = w[v][z];
    				parent[z] = v;
    				pQueue.offer(new Vertex(z, cost[z]));
    			}
    		}
    	}
    	for(int i = 0; i < cost.length; ++i) {
    		result += cost[i];
    	}
    	return result;
    }
    // TODO Vertex class used by Prim algo
    private static class Vertex implements Comparable<Vertex>{
    	int index;
    	double priority;
    	public Vertex(int index, double priority) {
    		this.index = index;
    		this.priority = priority;
    	}
    	@Override
    	public int compareTo(Vertex that) {
    		// TODO Auto-generated method stub
    		return (this.priority - that.priority > 0? 1: -1);
    	}
    	
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.println(minimumDistance(x, y));
    }
}



