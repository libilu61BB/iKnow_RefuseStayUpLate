package com.example.ikonw2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import android.text.Editable;
import android.text.TextWatcher;
import org.json.JSONObject;
import android.util.Log;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.*;

public class CreatActivity extends AppCompatActivity {
    private EditText nameText,starthourText,startminText,endhourText,endminText,addressText,sponserText,urlText,pictureText,introductionText;
    private DatePicker datePicker;
    private Button publishBtn;
    private ImageButton backBtn;
    private Boolean[] flag={true,true,true,true,true,true,true,true,true,true,true,true}; //用于记录是否所有的事件都通过检查
    private int year;
    private int month;
    private int day;
    public class Event{
        String eventName;
        int starthour;
        int startmin;
        int endhour;
        int endmin;
        int year;
        int month;
        int day;
        String address;
        String sponser;
        String url;
        String introduction;
        //定义各种set函数
        public void setEventName(String name){
            eventName=name;
        }
        public void setStarthour(int time1){
            starthour=time1;
        }
        public void setStartmin(int time2){
            startmin=time2;
        }
        public void setEndhour(int time3){
            endhour=time3;
        }
        public void setEndmin(int time4){
            endmin=time4;
        }
        public void setAddress(String add){
            address=add;
        }
        public void setSponser(String spon){
            sponser=spon;
        }
        public void setUrl(String urll){
            url=urll;
        }
        public void setIntroduction(String intro){
            introduction=intro;
        }
        public void setDate(int yearset,int monthset,int dayset){year=yearset;month=monthset;day=dayset;}
    }  //Event类的定义
    Event event=new Event();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createvents);
        publishBtn=(Button) findViewById(R.id.submitAll);
        backBtn=(ImageButton)findViewById(R.id.baccktoSetting);
        nameText = (EditText) findViewById(R.id.inputName);
        datePicker=(DatePicker) findViewById(R.id.inputeDate);
        starthourText=(EditText) findViewById(R.id.inputStartHour);
        startminText=(EditText)findViewById(R.id.inputStartMin);
        endhourText=(EditText)findViewById(R.id.inputEndHour);
        endminText=(EditText)findViewById(R.id.inputEndMin);
        addressText=(EditText)findViewById(R.id.inputAddress);
        sponserText=(EditText)findViewById(R.id.inputPeople);
        urlText=(EditText)findViewById(R.id.inputUrl);
        //设置监听器，如果输入内容有改动，就更新其内容；但是不能针对“什么都没输入就点击按钮”的情况，因为文本框内容没有变动
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String name=nameText.getText().toString(); //设置listener
                checkName(name);
            }
        });
        starthourText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String starthour=starthourText.getText().toString();
                checkStartHour(starthour);
            }
        });
        startminText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String startmin=startminText.getText().toString();
                checkStartMin(startmin);
            }
        });
        endhourText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String endhour=endhourText.getText().toString();
                checkEndHour(endhour);
            }
        });
        endminText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String endmin=endminText.getText().toString();
                checkEndMin(endmin);
            }
        });
        addressText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String address=addressText.getText().toString(); //设置listener
                checkAddress(address);
            }
        });
        sponserText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String sponser=sponserText.getText().toString(); //设置listener
                checkSponser(sponser);
            }
        });
        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameText.getText().toString(); //设置listener
                checkName(name);
                String starthour=starthourText.getText().toString();
                checkStartHour(starthour);
                String startmin=startminText.getText().toString();
                checkStartMin(startmin);
                String endhour=endhourText.getText().toString();
                checkEndHour(endhour);
                String endmin=endminText.getText().toString();
                checkEndMin(endmin);
                String address=addressText.getText().toString();
                checkAddress(address);
                String sponser=sponserText.getText().toString();
                checkSponser(sponser);
                Calendar c=Calendar.getInstance();  // 获取现在的时间
                int year1=c.get(Calendar.YEAR);
                final int month1=c.get(Calendar.MONTH);
                int day1=c.get(Calendar.DAY_OF_MONTH);
                datePicker.init(year1,month1,day1, new OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int yearx, int monthOfYear, int dayOfMonth) {
                        CreatActivity.this.year=yearx;
                        CreatActivity.this.month=monthOfYear;
                        CreatActivity.this.day=dayOfMonth;
                        checkDate(year,month,day);
                    }
                });
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            };
        });
    }//Oncreate结束
    public void checkDate (int year,int month, int day){
        Calendar d=Calendar.getInstance();  // 获取现在的时间
        int year2=d.get(Calendar.YEAR);
        final int month2=d.get(Calendar.MONTH);
        int day2=d.get(Calendar.DAY_OF_MONTH);
        if(year>year2|(year==year2 & month>month2 )|(year==year2 &month==month2 &day>=day2)) {  //时间的错误：如果发布的时间事件比当前日期早，就不行
            event.setDate(year,month,day);
            TextView Message3;
            Message3 = (TextView) findViewById(R.id.dateerror);
            Message3.setVisibility(View.INVISIBLE);
            flag[0]= true;
        }
        else
        { //返回错误
            TextView Message3;
            Message3 = (TextView) findViewById(R.id.dateerror);
            Message3.setVisibility(View.VISIBLE);
            flag[0] = false;
        }
    }
    public void checkName(String name){
        if(name.isEmpty()==false){  //检查事件名称的合理性：
            event.setEventName(name);
            TextView Message1;
            Message1 = (TextView) findViewById(R.id.namerror);
            Message1.setVisibility(View.INVISIBLE);
            flag[1]=true;
        }
        else{ //返回错误
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.namerror);
            Message1.setVisibility(View.VISIBLE);
            flag[1]=false;
        }
    }
    public void checkStartHour(String a){
        if(a.isEmpty()==false){
            int starthour=Integer.parseInt(a); //设置listener
            if(starthour<24 & starthour>=0){  //检查事件名称的合理性：
                event.setStarthour(starthour);
                TextView Message1;
                Message1 = (TextView) findViewById(R.id.starthourerror);
                Message1.setVisibility(View.INVISIBLE);
                flag[2]=true;
            }
            else{
                TextView Message1;
                Message1 = (TextView)findViewById(R.id.starthourerror);
                Message1.setVisibility(View.VISIBLE);
                flag[3]=false;
            }
        }
        else{
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.starthourerror);
            Message1.setVisibility(View.VISIBLE);
            flag[2]=false;
        }
    }
    public void checkStartMin(String a){
        if(a.isEmpty()==false){
            int startmin=Integer.parseInt(a); //设置listener
            if(startmin<60 & startmin>=0){  //检查事件名称的合理性：
                event.setStartmin(startmin);
                TextView Message1;
                Message1 = (TextView) findViewById(R.id.startminerror);
                Message1.setVisibility(View.INVISIBLE);
                flag[3]=true;
            }
            else{
                TextView Message1;
                Message1 = (TextView)findViewById(R.id.startminerror);
                Message1.setVisibility(View.VISIBLE);
                flag[3]=false;
            }
        }
        else{
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.startminerror);
            Message1.setVisibility(View.VISIBLE);
            flag[3]=false;
        }
    }
    public void checkEndHour(String a){
        if(a.isEmpty()==false){
            int endhour=Integer.parseInt(a); //设置listener
            if(endhour<24 & endhour>=0){  //检查事件名称的合理性：
                event.setEndhour(endhour);
                TextView Message1;
                Message1 = (TextView) findViewById(R.id.endhourerror);
                Message1.setVisibility(View.INVISIBLE);
                flag[4]=true;
            }
            else{
                TextView Message1;
                Message1 = (TextView)findViewById(R.id.endhourerror);
                Message1.setVisibility(View.VISIBLE);
                flag[4]=false;
            }
        }
        else{
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.endhourerror);
            Message1.setVisibility(View.VISIBLE);
            flag[4]=false;
        }
    }
    public void checkEndMin(String a){
        if(a.isEmpty()==false){
            int endmin=Integer.parseInt(a); //设置listener
            if(endmin<60 & endmin>=0){  //检查事件名称的合理性：
                event.setEndmin(endmin);
                TextView Message1;
                Message1 = (TextView) findViewById(R.id.endminerror);
                Message1.setVisibility(View.INVISIBLE);
                flag[5]=true;
            }
            else{
                TextView Message1;
                Message1 = (TextView)findViewById(R.id.endminerror);
                Message1.setVisibility(View.VISIBLE);
                flag[5]=false;
            }
        }
        else{
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.endminerror);
            Message1.setVisibility(View.VISIBLE);
            flag[5]=false;
        }
    }
    public void checkAddress(String address){
        if(address.isEmpty()==false){  //检查事件名称的合理性：
            event.setAddress(address);
            TextView Message1;
            Message1 = (TextView) findViewById(R.id.addresserror);
            Message1.setVisibility(View.INVISIBLE);
            flag[6]=true;
        }
        else{ //返回错误
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.addresserror);
            Message1.setVisibility(View.VISIBLE);
            flag[6]=false;
        }
    }
    public void checkSponser(String sponser){
        if(sponser.isEmpty()==false){  //检查事件名称的合理性：
            event.setSponser(sponser);
            TextView Message1;
            Message1 = (TextView) findViewById(R.id.sponsererror);
            Message1.setVisibility(View.INVISIBLE);
            flag[7]=true;
        }
        else{ //返回错误
            TextView Message1;
            Message1 = (TextView)findViewById(R.id.sponsererror);
            Message1.setVisibility(View.VISIBLE);
            flag[7]=false;
        }
    }
    //new Thread(new Runnable() {
        //@Override

    //}).start();
}
