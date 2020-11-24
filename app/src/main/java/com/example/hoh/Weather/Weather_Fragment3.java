package com.example.hoh.Weather;


import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoh.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class Weather_Fragment3 extends Fragment {
    NetworkImageView symbolView;
    TextView temperatureView;
    RecyclerView recyclerView;
    int[] max = new int[100];
    int[] min = new int[100];
    String[] save_symbol = new String[100];
    String[] save_time = new String[100];
    int max_result = 0;
    int min_result = 100;
    int max_count = 0;
    int min_count = 0;
    int symbol_count = 0;
    int time_count = 0;
    int time_check = 1;
    String save_max;
    String save_min;
    ImageRequest imageRequest;
    ImageRequest imageRequest1;
    Weather_Fragment3.MyAdapter adapter;
    ArrayList<ItemData> list;
    RequestQueue queue;
    int insert_check = 1;
    private String title;
    private int page;

    public static Weather_Fragment3 newInstance(int page, String title) {
        Weather_Fragment3 fragment = new Weather_Fragment3();
        Bundle args = new Bundle();
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.weather, container, false);

        TextView text = view.findViewById(R.id.Textview);
        text.setText(title);
        temperatureView = view.findViewById(R.id.weather_temperature);
        symbolView = view.findViewById(R.id.weather_symbol);
        recyclerView = view.findViewById(R.id.weather_recycler);

        list = new ArrayList<>();
        adapter = new Weather_Fragment3.MyAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Weather_Fragment3.MyItemDecoration());
        recyclerView.setAdapter(adapter);

        queue = Volley.newRequestQueue(this.getActivity());
        time_check = 1;
        StringRequest currentRequest = new StringRequest(Request.Method.POST, "https://api.openweathermap.org/data/2.5/weather?q=tangjin&mode=xml&units=metric&appid=2a0693060e6ad8a109af75afcb428f21", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseXMLCurrent(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        StringRequest forecastRequest = new StringRequest(Request.Method.POST, "https://api.openweathermap.org/data/2.5/forecast?q=tangjin&mode=xml&units=metric&appid=e959d55cc582f00055d5f278f469623b", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseXMLForecast(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(currentRequest);
        queue.add(forecastRequest);
        return view;
    }

    private class ItemData {
        public String max;
        public String min;
        public String day;
        public Bitmap image;
        public Bitmap image1;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dayView;
        public TextView maxView;
        public TextView minView;
        public ImageView imageView;
        public ImageView imageView1;

        public MyViewHolder(View itemView) {
            super(itemView);
            dayView = itemView.findViewById(R.id.weather_item_day);
            maxView = itemView.findViewById(R.id.weather_item_max);
            minView = itemView.findViewById(R.id.weather_item_min);
            imageView = itemView.findViewById(R.id.mission_item_image);
            imageView1 = itemView.findViewById(R.id.mission_item_image1);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<Weather_Fragment3.MyViewHolder> {
        private final List<Weather_Fragment3.ItemData> list;

        public MyAdapter(List<Weather_Fragment3.ItemData> list) {
            this.list = list;
        }

        @Override
        public Weather_Fragment3.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
            return new Weather_Fragment3.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Weather_Fragment3.MyViewHolder holder, int position) {
            Weather_Fragment3.ItemData vo = list.get(position);
            holder.dayView.setText(vo.day);
            holder.maxView.setText(vo.max);
            holder.minView.setText(vo.min);
            holder.imageView.setImageBitmap(vo.image);
            holder.imageView1.setImageBitmap(vo.image1);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(10, 10, 10, 10);
            view.setBackgroundColor(0x88929090);
        }
    }

    private void parseXMLCurrent(String response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();
            Element tempElement = (Element) (doc.getElementsByTagName("temperature").item(0));
            String temperature = tempElement.getAttribute("value");
            int temp = (int) Double.parseDouble(temperature);
            temperatureView.setText("" + temp);

            Element weatherElement = (Element) (doc.getElementsByTagName("weather").item(0));
            String symbol = weatherElement.getAttribute("icon");

            ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    return null;
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {

                }
            });
            symbolView.setImageUrl("https://openweathermap.org/img/wn/" + symbol + "@2x.png", imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseXMLForecast(String response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("time");
            for (int i = 0; i < nodeList.getLength(); i++) {
                final Weather_Fragment3.ItemData vo = new Weather_Fragment3.ItemData();
                Element timeNode = (Element) nodeList.item(i);
                Element symbolNode = (Element) timeNode.getElementsByTagName("symbol").item(0);
                String symbol = symbolNode.getAttribute("var");
                String temp = timeNode.getAttribute("from");
                String tempp = temp.substring(10, 19);
                if (tempp.equals("T00:00:00") && time_check == 1) {
                    save_time[time_count] = temp.substring(0, 10);
                    time_count++;
                    time_check++;
                } else if (i == nodeList.getLength() - 1) {
                    vo.day = save_time[time_count - 1];
                    insert_check = 0;
                    time_count = 0;
                } else if (!tempp.equals("T00:00:00")) {
                    save_time[time_count] = temp.substring(0, 10);
                    time_count++;
                    if (tempp.equals("T09:00:00")) {
                        save_symbol[symbol_count] = symbol;
                        symbol_count++;
                    } else if (tempp.equals("T15:00:00")) {
                        save_symbol[symbol_count] = symbol;
                        symbol_count++;
                    }
                } else {
                    vo.day = save_time[time_count - 1];
                    insert_check = 0;
                    time_count = 0;
                }
                Element temperatureNode = (Element) timeNode.getElementsByTagName("temperature").item(0);
                save_max = temperatureNode.getAttribute("max");
                if (insert_check == 1) {
                    max[max_count] = (int) Double.parseDouble(save_max);
                    max_count++;
                } else {
                    max[max_count] = (int) Double.parseDouble(save_max);
                    for (int a = 0; a < max.length; a++) {
                        if (max[a] >= max_result)
                            max_result = max[a];
                    }
                    vo.max = String.valueOf(max_result);
                    max_count = 0;
                    max_result = 0;
                }

                save_min = temperatureNode.getAttribute("min");
                if (insert_check == 1) {
                    min[min_count] = (int) Double.parseDouble(save_min);
                    min_count++;
                } else {
                    min[min_count] = (int) Double.parseDouble(save_min);
                    for (int a = 0; a < min_count; a++) {
                        if (min[a] <= min_result)
                            min_result = min[a];
                    }
                    vo.min = String.valueOf(min_result);
                    min_count = 0;
                    min_result = 100;
                    insert_check = 1;
                    String url = "https://openweathermap.org/img/wn/" + save_symbol[0] + "@2x.png";
                    imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            vo.image = response;
                            adapter.notifyDataSetChanged();
                        }
                    }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    url = "https://openweathermap.org/img/wn/" + save_symbol[1] + "@2x.png";
                    imageRequest1 = new ImageRequest(url, new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            vo.image1 = response;
                            adapter.notifyDataSetChanged();
                        }
                    }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(imageRequest);
                    queue.add(imageRequest1);
                    list.add(vo);
                    symbol_count = 0;
                }
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


