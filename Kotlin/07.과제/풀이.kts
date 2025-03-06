//1번
val A: Int = 10
val B: Int = 10
val boolean = A == B
println(boolean)
//자료형이 다르면 비교연산자를 사용할수 없다
//2번
val A1: Int = 10
val B2: Int = A1 * 2

println(B2)

//3번
fun checkGrade(score: Int): String {
    return when {
        score >= 90 -> "A학점"  //
        score in 80..89 -> "B학점" //
        score in 70..79 -> "C학점"
        else -> "F학점"
    }
}

val checkGrade = checkGrade(90)
println(checkGrade)

//4번
fun gradeTest(correctCount: Int): Any {
    return if (correctCount > 20) "20개 이하로 넣어주세요" else correctCount * 5
}
println(gradeTest(10))
println(gradeTest(21))

//5번
fun plustTwoNumbers(firstNum: Int?, secondNum: Int?): Int {
    val first: Int = if (firstNum == null) 0 else firstNum
    val second: Int = if (secondNum == null) 0 else secondNum
    return first + second
}

println(plustTwoNumbers(null, 10)) // 10
println(plustTwoNumbers(10, null)) // 10
println(plustTwoNumbers(10, 10)) // 20
println(plustTwoNumbers(null, null)) // 0
