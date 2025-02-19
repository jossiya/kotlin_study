class Calculator {
    var result:Int=0
        set(value) {
            field = value
            println("중간 계산값: " + field)
        }
    val sum: (Int) -> Int = {inputNumber ->
        result += inputNumber
        result
    }
    val minus: (Int) -> Int = {inputNumber ->
        result -= inputNumber
        result
    }

    val multiply: (Int)->Int ={inputNumber ->
        result *= inputNumber
        result
    }

    val device: (Int) -> Int = {inputNumber ->
        result /= inputNumber
        result
    }
}

val calculator1 = Calculator ()
println(calculator1.sum(10))
println(calculator1.minus(3))
println(calculator1.multiply(4))
println(calculator1.device(2))

class Calculater2 constructor(val initialValue: Int = 0) {
    var result: Int

    init {
        result = initialValue
    }

    val calculate: (funtion: Char, inputNumber: Int )-> Int ={funtion , inputNumber ->
        when (funtion) {
            '+' -> result += inputNumber
            '-' -> result -= inputNumber
            '*' -> result *= inputNumber
            '/' -> if (inputNumber != 0) result /= inputNumber else throw IllegalArgumentException("0으로 나눌 수 없습니다!")
            else -> throw IllegalArgumentException("지원하지 않는 연산입니다: $funtion")
        }
        result
    }
}

val calculater2 = Calculater2()
println(calculater2.calculate('+',10))
println(calculater2.calculate('-',2))
println(calculater2.calculate('*',13))
//println(calculater2.calculate('/',0))

class Calculater3{
    var result: Int= 0

    val calculate: (operaters: List<Char>, inputNumbers: List<Int>) -> Int = { operaters, inputNumbers ->
        operaters.forEachIndexed{ index: Int, operater:Char ->
        _calculate(operater,inputNumbers[index])
        }
        result
    }
   private val _calculate:(funtion:Char, inputNumber: Int) ->Int ={funtion, inputNumber ->
        when (funtion) {
            '+' -> result += inputNumber
            '-' -> result -= inputNumber
            '*' -> result *= inputNumber
            '/' -> if (inputNumber != 0) result /= inputNumber else throw IllegalArgumentException("0으로 나눌 수 없습니다!")
            else -> throw IllegalArgumentException("지원하지 않는 연산입니다: $funtion")
        }
        result
    }
}

val calculater3 = Calculater3()
calculater3.calculate(listOf<Char>('+', '-'), listOf<Int>(10, 20))
println(calculater3.result)
