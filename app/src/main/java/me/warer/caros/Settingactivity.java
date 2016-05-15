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

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Settingactivity extends AppCompatActivity {
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
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
 //                   | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
 //                   | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
 //                   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
 //                   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
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
            mControlsView.setVisibility(View.VISIBLE);
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

 public   int User=0;
 public   int num1=1 ;
public    RadioButton Auto1 ;
public    RadioButton Auto2 ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingactivity);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);

        Intent intent = getIntent();
        User = intent.getIntExtra("User", User);


        // Set up the user interaction to manually show or hide the system UI.


        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        Button button1 = (Button) findViewById(R.id.button2);
        Button button2 = (Button) findViewById(R.id.button3);
        Button button3 = (Button) findViewById(R.id.button4);
        Button button4 = (Button) findViewById(R.id.button6);
        Button button5 = (Button) findViewById(R.id.button7);
        Button button6= (Button) findViewById(R.id.button8);
       Auto1 = (RadioButton) findViewById(R.id.radioButton54);
       Auto2 = (RadioButton) findViewById(R.id.radioButton55);

        RadioGroup RadioGroup1 = (RadioGroup) findViewById(R.id.radioGroup3);
        RadioGroup1.setOnCheckedChangeListener(mChangeRadio1);
        switch (UserDao.getAutomode(User,getApplicationContext())) {
            case (1):
                Auto1.setChecked(true);
                break;
            case (0):
                Auto2.setChecked(true);
                break;
            default:
                Auto1.setChecked(true);
                break;
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Settingactivity.this)
                        .setTitle("确定重置用户1吗？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        UserDao.setUserName(1,"1号驾驶员",getApplicationContext());
                                        UserDao.setSupensMode(1,2,getApplicationContext());
                                        UserDao.setDriveMode(1,2,getApplicationContext());
                                        UserDao.setAutomode(1,1,getApplicationContext());


                                    }
                                }).setNegativeButton("取消", null).create()
                        .show();
            }
        });



       button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Settingactivity.this)
                        .setTitle("确定重置用户2吗？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        UserDao.setUserName(2,"2号驾驶员",getApplicationContext());
                                        UserDao.setSupensMode(2,2,getApplicationContext());
                                        UserDao.setDriveMode(2,2,getApplicationContext());
                                        UserDao.setAutomode(2,1,getApplicationContext());

                                    }
                                }).setNegativeButton("取消", null).create()
                        .show();
            }

        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Settingactivity.this)
                        .setTitle("确定重置用户3吗？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        UserDao.setUserName(3,"3号驾驶员",getApplicationContext());
                                        UserDao.setSupensMode(3,2,getApplicationContext());
                                        UserDao.setDriveMode(3,2,getApplicationContext());
                                        UserDao.setAutomode(3,1,getApplicationContext());

                                    }
                                }).setNegativeButton("取消", null).create()
                        .show();
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Settingactivity.this)
                        .setTitle("确定设置账户"+User+"为默认账户吗？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        UserDao.setDefaultUser(User,getApplicationContext());

                                    }
                                }).setNegativeButton("取消", null).create()
                        .show();
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           int DefaultUser = UserDao.getDefaultUser(getApplicationContext());
                                           if (DefaultUser != 0) {
                                               new AlertDialog.Builder(Settingactivity.this)
                                                       .setTitle("确定取消" + UserDao.getUserName(DefaultUser, getApplicationContext()) + "的默认登陆账户吗？")
                                                       .setPositiveButton("确定",
                                                               new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialog,
                                                                                       int which) {
                                                                       UserDao.setDefaultUser(0, getApplicationContext());

                                                                   }
                                                               }).setNegativeButton("取消", null).create().show();
                                           } else {
                                               new AlertDialog.Builder(Settingactivity.this)
                                                       .setTitle("当前不存在默认账户！")
                                                       .setPositiveButton("确定", null)
                                                       .show();
                                           }

                                       }
                                   });



        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao.setAutomode(User,num1,getApplicationContext());
                Intent intent2 = new Intent(Settingactivity.this, Mainactivity.class);
                intent2.putExtra("User", User);
                startActivity(intent2);
            }
        });

    }
    private RadioGroup.OnCheckedChangeListener mChangeRadio1 = new
            RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == Auto1.getId()) {
                        num1 = 1;
                    }
                    if (checkedId == Auto2.getId()) {
                        num1 = 0;
                    }

                }
            };


    @Override
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
    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
