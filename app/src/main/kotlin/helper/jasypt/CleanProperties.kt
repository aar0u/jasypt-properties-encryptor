package helper.jasypt

import java.io.File
import java.io.IOException
import java.io.Writer
import java.util.*

class CleanProperties : Properties() {
    fun encrypt(input: String, keywords: MutableList<String>, password: String, output: String) {
        val props = File(input).bufferedReader().use {
            this.apply { load(it) }
        }

        for (prop in props) {
            val theKey = prop.key.toString();
            var theValue = prop.value.toString();
            theValue = Encryptor().encryptValue(theKey, theValue, keywords, password)
            prop.setValue(theValue)
        }

        props.setProperty("jasypt.encryptor.password", password)
        props.store(File(output).bufferedWriter(), null)
    }

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