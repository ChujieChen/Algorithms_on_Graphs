package week1;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
	static boolean[] visited;
	static int[] ccNum;
	static int cc;
    // this method must be static (which can only call static explore method)
	// if this is non-static, object must be created in main beforehand
	private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        //write your code here
        // TODO by LCC on 12/08/2019
        for(int i = 0; i < adj.length; i++)
        	visited[i] = false; 
        cc = 0;
        for(int i = 0; i < adj.length; ++i) {
        	if(!visited[i]) {
        		explore(adj, i);
        		cc++;
        	}
        }
        result = cc;
        return result;
    }
    
    private static void explore(ArrayList<Integer>[] adj, int v) {
		// TODO Auto-generated method stub
    	visited[v] = true;
    	ccNum[v] = cc; 
    	for(int i = 0; i < adj[v].size(); ++i) {
    		if(!visited[adj[v].get(i)]) {
    			explore(adj, adj[v].get(i));
    		}
    	}
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; ++i) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        // TODO initialize visited
        visited = new boolean[n];
        // TODO initialize ccNum: connected component number
        ccNum = new int[n];
                
        System.out.println(numberOfComponents(adj));
    }
}

