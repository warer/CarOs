package me.warer.caros;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import me.warer.caros.UserDao;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Caractivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    public RadioButton drive1, drive2, drive3, suspen1, suspen2, suspen3;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.

        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }

        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    // @Override
    int User = 0;
    int num1 ;
    int num2 ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caractivity);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        drive1 = (RadioButton) findViewById(R.id.radioButton41);
        drive2 = (RadioButton) findViewById(R.id.radioButton42);
        drive3 = (RadioButton) findViewById(R.id.radioButton43);
        suspen1 = (RadioButton) findViewById(R.id.radioButton44);
        suspen2 = (RadioButton) findViewById(R.id.radioButton45);
        suspen3 = (RadioButton) findViewById(R.id.radioButton46);

        Intent intent = getIntent();
        User = intent.getIntExtra("User", User);

        switch (UserDao.getDriveMode(User, getApplicationContext())) {
            case (1):
                drive1.setChecked(true);
                num1=1;
                break;
            case (2):
                drive2.setChecked(true);
                num1=2;
                break;
            case (3):
                drive3.setChecked(true);
                num1=3;
                break;
            default:drive2.setChecked(true);
                      break;
        }
        switch (UserDao.getSupensMode(User, getApplicationContext())) {
            case (1):
                suspen1.setChecked(true);
                num2=1;
                break;
            case (2):
                suspen2.setChecked(true);
                num2=2;
                break;
            case (3):
                suspen3.setChecked(true);
                num2=3;
                break;
                default:suspen2.setChecked(true);
                   break;
        }
        RadioGroup RadioGroup1 = (RadioGroup) findViewById(R.id.radiogroup1);
        RadioGroup1.setOnCheckedChangeListener(mChangeRadio1);
        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao.setDriveMode(User, num1, getApplicationContext());
                UserDao.setSupensMode(User, num2, getApplicationContext());
                Intent intent2 = new Intent(Caractivity.this, Mainactivity.class);
                intent2.putExtra("User", User);
                startActivity(intent2);

            }
        });
        RadioGroup RadioGroup2 = (RadioGroup) findViewById(R.id.radiogroup2);
        RadioGroup2.setOnCheckedChangeListener(mChangeRadio2);


    }

    private RadioGroup.OnCheckedChangeListener mChangeRadio1 = new
            RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == drive1.getId()) {
                        num1 = 1;
                    }
                    if (checkedId == drive2.getId()) {
                        num1 = 2;
                    }
                    if (checkedId == drive3.getId()) {
                        num1 = 3;
                    }

                }
            };
    private RadioGroup.OnCheckedChangeListener mChangeRadio2 = new
            RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == suspen1.getId())

                    {
                        num2 = 1;
                    }

                    if (checkedId == suspen2.getId())

                    {
                        num2 = 2;
                    }

                    if (checkedId == suspen3.getId())

                    {
                        num2 = 3;
                    }
                }
            };


                protected void onPostCreate(Bundle savedInstanceState) {
                    super.onPostCreate(savedInstanceState);

                    // Trigger the initial hide() shortly after the activity has been
                    // created, to briefly hint to the user that UI controls
                    // are available.
                    delayedHide(100);
                }

                private void toggle() {
                    if (mVisible) {
                        hide();
                    } else {
                        show();
                    }
                }

                private void hide() {
                    // Hide UI first
                    ActionBar actionBar = getSupportActionBar();
                    if (actionBar != null) {
                        actionBar.hide();
                    }
                    mControlsView.setVisibility(View.GONE);
                    mVisible = false;

                    // Schedule a runnable to remove the status and navigation bar after a delay
                    mHideHandler.removeCallbacks(mShowPart2Runnable);
                    mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
                }

                @SuppressLint("InlinedApi")
                private void show() {
                    // Show the system bar
                    mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                    mVisible = true;

                    // Schedule a runnable to display UI elements after a delay
                    mHideHandler.removeCallbacks(mHidePart2Runnable);
                    mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
                }

                /**
                 * Schedules a call to hide() in [delay] milliseconds, canceling any
                 * previously scheduled calls.
                 */
                private void delayedHide(int delayMillis) {
                    mHideHandler.removeCallbacks(mHideRunnable);
                    mHideHandler.postDelayed(mHideRunnable, delayMillis);
                }

                protected void onResume() {
                    /**
                     * 设置为横屏
                     */
                    if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }
                    super.onResume();
                }
            }

