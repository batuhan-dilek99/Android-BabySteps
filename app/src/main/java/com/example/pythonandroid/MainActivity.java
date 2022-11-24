package com.example.pythonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button camBtn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        camBtn = (Button)findViewById(R.id.cambtn);
        tv = (TextView)findViewById(R.id.txt);
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("helloworld");
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                PyObject obj = pyobj.callAttr(("hello"));
                tv.setText(obj.toString());
            }
        });

        camBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try{
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

}