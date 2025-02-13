
fun plusNumbers(firstNum: Int, secondNum: Int): Int {
    val result: Int = firstNum + secondNum
    return result
}

//함수를 사용(호출)하는 방법
plusNumbers(firstNum = 10, secondNum = 20)
plusNumbers(10, 20)
val result: Int = plusNumbers(firstNum = 5, secondNum = 7)
println(result)

//기본값이 있는 함수를 선언하는 방법
fun plusNumbersWithDefalt(firstNum: Int, secondNum: Int = 10): Int {
    return firstNum + secondNum
}

val result2: Int = plusNumbersWithDefalt(firstNum = 10)
val result3: Int = plusNumbersWithDefalt(firstNum = 10, secondNum = 20)

println(result2)
println(result3)

fun plusNumberWithNoReturn(firstNum: Int, secondNum: Int): Unit {
    val result: Int = firstNum + secondNum
    println(result)
}
fun plusNumberWithNoReturn2(firstNum: Int, secondNum: Int){
    val result: Int = firstNum + secondNum
    println(result)
}
fun plusNumberWithNoReturn3(firstNum: Int, secondNum: Int){
    val result: Int = firstNum + secondNum
    println(result)
    return
}

plusNumberWithNoReturn(100, 200)
plusNumberWithNoReturn2(100, 200)
plusNumberWithNoReturn3(100, 200)

//함수선언을 간단하게 하는 방법

fun shortPlusNumbers(firstNum: Int, secondNum: Int) = firstNum + secondNum

val result4: Int = shortPlusNumbers(firstNum = 10, secondNum = 100)
println(result4)

fun plusMultipleNumber(vararg numbers: Int): Unit {
    println(numbers.contentToString())
}
plusMultipleNumber(1,2,3,4,5)