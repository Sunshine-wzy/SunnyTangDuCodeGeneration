package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.annotation.ExcelProperty

data class CodeInformation(
    @ExcelProperty(index = 0)
    var address: String,
    @ExcelProperty(index = 1)
    var content: String,
    @ExcelProperty(index = 2)
    var symbol: String,
    @ExcelProperty(index = 3)
    var description: String
) {
    constructor() : this("", "", "", "")
}