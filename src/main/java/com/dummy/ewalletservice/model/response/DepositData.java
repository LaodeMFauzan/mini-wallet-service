package com.dummy.ewalletservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositData {
    private String id;
    private String depositedBy;
    private String status;
    private String depositedAt;
    private Integer amount;
    private String referenceId;
}
