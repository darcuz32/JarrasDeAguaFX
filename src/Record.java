/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Asus
 */
public class Record 
{
    protected int noRecord;
    protected int rule;
    protected int prevX;
    protected int nextX;
    protected int prevY;
    protected int nextY;
        
    public Record(int noRecord, int rule, int prevX, int nextX, int prevY, int nextY){
        this.noRecord = noRecord;
        this.rule = rule;
        this.prevX = prevX;
        this.nextX = nextX;
        this.prevY = prevY;
        this.nextY = nextY;
    }

    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public int getRule(){
        return rule;
    }

    @Override
    public String toString(){
       return "MOVIMIENTO: " + noRecord + "\nREGLA : " + rule+ "\nX antes : " + prevX+ "\nY antes : " + prevY+ "\nX despues : " + nextX+ "\nY despues : " + nextY;
    }  

}
