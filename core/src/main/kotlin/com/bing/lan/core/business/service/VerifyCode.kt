package com.bing.lan.core.business.service

import lombok.Getter
import lombok.Setter
import lombok.ToString
import java.util.*

@Setter
@Getter
@ToString
class VerifyCode {
    lateinit var phoneNumber: String
    lateinit var randomCode: String
    lateinit var lastSendTime: Date
    lateinit var content: String

    constructor() : super() {}

    constructor(phoneNumber: String, randomCode: String, lastSendTime: Date,
                content: String) : super() {
        this.phoneNumber = phoneNumber
        this.randomCode = randomCode
        this.lastSendTime = lastSendTime
        this.content = content
    }
}
