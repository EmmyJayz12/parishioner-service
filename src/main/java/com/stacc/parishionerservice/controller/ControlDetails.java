package com.stacc.parishionerservice.controller;

import com.stacc.parishionerservice.model.BaseResponse;
import com.stacc.parishionerservice.model.ParishionerInfo;
import com.stacc.parishionerservice.model.ResponseInfo;
import com.stacc.parishionerservice.model.ResponseInformation;
import com.stacc.parishionerservice.service.ImplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value="/api/v1/parishoner")
public class ControlDetails {
    private ImplementService implementService;

    @Autowired
    public ControlDetails(ImplementService implementService){
        this.implementService = implementService;
    }

    @RequestMapping(value="/add", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    It is used to bind HTTP request with an object in a method parameter
    public BaseResponse addParishioners(@RequestBody ParishionerInfo parishionerInfo){
        return implementService.getMessage(parishionerInfo);
    }
    @RequestMapping(value="/update", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateParishioners(@RequestBody ParishionerInfo parishionerInfo){
        return implementService.update(parishionerInfo);
    }
    @RequestMapping(value="/delete/{verificationNumber}", method= RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteParishioners(@PathVariable("verificationNumber") String verificationNumber){
        return implementService.delete(verificationNumber);
    }
    @RequestMapping(value="/find-all", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseInformation getInfo(){
        return implementService.getInfo();
    }
    @RequestMapping(value="/find/{verificationNumber}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseInfo getDetail(@PathVariable("verificationNumber") String verificationNumber){
        return implementService.getDetail(verificationNumber);
    }

}
