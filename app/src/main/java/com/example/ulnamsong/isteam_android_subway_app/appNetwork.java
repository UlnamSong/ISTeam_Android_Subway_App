package com.example.ulnamsong.isteam_android_subway_app;

/**
 * Created by Ulnamsong on 2016. 2. 9..
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class appNetwork extends BroadcastReceiver {
    private Activity activity;
    public appNetwork() {
        super();
    }
    public appNetwork(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action= intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
                NetworkInfo _wifi_network = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if(_wifi_network != null) {
                    // wifi, 3g 둘 중 하나라도 있을 경우
                    if(_wifi_network != null && activeNetInfo != null){
                        //Do Nothing
                        Log.e("Yes Network", "Network Connection");
                        AlertDialog.Builder alert = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alert.setMessage("네트워크가 연결되었습니다.");
                        alert.setTitle("네트워크 연결됨");
                        alert.show();
                    }

                    // wifi, 3g 둘 다 없을 경우
                    else{
                        Log.e("No Network", "No Network Connection");
                        AlertDialog.Builder alert = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                        alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                activity.finish();
                            }
                        });
                        alert.setMessage("네트워크 연결을 확인하세요.");
                        alert.setTitle("네트워크 연결 확인");
                        alert.show();
                    }
                }
            } catch (Exception e) {
                Log.i("ULNetworkReceiver", e.getMessage());
            }
        }
    }
}

