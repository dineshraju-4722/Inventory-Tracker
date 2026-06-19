package com.inventory_tracker.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportedHistoryResponseDTO {

    private Long quantity;

    private LocalDate date;

    private LocalTime time;

    private ProductResponseDTO product;
}
