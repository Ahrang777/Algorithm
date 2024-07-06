import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer reader=new StringTokenizer(br.readLine());
    	int n=Integer.parseInt(reader.nextToken());
    	int h=Integer.parseInt(reader.nextToken());
    	int[] down=new int[h+1];
    	for(int i=0;i<n;i++){
            int j=Integer.parseInt(br.readLine());
    		if(i%2==0){
    			down[h-j]--;
    		}else{
    			down[j]++;
    		}
    	}
    	int min=down[h]+(n/2);
    	int count=1;
    	for(int i=1,k=min;i<h;i++){
            k+=down[h-i];
    		if(min>k){
    			min=k;
    			count=1;
    		}else if(min==k){
    			count+=1;
    		}
    	}
    	System.out.print(min+" "+count);
    }
}