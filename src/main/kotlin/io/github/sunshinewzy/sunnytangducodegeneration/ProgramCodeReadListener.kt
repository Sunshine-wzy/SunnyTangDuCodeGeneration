package io.github.sunshinewzy.sunnytangducodegeneration

import com.alibaba.excel.context.AnalysisContext
import com.alibaba.excel.read.listener.ReadListener

class ProgramCodeReadListener : ReadListener<ProgramCodeInformation> {
    
    private val cache: MutableList<ProgramCodeInformation> = ArrayList()
    

    override fun invoke(info: ProgramCodeInformation, context: AnalysisContext) {
        cache += info
    }

    override fun doAfterAllAnalysed(context: AnalysisContext) {
        val builder = StringBuilder()
        builder.appendLine("; //****** Start Of Main Memory Data ******//")
        for (info in cache) {
            if (info.address.isBlank() || info.content.isBlank())
                break
            
            builder.append("\$P ")
            builder.append(info.address.replace("H$".toRegex(), ""))
            builder.append(" ")
            builder.append(binaryToHex(info.content))
            
            val symbolNotBlank = info.symbol.isNotBlank()
            val descriptionNotBlank = info.description.isNotBlank()
            if (symbolNotBlank || descriptionNotBlank) {
                builder.append("    ; ")
                if (symbolNotBlank) builder.append(info.symbol)
                
                if (descriptionNotBlank) {
                    if (symbolNotBlank) builder.append("    ")
                    builder.append(info.description)
                }
            }
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