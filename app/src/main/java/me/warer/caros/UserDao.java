package me.warer.caros;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Context;
/**
 * Created by warer on 2016/5/3.
 */
public class UserDao {
public static boolean getFirstrun(Context context){
    SharedPreferences pref = context.getSharedPreferences("System", Context.MODE_PRIVATE);
    Boolean Firstrun = pref.getBoolean("Firstrun",true);
    return Firstrun;
}
    public static void setFirstrun(boolean firstrun,Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("System",
                Context.MODE_PRIVATE).edit();
        editor.putBoolean("Firstrun",firstrun);
        editor.apply();
    }


    public static String getUserName(int num, Context context) {
        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            String UserName1 = pref.getString("UserName1", "");
            return UserName1;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            String UserName2 = pref.getString("UserName2", "");
            return UserName2;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            String UserName3 = pref.getString("UserName3", "");

            return UserName3;
        } else
            return "error";
    }

    public static void setUserName(int num, String UserName, Context context) {
        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    Context.MODE_PRIVATE).edit();
            editor.putString("UserName1", UserName);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    Context.MODE_PRIVATE).edit();
            editor.putString("UserName2", UserName);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    Context.MODE_PRIVATE).edit();
            editor.putString("UserName3", UserName);
            editor.apply();

        }
    }

    public static int getAutomode(int num, Context context) {

        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            int Automode = pref.getInt("Automode1", 0);
            return Automode;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            int Automode = pref.getInt("Automode2", 0);
            return Automode;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", Context.MODE_PRIVATE);
            int Automode = pref.getInt("Automode3", 0);
            return Automode;
        } else
            return 1;

    }

    public static void setAutomode(int num, int mode, Context context) {
        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("Automode1", mode);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("Automode2", mode);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("Automode3", mode);
            editor.apply();

        }
    }

    public static int getDefaultUser(Context context) {


        SharedPreferences pref = context.getSharedPreferences("System", Context.MODE_PRIVATE);
        int DefaultUser = pref.getInt("DefaultUser", 0);
        return DefaultUser;


    }

    public static void setDefaultUser(int num, Context context) {


        SharedPreferences.Editor editor = context.getSharedPreferences("System", context.MODE_PRIVATE).edit();
        editor.putInt("DefaultUser", num);
        editor.apply();


    }

    public static void setDriveMode(int num, int mode, Context context) {

        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("driveMode1", mode);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("driveMode2", mode);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("driveMode3", mode);
            editor.apply();

        }
    }

    public static int getDriveMode(int num, Context context) {

        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int DriveMode = pref.getInt("driveMode1", 0);
            return DriveMode;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int DriveMode = pref.getInt("driveMode2", 0);
            return DriveMode;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int DriveMode = pref.getInt("driveMode3", 0);
            return DriveMode;
        } else
            return 1;

    }

    public static void setSupensMode(int num, int mode, Context context) {
        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("supensMode1", mode);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("supensMode2", mode);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("supensMode3", mode);
            editor.apply();

        }
    }

    public static int getSupensMode(int num, Context context) {

        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int SupensMode = pref.getInt("supensMode1", 0);
            return SupensMode;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int SupensMode = pref.getInt("supensMode2", 0);
            return SupensMode;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            int SupensMode = pref.getInt("supensMode3", 0);
            return SupensMode;
        } else
            return 1;

    }

    public static void setChairAngle(int num, int ChairAngleX, int ChairAngleY, Context context) {
        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("ChairAngleX1", ChairAngleX);
            editor.putInt("ChairAngleY1", ChairAngleY);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("ChairAngleX2", ChairAngleX);
            editor.putInt("ChairAngleY2", ChairAngleY);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("ChairAngleX3", ChairAngleX);
            editor.putInt("ChairAngleY3", ChairAngleY);
            editor.apply();

        }
    }

    public static int[] getChairAngle(int num, Context context) {
        int[] ChairAngle = {0, 0};
        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            ChairAngle[0] = pref.getInt("ChairAngleX1", 0);
            ChairAngle[1] = pref.getInt("ChairAngleY1", 0);
            return ChairAngle;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            ChairAngle[0] = pref.getInt("ChairAngleX2", 0);
            ChairAngle[1] = pref.getInt("ChairAngleY2", 0);
            return ChairAngle;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            ChairAngle[0] = pref.getInt("ChairAngleX3", 0);
            ChairAngle[1] = pref.getInt("ChairAngleY3", 0);
            return ChairAngle;

        }else
            return ChairAngle;
    }
    public static void setMirrorAngle(int num, int MirrorAngleX, int MirrorAngleY, Context context) {
        if (num == 1) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("MirrorAngleX1", MirrorAngleX);
            editor.putInt("MirrorAngleY1", MirrorAngleY);
            editor.apply();
        }

        if (num == 2) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("MirrorAngleX2", MirrorAngleX);
            editor.putInt("MirrorAngleY2", MirrorAngleY);
            editor.apply();
        }
        if (num == 3) {
            SharedPreferences.Editor editor = context.getSharedPreferences("User",
                    context.MODE_PRIVATE).edit();
            editor.putInt("MirrorAngleX3", MirrorAngleX);
            editor.putInt("MirrorAngleY3", MirrorAngleY);
            editor.apply();

        }
    }
    public static int[] getMirrorAngle(int num, Context context) {
        int[] MirrorAngle = {0, 0};
        if (num == 1) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            MirrorAngle[0] = pref.getInt("MirrorAngleX1", 0);
            MirrorAngle[1] = pref.getInt("MirrorAngleY1", 0);
            return MirrorAngle;
        }
        if (num == 2) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            MirrorAngle[0] = pref.getInt("MirrorAngleX2", 0);
            MirrorAngle[1] = pref.getInt("MirrorAngleY2", 0);
            return MirrorAngle;
        }
        if (num == 3) {
            SharedPreferences pref = context.getSharedPreferences("User", context.MODE_PRIVATE);
            MirrorAngle[0] = pref.getInt("MirrorAngleX3", 0);
            MirrorAngle[1] = pref.getInt("MirrorAngleY3", 0);
            return MirrorAngle;

        }else
            return MirrorAngle;
    }
}

