package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.EasyExcel

object SunnyTangDuCodeGeneration {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) return
        val path = args[0]
        if (path.endsWith(".xls") || path.endsWith(".xlsx")) {
            try {
                EasyExcel.read(path, ProgramCodeInformation::class.java, ProgramCodeReadListener())
                    .sheet("P")
                    .doRead()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            try {
                EasyExcel.read(path, MicroControllerCodeInformation::class.java, MicroControllerCodeReadListener())
                    .sheet("M")
                    .doRead()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
    
}