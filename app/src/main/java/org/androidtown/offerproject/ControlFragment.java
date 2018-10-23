package org.androidtown.offerproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;


public class ControlFragment extends Fragment {

    int fire_power=0;

    public ControlFragment() {
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
        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SeekBar seekBar = (SeekBar)getActivity().findViewById(R.id.seekBar);
        int fireValue;
        final TextView showValue = (TextView)getActivity().findViewById(R.id.textView);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //드래그 하는 중에 발생
                fire_power = i;
                if(fire_power==1){
                    showValue.setText("현재 불세기: 약");
                }
                else if(fire_power==2){
                    showValue.setText("현재 불세기: 중");
                }
                else if(fire_power==3){
                    showValue.setText("현재 불세기: 강");
                }
                else if(fire_power==4){
                    showValue.setText("현재 불세기: MAX");
                }
                else if(fire_power==0){
                    showValue.setText("현재 불세기: OFF");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //최초에 탭하여 드래그 시작할때
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //드래그 멈출때 발생
                //실제 사용
                if(fire_power==1){
                    ((Bluetooth)getActivity()).sendData("1");
                }
                else if(fire_power==2){
                    ((Bluetooth)getActivity()).sendData("2");
                }
                else if(fire_power==3){
                    ((Bluetooth)getActivity()).sendData("3");
                }
                else if(fire_power==4){
                    ((Bluetooth)getActivity()).sendData("4");
                }
                else if(fire_power==0){
                    ((Bluetooth)getActivity()).sendData("0");
                }
                //동영상용
                /*if(fire_power==1){
                    Toast.makeText(getContext(), "불의세기 : 약 (sendData)", Toast.LENGTH_SHORT).show();
                }
                else if(fire_power==2){
                    Toast.makeText(getContext(), "불의세기 : 중 (sendData)", Toast.LENGTH_SHORT).show();
                }
                else if(fire_power==3){
                    Toast.makeText(getContext(), "불의세기 : 강 (sendData)", Toast.LENGTH_SHORT).show();
                }
                else if(fire_power==4){
                    Toast.makeText(getContext(), "불의세기 : MAX (sendData)", Toast.LENGTH_SHORT).show();
                }
                else if(fire_power==0) {
                    Toast.makeText(getContext(), "불의세기 : OFF (sendData)", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}
