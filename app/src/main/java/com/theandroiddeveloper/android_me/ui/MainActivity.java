package com.theandroiddeveloper.android_me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.theandroiddeveloper.android_me.Constant;
import com.theandroiddeveloper.android_me.R;
import com.theandroiddeveloper.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements
        MasterListFragment.OnImageClickListener {
    private int headIndex, bodyIndex, legIndex;
    private boolean hasTwoPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hasTwoPaneLayout = (findViewById(R.id.llTabletContainer) != null);
        if (hasTwoPaneLayout) {
            ((GridView) findViewById(R.id.gridView))
                    .setNumColumns(2);
        }
        updateTwoPaneLayout();

        Button btView = (Button) findViewById(R.id.btView);
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AndroidMeActivity.class);
                intent.putExtra(Constant.IntentExtra.HEAD_INDEX, headIndex);
                intent.putExtra(Constant.IntentExtra.BODY_INDEX, bodyIndex);
                intent.putExtra(Constant.IntentExtra.LEG_INDEX, legIndex);
                startActivity(intent);
            }
        });
        btView.setVisibility(hasTwoPaneLayout ? View.GONE : View.VISIBLE);
    }

    private void updateTwoPaneLayout() {
        if (!hasTwoPaneLayout) {
            return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flHead,
                        new BodyPartFragment()
                                .setResIdList(AndroidImageAssets.getHeads())
                                .setSelectedIndex(headIndex))
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flBody,
                        new BodyPartFragment()
                                .setResIdList(AndroidImageAssets.getBodies())
                                .setSelectedIndex(bodyIndex))
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flLeg,
                        new BodyPartFragment()
                                .setResIdList(AndroidImageAssets.getLegs())
                                .setSelectedIndex(legIndex))
                .commit();
    }

    @Override
    public void onImageClick(int position) {
        final int bodyPartInt = position / 12;
        final int index = position - (12 * bodyPartInt);

        switch (bodyPartInt) {
            case 0:
                headIndex = index;
                break;
            case 1:
                bodyIndex = index;
                break;
            case 2:
                legIndex = index;
                break;
        }

        updateTwoPaneLayout();
    }
}
