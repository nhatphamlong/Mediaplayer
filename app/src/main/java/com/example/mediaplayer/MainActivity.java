package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Song> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listViewSong);
        runtimePermission();
        showSongs();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String songName = (String) listView.getItemAtPosition(i);
                startActivity(new Intent(getApplicationContext(),PlayerActivity.class)
                .putExtra("songs", mySongs)
                .putExtra("songname", songName)
                .putExtra("pos",i));
            }
        });
    }

    public void showSongs(){
        addSongs();
        customAdapter customAdapter = new customAdapter(this,mySongs);
        listView.setAdapter(customAdapter);
    }

    public void addSongs(){

        mySongs = new ArrayList<>();
        mySongs.add(new Song("Cây lặng gió ngừng",R.raw.caylanggiongung));
        mySongs.add(new Song("Chênh vênh",R.raw.chenhvenhlctl));
        mySongs.add(new Song("Chúng ta đang thở kìa",R.raw.chungtadangthokia));
        mySongs.add(new Song("Đôi bờ",R.raw.doibo));
        mySongs.add(new Song("Đưa cơm cho mẹ đi cày",R.raw.duacomchomedicay));
        mySongs.add(new Song("Này sao ơi",R.raw.naysaooi));
        mySongs.add(new Song("Tám chữ có",R.raw.tamchuco));
        mySongs.add(new Song("Thương",R.raw.thuonglctl));
        mySongs.add(new Song("Trốn tìm",R.raw.trontim));

    }

    public void runtimePermission()
    {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
}