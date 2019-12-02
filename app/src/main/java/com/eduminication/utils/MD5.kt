package com.eduminication.utils

import java.security.MessageDigest

fun md5Encode(text: String): String {
    //获取md5加密对象
    //对字符串加密，返回字节数组
    val digest: ByteArray = MessageDigest.getInstance("MD5").digest(text.toByteArray())
    val sb = StringBuffer()
    for (b in digest) {
        //获取低八位有效值
        //将整数转化为16进制
        var hexString = Integer.toHexString(b.toInt() and 0xff)

        if (hexString.length == 1)//如果是一位的话，补0
            hexString = "0$hexString"

        sb.append(hexString)
    }
    return sb.toString()
}
