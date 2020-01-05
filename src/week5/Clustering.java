package week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


public class Clustering {
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
    // No need to have countSets
//    private static double countSets(Subset[] subsets) {
//    	HashSet<Integer> set = new HashSet<>();
//    	for(Subset ss: subsets) {
//    		set.add(ss.parent);
//    	}
//    	return set.size();
//    }
    private static double clustering(int[] x, int[] y, int k) {
        //write your code here
    	//TODO use Kruskal's algorithm
    	// when we have k subsets, stop and calculate d
    	// TODO implement Kruskal's Algorithm
    	double result = Double.MAX_VALUE;
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
    	int unionNumber = 0;
    	for(Edge edge: edges) {
    		if(find(subsets, edge.u) != find(subsets, edge.v)) {
// ref: https://github.com/akueisara/algo-on-graphs/blob/master/week%205/clustering/Clustering.java
        		if(V - unionNumber <= k) return edge.weight;
        		
    			union(subsets, edge.u, edge.v);
    			unionNumber++;
//    			//TODO when there are k sets, stop
//    			if(countSets(subsets) == k) break;
			}
    	}
//    	for(Edge edge: edges) {
//    		if(find(subsets, edge.u) != find(subsets, edge.v))
//    			result = Math.min(result, edge.weight);
//    	}
    	return -1.0;
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
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

