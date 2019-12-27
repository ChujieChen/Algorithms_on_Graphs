package week4;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JSpinner.DateEditor;

import sun.security.action.GetBooleanAction;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
    	// TODO by LCC on 12/26/2019
    	// TODO Bellman-Ford algorithm
    	int V = adj.length;
    	int[] dist = new int[V];
    	int[] prev = new int[V];
    	for(int u = 0; u < V; ++u) {
    		// using Integer.MAX_VALUE would cause overflow
    		dist[u] = Integer.MAX_VALUE / 2;
    		prev[u] = -1; 
    	}
    	dist[0] = 0;
//    	boolean test = false;
    	for(int i = 0; i < V; ++i) {
    		for(int u = 0; u < V; ++u) {
    			for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
    				Relax(adj, cost, dist, prev, u, vIdx);
//    				System.out.print(test);
    			}
    		}
    	}
    	// TODO if any edge got relaxed on or after the V-th iteration
    	// there is a negative cycle
    	boolean changed = false;
    	for(int u = 0; u < V; ++u) {
    		for(int vIdx = 0; vIdx < adj[u].size(); ++vIdx) {
    			changed = Relax(adj, cost, dist, prev, u, vIdx);
//    			System.out.print(changed);
    			if(changed) return 1;
    		}
    	}
        return 0;
    }

    private static boolean Relax(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist, int[] prev, int u, int vIdx) {
		// TODO Auto-generated method stub
    	int v = adj[u].get(vIdx);
		if(dist[v] > dist[u] + cost[u].get(vIdx)) {
			dist[v] =  dist[u] + cost[u].get(vIdx);
			prev[v] = u; 
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}

