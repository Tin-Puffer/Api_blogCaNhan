package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.AdapterBlog;
import com.example.myapplication.Api.ApiService;
import com.example.myapplication.Model.Blog;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ArrayList<JsonObject> list;
    private ListView list_blog;
    private Button btn2;
    private Integer a;
//    private ArrayAdapter<Blog> adapter;
    private ArrayList<Blog> list_item= new ArrayList<>();
    private AdapterBlog adapterBlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_blog= findViewById(R.id.blog_list);
        btn2=findViewById(R.id.button2);
        callApi();

    }
//    private  void load_listview(){
//        adapter = new ArrayAdapter<Blog>(this, 0, list_item) {
//            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//                LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
//
//                convertView = inflater.inflate(R.layout.blog_item, null);
//                TextView name = convertView.findViewById(R.id.prd_name);
//                TextView amount = convertView.findViewById(R.id.prd_amount);
//                TextView price = convertView.findViewById(R.id.prd_price);
//                Blog s = list_item.get(position);
//                name.setText(s.getTitle());
//                amount.setText(s.getDecript());
//                price.setText("Post by admin "+s.getTime());
//
//                return convertView;
//            }
//
//        };
//        list_blog.setAdapter(adapter);
//    }
    private void addlist(JsonObject x){
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
        list_item.add(cs);
    }
    private void callApi() {
        ApiService.api.convertapi().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Toast.makeText(MainActivity.this, "thanh cong ", Toast.LENGTH_SHORT).show();
                list_item.clear();
                JsonArray obj= response.body();
                for(int i=0;i<obj.size();i++){
                    addlist((JsonObject) obj.get(i));
                }
                adapterBlog= new AdapterBlog( MainActivity.this,R.layout.blog_item,list_item);
                list_blog.setAdapter(adapterBlog);
                list_blog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String x= list_item.get(i).get_id();
                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.dailog_delete);
                        Button okebnt= dialog.findViewById(R.id.okebnt);
                        Button canbnt= dialog.findViewById(R.id.canbtn);
                        canbnt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        okebnt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                ApiService.api.deleteblog(list_item.get(i).get_id()).enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        dialog.cancel();
                                        Toast.makeText(MainActivity.this, "xoa thanh cong" , Toast.LENGTH_SHORT).show();
                                        callApi();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, "xoa that bai" , Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                        dialog.show();
                        return false;
                    }
                });
//                registerForContextMenu(list_blog);
                list_blog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String x= list_item.get(i).get_id();
                        Toast.makeText(MainActivity.this, "" + x, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,DetailsBlog.class);
                        sendID.id=x;
                        startActivity(intent);
                    }
                });

//                for(int i=0;i<list_item.size();i++){
//                    String id= list_item.get(i).get_id();
//                    String img= list_item.get(i).getImage();
//                    String time= list_item.get(i).getTime();
//                    String title= list_item.get(i).getTitle();
//                    String decript= list_item.get(i).getDecript();
//                    String content= list_item.get(i).getContent();
//                    Log.e("x",id);
//                    Log.e("x",img);
//                    Log.e("x",time);
//                    Log.e("x",title);
//                    Log.e("x",decript);
//                    Log.e("x",content);
//                    Log.e("x","------------------------------");
//                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,sendPost.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        menu.setHeaderTitle("select action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.callAplication){
            Toast.makeText(MainActivity.this, "long click 1", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }
    //        ApiService.api.convertapi().enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                Toast.makeText(MainActivity.this, "thanh cong ", Toast.LENGTH_SHORT).show();
//                JsonObject obj= response.body();
////                Currency ax= obj.;
//                String a= obj.toString();
//                Log.e("main",a);
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//        ApiService.api.convertapi().enqueue(new Callback<>() {
//            @Override
//            public void onResponse(Call<Currency> call, Response<Currency> response) {
//                Toast.makeText(MainActivity.this, "thanh cong ", Toast.LENGTH_SHORT).show();
//                Currency currency= response.body();
//
//            }
//
//            @Override
//            public void onFailure(Call<Currency> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "that bai", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}