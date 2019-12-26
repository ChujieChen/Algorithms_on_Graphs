package week4;

import java.util.*;

public class Dijkstra {
    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        // TODO by LCC on 12/24/2019
    	int[] dist = new int[adj.length];
        int[] prev = new int[adj.length];
        for(int i = 0; i < dist.length; ++i) {
        	dist[i] = Integer.MAX_VALUE;
        	prev[i] = -1; 
        }
        dist[s] = 0;
        // TODO Java do not support ChangePriority in PriorityQueue
    	// we use a HashSet to record incomplete Vertices
    	PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
    	HashSet<Integer> set = new HashSet<>();
    	for(int v = 0; v < adj.length; ++v) {
    		// Although it is not necessary to add all Vertices into pQueue
    		// at the beginning, to prevent consistency, I still add them all.
    		pQueue.offer(new Vertex(v, dist[v]));
    		set.add(v);
    	}
        while(!set.isEmpty()) {
        	Vertex u = pQueue.poll();
        	// TODO mark u as processed immediately
        	set.remove(u.index);
//        	System.out.print(u.index);
//        	System.out.print(": ");
        	for(int i = 0; i < adj[u.index].size(); ++i) {
        		int v = adj[u.index].get(i);
//        		System.out.print(v);
//        		System.out.print("  ");
        		// TODO have to check if v processed already
        		// since we cannot modify Vertices in pQueue
        		if(set.contains(v) && dist[v] > dist[u.index] + cost[u.index].get(i)) {
        			dist[v] = dist[u.index] + cost[u.index].get(i);
        			prev[v] = u.index;
        			pQueue.add(new Vertex(v, dist[v]));
        		}
        	}
//        	System.out.println();
        }
        // TODO if invalid, return -1
    	return (dist[t] >= 0 && dist[t] < Integer.MAX_VALUE)? dist[t]: -1;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}
// TODO Vertex(node, dist)
class Vertex implements Comparable<Vertex>{
	int index;
	int dist;
	public Vertex(int index, int dist) {
		this.index = index;
		this.dist = dist;
	}
	@Override
	public int compareTo(Vertex that) {
		// TODO Auto-generated method stub
		return this.dist - that.dist;
	}
	
}

