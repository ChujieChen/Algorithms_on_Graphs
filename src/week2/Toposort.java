package week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
    private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
        int used[] = new int[adj.length];
        ArrayList<Integer> order = new ArrayList<Integer>();
        //write your code here
        // TODO by LCC on 12/14/2019
        dfs(adj, used, order, 0);
        // TODO an easier way would be using stack
        Collections.reverse(order);
        return order;
    }

    private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {
      //write your code here
    	// TODO by LCC on 12/14/2019
    	for(int v = 0; v < adj.length; ++v) {
        	used[v] = 0; 
        }
    	for(; s < adj.length; ++s) {
    		if(used[s] == 0) {
    			explore(adj, used, order, s);
    		}
    	}
    }
    // TODO
    private static void explore(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int v) {
    	used[v] = 1;
    	for(int w = 0; w < adj[v].size(); ++w) {
    		if(used[adj[v].get(w)] == 0) {
    			explore(adj, used, order, adj[v].get(w));
    		}
    	}
    	postvisit(order, v);
    }
    private static void postvisit(ArrayList<Integer> order, int v) {
    	order.add(v);
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
        }
        ArrayList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

