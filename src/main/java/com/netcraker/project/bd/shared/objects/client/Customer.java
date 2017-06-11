package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gwt.user.client.ui.Widget;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.shared.objects.controled.FullWindow;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Customer extends ObjectBD implements FullWindow {

    public Customer(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("inn") && e.get("inn") != null)
            inn = Integer.valueOf(e.get("inn").toString());
        else
            inn = 0;
        if(e.containsKey("phone") && e.get("phone") != null)
            phone = Integer.valueOf(e.get("phone").toString());
        else
            phone = 0;
        if(e.containsKey("username") && e.get("username") != null)
            username = e.get("username").toString();
        else
            username = null;
        if(e.containsKey("fio") && e.get("fio") != null)
            fio = e.get("fio").toString();
        else
            fio = null;

        if(e.containsKey("password") && e.get("password") != null)
            password = e.get("password").toString();
        else
            password = null;

        if(e.containsKey("tariff") && e.get("tariff") != null)
            tariff = new Tariff((HashMap<String, Object>)e.get("tariff"));
        else
            tariff = null;

        if(e.containsKey("status") && e.get("status") != null)
            status = Integer.valueOf(e.get("status").toString());

        if(e.containsKey("balance") && e.get("balance") != null)
            balance = Integer.valueOf(e.get("balance").toString());
    }

    @Override
    public String publicName() {
        return getFio();
    }

    //private int type;
    private String fio;
    private int inn;
    private int phone;
    private String username;
    private String password;
    private Tariff tariff;
    private int status;
    private double balance;


    public int getType() {
        return getObjectType();
    }

    public void setType(int type) {
        //this.type = type;
        setObjectType(type);
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", type=" + getObjectType() +
                ", fio='" + fio + '\'' +
                ", inn=" + inn +
                ", phone=" + phone +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tariff=" + tariff +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }

    @JsonCreator
    public Customer(@JsonProperty("id") int id,@JsonProperty("type") int type,
                    @JsonProperty("fio") String fio,@JsonProperty("inn") int inn,
                    @JsonProperty("phone") int phone,@JsonProperty("username") String username,
                    @JsonProperty("password") String password,
                    @JsonProperty("tariff") Tariff tariff,@JsonProperty("status") int status,
                    @JsonProperty("balance") double balance) {
        super(id);

        //this.type = type;
        setObjectType(type);
        this.fio = fio;
        this.inn = inn;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.tariff = tariff;
        this.status = status;
        this.balance = balance;
    }

    @Override
    public String getObjectClass() {
        return "customer";
    }

    @Override
    public String getHtmlUl() {
        return publicName()+", inn:"+inn+", username:"+username+"|"+(BD.statuses.get(status)==null?"":BD.statuses.get(status).getText())+"|balance:"+balance;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
