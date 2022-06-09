package dev.gestionpedidos.service;

import dev.gestionpedidos.dto.OrderDetailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * OrderDetailService class test cases
 */
@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    private static final int ONE = 1;
    private static final int ORDER_ID = 1;
    private static final int PRODUCT_ID = 1;
    private static final int QUANTITY = 1;
    private static final double PRICE = 1.5;
    private static final double TOTAL = 1.5;
    private static final String SQL = "INSERT INTO orders_details (order_id, product_id, quantity, price, total) VALUES (1,1,1,1.5,1.5);";

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    OrderDetailServiceImpl orderDetailService;

    OrderDetailDto orderDetailDto = OrderDetailDto.builder()
        .order_id(ORDER_ID)
        .product_id(PRODUCT_ID)
        .quantity(QUANTITY)
        .price(PRICE)
        .total(TOTAL)
        .build();

    /**
     * Check that the service saves an order detail
     */
    @Test
    void should_save_orderDetail() {
        when(this.jdbcTemplate.update(SQL)).thenReturn(ONE);

        this.orderDetailService.saveOrderDetail(this.orderDetailDto);

        verify(this.jdbcTemplate, times(1)).update(SQL);
    }
}