package com.example.mediaplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter{
    private ArrayList<Song> songs;
    private LayoutInflater songInf;

    public customAdapter(Context c, ArrayList<Song> theSongs){
        songs = theSongs;
        songInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //ánh xạ layout đến mỗi bài
        LinearLayout songLayout = (LinearLayout) songInf.inflate(R.layout.song, viewGroup, false);
        TextView nameView = songLayout.findViewById(R.id.tv_songname);

        Song currentAudio = songs.get(position);

        //lấy tiêu đề bài hát
        nameView.setText(currentAudio.getName());
        songLayout.setTag(position);
        return songLayout;
    }
}
