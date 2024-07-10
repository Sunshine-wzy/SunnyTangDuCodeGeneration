package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.EasyExcel

object SunnyTangDuCodeGeneration {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) return
        val path = args[0]
        println(path)
        if (path.endsWith(".xls") || path.endsWith(".xlsx")) {
            try {
                EasyExcel.read(path, CodeInformation::class.java, CodeInformationReadListener())
                    .sheet("P")
                    .doRead()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            
        }
    }
    
}