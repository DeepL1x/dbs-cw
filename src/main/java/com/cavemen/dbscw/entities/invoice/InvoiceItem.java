package com.cavemen.dbscw.entities.invoice;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import jakarta.persistence.MapsId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class InvoiceItem {
    @Id
    private String id;
    @MapsId
    //private ReadyItem readyItem;
    private double totalPrice;
    private Long count;
    private double price;

    public InvoiceItem(String id, double price, Long count) {
        this.id = id;
        this.price = price;
        this.totalPrice = price * count;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public ReadyItem getReadyItem() {
//        return readyItem;
//    }
//
//    public void setReadyItem(ReadyItem readyItem) {
//        this.readyItem = readyItem;
//    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Items{" +
              //  "article=" + readyItem +
                ", totalPrice=" + totalPrice +
                ", count=" + count +
                '}';
    }
}
