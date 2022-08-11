package com.dummy.ewalletservice.model.request.patchdisable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDisableRequest {
    private String tokenAuth;

    private boolean isDisabled;
}
