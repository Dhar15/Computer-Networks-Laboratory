/* The leaky bucket algorithm is a method of temporarily storing a variable number of requests and organizing them into 
a set-rate output of packets in an asynchronous transfer mode (ATM) network.

The leaky bucket is used to implement traffic policing and traffic shaping in Ethernet and cellular data networks. */

import java.io.*;
import java.util.*;
public class LeakyBucket {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int no_groups, bucket_size;
		System.out.println("Enter the bucket size-");
		bucket_size = s.nextInt();
		System.out.println("Enter the no.of groups-");
		no_groups = s.nextInt();
		int in_bw[] = new int[no_groups];
		int no_packets[] = new int[no_groups];
		int out_bw, reqd_bw=0, tot_packets=0;
		for(int i=0;i<no_groups;i++) {
			System.out.println("Enter the number of packets of group " + (i+1));
			no_packets[i] = s.nextInt();
			System.out.println("Enter the input bandwidth of group " + (i+1));
			in_bw[i] = s.nextInt();
			
			if((tot_packets + no_packets[i]) < bucket_size) 
				tot_packets += no_packets[i];
			
			else
			{
				do {
					System.out.println("Bucket overflow.");
					System.out.println("Enter a value less than " + (bucket_size-tot_packets));
					no_packets[i] = s.nextInt();
				} while((tot_packets + no_packets[i]) > bucket_size);
				tot_packets += no_packets[i];
			}
			reqd_bw += (no_packets[i] * in_bw[i]);
		}
		System.out.println("\nThe total required bandwidth is: " + reqd_bw);
		System.out.println("Enter the ouput bandwidth: ");
		out_bw = s.nextInt();
		int temp = reqd_bw;
		int rem_packets = tot_packets;
		
		while((out_bw<=temp) && (rem_packets>0)) {
			System.out.println("Data Sent\n" + (--rem_packets) +  " packets remaining");
			System.out.println("Remaining BW: " + (temp -= out_bw));
			if((out_bw> temp) && (rem_packets>0))
				System.out.println(rem_packets + " have been discarded due to insufficient bandwidth.");
		}
	}
}

