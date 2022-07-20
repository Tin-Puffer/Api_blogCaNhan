package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Api.ApiService;
import com.example.myapplication.Model.Blog;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterBlog;
import com.example.myapplication.Api.ApiService;
import com.example.myapplication.Model.Blog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Currency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsBlog extends AppCompatActivity {
//    @Override
    private TextView title;
    private TextView decript;
    private TextView time;
    private ImageView img;
    private TextView content;
    private Integer a;
    private ImageButton bnt;


    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_blog);
        ApiService.api.getoneBlog(sendID.id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject x= response.body();
                title= findViewById(R.id.textView3);
                decript= findViewById(R.id.textView2);
                time= findViewById(R.id.textView4);
                content=findViewById(R.id.textView5);
                img=findViewById(R.id.imageView2);
                Toast.makeText(DetailsBlog.this, "thanh cong ", Toast.LENGTH_SHORT).show();
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
                decript.setText(cs.getDecript());
                time.setText(cs.getTime());
                content.setText(cs.getContent());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(DetailsBlog.this, "that bai", Toast.LENGTH_SHORT).show();

            }
        });
        bnt=findViewById(R.id.button3);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsBlog.this,EditBlog.class);
                startActivity(intent);
            }
        });

    }
}
