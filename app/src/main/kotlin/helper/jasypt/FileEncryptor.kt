package helper.jasypt

import java.io.File

class FileEncryptor {
    fun encrypt(input: String, keywords: MutableList<String>, password: String, output: String) {
        val lines = File(input).bufferedReader().readLines();
        val stringBuilder = StringBuilder()
        var hasPwd = false
        for (line in lines) {
            var newLine = line;
            if (!line.startsWith("#")) {
                val split = line.split("=")
                if (split.size == 2) {
                    val theKey = split[0]
                    var theValue = split[1]
                    theValue = Encryptor().encryptValue(theKey, theValue, keywords, password)
                    if (theKey.trim() == "jasypt.encryptor.password") {
                        hasPwd = true;
                        theValue = password;
                    }
                    newLine = "$theKey=$theValue"
                }
            }
            stringBuilder.appendLine(newLine)
        }
        if (!hasPwd) {
            stringBuilder.insert(0, "jasypt.encryptor.password=$password\n")
        }
        File(output).bufferedWriter().use { out -> out.write(stringBuilder.toString()) }
    }
}