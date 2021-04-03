package com.appplepie.retrofit_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_request = findViewById(R.id.btn_request);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service1 = retrofit.create(RetrofitService.class);

        Call<PostResult> call = service1.getPosts("1"); // {post}에 1을 대입

        btn_request.setOnClickListener(v -> {
            call.enqueue(new Callback<PostResult>() {
                @Override
                public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                    if(response.isSuccessful()){
                        PostResult result = response.body();
                        Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostResult> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }


}