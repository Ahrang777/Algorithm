import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][]map;
    static boolean[][]visit;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        String[] s = br.readLine().split(" ");
 
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
 
        map = new int[n][m];
 
        visit = new boolean[n][m];
 
        for(int i=0; i<n; i++){
            String[] s1 = br.readLine().split(" ");
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(s1[j]);
            }
        }
 
        dfs(0,0);
        System.out.println(result);
 
 
    }
 
    private static void dfs(int index, int temp){
        if(index ==n*m){
            result = Math.max(result, temp);
            return;
        }
 
        int y = index/m;
        int x = index%m;
 
        if(!visit[y][x]){
            if(y+1<n && x-1>=0 && !visit[y+1][x] && !visit[y][x-1]){
                visit[y][x]=true;
                visit[y+1][x]=true;
                visit[y][x-1]=true;
                dfs(index+1, temp+map[y+1][x]+map[y][x-1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y+1][x]=false;
                visit[y][x-1]=false;
            }
            if(y+1<n && x+1<m && !visit[y+1][x] && !visit[y][x+1]){
                visit[y][x]=true;
                visit[y+1][x]=true;
                visit[y][x+1]=true;
                dfs(index+1, temp+map[y+1][x]+map[y][x+1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y+1][x]=false;
                visit[y][x+1]=false;
            }
            if(y-1>=0 && x-1>=0 && !visit[y-1][x] && !visit[y][x-1]){
                visit[y][x]=true;
                visit[y-1][x]=true;
                visit[y][x-1]=true;
                dfs(index+1, temp+map[y-1][x]+map[y][x-1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y-1][x]=false;
                visit[y][x-1]=false;
            }
            if(y-1>=0 && x+1<m && !visit[y-1][x] && !visit[y][x+1]){
                visit[y][x]=true;
                visit[y-1][x]=true;
                visit[y][x+1]=true;
                dfs(index+1, temp+map[y-1][x]+map[y][x+1]+(map[y][x]*2));
                visit[y][x]=false;
                visit[y-1][x]=false;
                visit[y][x+1]=false;
            }
        }
        dfs(index+1, temp);
    }
}