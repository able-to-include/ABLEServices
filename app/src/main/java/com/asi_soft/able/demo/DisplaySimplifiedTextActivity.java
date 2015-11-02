package com.asi_soft.able.demo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class DisplaySimplifiedTextActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_simplified_text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        TextView textViewSimplifiedText = (TextView) findViewById(R.id.textViewSimplifiedText);
        Intent intent = getIntent();
        String object = intent.getStringExtra("object");

        try {
            JSONObject objectABLE = new JSONObject(object);
            String simplifiedText = objectABLE.getString("textSimplified");
            textViewSimplifiedText.setText(simplifiedText);
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
