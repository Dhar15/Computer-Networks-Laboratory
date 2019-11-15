import java.io.*;
import java.util.*;
import java.util.Scanner;

public class BellmanFord {
	public static void Bellman(Edge edges[], int edgeCount, int nodeCount, int source) {
		int infinity = 50000;
		int i,j;
		int distance[] = new int[nodeCount];
		for(i=0;i<nodeCount;i++) 
			distance[i] = infinity;
		distance[source] = 0;
		for(i=0;i<nodeCount;i++)
		{
			boolean somethingChanged = false;
			for(j=0;j<nodeCount;j++)
			{
				if(distance[edges[j].source] != infinity) 
				{
					int new_distance = distance[edges[j].source] + edges[j].weight;
					if(new_distance < distance[edges[j].dest]) {
						distance[edges[j].dest] = new_distance;
						somethingChanged = true;
					}
				}
			}
			if(!somethingChanged) break;
		}
		
		for(i=0;i<edgeCount;++i) {
			if(distance[edges[i].dest] > distance[edges[i].source] + edges[i].weight) {
				System.out.println("Negative weight cycles detected.");
			}
		}
		
		for(i=0;i<nodeCount;++i) {
			System.out.println("The shortest distance between " + source + " and " + i + " is " + distance[i]);
		}
	}
	public static void main(String[] args) {
		Edge edges[] = new Edge[10];
		Scanner s = new Scanner(System.in);
		
		for(int i=0;i<10;i++) {
			edges[i] = new Edge();
			
			System.out.println("Enter source for node " + (i+1));
			edges[i].source = s.nextInt();
					
			System.out.println("Enter dest for node " + (i+1));
			edges[i].dest = s.nextInt();
					
			System.out.println("Enter weight for node " + (i+1));
			edges[i].weight = s.nextInt();
			
			System.out.println();
		}
		Bellman(edges,10,7,0);
	}
}
