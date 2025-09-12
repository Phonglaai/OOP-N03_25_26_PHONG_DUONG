package models;
public class Bia {
    String nhanbia;
    String tenbia;
    String mausac;
    int luuluong;

    public String uong(String tenbia){
        System.out.println("Bia::" + tenbia);
        return tenbia;

    }
    public String getMausac(){
        return mausac;
    }
    public String getTen(){
        return tenbia;
    }
}