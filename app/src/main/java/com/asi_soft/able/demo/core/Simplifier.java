package com.asi_soft.able.demo.core;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.asi_soft.able.demo.DisplayPictosActivity;
import com.asi_soft.able.demo.DisplaySimplifiedTextActivity;
import com.asi_soft.able.demo.DisplaySpeechActivity;
import com.asi_soft.able.demo.R;
import com.asi_soft.able.demo.network.ABLEClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import static com.asi_soft.able.demo.core.Constants.SIMPLEXT;
import static com.asi_soft.able.demo.core.Constants.TEXT2PICTO;
import static com.asi_soft.able.demo.core.Constants.TEXT2SPEECH;

/**
 * Created by javier on 17/03/15.
 */
public class Simplifier {

    public void simplifyText(int method, EditText inputText, Context context, String language, String pictogram_base){
        String input = inputText.getText().toString();
        if (input.trim().length() > 0) {
            // Connect to the webservice via AsyncTask
            ABLEClient ableClient = new ABLEClient();

            String url;
            String parameters;
            try {
                switch (method) {
                    case SIMPLEXT:
                        url = Constants.SIMPLEXT_URL;
                        parameters = "text=" + URLEncoder.encode(input, "UTF-8") + "&language="
                                + language;
                        break;
                    case TEXT2PICTO:
                        url = Constants.TEXT2PICTO_URL;
                        parameters = "text=" + URLEncoder.encode(input, "UTF-8") + "&type="
                                + pictogram_base + "&language=" + language;
                        break;
                    case TEXT2SPEECH:
                        url = Constants.TEXT2SPEECH_URL;
                        parameters = "text=" + URLEncoder.encode(input, "UTF-8") + "&language="
                                + language;
                        break;
                    default:
                        url = Constants.SIMPLEXT_URL;
                        parameters = "text=" + input;
                }


                ableClient.execute(url, parameters);


                String result = ableClient.get();

                if (result.length() == 0) {
                    showError(context, context.getString(R.string.conversion_error),
                            context.getString(R.string.conversion_error_explanation));
                } else {
                    try {
                        String status = new JSONObject(result).getString("status");
                        if (status.compareTo("200") == 0) {
                            switch (method) {
                                case SIMPLEXT:
                                    openActivity(DisplaySimplifiedTextActivity.class, result, context);
                                    break;
                                case TEXT2PICTO:
                                    openActivity(DisplayPictosActivity.class, result, context);
                                    break;
                                case TEXT2SPEECH:
                                    openActivity(DisplaySpeechActivity.class, result, context);
                                    break;
                                default:
                                    openActivity(DisplaySimplifiedTextActivity.class, result, context);
                            }
                        } else {
                            showError(context, context.getString(R.string.service_error), getErrorDescription(result, context));
                        }


                    } catch (JSONException e) {
                        showError(context, context.getString(R.string.conversion_error),
                                context.getString(R.string.conversion_error_explanation));
                        e.printStackTrace();
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }catch(ExecutionException e){
                e.printStackTrace();
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
        } else {
            showError(context, context.getString(R.string.input_error),
                    context.getString(R.string.input_text_cannot_be_empty));
        }
    }

    /**
     * Shows an <code>AlertDialog</code> with the given title and message.
     */
    public void showError(Context context, String title, String message) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton(context.getString(R.string.ok), null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private String getErrorDescription(String result, Context context) {
        String description = "";
        if (result.compareTo("408") == 0) {
            description = context.getString(R.string.error408);
        } else if (result.compareTo("451") == 0) {
            description = context.getString(R.string.error451);
        } else if (result.compareTo("452") == 0) {
            description = context.getString(R.string.error452);
        } else if (result.compareTo("453") == 0) {
            description = context.getString(R.string.error453);
        } else if (result.compareTo("454") == 0) {
            description = context.getString(R.string.error454);
        } else if (result.compareTo("455") == 0) {
            description = context.getString(R.string.error455);
        } else if (result.compareTo("456") == 0) {
            description = context.getString(R.string.error456);
        } else if (result.compareTo("457") == 0) {
            description = context.getString(R.string.error457);
        } else if (result.compareTo("458") == 0) {
            description = context.getString(R.string.error458);
        }
        return description;
    }

    /*
    private String getRealLanguageName(String language, Context context) {
        String lng = "";
        String[] abbreviations = context.getResources().getStringArray(R.array.pref_language_values);
        String[] realNames = context.getResources().getStringArray(R.array.pref_language_titles);

        if (language.compareTo(abbreviations[0]) == 0) {
            // en
            lng = realNames[0];
        } else if (language.compareTo(abbreviations[1]) == 0) {
            // es
            lng = realNames[1];
        } else if (language.compareTo(abbreviations[2]) == 0) {
            // fr
            lng = realNames[2];
        } else if (language.compareTo(abbreviations[3]) == 0) {
            // cr
            lng = realNames[3];
        }
        return lng;
    }
    */

    private void openActivity(Class otherClass, String objectABLE, Context context) {
        Intent intent = new Intent(context, otherClass);
        intent.putExtra("object", objectABLE);
        context.startActivity(intent);
    }
}
