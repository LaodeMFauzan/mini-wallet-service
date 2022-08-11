package com.dummy.ewalletservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletData {
    private String id;
    private String ownedBy;
    private String status;
    private String enabledAt;
    private String disabledAt;
    private Integer balance;
}
