package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LAB71MainActivity extends AppCompatActivity {
    Button btn1 , btn2 , btn3 , btn4;
    TextView tvKQ;
    Context context=this;
    String strKQ="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab71_main);
        btn1 = findViewById(R.id.lab71Btn1);
        btn2 = findViewById(R.id.lab71Btn2);
        btn3 = findViewById(R.id.lab71Btn3);
        btn4 = findViewById(R.id.lab71Btn4);
        tvKQ = findViewById(R.id.lab71TvKq);
        btn1.setOnClickListener((view)->{
            selectVolley();
        });
        btn2.setOnClickListener((view)->{
            insertVolley();
        });
        btn3.setOnClickListener((view)->{
            updateVolley();
        });
        btn4.setOnClickListener((view)->{
            deleteVolley();
        });
    }

    private void deleteVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/delete_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",txt1.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void updateVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/update_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("pid",txt1.getText().toString());
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void insertVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/create_product.php";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("name",txt2.getText().toString());
                mydata.put("price",txt3.getText().toString());
                mydata.put("description",txt4.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void selectVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab7/get_all_product.php";
        //b4. Xac dinh loai request
        JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray products=response.getJSONArray("products");
                    strKQ="";
                    for (int i=0;i<products.length();i++)
                    {
                        JSONObject product=products.getJSONObject(i);
                        String pid=product.getString("pid");
                        String name=product.getString("name");
                        String price=product.getString("price");
                        String description=product.getString("description");
                        strKQ +="pid: "+pid +"\n";
                        strKQ +="name: "+name +"\n";
                        strKQ +="price: "+price +"\n";
                        strKQ +="description: "+description +"\n";
                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
}