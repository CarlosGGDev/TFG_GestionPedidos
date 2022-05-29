package dev.gestionpedidos.service;

import dev.gestionpedidos.dto.OrderDetailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {

    public static final int ONE = 1;
    public static final int ZERO = 0;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DataSource dataSource;

    @Mock
    private OrderDetailService orderDetailService;

    OrderDetailDto orderDetailDto = mock(OrderDetailDto.class);

    @Test
    void should_save_orderDetail() {
        doNothing().when(this.orderDetailService).saveOrderDetail(this.orderDetailDto);
        this.orderDetailService.saveOrderDetail(this.orderDetailDto);
        verify(this.orderDetailService, times(ONE)).saveOrderDetail(this.orderDetailDto);
    }

}