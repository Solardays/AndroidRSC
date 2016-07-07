package com.jc.androidrsc;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jc.androidrs.MyAIDLService;

public class MainActivity extends AppCompatActivity {

    ServiceConnection conn2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("TestService", "onServiceConnected");
            MyAIDLService myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int plus = myAIDLService.plus(3, 2);
                Log.i("TestService", "plus: " + plus);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("TestService", "onServiceDisconnected");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void remoteServ(View view) {
        Intent intent = new Intent();
        intent.setAction("com.jc.androidrs.MyAIDLService");
        bindService(intent, conn2, BIND_AUTO_CREATE);
    }
}
