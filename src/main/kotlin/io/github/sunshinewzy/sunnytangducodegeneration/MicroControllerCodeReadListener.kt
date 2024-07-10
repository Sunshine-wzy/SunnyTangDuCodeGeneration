package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.context.AnalysisContext
import com.alibaba.excel.read.listener.ReadListener

class MicroControllerCodeReadListener : ReadListener<MicroControllerCodeInformation> {

    private val cache: MutableList<MicroControllerCodeInformation> = ArrayList()
    
    override fun invoke(info: MicroControllerCodeInformation, context: AnalysisContext) {
        cache += info
    }

    override fun doAfterAllAnalysed(context: AnalysisContext) {
        val builder = StringBuilder()
        builder.appendLine("; //** Start Of MicroController Data **//")
        for (info in cache) {
            if (info.address.isBlank() || info.content.isBlank())
                break

            builder.append("\$M ")
            builder.append(info.address.replace("H$".toRegex(), ""))
            builder.append(" ")
            builder.append(info.content)
            
            if (info.description.isNotBlank()) {
                builder.append("    ; ")
                builder.append(info.description)
            }
            builder.appendLine()
        }
        builder.appendLine("; //** End Of MicroController Data **//")
        println(builder.toString())
    }
    
}