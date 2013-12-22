package flowcolor;


import cell.Net;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DigitalNet
 */
public class GameConrtoller {
    private Net net;//changes to this
    private Net lastNet;// save last move
    private Net net1;// net and last nat swap with these
    private Net net2;// there should be only two instance of Net
    private int size;
    private int moves;
    private Date timer;//get time from System.currentTimeMillis()
    private String gameFile;
    private ArrayList<PlayerGetable> bestPlayers;
    
    public void OpenGame(String gamefile){
        throw new UnsupportedOperationException("OpenGame not implemented yet");
    }
    
    public void SaveGame(){
        throw new UnsupportedOperationException("SaveGame not implemented yet");
    }
    
    public void Undo(){
        throw new UnsupportedOperationException("Undo not implemented yet");
    }
    public void Finished(){
        throw new UnsupportedOperationException("Finished not implemented yet");
    }


    public int getSize() {
        return size;
    }

    public int getMoves() {
        return moves;
    }

    public Date getTimer() {
        return timer;
    }

    public ArrayList<PlayerGetable> getBestPlayers() {
        Collections.sort(bestPlayers);
        return bestPlayers;
    }
    
    public void addPipeAt(int i,int j,int i0,int j0){
        removePipeAt(i, j);
        throw new UnsupportedOperationException("addPipeAt not implemented yet");
    }
    
    public void removePipeAt(int i,int j){
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{i,j});
        findPath(path);
        for(int[] l:path){
            net.removePipeAt(l[0], l[1], ofBridge);
        }
        throw new UnsupportedOperationException("removePipeAt not implemented yet");
    }
    
    public void removePipeAt(int i,int j,int i0,int j0){
        boolean ofBridge;
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{i,j});
        findPath(path);
        for(int[] l:path){
            net.removePipeAt(l[0], l[1], ofBridge);
        }
        throw new UnsupportedOperationException("removePipeAt not implemented yet");
    }

    private void findPath(List<int[]> path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    private static class Player implements Serializable,PlayerGetable{
        private final String name;
        private int moves;
        private long time;

        public Player(String name, int moves, long time) {
            this.name = name;
            this.moves = moves;
            this.time = time;
        }
        
        public void Update(int moves,long time){
           if(moves<this.moves){
               this.moves = moves;
               this.time = time;
           } else if(this.moves == moves && time < this.time){
               this.time = time;
           }
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getMoves() {
            return moves;
        }

        @Override
        public long getTime() {
            return time;
        }

        @Override
        public int compareTo(PlayerGetable o) {
            if(this.moves < o.getMoves())
                return 1;
            else if(this.moves > o.getMoves())
                return -1;
            else{
                if(this.time > o.getTime())
                    return -2;
                else if(this.time < o.getTime())
                    return 2;
                else
                    return 0;
            }
        }
    }
}
