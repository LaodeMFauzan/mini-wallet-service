package com.dummy.ewalletservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalData {
    private String id;
    private String withdrawnBy;
    private String status;
    private String withdrawnAt;
    private Integer amount;
    private String referenceId;
}
