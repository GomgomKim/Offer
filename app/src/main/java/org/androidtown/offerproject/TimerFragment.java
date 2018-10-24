package org.androidtown.offerproject;

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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class TimerFragment extends Fragment {

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
    long timeleft;
    CountDownTimer countDown;



    public TimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        set_timer();
        tt=timerTaskMaker();
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
                        tt=timerTaskMaker();
                        timer.schedule(tt,1000,1000);
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
                        ((Bluetooth)getActivity()).sendData("0");
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

        food_listview();

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

        food_list = (ListView)getActivity().findViewById(R.id.food_list);
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

    public TimerTask timerTaskMaker(){
        TimerTask tempTask=new TimerTask(){
            @Override
            public void run(){
                if (secondnum == 0 && minutenum!=0) {
                    secondnum=59;
                    minutenum--;
                }
                else
                    secondnum--;
                if(minutenum==0 && secondnum==0){
                    tt.cancel();
                    startcheck=false;
                }
                if (secondnum < 10) {
                    textview_s.setText("0" + secondnum);
                } else
                    textview_s.setText("" + secondnum);
                if (minutenum < 10) {
                    textview_m.setText("0" + minutenum);
                } else
                    textview_m.setText("" + minutenum);
            }

        };
        return tempTask;
    }

    public void food_listview(){
        food_list_array = new ArrayList<>();
        food_list_array.add("달걀 반숙");
        food_list_array.add("달걀 완숙");
        food_list_array.add("고구마");
        food_list_array.add("감자");
        food_list_array.add("찐만두");
        food_list_array.add("물만두");
        foodAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, food_list_array){
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
        food_list.setAdapter(foodAdapter);
        food_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        /* 계란반숙)
                        1. 처음에 강불에서 5분
                        2. 물이 끓으면 중불로 줄이기
                        3. 중불에서 7분 삶기
                        */
                        over_easy();
                        break;
                    case 1:
                        /*계란완숙)
                        1. 처음에 강불에서 5분
                        2. 물이 끓으면 중불로 줄이기
                        3. 중불에서 11분 삶기
                        */
                        over_hard();
                        break;
                    case 2:
                        /*
                        감자)
                        1. 강불로 5분
                        2.  아주 약한불로 20분
                        3. 8분은 끈채로 그대로 두기
                        */
                        Toast.makeText(getContext(), "제작 중입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getContext(), "제작 중입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getContext(), "제작 중입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getContext(), "제작 중입니다.", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //계란 반숙
    public void over_easy(){
        //맨처음 강불로 시작
        //((Bluetooth)getActivity()).sendData("4");
        Toast.makeText(getContext(), "불 세기 MAX로 전환합니다.", Toast.LENGTH_SHORT).show();

        //12분동안 요리
        textview_m.setText("12");
        textview_s.setText("00");
        if(startcheck==false){
            startcheck=true;
            minute = textview_m.getText().toString();
            second = textview_s.getText().toString();
            minutenum=Integer.parseInt(minute);
            secondnum=Integer.parseInt(second);
            minuteanswer=Integer.parseInt(minute);
            secondanswer=Integer.parseInt(second);
            tt=timerTaskMaker();
            timer.schedule(tt,1000,1000);
        }
        minuteanswer=minuteanswer*60;
        answersum=(minuteanswer + secondanswer)*1000;
        countDown = new CountDownTimer(answersum,1000){
            @Override

            public void onTick(long remain_time_millisec) {
                timeleft = (remain_time_millisec/1000) + 1; // 남은시간(초)
                //Toast.makeText(getContext(), String.valueOf(remain_time_millisec), Toast.LENGTH_SHORT).show();
                if(timeleft == 420) // 타이머 7분남았을때
                    //((Bluetooth)getActivity()).sendData("2");
                    Toast.makeText(getContext(), "중불로 전환합니다.", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFinish() {
                //타이머 다 돌았을때 => 전원 꺼짐
                Toast.makeText(getContext(), "요리 완료. 불이 꺼집니다.", Toast.LENGTH_SHORT).show();
                //((Bluetooth)getActivity()).sendData("0");
            }
        }.start();
    }

    //계란 완숙
    public void over_hard(){
        //맨처음 강불로 시작
        //((Bluetooth)getActivity()).sendData("4");
        Toast.makeText(getContext(), "불 세기 MAX로 전환합니다.", Toast.LENGTH_SHORT).show();

        //16분동안 요리
        textview_m.setText("16");
        textview_s.setText("00");
        if(startcheck==false){
            startcheck=true;
            minute = textview_m.getText().toString();
            second = textview_s.getText().toString();
            minutenum=Integer.parseInt(minute);
            secondnum=Integer.parseInt(second);
            minuteanswer=Integer.parseInt(minute);
            secondanswer=Integer.parseInt(second);
            tt=timerTaskMaker();
            timer.schedule(tt,1000,1000);
        }
        minuteanswer=minuteanswer*60;
        answersum=(minuteanswer + secondanswer)*1000;
        countDown = new CountDownTimer(answersum,1000){
            @Override

            public void onTick(long remain_time_millisec) {
                timeleft = (remain_time_millisec/1000) + 1; //남은시간(초)
                //Toast.makeText(getContext(), String.valueOf(remain_time_millisec), Toast.LENGTH_SHORT).show();
                if(timeleft == 720) //타이머 12분 남았을때
                    //((Bluetooth)getActivity()).sendData("2");
                    Toast.makeText(getContext(), "중불로 전환합니다.", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFinish() {
                //타이머 다 돌았을때 => 전원 꺼짐
                Toast.makeText(getContext(), "요리 완료. 불이 꺼집니다.", Toast.LENGTH_SHORT).show();
                //((Bluetooth)getActivity()).sendData("0");
            }
        }.start();
    }






}
