package com.cxj.kotlinlearning.lambda
//Lambda表达式作为接收者类型
class HTML {
    fun body() {
        println("Lambda表达式作为接收者类型")
    }
}
fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()//创建接收者对象
    html.init()
    return html
}

/**
 * 闭包
 */
//1.让函数返回一个函数，并携带状态
fun biBao01(b: Int): () -> Int  {
    var a = 3
    return fun() : Int {
        a++
        return a + b
    }
}
//2.引用外部变量，并改变外部变量的值
fun biBao02() {
    var sum = 0
    val arr = arrayOf(1,3,5,7,9)
    arr.filter { it < 7 }.forEach { sum += it }
    println(sum)
}




fun main() {
    //Lambda表达式作为接收者类型
    html { body() }

    //闭包
    println(biBao01(1).invoke())
    println(biBao02())
}