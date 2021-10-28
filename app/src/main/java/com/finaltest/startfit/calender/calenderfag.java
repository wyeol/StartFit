package com.finaltest.startfit.calender;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.finaltest.startfit.R;
import com.finaltest.startfit.calender.decorators.EventDecorator;
import com.finaltest.startfit.calender.decorators.OneDayDecorator;
import com.finaltest.startfit.calender.decorators.SaturdayDecorator;
import com.finaltest.startfit.calender.decorators.SundayDecorator;
import com.finaltest.startfit.calender.room.Memo;
import com.finaltest.startfit.calender.room.MemoDao;
import com.finaltest.startfit.calender.room.MemoDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class calenderfag extends Fragment {



    //캘린더 창에 사용할 변수 선언
    private ListView mListView;
    private SimpleAdapter mSAdapter;
    private ArrayList<HashMap<String, String>> mListData;
    private MemoDatabase mDatabase;
    private MemoDao mDao;
    private View view;
    private AdView mAdview; //애드뷰 변수 선언

    private int mISelectedItem = -1;
    private int mISelectedID = 0;
    private int mID = 0;

    public int a= 0;

    public Button timer_Btn, exercise_Btn;
    public String day;
    public MaterialCalendarView materialCalendarView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calender_fag, container, false);


        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        mAdview = view.findViewById(R.id.adView); //배너광고 레이아웃 가져오기
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
        AdView adView = new AdView(getActivity());
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("\n" + "ca-app-pub-3940256099942544/6300978111");

        timer_Btn = view.findViewById(R.id.timerbtn);
        exercise_Btn = view.findViewById(R.id.exericsebtn);
        materialCalendarView = view.findViewById(R.id.mcalendarView);

        //데이터베이스 선언
        mDatabase = MemoDatabase.getInstance(getActivity());
        mDao = mDatabase.memoDao();

        mListData = new ArrayList<>();
        mSAdapter = new SimpleAdapter(getActivity(), mListData, R.layout.list_item,
                new String[] {"exercise", "weight", "repeat", "number"}, new int[] {R.id.tv_exercise, R.id.tv_weight, R.id.tv_repeat, R.id.tv_number});
        mListView = view.findViewById(R.id.memolist);
        mListView.setAdapter(mSAdapter);

        materialCalendarView.setSelectedDate(CalendarDay.today());

        loadTable();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 클릭시 일어나는 이벤트
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mISelectedItem = position;
                HashMap <String,String> item = ((HashMap<String,String>)mSAdapter.getItem(position));
                mISelectedID = Integer.parseInt(item.get("id"));
                Toast.makeText(getActivity(), String.valueOf(mISelectedID), Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // 리스트를 길게 클릭할 시에 일어나나는 이벤트 (수정, 삭제)
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadTable();
                mISelectedItem = i;
                HashMap <String,String> item = ((HashMap<String,String>)mSAdapter.getItem(i));
                mISelectedID = Integer.parseInt(item.get("id"));

                PopupMenu popupMenu = new PopupMenu(getActivity(), view);
                MenuInflater menuInflater = getActivity().getMenuInflater();
                menuInflater.inflate(R.menu.calender_list_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem a_Item) {
                        switch (a_Item.getItemId()) {

                            case R.id.action_edit:

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                View aview = inflater.inflate(R.layout.edit_box, null, false);
                                builder.setView(aview);

                                final Button buttonSubmit = (Button) aview.findViewById(R.id.button_dialog_submit);
                                final EditText editTextExercise = (EditText) aview.findViewById(R.id.edittext_dialog_exercise);
                                final EditText editTextWeight = (EditText) aview.findViewById(R.id.edittext_dialog_weight);
                                final EditText editTextRepeat = (EditText) aview.findViewById(R.id.edittext_dialog_repeat);
                                final EditText editTextNumber = (EditText) aview.findViewById(R.id.edittext_dialog_number);
                                HashMap <String,String> Ehitem = ((HashMap<String,String>)mSAdapter.getItem(mISelectedItem));
                                editTextExercise.setText(Ehitem.get("exercise"));
                                editTextWeight.setText(Ehitem.get("weight"));
                                editTextRepeat.setText(Ehitem.get("repeat"));
                                editTextNumber.setText(Ehitem.get("number"));
                                String strDATE = Ehitem.get("date");
                                final AlertDialog dialog = builder.create();


                                //수정 버튼을 클릭했을 때 일어나는 이벤트
                                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String strEXERCISE = editTextExercise.getText().toString();
                                        String strWEIGHT = editTextWeight.getText().toString();
                                        String strREPEAT = editTextRepeat.getText().toString();
                                        String strNUMBER = editTextNumber.getText().toString();
                                        if(strEXERCISE.isEmpty() || strWEIGHT.isEmpty() || strREPEAT.isEmpty() || strNUMBER.isEmpty()){
                                            Toast.makeText(getActivity(), "내용을 빠짐없이 입력해주세요", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if(!strEXERCISE.isEmpty() || !strWEIGHT.isEmpty() || !strREPEAT.isEmpty() || !strNUMBER.isEmpty()){
                                            HashMap<String, String> hitem = new HashMap<>();
                                            hitem.put("id", String.valueOf(mISelectedID));
                                            hitem.put("exercise", strEXERCISE);
                                            hitem.put("date", strDATE);
                                            hitem.put("weight", strWEIGHT);
                                            hitem.put("repeat", strREPEAT);
                                            hitem.put("number", strNUMBER);
                                            mListData.set(i, hitem);

                                            Memo memo = new Memo();
                                            memo.setId(mISelectedID);
                                            memo.setExercise(strEXERCISE);
                                            memo.setWeight(strWEIGHT);
                                            memo.setDate(strDATE);
                                            memo.setRepeat(strREPEAT);
                                            memo.setNumber(strNUMBER);
                                            mDao.updateMemo(memo);
                                            mSAdapter.notifyDataSetChanged();
                                        }
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                                loadTable();
                                break;

                            case R.id.action_delete:
                                HashMap <String,String> Dhitem = ((HashMap<String,String>)mSAdapter.getItem(mISelectedItem));
                                Memo memo = new Memo();
                                memo.setId(Integer.parseInt(Dhitem.get("id")));
                                memo.setDate(Dhitem.get("date"));
                                memo.setExercise(Dhitem.get("exercise"));
                                memo.setWeight(Dhitem.get("weight"));
                                memo.setRepeat(Dhitem.get("repeat"));
                                memo.setNumber(Dhitem.get("number"));
                                mDao.deleteMemo(memo);

                                mListData.remove(mISelectedItem);
                                mSAdapter.notifyDataSetChanged();

                                mISelectedItem = -1;
                                mISelectedID = 0;

                                mListView.clearChoices();

                                if(mISelectedItem == -1) {
                                    Toast.makeText(getActivity(), "삭제완료", Toast.LENGTH_SHORT).show();
                                }
                                loadTable();
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
                return true;
            }
        });

        //캘린더 기능 선언
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2021, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        //캘린더의 날짜를 클릭했을 때 일어나는 이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                day = date.toString();

                loadTable();
                mISelectedItem = -1;
                mListView.clearChoices(); // 선택을 해제합니다.
            }
        });



        //캘린더 데코레이션 선언
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());

        //운동 추가 버튼을 클릭했을 때 일어나는 이벤트
        exercise_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(day == null){
                    Toast.makeText(getActivity(), "날짜를 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View aview = inflater.inflate(R.layout.edit_box, null, false);
                builder.setView(aview);

                final Button buttonSubmit = (Button) aview.findViewById(R.id.button_dialog_submit);
                final EditText editTextExercise = (EditText) aview.findViewById(R.id.edittext_dialog_exercise);
                final EditText editTextWeight = (EditText) aview.findViewById(R.id.edittext_dialog_weight);
                final EditText editTextRepeat = (EditText) aview.findViewById(R.id.edittext_dialog_repeat);
                final EditText editTextNumber = (EditText) aview.findViewById(R.id.edittext_dialog_number);

                buttonSubmit.setText("삽입");

                final AlertDialog dialog = builder.create();


                //삽입 버튼을 클릭했을 때 일어나는 이벤트
                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strEXERCISE = editTextExercise.getText().toString();
                        String strWEIGHT = editTextWeight.getText().toString();
                        String strREPEAT = editTextRepeat.getText().toString();
                        String strNUMBER = editTextNumber.getText().toString();
                        if(strEXERCISE.isEmpty() || strWEIGHT.isEmpty() || strREPEAT.isEmpty() || strNUMBER.isEmpty()){
                            Toast.makeText(getActivity(), "내용을 빠짐없이 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(!strEXERCISE.isEmpty() || !strWEIGHT.isEmpty() || !strREPEAT.isEmpty() || !strNUMBER.isEmpty()){
                            HashMap<String, String> hitem = new HashMap<>();
                            hitem.put("exercise", strEXERCISE);
                            hitem.put("weight", strWEIGHT);
                            hitem.put("date", day);
                            hitem.put("repeat", strREPEAT);
                            hitem.put("number", strNUMBER);
                            mListData.add(hitem);

                            Memo memo = new Memo();
                            memo.setDate(day);
                            memo.setExercise(strEXERCISE);
                            memo.setWeight(strWEIGHT);
                            memo.setRepeat(strREPEAT);
                            memo.setNumber(strNUMBER);
                            mDao.insertMemo(memo);

                            mSAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                        loadTable(); //운동을 추가하고 바로 운동 추가 클릭 시 발생하는 오류를 해결하기 위해 작성
                    }
                });
                dialog.show();
                mISelectedItem = -1;
            }
        });


        //타이머 클릭시 일어날 이벤트
        timer_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), timer2Activity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    //날짜별로 일정을 불러오는 메소드
    private void loadTable() {
        mListData.clear();
        List<Memo> list = mDao.getAllMemos();
        ArrayList<CalendarDay> dotlist = new ArrayList<>();

        for(Memo memo : list) {
            HashMap<String,String> hitem = new HashMap<>();
            if(day.equals(memo.getDate())) {
                int nID = memo.getId();
                mID=Math.max(mID,nID);
                hitem.put("id", String.valueOf(nID));
                hitem.put("date", memo.getDate());
                hitem.put("exercise", memo.getExercise());
                hitem.put("weight", memo.getWeight());
                hitem.put("repeat", memo.getRepeat());
                hitem.put("number", memo.getNumber());

                mListData.add(hitem);
            }
/*            if(!memo.getDate().isEmpty()) {
                String[] dots = memo.getDate().split("\\-");
                dotlist.add(CalendarDay.from(2021,9, 10));
            }*/
        }
        materialCalendarView.addDecorator(new EventDecorator(Color.RED, dotlist));
        mSAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildre(mListView);
    }



    //리스트의 크기 만큼 창을 늘려주는 메소드
    public static void setListViewHeightBasedOnChildre(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight;
        listView.setLayoutParams(params);

        listView.requestLayout();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mDatabase != null)
            mDatabase.close();
        /*materialCalendarView.clearSelection();
        day = null;*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mDatabase != null)
            mDatabase.close();
    }
}
