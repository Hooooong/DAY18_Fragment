package com.hooooong.fragmentbasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ListFragment extends Fragment {

    Button button;
    Callback callback = null;

    public ListFragment() {
        // Required empty public constructor
        // 여기는 코드를 작성하면 안된다.
    }

    @Override
    public void onAttach(Context context) {
        // Context 가 나를(ListFragment) 삽입한 Activity(MainActivity) 이다.
        // 1. 나를 삽입한 Activity 가 내가 제공한 인터페이스를 사용했는지 확인한다.
        if( context instanceof  Callback){
            // 2. 구현하였습면 인터페이스로 Casting 해서 사용
            callback = (Callback)context;
        }

        Log.d("Fragment", "===========================onAttach()");
        super.onAttach(context);
    }

    // 얘는 액티비티에 부착되면서 동작 시작
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        button = (Button)view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 내가 설계해놓은 인터페이스를 호출한다.
                // 나를 사용하는 측은 이 인터페이스를 강제로 구현해야 한다.
                callback.goDetail();
            }
        });

        // 프래그먼트 화면에 값을 세팅하는 로직은 이 사이에
        Log.d("Fragment", "===========================onCreateView()");
        return view;
    }

    public interface Callback{
        void goDetail();

    }

    @Override
    public void onStart() {
        Log.d("Fragment", "===========================onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Fragment", "===========================onResume()");
        super.onResume();
    }

    @Override
    public void onDetach() {
        Log.d("Fragment", "===========================onDetach()");
        super.onDetach();
    }
}
