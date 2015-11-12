package test.permissionmanager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Itzhar on 11/11/15.
 */
public class PermissionManager {
    private static PermissionsListener mListener;
    //request codes
    public static final int  RC_CALENDAR = 0;
    public static final int  RC_CAMERA = 1;
    public static final int  RC_CONTACTS = 2;
    public static final int  RC_LOCATION = 3;
    public static final int  RC_MICROPHONE = 4;
    public static final int  RC_PHONE = 5;
    public static final int  RC_SENSORS = 6;
    public static final int  RC_SMS = 7;
    public static final int  RC_STORAGE = 8;


    public static final String CALENDAR_EXPLAIN = "CALENDAR_EXPLAIN";
    public static final String CAMERA_EXPLAIN = "CAMERA_EXPLAIN";
    public static final String CONTACTS_EXPLAIN = "CONTACTS_EXPLAIN";
    public static final String LOCATION_EXPLAIN = "LOCATION_EXPLAIN";
    public static final String MICROPHONE_EXPLAIN = "MICROPHONE_EXPLAIN";
    public static final String PHONE_EXPLAIN = "PHONE_EXPLAIN";
    public static final String SENSORS_EXPLAIN = "SENSORS_EXPLAIN";
    public static final String SMS_EXPLAIN = "SMS_EXPLAIN";
    public static final String STORAGE_EXPLAIN = "STORAGE_EXPLAIN";


    //dengaruse permission list by caterory
    public static final String  READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    public static final String  WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;
    public static final String  CAMERA = Manifest.permission.CAMERA;
    public static final String  READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    public static final String  WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    public static final String  GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String  ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String  ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String  RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    public static final String  READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String  CALL_PHONE = Manifest.permission.CALL_PHONE;
    public static final String  READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    public static final String  WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    public static final String  ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    public static final String  USE_SIP = Manifest.permission.USE_SIP;
    public static final String  PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;
    public static final String  BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    public static final String  SEND_SMS = Manifest.permission.SEND_SMS;
    public static final String  RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    public static final String  READ_SMS = Manifest.permission.READ_SMS;
    public static final String  RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    public static final String  RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;
    public static final String  READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String  WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;


    public class PermissionDialod extends Dialog{

        public PermissionDialod(Context context, String explanation) {
            super(context);
            LinearLayout mainLayout = new LinearLayout(context);
            mainLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));


            TextView tvExplanation = new TextView(context);
            tvExplanation.setText(explanation);


            LinearLayout buttonsLayout = new LinearLayout(context);
            buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonsLayout.setOrientation(LinearLayout.HORIZONTAL);
            Button btnOk = new Button(context);
            Button btnCancel = new Button(context);


            buttonsLayout.addView(btnOk);
            buttonsLayout.addView(btnCancel);


            mainLayout.addView(tvExplanation);
            mainLayout.addView(buttonsLayout);
        }
    }
    public static void showExplanationDialog(final AppCompatActivity activity, String explanation,final String [] permissions,final int requestCode, final PermissionsListener listener){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle("App need permission");
        alertDialogBuilder
                .setMessage(explanation)
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.M)
                    public void onClick(DialogInterface dialog,int id) {
                        activity.requestPermissions(permissions, requestCode);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        listener.onDeny();
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public static void requestPermission(AppCompatActivity context, PermissionsListener listener,@Permission String... permissions){
        boolean permissionGranted = checkGroupPermissions(context,permissions);
        String explanation = "";
        int requestCode = -1;

            switch (permissions[0]){
                case Manifest.permission.READ_CALENDAR:
                case Manifest.permission.WRITE_CALENDAR:
                    explanation =CALENDAR_EXPLAIN;
                    requestCode = RC_CALENDAR;
                    break;
                case Manifest.permission.CAMERA:

                    explanation =CAMERA_EXPLAIN;
                    requestCode = RC_CAMERA;
                    break;
                case Manifest.permission.READ_CONTACTS:
                case Manifest.permission.WRITE_CONTACTS:
                case Manifest.permission.GET_ACCOUNTS:
                    explanation =CONTACTS_EXPLAIN;
                    requestCode = RC_CONTACTS;
                    break;
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                case Manifest.permission.ACCESS_FINE_LOCATION:
                    explanation =LOCATION_EXPLAIN;
                    requestCode = RC_LOCATION;
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    explanation =MICROPHONE_EXPLAIN;
                    requestCode = RC_MICROPHONE;
                    break;
                case Manifest.permission.READ_PHONE_STATE:
                case Manifest.permission.CALL_PHONE:
                case Manifest.permission.READ_CALL_LOG:
                case Manifest.permission.WRITE_CALL_LOG:
                case Manifest.permission.ADD_VOICEMAIL:
                case Manifest.permission.USE_SIP:
                case Manifest.permission.PROCESS_OUTGOING_CALLS:
                    explanation =PHONE_EXPLAIN;
                    requestCode = RC_PHONE;
                    break;
                case Manifest.permission.BODY_SENSORS:
                    explanation =SENSORS_EXPLAIN;
                    requestCode = RC_SENSORS;
                    break;
                case Manifest.permission.SEND_SMS:
                case Manifest.permission.RECEIVE_SMS:
                case Manifest.permission.READ_SMS:
                case Manifest.permission.RECEIVE_WAP_PUSH:
                case Manifest.permission.RECEIVE_MMS:
                    explanation =SMS_EXPLAIN;
                    requestCode = RC_SMS;
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    explanation =STORAGE_EXPLAIN;
                    requestCode = RC_STORAGE;
                    break;

            }

        requestPermission(context, permissionGranted,explanation,permissions,requestCode,listener);
    }

    private static boolean checkGroupPermissions(Activity context,String... permissions){
        boolean permissionGranted = true;
        for (String p:permissions) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                permissionGranted = false;
                break;
            }
        }
        return permissionGranted;
    }
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(AppCompatActivity activity ,boolean permissionsGranted,String explanation,String [] permissions,int requestCode,PermissionsListener listener){
        mListener = listener;
        if (!permissionsGranted) {

            // Should we show an explanation?
            if (activity.shouldShowRequestPermissionRationale(permissions[0])) {
                showExplanationDialog(activity,explanation,permissions,requestCode,listener);
            } else {
                // No explanation needed, we can request the permission.
                activity.requestPermissions(permissions, requestCode);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            listener.onAllow();
            mListener = null;
        }
    }

    @StringDef(value={
            Manifest.permission.READ_CALL_LOG,
    })
    @Retention(RetentionPolicy.SOURCE)
    private @interface Permission{}

    public interface PermissionsListener{
        public void onDeny();
        public void onAllow();
    }

    public static void onResult(int requestCode, String permissions[], int[] grantResults){
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mListener.onAllow();
        } else {
            mListener.onDeny();
        }
        /*switch (requestCode) {
            case PermissionManager.RC_CALENDAR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mListener.onAllow();
                } else {
                    mListener.onDeny();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }*/
    }


}
