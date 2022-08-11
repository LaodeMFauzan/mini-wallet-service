package com.dummy.ewalletservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostMoneyRequest {

    private String authToken;

    private String amount;

    private String referenceId;
}
