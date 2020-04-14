package com.stacc.parishionerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseInfo extends BaseResponse{
    public ParishionerInfo getParishionerInfo() {
        return parishionerInfo;
    }

    public void setParishionerInfo(ParishionerInfo parishionerInfo) {
        this.parishionerInfo = parishionerInfo;
    }

    private ParishionerInfo parishionerInfo;
}
