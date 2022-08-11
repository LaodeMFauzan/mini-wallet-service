package com.dummy.ewalletservice.model.response.postinitialize;

import com.dummy.ewalletservice.model.response.ErrorData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitializeData {
    private String token;

    private ErrorDataInitialize error;
}
