package com.dextto

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

class EmailService {
    private val from = System.getenv("EMAIL_FROM")?: ""
    private val key = System.getenv("EMAIL_KEY")?: ""

    /**
     * @param to: recipient's email address
     */
    fun send(to: String) {
        SimpleEmail().apply {
            setHostName("smtp.googlemail.com")
            setSmtpPort(465)
            setAuthenticator(DefaultAuthenticator(from, key))
            setSSLOnConnect(true)
            setFrom(to)
            setSubject("subject")
            setMsg("message")
            addTo(to)
        }.send() // will throw email-exception
    }
}