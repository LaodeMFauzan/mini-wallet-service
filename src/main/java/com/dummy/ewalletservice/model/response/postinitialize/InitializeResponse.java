package com.dummy.ewalletservice.model.response.postinitialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitializeResponse {

    private InitializeData data;

    private String status;
}
