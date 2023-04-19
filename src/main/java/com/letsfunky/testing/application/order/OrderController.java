package com.letsfunky.testing.application.order;

import com.letsfunky.testing.application.order.OrderDto.OrderDetailResponse;
import com.letsfunky.testing.application.order.OrderDto.OrderRequest;
import com.letsfunky.testing.application.order.OrderDto.TestRequest;
import com.letsfunky.testing.application.order.OrderDto.TestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public OrderDetailResponse getOrderDetail(@PathVariable long orderId) {
        return OrderDetailResponse.of(orderService.getOrderDetail(orderId));
    }

    @PostMapping("/")
    public OrderDetailResponse createOrder(@RequestBody OrderRequest request) {
        return OrderDetailResponse.of(
            orderService.createOrder(
                request.getOrdererId(),
                request.getPhoneNumber(), request.getShippingAddress(),
                request.getGoods(), request.getCount()
            )
        );
    }

    @PostMapping("/negate")
    public TestResponse dtoTest(@RequestBody TestRequest testRequest) {
        var negated = !testRequest.isPrimitive();
        return new TestResponse(negated, negated);
    }
}
