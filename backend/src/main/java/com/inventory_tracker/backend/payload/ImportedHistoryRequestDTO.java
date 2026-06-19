package com.inventory_tracker.backend.payload;

import com.inventory_tracker.backend.model.Supplier;
import com.inventory_tracker.backend.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportedHistoryRequestDTO {

    private Long quantity;

    private Long userId;

    private Long productId;
}
