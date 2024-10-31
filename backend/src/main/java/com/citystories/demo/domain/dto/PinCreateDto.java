package com.citystories.demo.domain.dto;

import com.citystories.demo.domain.enums.VisibilityDuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PinCreateDto {
    private Long userId;
    private String story;
    private VisibilityDuration visibilityDuration; // the enum field
}
