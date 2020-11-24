package com.example.hoh.Notice;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hoh.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Notice_Fragment3 extends Fragment {
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList1;
    private List<Notice> noticeList2;
    private List<Notice> noticeList3;
    private List<Notice> noticeList4;
    private List<Notice> noticeList5;


    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.notice_fragment3, container, false);
        noticeListView = viewGroup.findViewById(R.id.noticeListView);
        noticeList1 = new ArrayList<Notice>();
        noticeList2 = new ArrayList<Notice>();
        noticeList3 = new ArrayList<Notice>();
        noticeList4 = new ArrayList<Notice>();
        noticeList5 = new ArrayList<Notice>();

        final Button mainButton = viewGroup.findViewById(R.id.mainButton);
        final Button massButton = viewGroup.findViewById(R.id.massButton);
        final Button schButton = viewGroup.findViewById(R.id.schButton);
        final Button socialButton = viewGroup.findViewById(R.id.socialButton);
        final Button exButton = viewGroup.findViewById(R.id.exButton);
        final TextView noticeName = viewGroup.findViewById(R.id.notice_name);
        final TextView noticeUrl = viewGroup.findViewById(R.id.notice_url);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
                mainButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                massButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                schButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                socialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                exButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                noticeName.setText("공지사항");
                noticeUrl.setText("https://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=35209&schCategorycode=CTG_17082400011&schKeytype=subject&schKeyword=&pageIndex=1");

                adapter = new NoticeListAdapter(getActivity().getApplicationContext(), noticeList1);
                noticeListView.setAdapter(adapter);


            }
        });
        massButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite1();
                mainButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                massButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                schButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                socialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                exButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                noticeName.setText("학사공지");
                noticeUrl.setText("https://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=35209&schCategorycode=CTG_17082400012&schKeytype=subject&schKeyword=&pageIndex=1");

                adapter = new NoticeListAdapter(getActivity().getApplicationContext(), noticeList2);
                noticeListView.setAdapter(adapter);


            }
        });
        schButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite2();
                mainButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                massButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                schButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                socialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                exButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                noticeName.setText("장학공지");
                noticeUrl.setText("https://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=35209&schCategorycode=CTG_17082400013&schKeytype=subject&schKeyword=&pageIndex=1");

                adapter = new NoticeListAdapter(getActivity().getApplicationContext(), noticeList3);
                noticeListView.setAdapter(adapter);

            }
        });
        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite3();
                mainButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                massButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                schButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                socialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                exButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                noticeName.setText("사회봉사");
                noticeUrl.setText("https://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=35209&schCategorycode=CTG_17082400014&schKeytype=subject&schKeyword=&pageIndex=1");

                adapter = new NoticeListAdapter(getActivity().getApplicationContext(), noticeList4);
                noticeListView.setAdapter(adapter);

            }
        });
        exButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite4();
                mainButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                massButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                schButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                socialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                exButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

                noticeName.setText("외부공지");
                noticeUrl.setText("https://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=35209&schCategorycode=CTG_17082400015&schKeytype=subject&schKeyword=&pageIndex=1");

                adapter = new NoticeListAdapter(getActivity().getApplicationContext(), noticeList5);
                noticeListView.setAdapter(adapter);

            }
        });

        return viewGroup;

    }

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builder1 = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("https://www.hoseo.ac.kr/Home/BBSList.mbz?action=MAPP_1708240139&schCategorycode=CTG_17082400011").get();
                    Elements title = doc.select(" td.board-list-title > a");
                    Elements day = doc.select("#example1 > tbody > tr > td:nth-child(3)");
                    Elements write = doc.select("#example1 > tbody > tr > td.txt-center.pc_view");
                    for (Element e : title) {
                        builder.append(e.text() + "\n");
                    }
                    for (Element s : day) {
                        builder1.append(s.text() + "\n");
                    }
                    for (Element d : write) {
                        builder2.append(d.text() + "\n");
                    }
                } catch (IOException E) {
                    builder.append("Error");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noticeList1.clear();
                        String notice_title = builder.toString();
                        String[] title_array = notice_title.split("\n");
                        String notice_day = builder1.toString();
                        String[] day_array = notice_day.split("\n");
                        String notice_write = builder2.toString();
                        String[] write_array = notice_write.split("\n");
                        for (int i = 0; i < write_array.length; i++) {
                            noticeList1.add(new Notice(title_array[i], day_array[i], write_array[i]));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void getWebsite1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builder1 = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=0&schCategorycode=CTG_17082400012&schKeytype=subject&schKeyword=&pageIndex=1").get();
                    Elements title = doc.select(" td.board-list-title > a");
                    Elements day = doc.select("#example1 > tbody > tr > td:nth-child(3)");
                    Elements write = doc.select("#example1 > tbody > tr > td.txt-center.pc_view");
                    for (Element e : title) {
                        builder.append(e.text() + "\n");
                    }
                    for (Element s : day) {
                        builder1.append(s.text() + "\n");
                    }
                    for (Element d : write) {
                        builder2.append(d.text() + "\n");
                    }
                } catch (IOException E) {
                    builder.append("Error");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noticeList2.clear();
                        String notice_title = builder.toString();
                        String[] title_array = notice_title.split("\n");
                        String notice_day = builder1.toString();
                        String[] day_array = notice_day.split("\n");
                        String notice_write = builder2.toString();
                        String[] write_array = notice_write.split("\n");
                        for (int i = 0; i < write_array.length; i++) {
                            noticeList2.add(new Notice(title_array[i], day_array[i], write_array[i]));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void getWebsite2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builder1 = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=0&schCategorycode=CTG_17082400013&schKeytype=subject&schKeyword=&pageIndex=1").get();
                    Elements title = doc.select(" td.board-list-title > a");
                    Elements day = doc.select("#example1 > tbody > tr > td:nth-child(3)");
                    Elements write = doc.select("#example1 > tbody > tr > td.txt-center.pc_view");
                    for (Element e : title) {
                        builder.append(e.text() + "\n");
                    }
                    for (Element s : day) {
                        builder1.append(s.text() + "\n");
                    }
                    for (Element d : write) {
                        builder2.append(d.text() + "\n");
                    }
                } catch (IOException E) {
                    builder.append("Error");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noticeList3.clear();
                        String notice_title = builder.toString();
                        String[] title_array = notice_title.split("\n");
                        String notice_day = builder1.toString();
                        String[] day_array = notice_day.split("\n");
                        String notice_write = builder2.toString();
                        String[] write_array = notice_write.split("\n");
                        for (int i = 0; i < write_array.length; i++) {
                            noticeList3.add(new Notice(title_array[i], day_array[i], write_array[i]));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void getWebsite3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builder1 = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=0&schCategorycode=CTG_17082400014&schKeytype=subject&schKeyword=&pageIndex=1").get();
                    Elements title = doc.select(" td.board-list-title > a");
                    Elements day = doc.select("#example1 > tbody > tr > td:nth-child(3)");
                    Elements write = doc.select("#example1 > tbody > tr > td.txt-center.pc_view");
                    for (Element e : title) {
                        builder.append(e.text() + "\n");
                    }
                    for (Element s : day) {
                        builder1.append(s.text() + "\n");
                    }
                    for (Element d : write) {
                        builder2.append(d.text() + "\n");
                    }
                } catch (IOException E) {
                    builder.append("Error");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noticeList4.clear();
                        String notice_title = builder.toString();
                        String[] title_array = notice_title.split("\n");
                        String notice_day = builder1.toString();
                        String[] day_array = notice_day.split("\n");
                        String notice_write = builder2.toString();
                        String[] write_array = notice_write.split("\n");
                        for (int i = 0; i < write_array.length; i++) {
                            noticeList4.add(new Notice(title_array[i], day_array[i], write_array[i]));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void getWebsite4() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builder1 = new StringBuilder();
                final StringBuilder builder2 = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://www.hoseo.ac.kr/Home//BBSList.mbz?action=MAPP_1708240139&schIdx=0&schCategorycode=CTG_20012200070&schKeytype=subject&schKeyword=&pageIndex=1").get();
                    Elements title = doc.select(" td.board-list-title > a");
                    Elements day = doc.select("#example1 > tbody > tr > td:nth-child(3)");
                    Elements write = doc.select("#example1 > tbody > tr > td.txt-center.pc_view");
                    for (Element e : title) {
                        builder.append(e.text() + "\n");
                    }
                    for (Element s : day) {
                        builder1.append(s.text() + "\n");
                    }
                    for (Element d : write) {
                        builder2.append(d.text() + "\n");
                    }
                } catch (IOException E) {
                    builder.append("Error");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noticeList5.clear();
                        String notice_title = builder.toString();
                        String[] title_array = notice_title.split("\n");
                        String notice_day = builder1.toString();
                        String[] day_array = notice_day.split("\n");
                        String notice_write = builder2.toString();
                        String[] write_array = notice_write.split("\n");
                        for (int i = 0; i < write_array.length; i++) {
                            noticeList5.add(new Notice(title_array[i], day_array[i], write_array[i]));
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
