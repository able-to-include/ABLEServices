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
import com.asi_soft.able.demo.core.Simplifier;

import static com.asi_soft.able.demo.core.Constants.SIMPLEXT;

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
                Simplifier simplifier = new Simplifier();
                simplifier.simplifyText(SIMPLEXT, inputText, getActivity(), languages[spinnerLanguage.getSelectedItemPosition()], "");
            }
        });
    }




}
