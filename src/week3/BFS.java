package week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
    	// TODO by LCC on 12/21/2019
    	// TODO dist to store all distances
    	int nVertices = adj.length;
    	int[] dist = new int[nVertices];
    	for(int i = 0; i < nVertices; ++i) {
    		dist[i] = nVertices; 
    	}
    	// TODO important: set dist[s] to 0
    	dist[s] = 0; 
    	// TODO using queue to assign dist for each vertex
    	Queue<Integer> queue = new LinkedList<>();
    	queue.offer(s);
    	while(!queue.isEmpty()) {
    		int u = queue.poll();
    		for(int i = 0; i < adj[u].size(); ++i) {
    			int v = adj[u].get(i);
    			if(dist[v] == nVertices) {
    				queue.offer(v);
        			dist[v] = dist[u] + 1;
    			}
    			// TODO early termination (not necessary)
    			if(t == v) return dist[v];
    		}
    	}
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

