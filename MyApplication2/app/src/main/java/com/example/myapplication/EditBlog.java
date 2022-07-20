package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Api.ApiService;
import com.example.myapplication.Model.Blog;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBlog extends AppCompatActivity {
    private EditText title;
    private EditText decr;
    private EditText img;
    private EditText content;
    private Button bnt;
    private  Integer a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.edit_blog);
        title=findViewById(R.id.editTextTextPersonName);
        decr=findViewById(R.id.editTextTextPersonName2);
        img=findViewById(R.id.editTextTextPersonName4);
        content=findViewById(R.id.editTextTextMultiLine);
        bnt=findViewById(R.id.button);
        ApiService.api.getoneBlog(sendID.id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject x= response.body();
                Toast.makeText(EditBlog.this, "thanh cong ", Toast.LENGTH_SHORT).show();
                Blog cs= new Blog();
                JsonElement userIdentifier =  x.get("_id");
                a=userIdentifier.toString().length();
                cs.set_id(userIdentifier.toString().substring(1,a-1));
                JsonElement userIdentifier2 =  x.get("Image");
                a=userIdentifier2.toString().length();
                cs.setImage(userIdentifier2.toString().substring(1,a-1));
                JsonElement userIdentifier3 =  x.get("Title");
                a=userIdentifier3.toString().length();
                cs.setTitle(userIdentifier3.toString().substring(1,a-1));
                JsonElement userIdentifier4 =  x.get("Decript");
                a=userIdentifier4.toString().length();
                cs.setDecript(userIdentifier4.toString().substring(1,a-1));
                JsonElement userIdentifier5 =  x.get("Time");
                a=userIdentifier5.toString().length();
                cs.setTime(userIdentifier5.toString().substring(1,a-1));
                JsonElement userIdentifier6 =  x.get("Content");
                a=userIdentifier6.toString().length();
                cs.setContent(userIdentifier6.toString().substring(1,a-1));
                title.setText(cs.getTitle());
                decr.setText(cs.getDecript());
                content.setText(cs.getContent());
                img.setText(cs.getImage());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Blog bl= new Blog();
                bl.setTitle(title.getText().toString());
                bl.setDecript(decr.getText().toString());
                bl.setContent(content.getText().toString());
                DateFormat df = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
                String date = df.format(Calendar.getInstance().getTime());
                bl.setTime(date);
                if(bl.getContent().length()!=0 && bl.getDecript().length()!=0 && bl.getTitle().length()!=0){
                    ApiService.api.Update_post(sendID.id,bl).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(EditBlog.this, "thanhcong", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditBlog.this,MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(EditBlog.this, "thatbai", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditBlog.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
        super.onCreate(savedInstanceState);
    }
}
