package com.screen.quickprint.user.data.entity;

import com.screen.quickprint.common.model.BaseEntity;
import com.screen.quickprint.order.data.entity.Order;
import com.screen.quickprint.user.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity<Long> {
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
