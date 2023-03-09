package com.luinwee.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Account> accounts;
    private Boolean isAvailable;

    public Table(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
