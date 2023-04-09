/* **********************************************
 * 프로젝트명 :  CalendarProject
 * 작성자 : 2017015041 조정동
 * 작성일 : 2023.04.09
 *프로그램 설명 :  날짜/시간 프로그램
 ************************************************/

package com.example.example6_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono; // 스톱워치 객체
    Button btnStart, btnEnd; // 시작, 종료 버튼
    RadioButton rdoCal, rdoTime; // 라디오버튼(달력, 시간)
    CalendarView calView; // 달력 뷰
    TimePicker tPicker; // 시간 선택 뷰
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute; // 선택한 년월일 시분을 표시할 텍스트뷰
    int selectYear, selectMonth, selectDay; // 선택한 년월일 정보를 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        // 각 뷰들을 레이아웃과 연결
        btnStart = (Button) findViewById(R.id.btnStart);
        btnEnd = (Button) findViewById(R.id.btnEnd);
        chrono = (Chronometer) findViewById(R.id.chronometer1);
        rdoCal = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        tPicker = (TimePicker) findViewById(R.id.timePicker1);
        calView = (CalendarView) findViewById(R.id.calendarView1);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvMonth = (TextView) findViewById(R.id.tvMonth);
        tvDay = (TextView) findViewById(R.id.tvDay);
        tvHour = (TextView) findViewById(R.id.tvHour);
        tvMinute = (TextView) findViewById(R.id.tvMinute);

        // 초기화
        tPicker.setVisibility(View.INVISIBLE); // 시간 선택 뷰를 보이지 않게 함
        calView.setVisibility(View.INVISIBLE); // 달력 뷰를 보이지 않게 함

        // 라디오 버튼에 대한 클릭 이벤트 처리
        rdoCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE); // 시간 선택 뷰를 보이지 않게 함
                calView.setVisibility(View.VISIBLE); // 달력 뷰를 보이게 함
            }
        });

        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE); // 시간 선택 뷰를 보이게 함
                calView.setVisibility(View.INVISIBLE); // 달력 뷰를 보이지 않게 함
            }
        });

        // 시작 버튼에 대한 클릭 이벤트 처리
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime()); // 스톱워치 초기화
                chrono.start(); // 스톱워치 시작
                chrono.setTextColor(Color.RED); // 스톱워치 색깔을 빨간색으로 변경
            }
        });

        // 'btnEnd' 버튼 클릭 이벤트 처리
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 크로노미터 정지
                chrono.stop();
                // 글자 색상을 파란색으로 변경
                chrono.setTextColor(Color.BLUE);

                // 선택한 년, 월, 일을 각각의 TextView에 표시
                tvYear.setText(Integer.toString(selectYear));
                tvMonth.setText(Integer.toString(selectMonth));
                tvDay.setText(Integer.toString(selectDay));

                // TimePicker에서 선택한 시간과 분을 각각의 TextView에 표시
                tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));
            }

        });

        // 'calView' 캘린더뷰의 날짜 변경 이벤트 처리
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 선택한 날짜 정보를 변수에 저장
                selectYear = year;
                selectMonth = month + 1;
                selectDay = dayOfMonth;
            }
        });
    }
}