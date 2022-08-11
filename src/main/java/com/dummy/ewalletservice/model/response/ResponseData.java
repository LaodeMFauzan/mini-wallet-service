package com.dummy.ewalletservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private WalletData wallet;

    private WithdrawalData withdrawal;

    private DepositData deposit;

    private ErrorData error;
}
