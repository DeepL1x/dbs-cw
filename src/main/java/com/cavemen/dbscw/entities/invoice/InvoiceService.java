package com.cavemen.dbscw.entities.invoice;

import com.cavemen.dbscw.entities.article.ArticleRepository;
import com.cavemen.dbscw.entities.order.Order;
import com.cavemen.dbscw.entities.order.OrderItem;
import com.cavemen.dbscw.entities.order.OrderRepository;
import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import com.cavemen.dbscw.entities.readyItem.ReadyItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final OrderRepository orderRepository;
    private final ArticleRepository articleRepository;
    private final InvoiceRepository invoiceReposetory;
    private final ReadyItemRepository readyItemRepository;

    @Autowired
    public InvoiceService(OrderRepository orderRepository, ArticleRepository articleRepository, InvoiceRepository invoiceReposetory, ReadyItemRepository readyItemRepository) {
        this.orderRepository = orderRepository;
        this.articleRepository = articleRepository;
        this.invoiceReposetory = invoiceReposetory;
        this.readyItemRepository = readyItemRepository;
    }

    public Optional<Invoice> getInvoice(String id) {
        return invoiceReposetory.findById(id);
    }

    public List<Invoice> getInvoices(){
        return invoiceReposetory.findAll();
    }

    public Optional<Invoice> createInvoice(String id) {
        Optional<Order> order = orderRepository.findById(id);
        boolean flag = true;
        ArrayList<OrderItem> wishList = new ArrayList<>(order.get().getWishList());
        ArrayList<InvoiceItem> items = new ArrayList<>();
        for (OrderItem wishItem : wishList) {
            InvoiceItem item = null;
            Optional<ReadyItem> readyItem = readyItemRepository.findById(wishItem.getId());
            if (readyItem.isEmpty()) continue;
            if (readyItem.get().getAvailableItemAmount() >= wishItem.getValue()) {
                item = new InvoiceItem(readyItem.get().getId(), readyItem.get().getPrice(), wishItem.getValue());
                readyItem.get().setAvailableItemAmount(readyItem.get().getAvailableItemAmount() - wishItem.getValue());
            }
            if (readyItem.get().getAvailableItemAmount() < wishItem.getValue()) {
                item = new InvoiceItem(readyItem.get().getId(), readyItem.get().getPrice(), readyItem.get().getAvailableItemAmount());
                readyItem.get().setAvailableItemAmount(0L);

                flag = false;
            }
            readyItem.get().setReservedItemAmount(readyItem.get().getTotalItemAmount());
            items.add(item);
            readyItemRepository.save(readyItem.get());
        }
        return Optional.of(invoiceReposetory.save(new Invoice(id, items, flag)));
    }

    public Optional<Invoice> approveInvoice(String id) {
        Optional<Invoice> invoice = invoiceReposetory.findById(id);
        invoice.get().setApproved(true);
        return Optional.of(invoiceReposetory.save(invoice.get()));
    }
}
