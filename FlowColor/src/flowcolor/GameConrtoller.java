package flowcolor;


import cell.Cell;
import cell.Net;
import cell.componants.Componant;
import cell.componants.Dot;
import cell.componants.Pipe;
import java.awt.Color;
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
        Color color = this.net.getCellAt(i0, j0).getComponantColor();
        byte direction = this.getDirection(i, j, i0, j0);
        byte type;
        if(direction == Cell.EAST || direction == Cell.WEST)
            type = Pipe.HORIZENTAL;
        else
            type = Pipe.VERTICAL;
        removeLineAt(i,j,new Pipe(this.net.getCellAt(i, j).getComponantId(), this.net.getCellAt(i, j).getComponantColor()));
        this.net.getCellAt(i, j).addComponant(new Pipe(type, color));
    }
    private byte getDirection(int i,int j,int i0,int j0){
        int di=i-i0;
        int dj=j-j0;
        if(di==0){
            if(dj<0)
                return Cell.EAST;
            else if(dj>0)
                return Cell.WEST;
        }
        else if(dj==0){
            if(di<0)
                return Cell.SOUTH;
            else if(di>0)
                return Cell.NORTH;
        }
        throw new IllegalArgumentException();
    }
    public void removePipeAt(int i,int j,Pipe pipe){
        this.net.getCellAt(i, j).removePipe(pipe);
    }
    
    public void removeLineAt(int i,int j,Pipe pipe){
        List<Pipe> pipelist = this.findLine(pipe, i, j);
        List<int[]> pipLocation = this.findPath(pipe, i, j);
        for(int index=0;index<pipelist.size();index++){
            this.removePipeAt(pipLocation.get(index)[0], pipLocation.get(index)[1], pipelist.get(index));
        }
    }
    
    public void removePipeAt(int i,int j,int i0,int j0){
        byte pipeToRemoveType = i==i0?Pipe.HORIZENTAL:Pipe.VERTICAL;
        this.net.getCellAt(i, j).removePipe(new Pipe(pipeToRemoveType, Color.yellow));
            
        
//        boolean ofBridge;
//        List<int[]> path = new ArrayList<>();
//        path.add(new int[]{i,j});
//        findPath(path);
//        for(int[] l:path){
//            net.removePipeAt(l[0], l[1], ofBridge);
//        }
//        throw new UnsupportedOperationException("removePipeAt not implemented yet");
    }

    private List<int[]>  findPath(Pipe pipe,int i,int j) {
        List<int[]> path0 = null,path1 = new ArrayList<>();
        boolean notContectedToDot0=false,notContectedToDot1 =false;
        Pipe tempPipe0 =  pipe;
        Pipe tempPipe;
        byte direction;
        int i0=i,j0=j,i1,j1,di=0 ,dj=0;
        if(tempPipe0.isHorizental() || tempPipe0.isCORNOR_NORTH_EAST() || tempPipe0.isCORNOR_SHOUTH_EAST()){
            di=0;
            dj=1;
            direction = Cell.WEST;
        }
        else if(tempPipe0.isVertical() || tempPipe0.isCORNOR_SHOUTH_WEST()){
            di=1;
            dj=0;
            direction = Cell.NORTH;
        }
        else if(tempPipe0.isCORNOR_NORTH_WEST()){
            di = 0 ;
            dj = -1;
            direction = Cell.EAST;
        }
        else 
            throw new IllegalArgumentException("this should be never triged");
        i1=i0 + di;
        j1=j0 + dj;
        if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            tempPipe = this.net.getCellAt(i1, j1).getPipe(direction);
            path0 = this.findPath(i,j,tempPipe0,tempPipe,direction);
            i1 = path0.get(path0.size()-1)[0];
            j1 = path0.get(path0.size()-1)[1];
            path0.remove(path0.size()-1);
            if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            if(!this.net.getCellAt(i1, j1).containDot())
                notContectedToDot0 = true;
            else if((direction == Cell.NORTH || direction == Cell.SOUTH)
                    && (this.net.getCellAt(i1, j1).getBridgeId()&1)==0
                    && this.net.getCellAt(i1, j1).getComponantColor().equals(tempPipe0.getColor()))
                notContectedToDot0 = false;
            else if((direction == Cell.EAST || direction == Cell.WEST)
                    && (this.net.getCellAt(i1, j1).getBridgeId()&1)==1
                    && this.net.getCellAt(i1, j1).getComponantColor().equals(tempPipe0.getColor()))
                notContectedToDot0 = false;
            else
                notContectedToDot0 = true;
            }
        }
        
        if(notContectedToDot0 == true)
            return path0;
        
        if(tempPipe0.isHorizental()|| tempPipe0.isCORNOR_SHOUTH_WEST()){
            di=0;
            dj=-1;
            direction = Cell.EAST;
        }
        else if(tempPipe0.isVertical() || tempPipe0.isCORNOR_NORTH_EAST() || tempPipe0.isCORNOR_NORTH_WEST()){
            di=-1;
            dj=0;
            direction = Cell.SOUTH;
        }
        else if(tempPipe0.isCORNOR_SHOUTH_EAST()){
            di=1;
            dj=0;
            direction = Cell.NORTH;
        }
        i1=i0 + di;
        j1=j0 + dj;
        
        if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            tempPipe = this.net.getCellAt(i1, j1).getPipe(direction);
            path1 = this.findPath(i,j,tempPipe0,tempPipe,direction);
            i1 = path1.get(path1.size()-1)[0];
            j1 = path1.get(path1.size()-1)[1];
            path1.remove(path1.size()-1);
            if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            if(!this.net.getCellAt(i1, j1).containDot())
                notContectedToDot1 = true;
            else if((direction == Cell.NORTH || direction == Cell.SOUTH)
                    && (this.net.getCellAt(i1, j1).getBridgeId()&1)==0
                    && this.net.getCellAt(i1, j1).getComponantColor().equals(tempPipe0.getColor()))
                notContectedToDot1 = false;
            else if((direction == Cell.EAST || direction == Cell.WEST)
                    && (this.net.getCellAt(i1, j1).getBridgeId()&1)==1
                    && this.net.getCellAt(i1, j1).getComponantColor().equals(tempPipe0.getColor()))
                notContectedToDot1 = false;
            else
                notContectedToDot1 = true;
            }
        }
        
        if(notContectedToDot1 == true)
            return path1;
        path1.addAll(path0);
        return path1;
    }

    private List<int[]> findPath(int i, int j, Pipe tempPipe0, Pipe tempPipe, byte direction) {
        List<int[]> path0 = new ArrayList<>();
        int di=0 ,dj=0;
        
        while(tempPipe != null){
                if(!tempPipe.getColor().equals(tempPipe0.getColor()))
                    break;
                path0.add(new int[]{i,j});
                if(tempPipe.isHorizental()){
                    if(direction == Cell.WEST){
                        di=0;
                        dj=1;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=0;
                        dj=-1;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isVertical()){
                    if(direction == Cell.NORTH){
                        di=1;
                        dj=0;
                    }
                    else if(direction == Cell.SOUTH)
                    {
                        di=-1;
                        dj=0;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_NORTH_EAST()){
                    if(direction == Cell.NORTH){
                        di=0;
                        dj=1;
                        direction = Cell.WEST;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=-1;
                        dj=0;
                        direction = Cell.SOUTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_NORTH_WEST()){
                    if(direction == Cell.NORTH){
                        di=0;
                        dj=-1;
                        direction = Cell.EAST;
                    }
                    else if(direction == Cell.WEST)
                    {
                        di=-1;
                        dj=0;
                        direction = Cell.SOUTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_SHOUTH_EAST()){
                    if(direction == Cell.SOUTH){
                        di=0;
                        dj=1;
                        direction = Cell.WEST;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=1;
                        dj=0;
                        direction = Cell.NORTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_SHOUTH_WEST()){
                    if(direction == Cell.SOUTH){
                        di=0;
                        dj=-1;
                        direction = Cell.EAST;
                    }
                    else if(direction == Cell.WEST)
                    {
                        di=1;
                        dj=0;
                        direction = Cell.NORTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }

                i+=di;
                j+=dj;
                if(i>net.getDimenshin() - 1 || i<0
                   || j > net.getDimenshin() - 1 || j < 0){
                    break;
                }
                tempPipe0 = tempPipe;
                tempPipe = this.net.getCellAt(i, j).getPipe(direction);
        }
        path0.add(new int[]{i,j});
        return path0;
    }
    

    
    
    private List<Pipe>  findLine(Pipe pipe,int i,int j) {
        List<Pipe> rslt;
        rslt = new ArrayList<>();
        List<Componant> path0 = null,path1 = new ArrayList<>();
        boolean notContectedToDot0=false,notContectedToDot1 =false;
        Pipe tempPipe0 =  pipe;
        Pipe tempPipe;
        byte direction;
        int i0=i,j0=j,i1,j1,di=0 ,dj=0;
        if(tempPipe0.isHorizental() || tempPipe0.isCORNOR_NORTH_EAST() || tempPipe0.isCORNOR_SHOUTH_EAST()){
            di=0;
            dj=1;
            direction = Cell.WEST;
        }
        else if(tempPipe0.isVertical() || tempPipe0.isCORNOR_SHOUTH_WEST()){
            di=1;
            dj=0;
            direction = Cell.NORTH;
        }
        else if(tempPipe0.isCORNOR_NORTH_WEST()){
            di = 0 ;
            dj = -1;
            direction = Cell.EAST;
        }
        else 
            throw new IllegalArgumentException("this should be never triged");
        i1=i0 + di;
        j1=j0 + dj;
        if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            tempPipe = this.net.getCellAt(i1, j1).getPipe(direction);
            path0 = this.findLine(i,j,tempPipe0,tempPipe,direction);
            Dot last = (Dot)path0.get(path0.size()-1);
            notContectedToDot0 = last != null;
            if(!notContectedToDot0)
                path0.remove(path0.size()-1);
        }
        
        if(notContectedToDot0 == true){
            for(Componant c :path0){
                rslt.add((Pipe)c);
            }
            return rslt;
        }
            
        
        if(tempPipe0.isHorizental()|| tempPipe0.isCORNOR_SHOUTH_WEST()){
            di=0;
            dj=-1;
            direction = Cell.EAST;
        }
        else if(tempPipe0.isVertical() || tempPipe0.isCORNOR_NORTH_EAST() || tempPipe0.isCORNOR_NORTH_WEST()){
            di=-1;
            dj=0;
            direction = Cell.SOUTH;
        }
        else if(tempPipe0.isCORNOR_SHOUTH_EAST()){
            di=1;
            dj=0;
            direction = Cell.NORTH;
        }
        i1=i0 + di;
        j1=j0 + dj;
        
        if(i1>net.getDimenshin() - 1 || i1<0
               || j1 > net.getDimenshin() - 1 || j1 < 0){
            tempPipe = this.net.getCellAt(i1, j1).getPipe(direction);
            path0 = this.findLine(i,j,tempPipe0,tempPipe,direction);
            Dot last = (Dot)path1.get(path0.size()-1);
            notContectedToDot1 = last != null;
            if(!notContectedToDot1)
                path1.remove(path0.size()-1);
        }
        
        if(notContectedToDot1 == true){
            for(Componant c :path1){
                rslt.add((Pipe)c);
            }
            return rslt;
        }
        
        for(Componant c :path0){
                rslt.add((Pipe)c);
            }
        for(Componant c :path1){
                rslt.add((Pipe)c);
            }
        return rslt;
    }

    private List<Componant> findLine(int i, int j, Pipe tempPipe0, Pipe tempPipe, byte direction) {
        List<Componant> path0 = new ArrayList<>();
        int di=0 ,dj=0;
        
        while(tempPipe != null){
                if(!tempPipe.getColor().equals(tempPipe0.getColor()))
                    break;
                path0.add(tempPipe);
                if(tempPipe.isHorizental()){
                    if(direction == Cell.WEST){
                        di=0;
                        dj=1;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=0;
                        dj=-1;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isVertical()){
                    if(direction == Cell.NORTH){
                        di=1;
                        dj=0;
                    }
                    else if(direction == Cell.SOUTH)
                    {
                        di=-1;
                        dj=0;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_NORTH_EAST()){
                    if(direction == Cell.NORTH){
                        di=0;
                        dj=1;
                        direction = Cell.WEST;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=-1;
                        dj=0;
                        direction = Cell.SOUTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_NORTH_WEST()){
                    if(direction == Cell.NORTH){
                        di=0;
                        dj=-1;
                        direction = Cell.EAST;
                    }
                    else if(direction == Cell.WEST)
                    {
                        di=-1;
                        dj=0;
                        direction = Cell.SOUTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_SHOUTH_EAST()){
                    if(direction == Cell.SOUTH){
                        di=0;
                        dj=1;
                        direction = Cell.WEST;
                    }
                    else if(direction == Cell.EAST)
                    {
                        di=1;
                        dj=0;
                        direction = Cell.NORTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }
                else if(tempPipe.isCORNOR_SHOUTH_WEST()){
                    if(direction == Cell.SOUTH){
                        di=0;
                        dj=-1;
                        direction = Cell.EAST;
                    }
                    else if(direction == Cell.WEST)
                    {
                        di=1;
                        dj=0;
                        direction = Cell.NORTH;
                    }
                    else
                        throw new IllegalArgumentException("this should be never triged");
                }

                i+=di;
                j+=dj;
                if(i>net.getDimenshin() - 1 || i<0
                   || j > net.getDimenshin() - 1 || j < 0){
                    break;
                }
                tempPipe0 = tempPipe;
                tempPipe = this.net.getCellAt(i, j).getPipe(direction);
        }
        if(i<net.getDimenshin() && i>=0
           && j < net.getDimenshin() && j <= 0){
           path0.add(new Dot(this.net.getCellAt(i, j).getComponantColor()));
        }
        if(i>net.getDimenshin() - 1 || i<0
               || j > net.getDimenshin() - 1 || j < 0){
                if(!this.net.getCellAt(i, j).containDot())
                {}
                else if((direction == Cell.NORTH || direction == Cell.SOUTH)
                        && (this.net.getCellAt(i, j).getBridgeId()&1)==0
                        && this.net.getCellAt(i, j).getComponantColor().equals(tempPipe0.getColor()))
                    path0.add(new Dot(this.net.getCellAt(i, j).getComponantColor()));
                else if((direction == Cell.EAST || direction == Cell.WEST)
                        && (this.net.getCellAt(i, j).getBridgeId()&1)==1
                        && this.net.getCellAt(i, j).getComponantColor().equals(tempPipe0.getColor()))
                    path0.add(new Dot(this.net.getCellAt(i, j).getComponantColor()));
                else
                {}
            }
        return path0;
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
                    return 0;;
            }
        }
    }
}
