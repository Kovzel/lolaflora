package com.kovzel.entity;

import javax.persistence.*;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "userOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String status;
    private String nameUser;
    private String telephoneUser;
    private String addressUser;
    private String cityUser;
    private String countryUser;
    private String emailUser;
    private Date date;
    private Integer price;
    private String NameOnCard;
    private String CreditCardNumber;
    private String ExpMonth;
    private String CVV;
    private String ExpYear;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.REFRESH}
    )
    @JoinTable(
            name = "flower_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "flower_id"))
    private List<Flower> flowers;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTelephoneUser() {
        return telephoneUser;
    }

    public void setTelephoneUser(String telephoneUser) {
        this.telephoneUser = telephoneUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getNameOnCard() {
        return NameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        NameOnCard = nameOnCard;
    }

    public String getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public String getExpMonth() {
        return ExpMonth;
    }

    public void setExpMonth(String expMonth) {
        ExpMonth = expMonth;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getExpYear() {
        return ExpYear;
    }

    public void setExpYear(String expYear) {
        ExpYear = expYear;
    }

    public String getCityUser() {
        return cityUser;
    }

    public void setCityUser(String cityUser) {
        this.cityUser = cityUser;
    }

    public String getCountryUser() {
        return countryUser;
    }

    public void setCountryUser(String countryUser) {
        this.countryUser = countryUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(ArrayList<Flower> flowers) {
        this.flowers = flowers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}