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
package com.asi_soft.able.demo.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.asi_soft.able.demo.R;
import com.asi_soft.able.demo.core.simplifier.SimplextSimplifier;
import com.asi_soft.able.demo.core.simplifier.Simplifier;

/**
 * Created 17/03/2015
 *
 * @author Javier Sanchez
 */
public class SimplextFragment extends Fragment {

    private EditText inputText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simplext,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Spinner spinnerLanguage = (Spinner) getActivity().findViewById(R.id.spinnerLanguage1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.languages_simplext, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerLanguage.setAdapter(adapter);

        Resources res = getActivity().getResources();
        final String[] languages = res.getStringArray(R.array.languages_simplext_values);

        inputText = (EditText) getActivity().findViewById(R.id.editTextInput1);

        ImageButton buttonSimplext = (ImageButton) getActivity().findViewById(R.id.buttonSimplext);
        buttonSimplext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Simplifier simplifier = new SimplextSimplifier();
                simplifier.simplifyText(inputText, getActivity(), languages[spinnerLanguage.getSelectedItemPosition()], "");
            }
        });
    }




}
