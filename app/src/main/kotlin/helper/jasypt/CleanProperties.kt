package helper.jasypt

import java.io.IOException
import java.io.Writer
import java.util.*

class CleanProperties : Properties() {
    override fun store(writer: Writer, comments: String?) {
        writer.use {
            keys.stream().map { k: Any? -> k as String? }.sorted().forEach { k: String? ->
                try {
                    writer.append(String.format("%s=%s\n", k, get(k)))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}