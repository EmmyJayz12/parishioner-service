package com.stacc.parishionerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseInformation extends BaseResponse {
    public List<ParishionerInfo> getParishionerInfo() {
        return parishionerInfo;
    }

    public void setParishionerInfo(List<ParishionerInfo> parishionerInfo) {
        this.parishionerInfo = parishionerInfo;
    }

    List<ParishionerInfo> parishionerInfo;
}
