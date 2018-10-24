package org.androidtown.offerproject;

import android.widget.ArrayAdapter;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.offerproject.R;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTimeFragment extends Fragment {
    ArrayAdapter<String> arrayAdapter, foodAdapter;
    ListView listview_m, listview_s, food_list;
    ArrayList<String> clock_num, food_list_array;
    TextView textview_m, textview_s;
    TextView upper_text_m, upper_Text_s;
    String minute="";
    String second="";
    int minutenum=0;
    int secondnum=0;
    int minuteanswer=0; //설정한 시간 저장
    int secondanswer=0;
    int answersum=0;
    TimerTask tt;
    Timer timer;
    boolean startcheck=false;
    Button start, clear;
    OutputStream mOutputStream = null;
    String mStrDelimiter = "\n";


    public SelectTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_time, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        set_timer();
        //    tt=timerTaskMaker();
        timer=new Timer();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(startcheck==false){
                    startcheck=true;
                    minute = textview_m.getText().toString();
                    second = textview_s.getText().toString();
                    minutenum=Integer.parseInt(minute);
                    secondnum=Integer.parseInt(second);
                    minuteanswer=Integer.parseInt(minute);
                    secondanswer=Integer.parseInt(second);
                    if((minutenum==0 && secondnum==0) ||(minutenum<=0 && secondnum<=0)){
                        Toast.makeText(getContext(), "먼저, 분과 초를 선택해 주세요 !", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //      tt=timerTaskMaker();
                        //     timer.schedule(tt,1000,1000);
//                System.out.println(minuteanswer);
//                System.out.println(secondanswer);

                    }
                }

                minuteanswer=minuteanswer*60;
                answersum=(minuteanswer + secondanswer)*1000;

                new CountDownTimer(answersum,1000){
                    @Override
                    public void onTick(long l) {


                    }


                    @Override
                    public void onFinish() {
                        //타이머 다 돌았을때 => 전원 꺼짐
                        //Toast.makeText(getContext(), "전원 OFF (sendData)", Toast.LENGTH_SHORT).show();
                        //  ((Bluetooth)getActivity()).sendData("0");
                    }
                }.start();


            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startcheck=false;
                minutenum = 0;
                secondnum = 0;
                textview_m.setText("0" + minutenum);
                textview_s.setText("0" + secondnum);
                tt.cancel();
                startcheck=false;
            }
        });

    }


    public void init(){
        listview_m = (ListView)getActivity().findViewById(R.id.list_minute);
        listview_s = (ListView)getActivity().findViewById(R.id.list_second);
        textview_m = (TextView)getActivity().findViewById(R.id.text_minute);
        textview_s = (TextView)getActivity().findViewById(R.id.text_second);

        start = (Button) getActivity().findViewById(R.id.start);
        clear = (Button) getActivity().findViewById(R.id.clear);
        upper_text_m = (TextView)getActivity().findViewById(R.id.textView__upper_m);
        upper_Text_s = (TextView)getActivity().findViewById(R.id.textView__upper_s);

        Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"fonts/210 앱굴림R.ttf");
        textview_s.setTypeface(typeface);
        textview_m.setTypeface(typeface);
        upper_text_m.setTypeface(typeface);
        upper_Text_s.setTypeface(typeface);

    }

    public void set_timer(){
        clock_num = new ArrayList<>();
        for(int i=0; i<=60; i++){
            String num_str = String.valueOf(i);
            clock_num.add(num_str);
        }

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, clock_num){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                Typeface typeface= Typeface.createFromAsset(getActivity().getAssets(),"fonts/210 앱굴림R.ttf");
                tv.setTypeface(typeface);
                return view;

            }
        };
        listview_m.setAdapter(arrayAdapter);
        listview_s.setAdapter(arrayAdapter);
        listview_m.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String minute = adapterView.getAdapter().getItem(i).toString();
                if(minute.equals("0")||minute.equals("1")||minute.equals("2")||minute.equals("3")||minute.equals("4")||minute.equals("5")||
                        minute.equals("6")||minute.equals("7")||minute.equals("8")||minute.equals("9")){
                    textview_m.setText("0"+minute);
                }
                else
                    textview_m.setText(minute);
            }
        });
        listview_s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String second = adapterView.getAdapter().getItem(i).toString();
                if(second.equals("0")||second.equals("1")||second.equals("2")||second.equals("3")||second.equals("4")||second.equals("5")||
                        second.equals("6")||second.equals("7")||second.equals("8")||second.equals("9")){
                    textview_s.setText("0"+second);
                }
                else
                    textview_s.setText(second);
            }
        });
    }


}