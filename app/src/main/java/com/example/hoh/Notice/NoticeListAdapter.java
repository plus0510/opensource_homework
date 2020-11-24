package com.example.hoh.Notice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hoh.R;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {

    private Context context;
    private List<Notice> noticeList;


    public NoticeListAdapter(Context context, List<Notice> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.notice_item, null);
        TextView noticeText = v.findViewById(R.id.noticeText);
        TextView nameText = v.findViewById(R.id.nameText);
        TextView dateText = v.findViewById(R.id.dateText);

        noticeText.setText(noticeList.get(i).getNotice());
        nameText.setText(noticeList.get(i).getName());
        dateText.setText(noticeList.get(i).getDate());

        v.setTag(noticeList.get(i).getNotice());
        return v;
    }
}