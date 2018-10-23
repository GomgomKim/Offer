package org.androidtown.offerproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.OutputStream;


public class TurnOffFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    ImageButton btn;
    OutputStream mOutputStream = null;
    String mStrDelimiter = "\n";


    public TurnOffFragment() {
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
        return inflater.inflate(R.layout.fragment_turn_off, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn = (ImageButton)getActivity().findViewById(R.id.turnon);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setImageResource(R.drawable.switchoff);
                //실제 사용할것
                ((Bluetooth)getActivity()).sendData("0");

                //앱 시연용
                //Toast.makeText(getContext(), "불이꺼짐 (sendData)", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
