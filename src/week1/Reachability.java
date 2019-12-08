package week1;
import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
	// by LCC: have to add static!!!
	static boolean[] visited;
	
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        //write your code here
    	// by LCC on 12/07/2019
    	visited[x] = true;
    	if(visited[y]) return 1;
    	for(int w = 0; w < adj[x].size(); ++w) {
//    		System.out.println(adj[x].get(w));
    		if(!visited[adj[x].get(w)]) reach(adj, adj[x].get(w), y);
    		// return from inner call would not be returned in the outmost method
    		// so double check if we marked y as visited and return if so
        	if(visited[y]) return 1;
    	}
    	
    	return 0;
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
        
        // by LCC: initialize `visited`
        visited = new boolean[n];
        for(int i = 0; i< visited.length; ++i) {
        	visited[i] = false; 
        }
        
        System.out.println(reach(adj, x, y));
    }
}

