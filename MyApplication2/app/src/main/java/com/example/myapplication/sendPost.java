package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Api.ApiService;
import com.example.myapplication.Model.Blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sendPost extends AppCompatActivity {
//    @Override
    private EditText title;
    private EditText decr;
    private EditText img;
    private EditText content;
    private Button bnt;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_now);
        title=findViewById(R.id.editTextTextPersonName);
        decr=findViewById(R.id.editTextTextPersonName2);
        img=findViewById(R.id.editTextTextPersonName3);
        content=findViewById(R.id.editTextTextMultiLine);
        bnt=findViewById(R.id.button);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Blog bl= new Blog();
                bl.setTitle(title.getText().toString());
                bl.setDecript(decr.getText().toString());
                bl.setImage(img.getText().toString());
                bl.setContent(content.getText().toString());
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");

                String date = df.format(Calendar.getInstance().getTime());
                bl.setTime(date);
                if(bl.getImage().length()!=0 && bl.getContent().length()!=0 && bl.getDecript().length()!=0 && bl.getTitle().length()!=0){
                    ApiService.api.sendpost(bl).enqueue(new Callback<Blog>() {
                        @Override
                        public void onResponse(Call<Blog> call, Response<Blog> response) {
                            Toast.makeText(sendPost.this, "thanhcong", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(sendPost.this,MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Blog> call, Throwable t) {
                            Toast.makeText(sendPost.this, "thatbai", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(sendPost.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
//                bl.setTitle(title.getText().toString());
            }
        });
    }
}
