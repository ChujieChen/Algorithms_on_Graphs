package week2;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	// TODO by LCC on 12/15/2019
    	// TODO get reversed graph (G^R)
    	ArrayList<Integer>[] adjReversed = new ArrayList[adj.length];
    	for(int i = 0; i < adjReversed.length; ++i) {
    		adjReversed[i] = new ArrayList<Integer>(); 
    	}
    	for(int v = 0; v < adj.length; ++v) {
    		for(int w = 0; w < adj[v].size(); ++w) {
    			// it is adj[v].get(w) not w
    			adjReversed[adj[v].get(w)].add(v);
    		}
    	}
    	// TODO DFS on G^R
    	ArrayList<Integer> preorder = new ArrayList<>();
    	ArrayList<Integer> postorder = new ArrayList<>();
    	dfs(adjReversed, preorder, postorder);
//    	for(int i = 0; i < postorder.size(); ++i) {
//    		System.out.println(postorder.get(i)+1);
//    	}
//    	System.out.println();
    	// TODO explore vertices in reverse postorder from G^R
    	ArrayList<Integer> reversedPostorder = new ArrayList<>(postorder);
    	Collections.reverse(reversedPostorder);
    	boolean[] visited = new boolean[reversedPostorder.size()];
    	for(int i = 0; i < visited.length; ++i) {
    		visited[i] = false; 
    	}
    	int scc = 0;
    	for(int i = 0; i < reversedPostorder.size(); ++i) {
    		int v = reversedPostorder.get(i);
    		if(!visited[v]) {
//    			System.out.println(v+1);
    			// preorder and postorder are not necessary now
    			explore(adj, v, visited, preorder, postorder);
    			scc++;
    		}
    	}   	
        return scc;
    }
    // TODO dfs
    private static void dfs(ArrayList<Integer>[] adj, ArrayList<Integer> preorder,
			ArrayList<Integer> postorder) {
		boolean[] visited = new boolean[adj.length];
		for(int i = 0; i < visited.length; ++i) {
			visited[i] = false; 
		}
		for(int v = 0; v < adj.length; ++v) {
			if(!visited[v]) {
				explore(adj, v, visited, preorder, postorder);
			}
		}
		
	}
    // TODO explore
	private static void explore(ArrayList<Integer>[] adj, int v, boolean[] visited, ArrayList<Integer> preorder, ArrayList<Integer> postorder) {
		// TODO Auto-generated method stub
		visited[v] = true;
		preorder.add(v);
		for(int w = 0; w < adj[v].size(); ++w) {
			if(!visited[adj[v].get(w)]) {
				explore(adj, adj[v].get(w), visited, preorder, postorder);
			}
		}
		postorder.add(v);
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

