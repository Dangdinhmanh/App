package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    ImageView ivBack,IvStatus2;
    TextView txtNamcity2;
    TextView tvMaxTemp,tvMinTemp;
    TextView tvStatus2;
    ListView lv;
    CustomAdapter customAdapter;
    ArrayList<ThoiTiet> mangthoitiet;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String city= intent.getStringExtra("name");
       Log.d("kq","truyendl"+city);
        GetWeather7DayData(city);




        TID();
    }

    private void TID() {
        ivBack = (ImageView) findViewById(R.id.IvBack);
        tvMaxTemp=(TextView )findViewById(R.id.tvMaxTemp);
        tvMinTemp= (TextView) findViewById(R.id.tvMinTemp);
        tvStatus2=(TextView) findViewById(R.id.tvStatus2);
        IvStatus2 =(ImageView) findViewById(R.id.IvStatus2);
        txtNamcity2= (TextView) findViewById(R.id.tvNameCity2);
        lv= (ListView) findViewById(R.id.listview);

        mangthoitiet= new ArrayList<ThoiTiet>();
        customAdapter = new CustomAdapter(Main2Activity.this,mangthoitiet);
        lv. setAdapter(customAdapter);                                           // set Text cho list view

    }

    private void GetWeather7DayData(String data) {
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+data+"&mode=xml&units=metric&cnt=7&appid=211ff006de9aba9ddd122331f87cdf8b";
                //"http://api.openweathermap.org/data/2.5/forecast/daily?q="+data+"&units=metric&cnt=7&appid=211ff006de9aba9ddd122331f87cdf8b" ;
      // http://api.openweathermap.org/data/2.5/forecast/daily?q=Hanoi&units=metric&cnt=7&appid=211ff006de9aba9ddd122331f87cdf8b

        RequestQueue requestQueue = Volley.newRequestQueue(Main2Activity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // dẫn kết quả trả về trong này



                try {
                    JSONObject jsonObject = new JSONObject(response);

                      JSONObject jsonObjectCity= jsonObject.getJSONObject("city");
                      String nameCity= jsonObjectCity.getString("name");
                      txtNamcity2.setText(nameCity);

                    JSONArray jsonArrayList= jsonObject.getJSONArray("list");
                    for (int i=0; i<jsonArrayList.length();i++){
                        JSONObject jsonObjectList= jsonArrayList.getJSONObject(i);

                        String  Day2= jsonObjectList.getString("dt");
                        long l = Long.valueOf(Day2);
                        Date date = new Date(1*1000L);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE yyyy-MM-dd");
                        String NgayThang= simpleDateFormat.format(date);


                        JSONObject jsonObjectTemp= jsonObjectList.getJSONObject("temp ");
                           String maxtemp= jsonObjectTemp.getString("max");
                           String mintemp=jsonObjectTemp.getString("min");
                           tvMaxTemp.setText(maxtemp);
                           tvMinTemp.setText(mintemp);


                          JSONArray jsonArrayWeather=jsonObjectList.getJSONArray("weather");
                          JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(1);
                            String status2=jsonObjectWeather.getString("description");
                            tvStatus2.setText(status2);
                            String icon2=jsonObjectWeather.getString("icon");

                            mangthoitiet.add(new ThoiTiet(NgayThang,maxtemp,mintemp,status2,icon2));

                    }

                    customAdapter.notifyDataSetChanged();




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }


    public void SetImage2(String status2){
        String input = status2.trim().toLowerCase();
        switch (input){
            case "clear":
                IvStatus2.setImageResource(R.drawable.sunnyhn);
                break;
            case "few ":
                IvStatus2.setImageResource(R.drawable.fewhn);
                break;
            case "clouds":
                IvStatus2.setImageResource(R.drawable.cloudshn);
                break;
               //  case "scattered":
             //      IVIcon.setImageResource(R.drawable.scatteredhn);
            //       break;
            //     case "broken":
            //        IVIcon.setImageResource(R.drawable.scatteredhn);
            //      break;
            //    case "shower":
            //       IVIcon.setImageResource(R.drawable.rainhn);
            //     break;
            case "rain":
                IvStatus2.setImageResource(R.drawable.rainhn);
                break;
            case "thunderstorm":
                IvStatus2.setImageResource(R.drawable.thunderstormhn);
                break;
            case "snow":
                IvStatus2.setImageResource(R.drawable.snowhn);
                break;
            case "mist":
                IvStatus2.setImageResource(R.drawable.misthn);
                break;


        }
    }


}
