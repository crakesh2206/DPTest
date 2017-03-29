package com.naruto.dptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bcgdv.asia.lib.fanmenu.FanMenuButtons;
import com.naruto.dptest.frags.FirstFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FanMenuButtons sub = (FanMenuButtons) findViewById(R.id.myFABSubmenu);
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);



//        //Display Sync status of SQLite DB
//        DBController controller = new DBController(this);
//        Toast.makeText(getApplicationContext(), controller.getSyncStatus(), Toast.LENGTH_LONG).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                sub.toggleShow();
                Intent i = new Intent(MainActivity.this,BollwoodActivity.class);
               startActivity(i);

            }
        });



        if (sub != null) {
            sub.setOnFanButtonClickListener(new FanMenuButtons.OnFanClickListener() {
                @Override
                public void onFanButtonClicked(int index) {
                    String[] a = getResources().getStringArray(R.array.fan_labels);

                    Toast.makeText(MainActivity.this, "Button Clicked = " +a[index], Toast.LENGTH_SHORT).show();
                }
            });

            sub.setOnFanAnimationListener(new FanMenuButtons.OnFanAnimationListener() {
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private  void isSequence(int number){

        int[] test = new int[] {4,5,7};

        Arrays.sort(test);
        for (int i = 0; i < test.length - 1; i++) {
            if (test[i] + 1 != test[i + 1]) {
                // Not sequential
            }
        }

    }
    public int  ArraytoNumber(int[] numbtoarray){
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

    public int[] intTOArray(int guess){
        String temp = Integer.toString(guess);
        int[] newGuess = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            newGuess[i] = temp.charAt(i) - '0';
        }
        return newGuess;
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FirstFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FirstFragment.newInstance(2, "Page # 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }





}
// take number
//convert to array>>>
// >>sorting>>
// >convert to sorted number then compareboth if true green alse right