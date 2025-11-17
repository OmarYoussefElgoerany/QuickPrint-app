package com.screen.quickprint.payment.data.entity;

import com.screen.quickprint.common.model.BaseEntity;
import com.screen.quickprint.order.data.entity.Order;
import com.screen.quickprint.payment.data.enums.PaymentMethod;
import com.screen.quickprint.payment.data.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity<Long> {
    private PaymentMethod paymentMethod;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, PAID, FAILED

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
