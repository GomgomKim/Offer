package org.androidtown.offerproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodAddFragment extends Fragment {
    Spinner powers, minutes, seconds; // 불세기, 분, 초
    Button add_cook, add_cancel, add_save; // 추가, 취소, 확인
    ListView add_list; // 리스트
    ArrayAdapter<String> add_food_adapter; // 리스트 어댑터


    public FoodAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_add, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        // 불세기선택
        powers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 분 선택
        minutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 초 선택
        seconds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 추가
        add_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 리스트뷰 추가
            }
        });

        // 취소버튼 클릭
        add_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.myframe, new TimerFragment())
                        .commit();
            }
        });

        //확인버튼 클릭
        add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SQLite 저장
            }
        });
    }

    //초기화
    public void init(){
        powers = (Spinner)getActivity().findViewById(R.id.powers);
        minutes = (Spinner)getActivity().findViewById(R.id.minutes);
        seconds = (Spinner)getActivity().findViewById(R.id.seconds);
        add_cook = (Button)getActivity().findViewById(R.id.add_cook);
        add_cancel = (Button)getActivity().findViewById(R.id.add_cancel);
        add_save = (Button)getActivity().findViewById(R.id.add_save);
        add_list = (ListView)getActivity().findViewById(R.id.add_list);
    }
}
