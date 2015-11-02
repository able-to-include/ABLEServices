package com.asi_soft.able.demo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayPictosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pictos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        Intent intent = getIntent();
        String object = intent.getStringExtra("object");
        try {
            JSONObject objectABLE = new JSONObject(object);

            JSONArray pictos = objectABLE.getJSONArray("pictos");

            ArrayList<String> mPictos = new ArrayList<>();

            for(int i = 0; i < pictos.length(); i++) {
                String pictoURL = pictos.getString(i);
                mPictos.add(pictoURL);
                System.out.println(pictoURL);
            }

            PictoListAdapter adapter = new PictoListAdapter(this, R.layout.pictos_list_item, mPictos);
            ListView mListView = (ListView) findViewById(R.id.listView);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            //TODO Show error
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
