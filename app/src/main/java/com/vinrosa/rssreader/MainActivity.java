package com.vinrosa.rssreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        switch (view.getId()){
            case R.id.btn_1:
                intent.putExtra(DetailActivity.EXTRA_URL, "https://www.diariolibre.com/rss/portada.xml");
                break;
            case R.id.btn_2:
                intent.putExtra(DetailActivity.EXTRA_URL, "http://www.listindiario.com/rss/portada/");
                break;
            case R.id.btn_3:
                intent.putExtra(DetailActivity.EXTRA_URL, "http://elnacional.com.do/feed/");
                break;
        }
        startActivity(intent);
    }
}
