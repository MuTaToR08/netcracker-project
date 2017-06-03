package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Customer extends ObjectBD {
    @Override
    public String publicName() {
        return getObjName();
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
