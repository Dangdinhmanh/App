package com.example.myapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    Button btnsearchOK,btnNextDay;
    TextView tvNameCity,tvTemp,tvStatus,tvHumidity,tvWind;

    ImageView IVIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("OpenWethearMap");
        AXID();
        GetWeatherData("Hanoi");
        btnsearchOK = (Button) findViewById(R.id.btnSearchOk);
        btnsearchOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= edtSearch.getText().toString();
                GetWeatherData(city);

                }
            }
        );

        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city= edtSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);        // chuyen man hinh
                   intent.putExtra("name",city);
                   startActivity(intent);


            }
        });

    }
    public  void GetWeatherData(String data){
        String url = " https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=92759166c55be6a02754deb37e72cbec";
    //  https://api.openweathermap.org/data/2.5/weather?q=Hanoi&units=metric&appid=92759166c55be6a02754deb37e72cbec

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this); // truyền tới màn hình đang đứng
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Log.d("doc",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                        String name = jsonObject.getString("name");
                          tvNameCity.setText("Tên Thành Phố : " +name);

                    JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);                                                                                                                     // dữ liệu lấy từ Arrayweather xong đi vào jsonojbect
                      String status = jsonObjectWeather.getString("main");                                                                                                                                      // lấy từ jsonobjectweather
                      String icon = jsonObjectWeather.getString("icon");
                   // Toast.makeText(MainActivity.this, icon, Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(MainActivity.this, "Link nè: "+url1, Toast.LENGTH_SHORT).show();


                    tvStatus.setText(status);

                    SetImage(status);

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                      String temp= jsonObjectMain.getString("temp");
                      String humidity= jsonObjectMain.getString("humidity");
                      tvTemp.setText(temp+"°C");
                      tvHumidity.setText("Độ Ẩm:"+humidity+"%");

                      JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                         String wind = jsonObjectWind.getString("speed");
                         tvWind.setText("Gió:"+wind+"m/s");


                } catch (JSONException e) {
                    e.printStackTrace();
                }


           }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })  ;                                                                                                                                                                                                       // đọc dữ liệu đường dẫn

        requestQueue.add(stringRequest );                                                                                                                                                                       // để thực thi, gọi lại requet ban đầu đã tạo, add vào reuqet muốn gửi đi




    }





    private void AXID() {
        edtSearch =(EditText) findViewById(R.id.edtSearch);

        btnsearchOK = (Button) findViewById(R.id.btnSearchOk);
        btnNextDay = (Button) findViewById(R.id.btnNextDay);
       // btnHistory = (Button) findViewById(R.id.btnHistory);

        tvNameCity=(TextView) findViewById(R.id.tvNameCity);
        tvTemp=(TextView) findViewById(R.id.tvTemp);
        tvStatus=(TextView) findViewById(R.id.tvStatus);
        tvHumidity=(TextView) findViewById(R.id.tvHumidity);
        tvWind=(TextView) findViewById(R.id.tvWind);




        IVIcon= (ImageView) findViewById(R.id.IVIcon);
    }


    public void SetImage(String status){
        String input = status.trim().toLowerCase();
        switch (input){
            case "clear":
                IVIcon.setImageResource(R.drawable.sunnyhn);
                break;
            case "few ":
                IVIcon.setImageResource(R.drawable.fewhn);
                break;
            case "clouds":
                IVIcon.setImageResource(R.drawable.cloudshn);
                break;
       //     case "scattered":
         //       IVIcon.setImageResource(R.drawable.scatteredhn);
         //       break;
       //     case "broken":
        //        IVIcon.setImageResource(R.drawable.scatteredhn);
          //      break;
        //    case "shower":
         //       IVIcon.setImageResource(R.drawable.rainhn);
           //     break;
            case "rain":
                IVIcon.setImageResource(R.drawable.rainhn);
                break;
            case "thunderstorm":
                IVIcon.setImageResource(R.drawable.thunderstormhn);
                break;
            case "snow":
                IVIcon.setImageResource(R.drawable.snowhn);
                break;
            case "mist":
                IVIcon.setImageResource(R.drawable.misthn);
                break;


        }
    }








    }












