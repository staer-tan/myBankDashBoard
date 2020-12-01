package com.mybankdashboard.springboot.Controller;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybankdashboard.springboot.DataObject.BankData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mybankdashboard.springboot.Mapper.BankDataMapper;

@Controller
public class BankDataController {

    @Autowired
    BankDataMapper bankDataMapper;

    @RequestMapping("/listBankData")
    public String listBankData(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "10") int size){
        PageHelper.startPage(start, size, "id asc");
        List<BankData> bankDataList = bankDataMapper.findAll();
        PageInfo<BankData> page = new PageInfo<>(bankDataList);
        model.addAttribute("page", page);
        return "listBankData";
    }


    /**
     * 银行数据总览
     */
    @RequestMapping("/dashboard")
    public String allDashboard(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                        @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(start, size, "id asc");
        // 银行数据
        List<BankData> bankDataList = bankDataMapper.findAll();
        PageInfo<BankData> page = new PageInfo<>(bankDataList);
        model.addAttribute("page", page);
        return "dashboard";
    }

    @RequestMapping("/bankofchina")
    public String bocDashboard(Model model, @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(start, size, "id asc");
        // 银行数据
        List<BankData> bankDataList = bankDataMapper.findBank("中国银行");
        PageInfo<BankData> page = new PageInfo<>(bankDataList);
        model.addAttribute("page", page);
        return "bankofchina";
    }

    @RequestMapping("/searchbank")
    public String searchBank(Model model, @RequestParam(value = "searchStr", defaultValue = "") String searchStr){
        if(searchStr == "" || searchStr == null)
            return "dashboard";

        String[] info = searchStr.split("-");
        String bankType = info[0];
        String province = info[1];
        String city = info[2];
        String area = info[3];

        List<BankData> bankDataList = bankDataMapper.findABankWithProvinceAndCityAndArea(bankType, province, city, area);
        PageInfo<BankData> page = new PageInfo<>(bankDataList);
        model.addAttribute("page", page);

        return bankType;
    }
}
