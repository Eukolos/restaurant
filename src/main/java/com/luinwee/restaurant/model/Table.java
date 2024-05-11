package com.luinwee.restaurant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Entity(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Cascade(ALL)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Account> accounts;
    private Boolean isAvailable;

    private String customerName;

    public Table(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
