/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * The work represented by this file is partially funded by the ABLE-TO-INCLUDE
 * project through the European Commission's ICT Policy Support Programme as
 * part of the Competitiveness & Innovation Programme (Grant no.: 621055)
 * Copyright © 2016, ABLE-TO-INCLUDE Consortium.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions & limitations
 * under the License.
 */
package com.asi_soft.able.demo.core.simplifier;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.asi_soft.able.demo.DisplayPictosActivity;
import com.asi_soft.able.demo.R;
import com.asi_soft.able.demo.core.Constants;
import com.asi_soft.able.demo.network.ABLEClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

/**
 * Created on 15/02/2016.
 *
 * @author Javier Sanchez
 */
public class Text2PictoSimplifier extends Simplifier {
    /**
     * Method to process selected parameters and open the <code>DisplayPictosActivity</code>.
     *
     * @param inputText
     * @param context
     * @param language
     * @param pictogram_base
     */
    @Override
    public void simplifyText(EditText inputText, Context context, String language, String pictogram_base) {
        String input = inputText.getText().toString();
        if (input.trim().length() > 0) {
            // Connect to the webservice via AsyncTask
            ABLEClient ableClient = new ABLEClient();

            String url;
            String parameters;
            try {

                url = Constants.TEXT2PICTO_URL;
                parameters = "text=" + URLEncoder.encode(input, "UTF-8") + "&type=" + pictogram_base
                        + "&language=" + language;

                ableClient.execute(url, parameters);


                String result = ableClient.get();

                if (result.length() == 0) {
                    showError(context, context.getString(R.string.conversion_error),
                            context.getString(R.string.conversion_error_explanation));
                } else {
                    try {
                        String status = new JSONObject(result).getString("status");
                        if (status.compareTo("200") == 0) {
                            Intent intent = new Intent(context, DisplayPictosActivity.class);
                            intent.putExtra("object", result);
                            context.startActivity(intent);
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
}
