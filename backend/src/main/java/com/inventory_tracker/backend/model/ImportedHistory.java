package com.inventory_tracker.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "imported_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportedHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long importedHistoryId;

    private Long quantity;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void persist(){
        this.setDate(LocalDate.now());
        this.setTime(LocalTime.now());
    }
}
