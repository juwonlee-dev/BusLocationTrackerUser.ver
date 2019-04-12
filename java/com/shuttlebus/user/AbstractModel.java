package com.shuttlebus.user;

import android.widget.SeekBar;

import java.util.ArrayList;

public class AbstractModel {

    private SeekBar seekBar;
    private String start;
    private String message;
    private String end;


    public AbstractModel(String start, String message) {
        this.seekBar = seekBar;
        this.start = start;
        this.message = message;
    }
    public AbstractModel(String start, String message , String end) {
        this.start = start;
        this.message = message;
        this.end = end;
    }

    public AbstractModel(SeekBar seekBar, String start, String message) {
        this.seekBar = seekBar;
        this.start = start;
        this.message = message;
    }


    public SeekBar getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getEnd(){
        return end;
    }
    public void setEnd(String end){
        this.end = end;
    }
}
