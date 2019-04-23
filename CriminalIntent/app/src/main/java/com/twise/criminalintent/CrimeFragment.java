package com.twise.criminalintent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

public class CrimeFragment extends Fragment
        implements AdapterView.OnItemSelectedListener {

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText titleField;
    private Button dateButton;
    private Spinner severitySpinner;
    private CheckBox solvedCheckedBox;

    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);
        CrimeFragment frag = new CrimeFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        // --- TITLE TEXT FIELD
        titleField = (EditText) view.findViewById(R.id.crime_title);
        titleField.setText(mCrime.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Insert Code Here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Insert Code Here
            }
        });

        // --- DATE BUTTON
        dateButton = (Button) view.findViewById(R.id.crime_date);
        dateButton.setText(mCrime.getDate().toString());
        dateButton.setEnabled(false);

        // --- SEVERITY SPINNER
        severitySpinner = (Spinner) view.findViewById(R.id.severity_spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(),
                        R.array.severity_label,
                        android.R.layout.simple_spinner_item);
        severitySpinner.setAdapter(adapter);
        severitySpinner.setOnItemSelectedListener(this);
        int pos = 0;
        if (mCrime.getSeverity().equals("War Criminal"))
            pos = 1;
        else if (mCrime.getSeverity().equals("Boss Level Criminal"))
            pos = 2;
        else if (mCrime.getSeverity().equals("Extreme Felony"))
            pos = 3;
        else if (mCrime.getSeverity().equals("Felony"))
            pos = 4;
        else if (mCrime.getSeverity().equals("Misdemeanor"))
            pos = 5;
        else if (mCrime.getSeverity().equals("Infraction"))
            pos = 6;
        else if (mCrime.getSeverity().equals("Wrist Slap"))
            pos = 7;

        // --- SOLVED CHECKBOX
        solvedCheckedBox = (CheckBox) view.findViewById(R.id.crime_solved);
        solvedCheckedBox.setChecked(mCrime.isSolved());
        solvedCheckedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mCrime.setSeverity(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
