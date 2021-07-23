package helper.jasypt

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig

class Encryptor {
    private fun getEncryptor(password: String): PooledPBEStringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.password = password
        config.algorithm = "PBEWITHHMACSHA512ANDAES_256"
        config.setKeyObtentionIterations("1000")
        config.setPoolSize("1")
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator")
        config.stringOutputType = "base64"
        encryptor.setConfig(config)
        return encryptor
    }

    fun encryptValue(theKey: String, theValue: String, keywords: MutableList<String>, password: String): String {
        var outputValue = theValue
        if (theKey != "jasypt.encryptor.password" && !theValue.startsWith("ENC(")) {
            for (keyword in keywords) {
                if (theKey.contains(keyword, ignoreCase = true)) {
                    val encrypted = getEncryptor(password).encrypt(theValue)
                    println(" $theKey: $theValue -> $encrypted")
                    outputValue = "ENC($encrypted)"
                }
            }
        }
        return outputValue
    }
}