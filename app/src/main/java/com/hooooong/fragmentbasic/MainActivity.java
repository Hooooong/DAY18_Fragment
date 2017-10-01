package com.hooooong.fragmentbasic;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ListFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Activity", "===========================onCreate()");

        initFragment();
    }

    public void initFragment(){
        // 1. FragmentManager 생성
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 2. 트랜잭션 처리자
        // Fragment 를 처리하기 위한 트렌잭션 실행
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        // 3. Activity Layout 에 Fragment 를 부착하고
        Log.d("Activity", "===========================Before Add()");
        fragmentTransaction.add(R.id.frameLayout, new ListFragment());
        // 내부적으로 ListFragment.onAttach(this); 가 호출된다.
        Log.d("Activity", "=========================== After Add()");
        // 4. Commit 한다.
        fragmentTransaction.commit();
        Log.d("Activity", "=========================== Before Commit()");
    }

    /**
     * Activity 에 부착된 Fragment 에 있는 버튼의 Event 처리는
     * Activity 에 만든 다음 XML 에 설정하는것이 좋다.
     */
    @Override
    public void goDetail(){
        // 3. Activity Layout 에 Fragment 를 부착하고
        Log.d("Activity", "===========================Before Add()");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, new DetailFragment())
                .addToBackStack(null) // 이 명령어를 호출하게 되면 트랜잭션 전체를 Stack 에 담는다.
                .commit();
        Log.d("Activity", "=========================== After Commit()");
    }

    @Override
    protected void onStart() {
        Log.d("Activity", "===========================onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Activity", "===========================onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Activity", "===========================onPause()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity", "===========================onDestroy()");
    }
}
