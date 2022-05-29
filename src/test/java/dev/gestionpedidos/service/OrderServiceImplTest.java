package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final String NAME = "Name";
    public static final int ONE = 1;
    public static final String SHIPPING_ADDRESS = "Street";
    public static final String STATUS = "Status";
    public static final String PENDING = "pendiente";
    public static final String SHIPPED = "enviado";
    public static final String COMMENT = "Comment";
    public static final double TOTAL = 10;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderService orderService;

    @Test
    void should_return_all_orders() {
        Order orderDummy = Order.builder()
                .id(ONE)
                .user(new User())
                .orderDate(LocalDateTime.now())
                .shippingAddress(SHIPPING_ADDRESS)
                .status(STATUS)
                .comment(COMMENT)
                .orderDetails(List.of())
                .total(TOTAL)
                .build();

        List<Order> ordersDummy = List.of(orderDummy);

        when(this.orderService.getOrders()).thenReturn(Optional.of(ordersDummy));
        List<Order> orders = this.orderService.getOrders().get();

        assertThat(orders).isNotNull();
        assertEquals(ordersDummy, orders);
    }

    @Test
    void should_return_all_pending_orders() {
        Order pendingOrderDummy = Order.builder()
                .id(ONE)
                .user(new User())
                .orderDate(LocalDateTime.now())
                .shippingAddress(SHIPPING_ADDRESS)
                .status(PENDING)
                .comment(COMMENT)
                .orderDetails(List.of())
                .total(TOTAL)
                .build();

        List<Order> pendingOrdersDummy = List.of(pendingOrderDummy);

        when(this.orderService.getOrders()).thenReturn(Optional.of(pendingOrdersDummy));
        List<Order> pendingOrders = this.orderService.getPendingOrders().get();

        assertThat(pendingOrders).isNotNull();
        assertEquals(pendingOrdersDummy, pendingOrders);
    }

    @Test
    void should_return_all_sent_orders() {
        Order sentOrderDummy = Order.builder()
                .id(ONE)
                .user(new User())
                .orderDate(LocalDateTime.now())
                .shippingAddress(SHIPPING_ADDRESS)
                .status(SHIPPED)
                .comment(COMMENT)
                .orderDetails(List.of())
                .total(TOTAL)
                .build();

        List<Order> sentOrdersDummy = List.of(sentOrderDummy);

        when(this.orderService.getSentOrders()).thenReturn(Optional.of(sentOrdersDummy));
        List<Order> sentOrders = this.orderService.getSentOrders().get();

        assertThat(sentOrders).isNotNull();
        assertEquals(sentOrdersDummy, sentOrders);
    }
/*
    @Test
    void should_return_all_customer_pending_orders() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesFake));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesFake, categories);
    }

    @Test
    void should_return_all_customer_sent_orders() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesFake));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesFake, categories);
    }

    @Test
    void should_return_all_customer_delviered_orders() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesFake));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesFake, categories);
    }

    @Test
    void should_return_one_order() {
        when(this.categoryService.getCategory(anyInt())).thenReturn(Optional.of(this.categoryFake));
        Category category = this.categoryService.getCategory(anyInt()).get();

        assertThat(category).isNotNull();
        assertEquals(this.categoryFake, category);
    }

    @Test
    void should_save_order() {
        doNothing().when(this.categoryService).saveCategory(this.categoryFake);
        this.categoryService.saveCategory(this.categoryFake);
        verify(this.categoryService, times(ONE)).saveCategory(this.categoryFake);
    }

    @Test
    void should_edit_order_status() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesFake));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesFake, categories);
    }

    @Test
    void should_edit_order_comment() {
        when(this.categoryService.getCategories()).thenReturn(Optional.of(this.categoriesFake));
        List<Category> categories = this.categoryService.getCategories().get();

        assertThat(categories).isNotNull();
        assertEquals(this.categoriesFake, categories);
    }

    @Test
    void should_delete_order() {
        doNothing().when(this.categoryService).deleteCategory(ONE);
        this.categoryService.deleteCategory(ONE);
        verify(this.categoryService, times(ONE)).deleteCategory(ONE);
    }*/
}