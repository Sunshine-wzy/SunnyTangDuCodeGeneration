package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.context.AnalysisContext
import com.alibaba.excel.read.listener.ReadListener

class CodeInformationReadListener : ReadListener<CodeInformation> {
    
    private val cache: MutableList<CodeInformation> = ArrayList()
    

    override fun invoke(info: CodeInformation, context: AnalysisContext) {
        cache += info
    }

    override fun doAfterAllAnalysed(context: AnalysisContext) {
        val builder = StringBuilder()
        builder.appendLine("; //****** Start Of Main Memory Data ******//")
        for (info in cache) {
            if (info.address.isBlank() || info.content.isBlank())
                break
            
            println(info)
            builder.append("\$P ")
            builder.append(info.address)
            builder.append(" ")
            builder.append(binaryToHex(info.content))
            builder.append("    ; ")
            builder.append(info.symbol)
            builder.append("    ")
            builder.append(info.description)
            builder.appendLine()
        }
        builder.appendLine("; //****** End Of Main Memory Data ******//")
        println(builder.toString())
    }
    
    private fun binaryToHex(binary: String): String {
        if (binary.length != 8) throw IllegalArgumentException("Binary string must be 8 bits long")
        val decimal = binary.toLong(2)
        return String.format("%02X", decimal)
    }
    
}