package models;

public class Time {
    private int hour;
    private int minute;
    private int second;

    public Time(int hour, int minute, int second) {
        this.hour=hour;
        this.minute=minute;
        this.second=second;
    }
    public int getHour(){
        return hour;
    }
    public void setHour(int hour){
        this.hour=hour;
    }
    public int getMinute(){
        return minute;
    }
    public void setMinute(int minute){
        this.minute=minute;
    }
    public int getSecond(){
        return second;
    }
    public void setSecond(int second){
        this.second=second;
    }
    public int toSeconds(){
        return hour * 3600 + minute * 60 + second;
    }

    public void fromSeconds(int totalSeconds){
        hour = totalSeconds / 3600;
        totalSeconds %= 3600;
        minute = totalSeconds / 60;
        second = totalSeconds % 60;
    }
    public String toString(){
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

}