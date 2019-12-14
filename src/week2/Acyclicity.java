package week2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
	static boolean[] visited;
	static boolean[] isAncestor;
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	/*
    	 * by LCC on 12/13/2019
    	 * Thought 1: [WRONG]
    	 * Do DFS following arrows, if encounter a visited vertex
    	 * then there is a cycle. (change explore()) [WRONG!!!]
    	 * Here, acyclic() is equal to DFS()
    	 * Thought 2 on 12/14/2019:
    	 * https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
    	 * isAncestor is used to indicate ancestors
    	 * if we explore from v, then v is ancestor for all following vertices
    	 * note that we need to reset isAncestor every time we done exploring v
    	 * unlike visited.
    	 * For example, 3 -> 0 -> 1 -> 2, if we start from 0,
    	 * 0 is the ancestor of 1 and 2 (1 is ancestor of 2). T
    	 * hen we start from 3
    	 * although 0 is visited, but it is not ancestor of 3.
    	 * While 0 -> 1 -> 0 we will encounter isAncestor 0.
    	 */
    	for(int i = 0; i < visited.length; ++i) {
    		visited[i] = false;
    		isAncestor[i] = false; 
    	}
    	// adj is an array[] of ArrayList<Integer>
    	// TODO
    	boolean hasCycle = false;
    	for(int i = 0; i < adj.length; ++i) {
    		if(!visited[i])
    			hasCycle = explore(adj, i);
			if(hasCycle) return 1; // has to be inside the loop
    	}
        return 0;
    }
    /**
     * 
     * @param adj: ajacency list
     * @param v: starting vertex
     * @return hasCycle
     */
    private static boolean explore(ArrayList<Integer>[] adj, int v) {
    	boolean hasCycle = false;
    	// TODO recursion
    	visited[v] = true;
    	isAncestor[v] = true; 
    	for(int w = 0; w < adj[v].size(); ++w) {
    		// Important
    		// made mistake three times already
    		// !!!!!!!!!!!!!
    		if(!visited[adj[v].get(w)]) {
    			hasCycle = explore(adj, adj[v].get(w));
    			if(hasCycle) return true;
    		}
    		// TODO important
    		// visited does not mean isAncestor
    		// NOT w !!!!! but `adj[v].get(w)`
			else if (isAncestor[adj[v].get(w)]) {
				return true;
			}
    	}
    	// reset starting node
    	isAncestor[v] = false; 
    	return hasCycle;
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
        
        /*
         * by LCC on 12/13/2019
         * 
         */
        visited = new boolean[n];
        isAncestor = new boolean[n];
        
        
        
        System.out.println(acyclic(adj));
    }
}

