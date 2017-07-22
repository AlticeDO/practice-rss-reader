package com.vinrosa.rssreader;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "URL";
    ListView detailListView;
    private ArrayList<Article> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailListView = (ListView) findViewById(R.id.detail_list_view);
        String url = getIntent().getExtras().getString(EXTRA_URL);
        Parser parser = new Parser();
        parser.onFinish(new Parser.OnTaskCompleted(){

            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                mainList = list;
                detailListView.setAdapter(new FeedAdapter(list));
            }

            @Override
            public void onError() {

            }
        });
        parser.execute(url);

        detailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article article = mainList.get(i);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(article.getLink()));
                startActivity(intent);
            }
        });
    }

    public class FeedAdapter extends ArrayAdapter<Article> {

        public FeedAdapter(@NonNull List<Article> objects) {
            this(DetailActivity.this, 0, objects);
        }

        public FeedAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Article> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(DetailActivity.this).inflate(R.layout.feed_item, parent, false);
            TextView title = view.findViewById(R.id.lbl_title);
            TextView desc = view.findViewById(R.id.lbl_desc);

            title.setText(getItem(position).getTitle());
            desc.setText(getItem(position).getDescription());
            return view;
        }
    }
}
