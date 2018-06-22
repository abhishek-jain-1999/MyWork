package me.vable.android.myminesweeper;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

/**
 * Created by Abhishek on 18-06-2018.
 */

public class MineSweepButton extends Button {

   private boolean Mine=false;
   private Boolean Zero=false;
   private Boolean No=false;
    public int R;
    public int C;
    private int values =0;
    private String Svalues="";
    public boolean isFlag=false;

    public MineSweepButton(Context context) {
        super(context);
    }
    public void setMine(){
        if(Mine==false) {
            Mine = true;
        }
        else {
            Mine=false;
        }
    }
    public void setZero(){
        if (Zero==true) {
            Zero = false;
        }
        else {
            Zero=true;
        }
    }
    public void setNo(){
        if (No==false) {
            No = true;
        }
        else {
            No=false;
        }
    }
    public void setEnabledd(Boolean g){
        this.setEnabled(g);
        if(g==false){

            this.setBackgroundColor(Color.WHITE);
            this.setTextColor(Color.BLACK);
        }

    }

    public void setSvalue(String Svalue){
        this.Svalues=Svalue;
    }
    public String getSvalues(){
        return Svalues;
    }
    public void setValue(){
        values++;
    }
    public int getValue(){
        return values;
    }
    public  boolean getMine(){
        return Mine;
    }
    public boolean getZero(){
        return Zero;
    }
    public Boolean getNo(){
        return No;
    }



}
