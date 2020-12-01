package com.mybankdashboard.springboot.Mapper;

import com.mybankdashboard.springboot.DataObject.BankData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BankDataMapper {

    // 全局总览搜索
    @Select("select * from bankdata")
    List<BankData> findAll();

    @Select("select * from bankdata where province = #{province}")
    List<BankData> findAllWithProvince(String province);

    @Select("select * from bankdata where province = #{province} and city = #{city}")
    List<BankData> findAllWithProvinceAndCity(String province, String city);

    @Select("select * from bankdata where bank_type = #{bankType}")
    List<BankData> findBank(String bankType);

    @Select("select * from bankdata where bank_type = #{bankType} and province = #{province}")
    List<BankData> findBankWithProvince(String bankType, String province);

    @Select("select * from bankdata where province = #{province} and city = #{city}")
    List<BankData> findBankWithProvinceAndCity(String province, String city);

    @Select("select * from bankdata where bank_type = #{bankType} and ((province = #{province} and city = #{city}) and area = #{area})")
    List<BankData> findABankWithProvinceAndCityAndArea(String bankType, String province, String city, String area);
}
