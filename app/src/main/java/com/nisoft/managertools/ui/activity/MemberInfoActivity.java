package com.nisoft.managertools.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.nisoft.managertools.R;

public class MemberInfoActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText numberText;
    private EditText organization_text;
    private EditText workSpaceText;
    private EditText workTypeText;
    private EditText positionText;
    private EditText jobText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_info);
    }
}
