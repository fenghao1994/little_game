package com.little.game.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.little.game.adpter.MyAdapter;
import com.little.game.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private GridView gridView;
    private static int AMOUNT_DEFAULT = 3;
    private List<Boolean> buttons = new ArrayList<Boolean>();
    private int COLOR_DEFAULT = Color.RED;
    private MyAdapter myAdapter;
    private List<Button> buttonList = new ArrayList<Button>();
    private int columnFlag;
    private Button button;
    private Button rule;

    private Button bg1, bg2, bg3, bg4, bg5, bg6, bg7;
    private Button bgc1, bgc2, bgc3, bgc4, bgc5, bgc6, bgc7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        listener();
        createLayout();
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.game_layout);
        myAdapter = new MyAdapter(buttons, this, COLOR_DEFAULT);
        gridView.setAdapter(myAdapter);
        button = (Button) findViewById(R.id.reset);
        rule = (Button) findViewById(R.id.rule);

    }

    private void initDate() {
        bgc1 = (Button) findViewById(R.id.bgc1);

        bgc2 = (Button) findViewById(R.id.bgc2);

        bgc3 = (Button) findViewById(R.id.bgc3);

        bgc4 = (Button) findViewById(R.id.bgc4);

        bgc5 = (Button) findViewById(R.id.bgc5);

        bgc6 = (Button) findViewById(R.id.bgc6);
        bgc7 = (Button) findViewById(R.id.bgc7);

        bgc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color1);
                createLayout();
            }
        });

        bgc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color2);
                createLayout();
            }
        });

        bgc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color3);
                createLayout();
            }
        });

        bgc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color4);
                createLayout();
            }
        });

        bgc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color5);
                createLayout();
            }
        });

        bgc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color6);
                createLayout();
            }
        });

        bgc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COLOR_DEFAULT = getResources().getColor(R.color.color7);
                createLayout();
            }
        });


        bg1 = (Button) findViewById(R.id.bg1);

        bg2 = (Button) findViewById(R.id.bg2);

        bg3 = (Button) findViewById(R.id.bg3);

        bg4 = (Button) findViewById(R.id.bg4);

        bg5 = (Button) findViewById(R.id.bg5);

        bg6 = (Button) findViewById(R.id.bg6);

        bg7 = (Button) findViewById(R.id.bg7);

        buttonList = new ArrayList<Button>();
        buttonList.add(bg1);
        buttonList.add(bg2);
        buttonList.add(bg3);
        buttonList.add(bg4);
        buttonList.add(bg5);
        buttonList.add(bg6);
        buttonList.add(bg7);

        for (int i = 0; i < buttonList.size(); i++) {
            final int finalI = i;
            buttonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    columnFlag = finalI;
                    AMOUNT_DEFAULT = finalI + 3;
                    createLayout();
                }
            });
        }

    }

    private void createLayout() {
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == columnFlag) {
                buttonList.get(i).setBackgroundColor(COLOR_DEFAULT);
                buttonList.get(i).setTextColor(Color.WHITE);
            } else {
                buttonList.get(i).setBackgroundColor(Color.WHITE);
                buttonList.get(i).setTextColor(Color.RED);
            }
        }
        gridView.setNumColumns(AMOUNT_DEFAULT);
        buttons.clear();
        for (int i = 0; i < AMOUNT_DEFAULT * AMOUNT_DEFAULT; i++) {
            buttons.add(false);
        }
        myAdapter.setColumn(AMOUNT_DEFAULT);
        myAdapter.setColor(COLOR_DEFAULT);
        myAdapter.setFlag(false);
        myAdapter.notifyDataSetChanged();
    }

    private void listener() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                myAdapter.itemClick(i, AMOUNT_DEFAULT);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                columnFlag = 0;
                COLOR_DEFAULT = Color.RED;
                AMOUNT_DEFAULT = 3;
                createLayout();
            }
        });

        rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RuleActivity.class);
                startActivity(intent);
            }
        });
    }
}
