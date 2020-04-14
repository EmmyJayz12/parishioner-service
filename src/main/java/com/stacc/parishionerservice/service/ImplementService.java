package com.stacc.parishionerservice.service;

import com.stacc.parishionerservice.model.BaseResponse;
import com.stacc.parishionerservice.model.ParishionerInfo;
import com.stacc.parishionerservice.model.ResponseInfo;
import com.stacc.parishionerservice.model.ResponseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ImplementService {
    private JdbcTemplate jdbcTemplate;
    private ResponseInfo responseInfo;

    @Autowired
    public ImplementService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public BaseResponse processQuery(int res) {
        BaseResponse base = new BaseResponse();
        if (res == 0) {
            base.setResponseCode("10001");
            base.setResponseMessage("something wen wrong");
            return base;
        }
        base.setResponseCode("00");
        base.setResponseMessage("successful");
        return base;

    }

    public BaseResponse getMessage(ParishionerInfo info) {
        int res = 0;
        BaseResponse base = new BaseResponse();

        try {
            String sql = "INSERT INTO congregation(first_name, middle_name, surname, society, verification_number) VALUES (?, ?, ?, ?, ?)";
            res = this.jdbcTemplate.update(sql, new Object[]{info.getFirstName(), info.getMiddleName(), info.getSurname(), info.getSociety(), info.getVerificationNumber()});
            return processQuery(res);
        } catch (Exception e) {
            e.printStackTrace();
            base.setResponseCode("10002");
            base.setResponseMessage(e.getCause().getMessage());
        }
        return base;
    }

    public BaseResponse update(ParishionerInfo info) {
        int res = 0;
        BaseResponse base = new BaseResponse();

        try {
            String sql = "UPDATE congregation SET first_name=?, middle_name=?, surname=?, society=? WHERE verification_number=?";
            res = this.jdbcTemplate.update(sql, new Object[]{info.getFirstName(), info.getMiddleName(), info.getSurname(), info.getSociety(), info.getVerificationNumber()});
            return processQuery(res);
        } catch (Exception e) {
            e.printStackTrace();
            base.setResponseCode("10002");
            base.setResponseMessage(e.getCause().getMessage());
        }
        return base;
    }
    public BaseResponse delete(String verificationNumber) {
        int res = 0;
        BaseResponse base = new BaseResponse();

        try {
            String sql = "DELETE FROM `congregation` WHERE verification_number=?";
            res = this.jdbcTemplate.update(sql, new Object[]{verificationNumber});
            return processQuery(res);
        } catch (Exception e) {
            e.printStackTrace();
            base.setResponseCode("10002");
            base.setResponseMessage(e.getCause().getMessage());
        }
        return base;
    }

    public ResponseInformation getInfo(){
        ResponseInfo response = new ResponseInfo();
        ResponseInformation resInfo=new ResponseInformation();
        try {
            String sql = "SELECT * FROM congregation";
            List<ParishionerInfo> info = this.jdbcTemplate.query(sql, new DetailsRowMapper());
            if ((info.isEmpty()) || (info == null)) {
                resInfo.setResponseCode("10002");
                resInfo.setResponseMessage("No Record Found");
                return resInfo;
            }
            resInfo.setResponseCode("00");
            resInfo.setResponseMessage("Successful");
            resInfo.setParishionerInfo(info);

            }
        catch (Exception e){
            e.printStackTrace();
            e.getCause().getMessage();
        }
        return resInfo;
    }
    private class DetailsRowMapper implements RowMapper {

        @Override
        public ParishionerInfo mapRow(ResultSet rs, int i) throws SQLException {
            ParishionerInfo info= new ParishionerInfo();
            info.setId(rs.getString("id"));
            info.setFirstName(rs.getString("first_name"));
            info.setMiddleName(rs.getString("middle_Name"));
            info.setSurname(rs.getString("surname"));
            info.setSociety(rs.getString("society"));
            info.setVerificationNumber(rs.getString("verification_number"));
            info.setDateTime(rs.getString("date_time"));
            return info;
        }
    }
    public ResponseInfo getDetail(String verificationNumber) {
        ResponseInfo responseInfo= new ResponseInfo();
        try {
            String sql = "SELECT * FROM congregation WHERE verification_number=?";
            ParishionerInfo parishionerInfo = this.jdbcTemplate.queryForObject(sql, new Object[]{verificationNumber}, new RowMapperInfo());
            if ((parishionerInfo == null)) {
                responseInfo.setResponseCode("10001");
                responseInfo.setResponseMessage("No Record Found");
            }
            responseInfo.setResponseCode("00");
            responseInfo.setResponseMessage("Successful");
            responseInfo.setParishionerInfo(parishionerInfo);
        }
            catch (Exception e){
                e.printStackTrace();
                responseInfo.setResponseCode("10002");
                responseInfo.setResponseMessage(e.getMessage());

            }
        return responseInfo;
    }
    private class RowMapperInfo implements RowMapper<ParishionerInfo> {
        @Override
        public ParishionerInfo mapRow(ResultSet rs, int i) throws SQLException {
            ParishionerInfo info = new ParishionerInfo();
            info.setId(rs.getString("id"));
            info.setFirstName(rs.getString("first_name"));
            info.setMiddleName(rs.getString("middle_Name"));
            info.setSurname(rs.getString("surname"));
            info.setSociety(rs.getString("society"));
            info.setVerificationNumber(rs.getString("verification_number"));
            info.setDateTime(rs.getString("date_time"));
            return info;
        }
    }
}