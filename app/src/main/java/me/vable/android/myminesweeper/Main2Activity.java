package me.vable.android.myminesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }



    public void f1(View view) {
        Button button=(Button)view;
        Intent intent=new Intent(this,MainActivity.class);
       int id= button.getId();
        if(id==R.id.b1){
            intent.putExtra("1",1);
        }
        if(id==R.id.b2){
            intent.putExtra("1",2);
        }
        if(id==R.id.b2){
            intent.putExtra("1",3);
        }
        startActivity(intent);
    }
}
