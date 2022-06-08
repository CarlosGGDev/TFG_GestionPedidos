package dev.gestionpedidos.service;

import dev.gestionpedidos.model.Order;
import dev.gestionpedidos.model.OrderDetail;
import dev.gestionpedidos.model.Role;
import dev.gestionpedidos.model.User;
import dev.gestionpedidos.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * OrderService class test cases
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    public static final String NAME = "Name";
    public static final int ONE = 1;
    public static final String SHIPPING_ADDRESS = "Street";
    public static final String STATUS = "Status";
    public static final String PENDING = "pendiente";
    public static final String SHIPPED = "enviado";
    public static final String DELIVERED = "entregado";
    public static final String COMMENT = "Comment";
    public static final double TOTAL = 10;
    public static final int USER_ID = 1;
    public static final String NIF = "A00000000";
    public static final String EMAIL = "email@gmail.com";
    public static final String PHONE = "636123456";
    public static final String ADDRESS = "C/ Test, 4";
    public static final int ZIPCODE = 07001;
    public static final String TOWN = "Palma";
    public static final String PASSWORD = "123";

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceImpl orderService;

    User user = User.builder()
        .id(USER_ID)
        .nif(NIF)
        .name(NAME)
        .email(EMAIL)
        .phone(PHONE)
        .address(ADDRESS)
        .zipcode(ZIPCODE)
        .town(TOWN)
        .password(PASSWORD)
        .role(Role.ROLE_USER)
        .build();

    Order order = Order.builder()
        .id(ONE)
        .user(this.user)
        .orderDate(LocalDateTime.now())
        .shippingAddress(SHIPPING_ADDRESS)
        .status(STATUS)
        .comment(COMMENT)
        .orderDetails(List.of(new OrderDetail()))
        .total(TOTAL)
        .build();

    List<Order> orders = List.of(this.order);

    Order pendingOrder = Order.builder()
        .id(ONE)
        .user(user)
        .orderDate(LocalDateTime.now())
        .shippingAddress(SHIPPING_ADDRESS)
        .status(PENDING)
        .comment(COMMENT)
        .orderDetails(List.of(new OrderDetail()))
        .total(TOTAL)
        .build();

    List<Order> pendingOrders = List.of(this.pendingOrder);

    Order sentOrder = Order.builder()
        .id(ONE)
        .user(user)
        .orderDate(LocalDateTime.now())
        .shippingAddress(SHIPPING_ADDRESS)
        .status(SHIPPED)
        .comment(COMMENT)
        .orderDetails(List.of(new OrderDetail()))
        .total(TOTAL)
        .build();

    List<Order> sentOrders = List.of(this.sentOrder);

    Order customerPendingOrder = Order.builder()
        .id(ONE)
        .user(user)
        .orderDate(LocalDateTime.now())
        .shippingAddress(SHIPPING_ADDRESS)
        .status(PENDING)
        .comment(COMMENT)
        .orderDetails(List.of(new OrderDetail()))
        .total(TOTAL)
        .build();

    List<Order> customerPendingOrders = List.of(this.customerPendingOrder);

    Order customerDeliveredOrder = Order.builder()
        .id(ONE)
        .user(user)
        .orderDate(LocalDateTime.now())
        .shippingAddress(SHIPPING_ADDRESS)
        .status(DELIVERED)
        .comment(COMMENT)
        .orderDetails(List.of(new OrderDetail()))
        .total(TOTAL)
        .build();

    List<Order> customerDeliveredOrders = List.of(this.customerDeliveredOrder);

    /**
     * Check that the service returns an order
     */
    @Test
    void should_return_one_order() {
        when(this.orderRepository.findById(ONE)).thenReturn(Optional.of(this.order));

        Optional<Order> order = this.orderService.getOrder(ONE);

        assertEquals(order.get(), this.order);
    }

    /**
     * Check that the service returns all orders
     */
    @Test
    void should_return_all_orders() {
        when(this.orderRepository.findAll()).thenReturn(this.orders);

        Optional<List<Order>> orders = this.orderService.getOrders();

        assertEquals(orders.get(), this.orders);
    }

    /**
     * Check that the service returns all orders with status 'pendiente'
     */
    @Test
    void should_return_all_pending_orders() {
        when(this.orderRepository.getPendingOrders()).thenReturn(Optional.of(this.pendingOrders));

        Optional<List<Order>> orders = this.orderService.getPendingOrders();

        assertEquals(orders.get(), this.pendingOrders);
        assertThat(orders.get().get(0).getStatus().equals(PENDING));
    }

    /**
     * Check that the service returns all orders with status 'enviado'
     */
    @Test
    void should_return_all_sent_orders() {
        when(this.orderRepository.getSentOrders()).thenReturn(Optional.of(this.sentOrders));

        Optional<List<Order>> orders = this.orderService.getSentOrders();

        assertEquals(orders.get(), this.sentOrders);
        assertThat(orders.get().get(0).getStatus().equals(SHIPPED));
    }

    /**
     * Check that the service returns all orders from a customer whose status is 'pendiente'
     */
    @Test
    void should_return_customer_pending_orders() {
        when(this.orderRepository.getCustomerPendingOrders(ONE)).thenReturn(Optional.of(this.customerPendingOrders));

        Optional<List<Order>> orders = this.orderService.getCustomerPendingOrders(ONE);

        assertEquals(orders.get(), this.customerPendingOrders);
        assertThat(orders.get().get(0).getUser().equals(this.user));
        assertThat(orders.get().get(0).getStatus().equals(PENDING));
    }

    /**
     * Check that the service returns all orders from a customer whose status is 'entregado'
     */
    @Test
    void should_return_customer_delivered_orders() {
        when(this.orderRepository.getCustomerDeliveredOrders(ONE)).thenReturn(Optional.of(this.customerDeliveredOrders));

        Optional<List<Order>> orders = this.orderService.getCustomerDeliveredOrders(ONE);

        assertEquals(orders.get(), this.customerDeliveredOrders);
        assertThat(orders.get().get(0).getUser().equals(this.user));
        assertThat(orders.get().get(0).getStatus().equals(DELIVERED));
    }

    /**
     * Check that the service saves an order
     */
    @Test
    void should_save_one_order() {
        when(this.orderRepository.save(this.order)).thenReturn(this.order);

        Optional<Order> order = this.orderService.saveOrder(this.order);

        assertEquals(order.get(), this.order);
    }

    /**
     * Check that the service edits the status of an order
     */
    @Test
    void should_edit_one_order_status() {
        doNothing().when(this.orderRepository).editOrderStatus(ONE, STATUS);

        this.orderService.editOrderStatus(ONE, STATUS);

        verify(this.orderRepository, times(1)).editOrderStatus(ONE, STATUS);
    }

    /**
     * Check that the service edits the comment of an order
     */
    @Test
    void should_edit_one_order_comment() {
        doNothing().when(this.orderRepository).editOrderComment(ONE, COMMENT);

        this.orderService.editOrderComment(ONE, COMMENT);

        verify(this.orderRepository, times(1)).editOrderComment(ONE, COMMENT);
    }

    /**
     * Check that the service deletes an order
     */
    @Test
    void should_delete_one_order() {
        when(this.orderService.getOrder(ONE)).thenReturn(Optional.of(this.order));
        doNothing().when(this.orderRepository).deleteById(ONE);

        this.orderService.deleteOrder(ONE);

        verify(this.orderRepository, times(1)).deleteById(ONE);
    }
}