package com.naruto.dptest;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bcgdv.asia.lib.fanmenu.FanMenuButtons;
import com.naruto.dptest.frags.FirstFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private MyPagerAdapter adapterViewPager;
    ArrayList<HashMap<String, String>> dataofTable;
    ViewPager vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.GREEN);
        pagerTabStrip.setTextColor(Color.GREEN);

        final FanMenuButtons submenu = (FanMenuButtons) findViewById(R.id.myFABSubmenu);

        vpPager = (ViewPager) findViewById(R.id.vpPager);

        new LongOperation().execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                submenu.toggleShow();


            }
        });


        if (submenu != null) {
            submenu.setOnFanButtonClickListener(new FanMenuButtons.OnFanClickListener() {
                @Override
                public void onFanButtonClicked(int index) {
                    String[] a = getResources().getStringArray(R.array.fan_labels);
                    if (a[index].equals("Technology News")) {
                        submenu.toggleShow();
                        Intent i = new Intent(MainActivity.this, BollwoodActivity.class);
                        startActivity(i);
                    }
                    Toast.makeText(MainActivity.this, "Button Clicked = " + a[index], Toast.LENGTH_SHORT).show();
                }
            });

            submenu.setOnFanAnimationListener(new FanMenuButtons.OnFanAnimationListener() {
                @Override
                public void onAnimateInStarted() {
                }

                @Override
                public void onAnimateOutStarted() {
                }

                @Override
                public void onAnimateInFinished() {
                }

                @Override
                public void onAnimateOutFinished() {
                }
            });
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mybutton) {

            DBController db = new DBController(getApplicationContext());
            Toast.makeText(this,db.getSyncStatus(),Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void isSequence(int number) {

        int[] test = new int[]{4, 5, 7};

        Arrays.sort(test);
        for (int i = 0; i < test.length - 1; i++) {
            if (test[i] + 1 != test[i + 1]) {
                // Not sequential
            }
        }

    }

    public int ArraytoNumber(int[] numbtoarray) {
        //    int a[] = {60, 321, 5};

        int finalNumber = 0;
        for (int i = 0; i < numbtoarray.length; i++) {
            int num = numbtoarray[i];
            if (num != 0) {
                while (num > 0) {
                    finalNumber *= 10;
                    num /= 10;
                }
                finalNumber += numbtoarray[i];
            } else {
                finalNumber *= 10;
            }
        }
        return finalNumber;
    }

    public int[] intTOArray(int guess) {
        String temp = Integer.toString(guess);
        int[] newGuess = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            newGuess[i] = temp.charAt(i) - '0';
        }
        return newGuess;
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        private ArrayList<HashMap<String, String>> dataofTable;

        public MyPagerAdapter(FragmentManager fragmentManager, ArrayList<HashMap<String, String>> dataofTable) {
            super(fragmentManager);
            this.dataofTable = dataofTable;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return dataofTable.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return new FirstFragment(dataofTable.get(position));

        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return dataofTable.get(position).get(DBController.KEY_DATE);
        }

    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            DBController rj = new DBController(getApplicationContext());
            dataofTable = rj.getAllUsers();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            adapterViewPager = new MainActivity.MyPagerAdapter(getSupportFragmentManager(), dataofTable);
            vpPager.setAdapter(adapterViewPager);
        }
    }
}


// take number
//convert to array>>>
// >>sorting>>
// >convert to sorted number then compareboth if true green alse right