package com.tatuas.localbroadcastmanagersample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * LocalBroadcastManagerは、registerしていない間に結果が返された時も再度registerされるまで結果を保持する。
 * 逆に言えば、unregisterしないかぎりはBroadcast結果を回転するたびにキューとしてストックしつづける。
 * なので、registerとunregisterは正しく扱う必要がある。
 */
public class MainActivity extends AppCompatActivity {

    private final IntentFilter somethingServiceFilter = new IntentFilter(SomethingService.FILTER_NAME);

    private BroadcastReceiver somethingServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "stop", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                somethingServiceReceiver, somethingServiceFilter);
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                somethingServiceReceiver);
        super.onDestroy();
    }

    @OnClick(R.id.hello)
    void hello() {
        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
        startService(SomethingService.createIntent(this));
    }
}
