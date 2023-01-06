package com.cavemen.dbscw.services;

import com.cavemen.dbscw.entities.article.ArticleRepository;
import com.cavemen.dbscw.entities.invoice.Invoice;
import com.cavemen.dbscw.entities.invoice.InvoiceItem;
import com.cavemen.dbscw.entities.invoice.InvoiceRepository;
import com.cavemen.dbscw.entities.invoice.InvoiceService;
import com.cavemen.dbscw.entities.order.OrderRepository;
import com.cavemen.dbscw.entities.readyItem.ReadyItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {
    static ArrayList<InvoiceItem> items1 = new ArrayList<>();

    private static InvoiceItem item1 =
            new InvoiceItem("2", 20.5, 1L);
    private static InvoiceItem item2 =
            new InvoiceItem("3", 21.5, 2L);

    private Invoice invoice1 =
            new Invoice("1", items1, true);

    @Mock
    InvoiceRepository invoiceRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    ReadyItemRepository readyItemRepository;

    private InvoiceService invoiceService;

    static {
        items1.add(item1);
        items1.add(item2);
    }
    @BeforeEach
    void setService() {

        invoiceService = new InvoiceService(orderRepository, articleRepository, invoiceRepository, readyItemRepository);
    }

    @Test
    void getInvoiceTest() {
        when(invoiceRepository.findById(invoice1.getId())).thenReturn(Optional.of(invoice1));
        assertEquals(invoice1, invoiceService.getInvoice(invoice1.getId()).get());
    }

    @Test
    void approveInvoiceTest() {
        when(invoiceRepository.findById(invoice1.getId())).thenReturn(Optional.of(invoice1));
        when(invoiceRepository.save(invoice1)).thenReturn(invoice1);
        assertEquals(invoiceService.approveInvoice(invoice1.getId()).get().isApproved(), invoice1.isApproved());
    }

}
