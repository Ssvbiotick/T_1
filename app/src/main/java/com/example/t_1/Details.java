package com.example.t_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Details extends AppCompatActivity {
    TextView textTitle;
    TextView textDetails;

    private Document doc;
    private Document doc_1;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;
    private int chosen_pos;

    MyDatabaseHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


//        setContentView(R.layout.activity_details);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

//        textTitle = findViewById(R.id.detailTitle);
//        textDetails = findViewById(R.id.textView5);

        Intent i = getIntent();
        String title = i.getStringExtra("title");
        actionBar.setTitle(title);
        chosen_pos = i.getIntExtra("pos",0);
        init();

    }



    private void init()
    {
//        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        //tvData1 = findViewById(R.id.tvData1);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this,R.layout.list_item_1,arrayList,getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
//        String text = def_pref.getString("main_text_size","Средний");
//        if (text != null)
//        {
//            switch(text)
//            {
//                case "Большой":
//                    tvData1.setTextSize(20);
//                    break;
//                case "Средний":
//                    tvData1.setTextSize(14);
//                    break;
//                case "Маленький":
//                    tvData1.setTextSize(10);
//                    break;
//            }
//        }
    }

    private void getWeb()
    {

        try {

            dbHelper = new MyDatabaseHelper(this);
            Cursor cursor = dbHelper.readAllData();

            String url = "";

            while (cursor.moveToNext())
            {
                if(cursor.getString(3).equals(String.valueOf(chosen_pos+1)))
                {
                    url = cursor.getString(2);
                    break;
                }
            }

            cursor.close();
            dbHelper.close();

            if (!url.equals(""))
            {
                url = "https://" + url;
                doc = Jsoup.connect(url).get();
                String title_1 = doc.title();
                Element tables = doc.getElementById("articleText");

                ListItemClass items = new ListItemClass();
                items.setData_1(tables.text());
                items.setData_2(title_1);
                arrayList.add(items);
            }

//            Element our_table = tables.get(0);
//            Elements elements_from_table = our_table.children();
//            Element dollar = elements_from_table.get(0);
//            Elements dollar_elements = dollar.children();
//            Log.d("MyLog","Tbody size : " + our_table.children().get(0).text());
//            for(int i = 0;i < our_table.childrenSize();i++ )
//            {
//                ListItemClass items = new ListItemClass();
//                items.setData_1(our_table.children().get(i).child(0).text());
//                arrayList.add(items);
//            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() { adapter.notifyDataSetChanged(); }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}