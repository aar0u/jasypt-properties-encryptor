/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package helper.jasypt

import helper.jasypt.App
import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun testAppHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.text, "app should have a greeting")
    }
}