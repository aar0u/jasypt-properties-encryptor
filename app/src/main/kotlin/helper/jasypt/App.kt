/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package helper.jasypt

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory

class App {
    val text: String
        get() {
            return "Encrypting"
        }
}

fun main(args: Array<String>) {
    val password = args[0]
    val input = args[1]

    println("${App().text} $input")

    val mapper = ObjectMapper(YAMLFactory())

    val value = mapper.readTree(ClassLoader.getSystemResource("config.yaml").readBytes())

    val keywords: MutableList<String> = ArrayList();

    value.withArray<JsonNode>("keywords").elements()
            .forEachRemaining { keywords.add(it.asText()) }

    CleanProperties().encrypt(input, keywords, password, "${input}_enc_sorted")
    FileEncryptor().encrypt(input, keywords, password, "${input}_enc")
}
