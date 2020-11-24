package com.example.hoh.Map;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hoh.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_Fragment4 extends Fragment implements OnMapReadyCallback {
    View viewGroup;
    MapView mapView ;
    private GoogleMap map;
    Spinner mapItem;
    private static final String[] campus = new String[]{"아산 캠퍼스","천안 캠퍼스","당진 캠퍼스"};
    LatLng ASAN = new LatLng(36.736112,  127.074359);
    LatLng CHEONAN = new LatLng(36.828170,  127.183375);
    LatLng Dangjin = new LatLng(37.002946, 126.584241);

    public Map_Fragment4()
    {
// required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        viewGroup =  inflater.inflate(R.layout.map_fragment4, container, false);
        mapView =viewGroup.findViewById(R.id.map);
        mapItem =viewGroup.findViewById(R.id.mapitems);
        ArrayAdapter<String> mapList = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,campus);
        mapList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapItem.setAdapter(mapList);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return viewGroup;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        mapItem.setSelection(0);
        map = googleMap;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                37.003238, 126.584230), 18));
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMinZoomPreference(15);
        map.moveCamera(CameraUpdateFactory.newLatLng(ASAN));
        MarkerOptions markerOptions = new MarkerOptions();


        LatLng ASAN01 = new LatLng(36.738101, 127.075249 );
        markerOptions.position(ASAN01).title("아산캠퍼스");
        markerOptions.title("대학교회");
        map.addMarker(markerOptions);

        LatLng ASAN02 = new LatLng(36.737791, 127.074551 );
        markerOptions.position(ASAN02).title("아산캠퍼스");
        markerOptions.title("대학본부");
        map.addMarker(markerOptions);

        LatLng ASAN03 = new LatLng(36.739481, 127.074187 );
        markerOptions.position(ASAN03).title("아산캠퍼스");
        markerOptions.title("학생회관");
        map.addMarker(markerOptions);

        LatLng ASAN04 = new LatLng(36.739769, 127.074220 );
        markerOptions.position(ASAN04).title("아산캠퍼스");
        markerOptions.title("학술지원동");
        map.addMarker(markerOptions);

        LatLng ASAN05 = new LatLng(36.739941, 127.073889 );
        markerOptions.position(ASAN05).title("아산캠퍼스");
        markerOptions.title("가공학및발효실험동");
        map.addMarker(markerOptions);

        LatLng ASAN06 = new LatLng(36.739414, 127.073471 );
        markerOptions.position(ASAN06).title("아산캠퍼스");
        markerOptions.title("소프트볼장");
        map.addMarker(markerOptions);

        LatLng ASAN07 = new LatLng(36.738522, 127.073787 );
        markerOptions.position(ASAN07).title("아산캠퍼스");
        markerOptions.title("운동장");
        map.addMarker(markerOptions);

        LatLng ASAN08 = new LatLng(36.737979, 127.072484 );
        markerOptions.position(ASAN08).title("아산캠퍼스");
        markerOptions.title("학군단");
        map.addMarker(markerOptions);

        LatLng ASAN09 = new LatLng(36.738040, 127.072205 );
        markerOptions.position(ASAN09).title("아산캠퍼스");
        markerOptions.title("자동차공학전공실습장");
        map.addMarker(markerOptions);

        LatLng ASAN10 = new LatLng(36.737803, 127.071599 );
        markerOptions.position(ASAN10).title("아산캠퍼스");
        markerOptions.title("테니스장");
        map.addMarker(markerOptions);

        LatLng ASAN11 = new LatLng(36.737631, 127.072517 );
        markerOptions.position(ASAN11).title("아산캠퍼스");
        markerOptions.title("체육관");
        map.addMarker(markerOptions);

        LatLng ASAN12 = new LatLng(36.737128, 127.072522 );
        markerOptions.position(ASAN12).title("아산캠퍼스");
        markerOptions.title("산업안전동");
        map.addMarker(markerOptions);

        LatLng ASAN13 = new LatLng(36.736595, 127.072340 );
        markerOptions.position(ASAN13).title("아산캠퍼스");
        markerOptions.title("자연과학관");
        map.addMarker(markerOptions);

        LatLng ASAN14 = new LatLng(36.735490, 127.070569 );
        markerOptions.position(ASAN14).title("아산캠퍼스");
        markerOptions.title("학생창업보육센터");
        map.addMarker(markerOptions);

        LatLng ASAN15 = new LatLng(36.735331, 127.070237 );
        markerOptions.position(ASAN15).title("아산캠퍼스");
        markerOptions.title("건축학부실험동");
        map.addMarker(markerOptions);

        LatLng ASAN16 = new LatLng(36.735229, 127.072258 );
        markerOptions.position(ASAN16).title("아산캠퍼스");
        markerOptions.title("골프전공 실습장");
        map.addMarker(markerOptions);

        LatLng ASAN17 = new LatLng(36.735556, 127.072864 );
        markerOptions.position(ASAN17).title("아산캠퍼스");
        markerOptions.title("교직원회관");
        map.addMarker(markerOptions);

        LatLng ASAN18 = new LatLng(36.736248, 127.073030 );
        markerOptions.position(ASAN18).title("아산캠퍼스");
        markerOptions.title("제2공학관");
        map.addMarker(markerOptions);

        LatLng ASAN19 = new LatLng(36.736412, 127.074393 );
        markerOptions.position(ASAN19).title("아산캠퍼스");
        markerOptions.title("제1공학관");
        map.addMarker(markerOptions);

        LatLng ASAN20 = new LatLng(36.735556, 127.073599 );
        markerOptions.position(ASAN20).title("아산캠퍼스");
        markerOptions.title("지역혁신센터(RIC)");
        map.addMarker(markerOptions);

        LatLng ASAN21 = new LatLng(36.735075, 127.074248 );
        markerOptions.position(ASAN21).title("아산캠퍼스");
        markerOptions.title("조형과학관");
        map.addMarker(markerOptions);

        LatLng ASAN22 = new LatLng(36.735784, 127.074800 );
        markerOptions.position(ASAN22).title("아산캠퍼스");
        markerOptions.title("강석규교육관");
        map.addMarker(markerOptions);

        LatLng ASAN23 = new LatLng(36.735032, 127.075246 );
        markerOptions.position(ASAN23).title("아산캠퍼스");
        markerOptions.title("예술관");
        map.addMarker(markerOptions);

        LatLng ASAN24 = new LatLng(36.735848, 127.075659 );
        markerOptions.position(ASAN24).title("아산캠퍼스");
        markerOptions.title("산학협동 1호관");
        map.addMarker(markerOptions);

        LatLng ASAN25 = new LatLng(36.735939, 127.076329 );
        markerOptions.position(ASAN25).title("아산캠퍼스");
        markerOptions.title("산학협동 2호관");
        map.addMarker(markerOptions);

        LatLng ASAN26 = new LatLng(36.736339, 127.076308 );
        markerOptions.position(ASAN26).title("아산캠퍼스");
        markerOptions.title("학생식당");
        map.addMarker(markerOptions);

        LatLng ASAN27 = new LatLng(36.730748, 127.074944 );
        markerOptions.position(ASAN27).title("아산캠퍼스");
        markerOptions.title("안정성평가센터(GLP)");
        map.addMarker(markerOptions);

        LatLng ASAN28 = new LatLng(36.732244, 127.076082 );
        markerOptions.position(ASAN28).title("아산캠퍼스");
        markerOptions.title("호서벤처밸리");
        map.addMarker(markerOptions);

        LatLng ASAN29 = new LatLng(36.731763, 127.077144 );
        markerOptions.position(ASAN29).title("아산캠퍼스");
        markerOptions.title("후생관");
        map.addMarker(markerOptions);

        LatLng ASAN30 = new LatLng(36.730232, 127.078120 );
        markerOptions.position(ASAN30).title("아산캠퍼스");
        markerOptions.title("생활관 G동");
        map.addMarker(markerOptions);

        LatLng ASAN31 = new LatLng(36.730688,  127.077873);
        markerOptions.position(ASAN31).title("아산캠퍼스");
        markerOptions.title("생활관 F동");
        map.addMarker(markerOptions);

        LatLng ASAN32 = new LatLng(36.731186, 127.077637 );
        markerOptions.position(ASAN32).title("아산캠퍼스");
        markerOptions.title("생활관 E동");
        map.addMarker(markerOptions);

        LatLng ASAN33 = new LatLng(36.731616, 127.077616 );
        markerOptions.position(ASAN33).title("아산캠퍼스");
        markerOptions.title("생활관 D동");
        map.addMarker(markerOptions);

        LatLng ASAN34 = new LatLng(36.732347, 127.077670 );
        markerOptions.position(ASAN34).title("아산캠퍼스");
        markerOptions.title("생활관 C동");
        map.addMarker(markerOptions);

        LatLng ASAN35 = new LatLng(36.733663, 127.077160 );
        markerOptions.position(ASAN35).title("아산캠퍼스");
        markerOptions.title("외국인교수 사택");
        map.addMarker(markerOptions);

        LatLng ASAN36 = new LatLng(36.734437, 127.077128 );
        markerOptions.position(ASAN36).title("아산캠퍼스");
        markerOptions.title("생활관 B동");
        map.addMarker(markerOptions);

        LatLng ASAN37 = new LatLng(36.734833, 127.077074 );
        markerOptions.position(ASAN37).title("아산캠퍼스");
        markerOptions.title("생활관 A동");
        map.addMarker(markerOptions);

        LatLng ASAN38 = new LatLng(36.735245, 127.077080 );
        markerOptions.position(ASAN38).title("아산캠퍼스");
        markerOptions.title("학생벤처창업관");
        map.addMarker(markerOptions);

        LatLng ASAN39 = new LatLng(36.735597, 127.077241 );
        markerOptions.position(ASAN39).title("아산캠퍼스");
        markerOptions.title("벤처창업기업관");
        map.addMarker(markerOptions);

        LatLng ASAN40 = new LatLng(36.736122, 127.077338 );
        markerOptions.position(ASAN40).title("아산캠퍼스");
        markerOptions.title("벤처창조융합관");
        map.addMarker(markerOptions);

        LatLng ASAN41 = new LatLng(36.736956, 127.078003 );
        markerOptions.position(ASAN41).title("아산캠퍼스");
        markerOptions.title("벤처산학협력관");
        map.addMarker(markerOptions);

        LatLng ASAN42 = new LatLng(36.737566, 127.078046 );
        markerOptions.position(ASAN42).title("아산캠퍼스");
        markerOptions.title("교육문화관");
        map.addMarker(markerOptions);

        LatLng ASAN43 = new LatLng(36.737265, 127.076308 );
        markerOptions.position(ASAN43).title("아산캠퍼스");
        markerOptions.title("학술정보관");
        map.addMarker(markerOptions);




        mapItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(ASAN, 18));
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        map.getUiSettings().setZoomControlsEnabled(true);
                        map.setMinZoomPreference(15);
                        map.moveCamera(CameraUpdateFactory.newLatLng(ASAN));

                        break;
                    case 1:
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CHEONAN, 18));
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        map.getUiSettings().setZoomControlsEnabled(true);
                        MarkerOptions markerOptions1 = new MarkerOptions();
                        map.setMinZoomPreference(15);
                        map.moveCamera(CameraUpdateFactory.newLatLng(CHEONAN));

                        LatLng CHEONAN2 = new LatLng(36.831007,  127.180308);
                        markerOptions1.position(CHEONAN2).title("천안캠퍼스");
                        markerOptions1.title("부속유치원");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN3 = new LatLng(36.830816,  127.180696);
                        markerOptions1.position(CHEONAN3).title("천안캠퍼스");
                        markerOptions1.title("생환관A");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN4 = new LatLng(36.830533,  127.181350);
                        markerOptions1.position(CHEONAN4).title("천안캠퍼스");
                        markerOptions1.title("생활관B");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN5 = new LatLng(36.830469,  127.180733);
                        markerOptions1.position(CHEONAN5).title("천안캠퍼스");
                        markerOptions1.title("교회부속동");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN6 = new LatLng(36.830275,  127.180717);
                        markerOptions1.position(CHEONAN6).title("천안캠퍼스");
                        markerOptions1.title("부설어린이집");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN7 = new LatLng(36.830155,  127.181624);
                        markerOptions1.position(CHEONAN7).title("천안캠퍼스");
                        markerOptions1.title("대학교회");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN8 = new LatLng(36.829142,  127.180487);
                        markerOptions1.position(CHEONAN8).title("천안캠퍼스");
                        markerOptions1.title("운동장");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN9 = new LatLng(36.828988,  127.181582);
                        markerOptions1.position(CHEONAN9).title("천안캠퍼스");
                        markerOptions1.title("복지회관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN10 = new LatLng(36.828721,  127.182061);
                        markerOptions1.position(CHEONAN10).title("천안캠퍼스");
                        markerOptions1.title("복지교육관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN11 = new LatLng(36.827999,  127.183617);
                        markerOptions1.position(CHEONAN11).title("천안캠퍼스");
                        markerOptions1.title("종합정보관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN12 = new LatLng(36.827362,  127.183488);
                        markerOptions1.position(CHEONAN12).title("천안캠퍼스");
                        markerOptions1.title("1호관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN13 = new LatLng(36.826760,  127.183314);
                        markerOptions1.position(CHEONAN13).title("천안캠퍼스");
                        markerOptions1.title("2호관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN14 = new LatLng(36.826229,  127.183833);
                        markerOptions1.position(CHEONAN14).title("천안캠퍼스");
                        markerOptions1.title("3호관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN15 = new LatLng(36.827049,  127.184217);
                        markerOptions1.position(CHEONAN15).title("천안캠퍼스");
                        markerOptions1.title("음악관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN16 = new LatLng(36.826871,  127.182541);
                        markerOptions1.position(CHEONAN16).title("천안캠퍼스");
                        markerOptions1.title("본관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN17 = new LatLng(36.826185,  127.182766);
                        markerOptions1.position(CHEONAN17).title("천안캠퍼스");
                        markerOptions1.title("대강당");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN18 = new LatLng(36.825927,  127.182292);
                        markerOptions1.position(CHEONAN18).title("천안캠퍼스");
                        markerOptions1.title("체육관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN19 = new LatLng(36.825504,  127.182689);
                        markerOptions1.position(CHEONAN19).title("천안캠퍼스");
                        markerOptions1.title("학생회관");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN20 = new LatLng(36.829242,  127.182403);
                        markerOptions1.position(CHEONAN20).title("천안캠퍼스");
                        markerOptions1.title("교훈탑");
                        map.addMarker(markerOptions1);

                        LatLng CHEONAN21 = new LatLng(36.827988,  127.182387);
                        markerOptions1.position(CHEONAN21).title("천안캠퍼스");
                        markerOptions1.title("벤처교육관");
                        map.addMarker(markerOptions1);

                        break;
                    case 2:
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                                37.002946, 126.584241), 18));
                        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        map.getUiSettings().setZoomControlsEnabled(true);
                        map.setMinZoomPreference(15);
                        map.moveCamera(CameraUpdateFactory.newLatLng(Dangjin));
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                map = googleMap;
            }
        });
    }
}