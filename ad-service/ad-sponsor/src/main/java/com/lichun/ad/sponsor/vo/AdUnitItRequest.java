package com.lichun.ad.sponsor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItRequest {

    private List<UnitId> unitIds;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitId {
        private Long unitId;
        private String itTag;
    }

}
