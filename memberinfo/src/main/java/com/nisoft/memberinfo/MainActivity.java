package com.nisoft.memberinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText teamNameEdit;
    private Spinner teamTypeSpinner;
    private ListView managementLevelsList;
    private ArrayAdapter<String> spinnerAdapter;
    private static final String [] TEAM_TYPES = {
        "公司",
        "项目组",
        "其他"
    };
    private ArrayList<String> managementLevels;
    private ManageLevelAdapter managementLevelAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }

    private void init() {
        spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,TEAM_TYPES);
        managementLevels = new ArrayList<>();
        managementLevelAdapter = new ManageLevelAdapter();
    }

    private void initView() {
        teamNameEdit = (EditText) findViewById(R.id.team_name_edit);
        teamTypeSpinner = (Spinner) findViewById(R.id.team_type_spinner);
        managementLevelsList = (ListView) findViewById(R.id.management_levels_set_list);
        teamTypeSpinner.setAdapter(spinnerAdapter);
        teamTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (managementLevels.size()>0){
                    managementLevels.set(0,spinnerAdapter.getItem(position));
                }else{
                    managementLevels.add(spinnerAdapter.getItem(position));
                }

                managementLevelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        managementLevelsList.setAdapter(managementLevelAdapter);
        managementLevelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==managementLevels.size()){
                    String newLevel = new String();
                    managementLevels.add(newLevel);
                    managementLevelAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private class ManageLevelAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return managementLevels.size()+1;
        }

        @Override
        public Object getItem(int position) {
            return managementLevels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = View.inflate(MainActivity.this,R.layout.managerment_levels_set_item,null);

            LinearLayout container = (LinearLayout) convertView.findViewById(R.id.container);
            TextView levelNameText = (TextView) convertView.findViewById(R.id.management_level_text);
            final EditText levelNameEdit = (EditText) convertView.findViewById(R.id.management_level_edit);
            final Button levelEditButton = (Button) convertView.findViewById(R.id.management_level_edit_button);
            if (position == managementLevels.size()){
                container.removeAllViews();
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setText("点击这里添加管理层级");
                textView.setGravity(Gravity.CENTER);
                container.addView(textView);
            }else {
                levelNameText.setText("第 " + (position+1) + " 层：");
                levelNameEdit.setText(managementLevels.get(position));
                levelEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (levelNameEdit.isFocusable()){
                            managementLevels.set(position,levelNameEdit.getText().toString());
                            levelEditButton.setBackgroundResource(android.R.drawable.ic_menu_edit);
                            levelNameEdit.setFocusable(false);
                            levelNameEdit.setFocusableInTouchMode(false);
                            managementLevelAdapter.notifyDataSetChanged();

                        }else{
                            levelEditButton.setBackgroundResource(android.R.drawable.stat_sys_download_done);
                            levelNameEdit.setFocusable(true);
                            levelNameEdit.setFocusableInTouchMode(true);
                            levelNameEdit.requestFocus();
                        }
                    }
                });
            }
            return convertView;
        }
    }
}
