package com.gradu.gathering.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class UserExcel {

    @Excel(name = "手机号")
    private String mobile;

    @Excel(name = "昵称")
    private String nickname;
}
