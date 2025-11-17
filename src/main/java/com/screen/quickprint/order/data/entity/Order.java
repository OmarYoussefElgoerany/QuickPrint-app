package com.screen.quickprint.order.data.entity;

import com.screen.quickprint.common.model.BaseEntity;
import com.screen.quickprint.order.enums.OrderStatus;
import com.screen.quickprint.order_item.data.entity.OrderItem;
import com.screen.quickprint.payment.data.entity.Payment;
import com.screen.quickprint.user.data.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;
}
