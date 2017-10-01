Android Programing
----------------------------------------------------
### 2017.09.27 12일차

#### 예제
____________________________________________________

- Fragment 를 사용한 화면 분할

#### 공부정리
____________________________________________________

##### __Fragment__

- Fragment 란?

  ![Fragment 예시](https://github.com/Hooooong/DAY18_Fragment/blob/master/image/fragments.png)

  > 태블릿과 같은 큰 화면에서 보다 역동적이고 유연한 UI 디자인을 지원하는 것을 목적으로 나온 Activity 의 하위 개념이다. 여러 개의 Fragment 를 하나의 Activity 에 조합하여 창이 여러개인 UI를 구축할 수 있으며, 하나의 Fragment 를 여러 Activity에서 재사용할 할 수 있다.<br>
  > Fragment 는 Activity 와 유사하게 생명주기를 자체적으로 가지고 있기 때문에 개발자가 세심하게 관리해야 하는 단점이 있다.

- Fragment 생명주기

  ![Fragment 생명주기](https://github.com/Hooooong/DAY18_Fragment/blob/master/image/fragment_lifecycle.png)

  메소드 | 설명
  :----: | :----:
  onAttach() | add() 한 후 Activity 가 onStart() 를 호출하면 onAttach() 가 호출된다. 이때 매개변수로 자신을 호출한 Activity의 Context를 받는다
  onCreate() | onAttach() 가 호출된 후 Fragment 를 생성할 때 호출된다. Activity 와는 다르게 onCreate()에서 View 에 대한 작업을 할 수 없다
  onCreateView() | Fragment 의 UI를 그릴 때 호출한다. View 에 대한 작업을 이 메소드 안에서 하고, 작업한 View를 반환한다
  onStart() | Fragment 가 사용자에게 보여지기 바로 직전에 호출된다
  onResume() | Activity의 onResume() 이 호출 된 후, Fragment 의 onResume() 이 호출된다. Fragment 가 사용자와 상호작용하기 바로 전에 호출된다
  onPause() | 다른 Fragment 가 보여질 때 호출된다
  onStop() | Fragment 가 더이상 사용자에게 보여지지 않을 때 호출된다
  onDestroyView() | View 리소스를 해제 할수 있도록 호출된다. backstack을 사용 했다면 Fragment를 다시 돌아 갈때 onCreateView()가 호출 된다
  onDestroy() | Fragment 를 완전히 종료 할 수 있도록 호출 한다
  onDetach() | Fragment 가 Activity와 연결이 완전히 끊기기 직전에 호출 된다

- Fragment 사용하기

  - Fragment 를 사용하는 방법은 `activity_main.xml` 에서 직접 사용하는 방법과 `activity.class` 에서 호출하는 방법이 있다.

  - `activity_main.xml` 에서는 `<Fragment>` 의 `<name>` 속성에 Framgnet 패키지 경로를 작성하면 된다.

  - `activity.class` 에서는 `FragmentManager` 를 통해 Fragment 를 처리한다.

  - 기본적으로 Fragment 를 상속받는 class 를 먼저 생성한 후 작성해야 한다.

  ```java
  public class ListFragment extends Fragment {

      public ListFragment() {
          // Required empty public constructor
      }

      // Fragment 의 경우 onCreate 에서 View 의 작업을 하면 안되고
      // onCreateView 에서 View 의 작업을 해줘야 한다.
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          return inflater.inflate(R.layout.fragment_list, container, false);
      }
  }
  ```

  1. xml에서 사용하기

  ```xml
  <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="horizontal">

      <!-- Fragment 를 xml에서 호출한다. -->
      <!-- name 에 Fragment의 .java 파일 경로를 작성한다. -->
      <fragment
          android:id="@+id/fragment"
          android:name="com.hooooong.fragmentbasic.ListFragment"
          android:layout_width="0dp"
          android:layout_height="match_parent"
          android:layout_weight="1" />
  </LinearLayout>
  ```

  2. activity.java 에서 호출하기

    ```java
    // 1. FragmentManager 생성
    FragmentManager fragmentManager = getSupportFragmentManager();
    // 2. 트랜잭션 처리자
    // Fragment 를 처리하기 위한 트렌잭션 실행
    FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
    // 3. Activity Layout 에 Fragment 를 부착하고
    fragmentTransaction.add(R.id.frameLayout, new ListFragment());
    // 내부적으로 ListFragment.onAttach(this); 가 호출된다.
    fragmentTransaction.addToBackStack(null);
    // 4. Commit 한다.
    fragmentTransaction.commit();

    // 좀 더 쉽게 표현하면
    getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.frameLayout, new ListFragment())
        .addToBackStack(null) // 이 명령어를 호출하게 되면 트랜잭션 전체를 Stack 에 담는다.
        .commit();
    ```

- Fragment 사용 시 주의점

  ![background](https://github.com/Hooooong/DAY18_Fragment/blob/master/image/background.gif)

  - `<background>` 속성 설정

    - Fragment 는 `<background>` 를 설정해줘야 한다. `getSupportFragmentManager().add()`를 하게 되면 동일한 View에 그려지기 때문에 텍스트나 이미지가 곂쳐보이게 된다.

    - 또는 `getSupportFragmentManager().replace()` 를 하여 Fragment 를 바꿔주면 해결할 수 있다.

  - `<clickable>` 속성 설정

    - Fragment 는 Activity 와 다르게 Click 시 밑에 쌓여있던 버튼들이 클릭되기 때문에 Clickable = true 로 해줘야 한다.

  ![addToBackStack](https://github.com/Hooooong/DAY18_Fragment/blob/master/image/addToBackStack.gif)

  - `addToBackStack(null)`

    - `getSupportFragmentManager().add()` 또는 `getSupportFragmentManager().replace()` 를 할 경우 `addToBackStack(null)` 속성을 추가하지 않으면 이전 Fragment 로 돌아갈 수 없다.

    - `addToBackStack(null)` 은 트랜잭션을 Fragment 트랜잭션의 백 스택에 추가할 수 있다. 이 백 스택을 액티비티가 관리하며, 이를 통해 사용자가 이전 Fragment 상태로 되돌아갈 수 있다.

- 참조 : [Fragment](https://developer.android.com/guide/components/fragments.html?hl=ko)
