package com.shuttlebus.user;

import android.widget.SeekBar;


public class AbstractModel {

    private SeekBar seekBar;
    private String start;
    private String message;
    private String end;
    private int progress;
    private boolean isArrived;


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

    public AbstractModel(String start, String message , String end ,int progress) {
        this.start = start;
        this.message = message;
        this.end = end;
        this.progress = progress;
    }

    public AbstractModel(String start, String message , String end ,int progress , boolean isArrived) {
        this.start = start;
        this.message = message;
        this.end = end;
        this.progress = progress;
        this.isArrived = isArrived;
    }

    public AbstractModel(SeekBar seekBar, String start, String message) {
        this.seekBar = seekBar;
        this.start = start;
        this.message = message;
    }


    public boolean getIsArrived(){
        return isArrived;
    }

    public void setArrived(boolean is){
        isArrived = is;
    }
    public SeekBar getSeekBar() {
        return seekBar;
    }
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress){
        this.progress = progress;
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
