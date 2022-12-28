package com.cavemen.dbscw.entities.order;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Document
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @JsonIgnoreProperties
    private String id;
    private String address;

    private ArrayList<OrderItem> wishList;

    @JsonIgnore
    private ArrayList<String> ids;

    public Order(String id, String address, ArrayList<OrderItem> wishList) {
        this.address = address;
        this.wishList = wishList;
        this.id = id;
        ids = (ArrayList<String>) wishList.stream().map(OrderItem::getId).collect(Collectors.toList());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<OrderItem> getWishList() {
        return wishList;
    }

    public String getId() {
        return id;
    }

    public void addItem(OrderItem customKeyValue){
        if(ids.contains(customKeyValue.getId())){
            OrderItem c = wishList.get(getIndex(customKeyValue.getId()));
            Long  v = c.getValue();
            c.setValue(v + customKeyValue.getValue());
        }
        else this.wishList.add(customKeyValue);
    }

    private int getIndex(String id){
        for(int i = 0; i < wishList.size(); i++){
            if(wishList.get(i).getId().equals(id))
                return i;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", wishList=" + wishList +
                '}';
    }

}
