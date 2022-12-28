package com.cavemen.dbscw.entities.invoice;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Invoice {

    private String id;
    private ArrayList<InvoiceItem> items;
    private boolean approved;
    private double totalSum;

    public Invoice(String id, ArrayList<InvoiceItem> items, boolean approved) {
        this.id = id;
        this.items = items;
        this.approved = approved;
        totalSum = items.stream().map(InvoiceItem::getTotalPrice).reduce(Double::sum).get();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
        //        ", items=" + items +
                ", approved=" + approved +
                ", totalSum=" + totalSum +
                '}';
    }
}
