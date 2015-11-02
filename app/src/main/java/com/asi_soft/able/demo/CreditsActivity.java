package com.asi_soft.able.demo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

/**
 *
 * @author Javier Sanchez
 */
public class CreditsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        String version = getVersion();
        TextView textViewVersion = (TextView) findViewById(R.id.textViewVersion);
        textViewVersion.setText(version);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @return The version text
     */
    private String getVersion() {
        String version = "";
        String versionName = "0";
        int versionNumber = 0;
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pInfo.versionName;
            versionNumber = pInfo.versionCode;
            version = versionName + " (" + Integer.toString(versionNumber) + ")";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
