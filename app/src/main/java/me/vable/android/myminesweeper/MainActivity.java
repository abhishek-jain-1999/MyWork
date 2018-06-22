package me.vable.android.myminesweeper;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , View.OnLongClickListener{


    public ArrayList<LinearLayout> row;
    public MineSweepButton[][] board;
    LinearLayout l1;
    int c=10;
    int r=15;
    int nofMine=25;
    boolean firstClicked=false;
    boolean gameStatus=true;
    public int[][] locationOfMine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        int no=intent.getIntExtra("1",3);
        if(no==1){
            c=5;
            r=5;
            nofMine=5;
        }
        if(no==2){
            c=5;
            r=10;
            nofMine=10;
        }
        if(no==3){
            c=10;
            r=15;
            nofMine=25;
        }


        l1=findViewById(R.id.L1);
        setBoard();
    }
    public void setBoard(){

        firstClicked=false;
        gameStatus=true;
        row=new ArrayList<>();
        l1.removeAllViews();
        locationOfMine=new int[nofMine][2];
        board=new MineSweepButton[r][c];

        for(int i=0;i<r;i++){
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            l1.addView(linearLayout);
            row.add(linearLayout);
        }
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                MineSweepButton button=new MineSweepButton(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);
                button.setOnClickListener(this);
                button.setOnLongClickListener(this);
                button.setTextSize(10);
                button.setTextColor(Color.WHITE);
                button.setBackgroundResource(R.drawable.button_back);
                button.R=i;
                button.C=j;

                LinearLayout rows =row.get(i);
                rows.addView(button);
                board[i][j]= button;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int Id=item.getItemId();
        if(Id==R.id.reset){
                setBoard();
        }
        if(Id==R.id.low){
            Toast.makeText(this,"low clicked",Toast.LENGTH_LONG).show();
            r=5;
            c=5;
            nofMine=5;
            setBoard();
        }
        if(Id==R.id.mid){
            Toast.makeText(this,"mid clicked",Toast.LENGTH_LONG).show();
            c=5;
            r=10;
            nofMine=10;
            setBoard();
        }
        if(Id==R.id.hard){
            Toast.makeText(this,"hard clicked",Toast.LENGTH_LONG).show();
            c=10;
            r=15;
            nofMine=25;
            setBoard();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setBoardButtons(MineSweepButton button){
        int rows=button.R;
        int cols=button.C;
        arroundZero(rows,cols);
        setBoardMine();
        //try1();
        setValuesOfButton();
        setButtonZero();
        showButton(rows,cols);


    }
    public  void setButtonZero(){
        for(int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                if(board[i][j].getMine()==false&&board[i][j].getNo()==false&&board[i][j].getZero()==false){
                    board[i][j].setZero();
                }
            }
        }
    }

    public void setValuesOfButton(){
        int l=0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(board[i][j].getMine()==true) {
                    locationOfMine[l][0]=i;
                    locationOfMine[l][1]=j;
                    l++;
                    if (!(j == 0)) {
                        if (board[i][j - 1].getMine() == false) {
                            board[i][j - 1].setValue();
                            board[i][j - 1].setSvalue("" + board[i][j - 1].getValue());
                            if (board[i][j-1].getNo()==false)
                            board[i][j-1].setNo();
                            if (board[i][j-1].getZero()==true)
                            board[i][j-1].setZero();
                        }
                    }
                    if (!(j == c - 1)) {
                        if (board[i][j + 1].getMine() == false) {
                            board[i][j + 1].setValue();
                            board[i][j + 1].setSvalue("" + board[i][j + 1].getValue());
                            if (board[i][j+1].getNo()==false)
                            board[i][j+1].setNo();
                            if (board[i][j+1].getZero()==true)
                            board[i][j+1].setZero();
                        }
                    }
                    if(!(i==0)){
                        for (int k=0;k<3;k++){
                            if((j-1+k>=0)&&(j-1+k<c)) {
                                if (board[i - 1][j - 1 + k].getMine() == false) {
                                    board[i - 1][j - 1 + k].setValue();
                                    board[i - 1][j - 1 + k].setSvalue("" + board[i - 1][j - 1 + k].getValue());
                                    if (board[i-1][j-1+k].getNo()==false)
                                    board[i-1][j-1+k].setNo();
                                    if (board[i-1][j-1+k].getZero()==true)
                                    board[i-1][j-1+k].setZero();

                                }
                            }
                        }

                    }
                    if(!(i==r-1)){
                        for (int k=0;k<3;k++){
                            if((j-1+k>=0)&&(j-1+k<c)) {
                                if (board[i + 1][j - 1 + k].getMine() == false) {
                                    board[i + 1][j - 1 + k].setValue();
                                    board[i + 1][j - 1 + k].setSvalue("" + board[i + 1][j - 1 + k].getValue());
                                    if (board[i+1][j-1+k].getNo()==false)
                                    board[i+1][j-1+k].setNo();
                                    if (board[i+1][j-1+k].getZero()==true)
                                    board[i+1][j-1+k].setZero();
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    public void arroundZero(int rs,int cs){
        MineSweepButton button=board[rs][cs];
        button.setText("0");
        if(button.getZero()==false)
            button.setZero();
        button.setEnabledd(false);
        if(!(cs==0)) {
            button = board[rs][cs - 1];
            button.setSvalue("");
            if(button.getZero()==false)
                button.setZero();
            button.setText("0");
        }
        if(!(cs==c-1)) {
            button = board[rs][cs + 1];
            button.setSvalue("");
            if(button.getZero()==false)
                button.setZero();
            button.setText("0");
        }
        if(!(rs==0)){
            for (int i=0;i<3;i++){
                if((cs-1+i>=0)&&(cs-1+i<c)) {
                    button = board[rs - 1][cs - 1 + i];
                    button.setText("0");
                    if(button.getZero()==false)
                        button.setZero();
                    button.setSvalue("");
                }
            }
        }
        if(!(rs==r-1)) {
            for (int i = 0; i < 3; i++) {
                if ((cs-1+i>=0)&&(cs-1+i<c)) {
                    button = board[rs + 1][cs - 1 + i];
                    button.setText("0");
                    if(button.getZero()==false)
                        button.setZero();
                    button.setSvalue("");
                }
            }
        }



    }
    public int randomnumber(int ans){
        Random rand=new Random();
        return rand.nextInt(ans - 1) + 0;
    }
    public void setBoardMine(){

            for(int i=0;i<nofMine;i++){
                boolean done=true;
                while (done) {
                    int row = randomnumber(r);
                    int col = randomnumber(c);
                    if (board[row][col].isEnabled()==true && board[row][col].getMine() == false &&board[row][col].getZero()==false) {
                        board[row][col].setSvalue("M");
                        //board[row][col].setText("M");
                        board[row][col].setMine();
                        board[row][col].setZero();
                        done = false;
                    }
                }



            }
    }
    public void try1(){
        int[] row={6,1,5,7,7,9,10,11};
        int [] col={4,1,5,3,8,9,6,8};
        board[row[0]][col[0]].setSvalue("M");
        board[row[0]][col[0]].setText("M");
        board[row[0]][col[0]].setMine();
        board[row[0]][col[0]].setZero();

        board[row[1]][col[1]].setSvalue("M");
        board[row[1]][col[1]].setText("M");
        board[row[1]][col[1]].setMine();
        board[row[1]][col[1]].setZero();

        board[row[2]][col[2]].setSvalue("M");
        board[row[2]][col[2]].setText("M");
        board[row[2]][col[2]].setMine();
        board[row[2]][col[2]].setZero();

        board[row[3]][col[3]].setSvalue("M");
        board[row[3]][col[3]].setText("M");
        board[row[3]][col[3]].setMine();
        board[row[3]][col[3]].setZero();

        board[row[4]][col[4]].setSvalue("M");
        board[row[4]][col[4]].setText("M");
        board[row[4]][col[4]].setMine();
        board[row[4]][col[4]].setZero();

        board[row[5]][col[5]].setSvalue("M");
        board[row[5]][col[5]].setText("M");
        board[row[5]][col[5]].setMine();
        board[row[5]][col[5]].setZero();

        board[row[6]][col[6]].setSvalue("M");
        board[row[6]][col[6]].setText("M");
        board[row[6]][col[6]].setMine();
        board[row[6]][col[6]].setZero();


    }
    public  void showButton(int rs,int cs ){
        board[rs][cs].setEnabledd(false);
        board[rs][cs].setText(board[rs][cs].getSvalues());
        boolean done=true;

        if(!(rs==0)&&!(cs==0)){
            if (board[rs-1][cs-1].getMine()==false&&board[rs-1][cs-1].isEnabled()==true) {
                board[rs - 1][cs - 1].setEnabledd(false);
                board[rs - 1][cs - 1].setText(board[rs - 1][cs - 1].getSvalues());

                if (board[rs - 1][cs - 1].getZero() == true) {

                    showButton(rs - 1, cs - 1);
                }
            }


        }
        if(!(rs==0)){
            if (board[rs-1][cs].getMine()==false&&board[rs-1][cs].isEnabled()==true) {
                board[rs - 1][cs].setEnabledd(false);
                board[rs - 1][cs].setText(board[rs - 1][cs].getSvalues());

                if (board[rs - 1][cs].getZero() == true) {
                    showButton(rs - 1, cs);
                }
            }

        }
        if(!(rs==0)&&!(cs==c-1)){
            if (board[rs-1][cs+1].getMine()==false&&board[rs-1][cs+1].isEnabled()==true) {
                board[rs -1][cs+1].setEnabledd(false);
                board[rs - 1][cs+1].setText(board[rs - 1][cs+1].getSvalues());

                if (board[rs - 1][cs+1].getZero() == true) {
                    showButton(rs - 1, cs+1);
                }
            }

        }
        if(!(cs==0)){
            if (board[rs][cs-1].getMine()==false&&board[rs][cs-1].isEnabled()==true) {
                board[rs][cs - 1].setEnabledd(false);
                board[rs][cs - 1].setText(board[rs][cs - 1].getSvalues());

                if (board[rs][cs - 1].getZero() == true) {
                    showButton(rs, cs - 1);
                }
            }

        }
        //if(5){

        //}
        if(!(cs==c-1)){
            if (board[rs][cs+1].getMine()==false&&board[rs][cs+1].isEnabled()==true) {
                board[rs][cs + 1].setEnabledd(false);
                board[rs][cs + 1].setText(board[rs][cs + 1].getSvalues());

                if (board[rs][cs + 1].getZero() == true) {
                    showButton(rs, cs + 1);
                }
            }

        }
        if(!(rs==r-1)&&!(cs==0)){
            if (board[rs+1][cs-1].getMine()==false&&board[rs+1][cs-1].isEnabled()==true) {
                board[rs + 1][cs - 1].setEnabledd(false);
                board[rs + 1][cs - 1].setText(board[rs + 1][cs - 1].getSvalues());

                if (board[rs + 1][cs - 1].getZero() == true) {
                    showButton(rs + 1, cs - 1);
                }
            }

        }
        if(!(rs==r-1)){
            if (board[rs+1][cs].getMine()==false&&board[rs+1][cs].isEnabled()==true) {
                board[rs + 1][cs].setEnabledd(false);
                board[rs + 1][cs].setText(board[rs + 1][cs].getSvalues());

                if (board[rs + 1][cs].getZero() == true) {
                    showButton(rs + 1, cs);
                }
            }

        }
        if(!(rs==r-1)&&!(cs==c-1)){
            if (board[rs+1][cs+1].getMine()==false&&board[rs+1][cs+1].isEnabled()==true) {
                board[rs + 1][cs + 1].setEnabledd(false);
                board[rs + 1][cs + 1].setText(board[rs + 1][cs + 1].getSvalues());

                if (board[rs + 1][cs + 1].getZero() == true) {
                    showButton(rs + 1, cs + 1);
                }
            }

        }



    }
    public void showNonZeroButton(int rs,int cs){
        if(board[rs][cs].getNo()==true){
            board[rs ][cs ].setEnabledd(false);
            board[rs ][cs ].setText(board[rs ][cs].getSvalues());
        }else {
            if (board[rs][cs].getZero()==true){
                showButton(rs,cs);
            }
        }
    }
    public void foundMinePro(){
        gameStatus=false;
        for (int i=0;i<nofMine;i++){
            board[locationOfMine[i][0]][locationOfMine[i][1]].setText("M");
        }
        Toast.makeText(this,"You lose Buddy!!\n         SORRY",Toast.LENGTH_LONG).show();

    }
    public  void checkProcedure(MineSweepButton button){


        if(button.getMine()==true){
            foundMinePro();
            return;
        }
        showNonZeroButton(button.R,button.C);
    }
    public  void checkForWinner(){
        int count=0;
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                if(count>nofMine){
                    return;
                }
                if(board[i][j].isEnabled()==true){
                    count++;
                }

            }
        }
        if(count==nofMine){
            gameStatus=false;
            for (int i=0;i<nofMine;i++){
                board[locationOfMine[i][0]][locationOfMine[i][1]].setText("M");
            }
            Toast.makeText(this,"You are the WINNER!!",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        if(gameStatus==true) {
            MineSweepButton button = (MineSweepButton) view;
            if (firstClicked == false) {
                firstClicked = true;
                setBoardButtons(button);
            } else {
                if(button.isFlag==false) {
                    checkProcedure(button);
                    checkForWinner();
                }

            }
        }



    }

    @Override
    public boolean onLongClick(View view) {
        if(gameStatus==true) {
            if(firstClicked==true) {
                MineSweepButton button = (MineSweepButton) view;
                if (button.isFlag == true) {
                    button.setText("");
                    button.isFlag = false;
                } else {
                    button.setText("F");
                    button.isFlag = true;
                }
            }
        }
        return true;
    }
}
