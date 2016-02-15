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

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

import com.asi_soft.able.demo.R;

/**
 * Created 17/03/15.
 *
 * @author Javier Sanchez
 */
public abstract class Simplifier {

    /**
     * Method to process selected parameters and open the correct <code>Activity</code>.
     *
     * @param inputText
     * @param context
     * @param language
     * @param pictogram_base
     */
    public abstract void simplifyText(EditText inputText, Context context, String language, String pictogram_base);

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

    /**
     * Returns a textual description for the given error code.
     *
     * @param result
     * @param context
     * @return
     */
    protected static String getErrorDescription(String result, Context context) {
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
}
