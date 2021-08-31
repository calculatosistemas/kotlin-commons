package org.calculato.commons.number

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.Duration

class DecimalUtilsTest : StringSpec() {
    init {
        "zero"{
            zero() shouldBe BigDecimal.ZERO
        }
        "decimalCalcDiv"{
            val x = BigDecimal.valueOf(1500)
            val y = BigDecimal.valueOf(31)
            val z = BigDecimal.valueOf(21)

            (x.div(y)) shouldBe BigDecimal.valueOf(48.387097)
            (x / y) shouldBe BigDecimal.valueOf(48.387097)
            (x / y * z) shouldBe BigDecimal.valueOf(1016.129037)

            (15.toBigDecimal() / zero()) shouldBe zero()
            (1.toBigDecimal() / 0.0.toBigDecimal()) shouldBe 0.00.toBigDecimal()
        }
        "Duration.toDecimal"{
            Duration.parse("PT12H33M").toDecimal() shouldBe 12.55.toBigDecimal().setScale(6)
            Duration.ofMinutes(90).toDecimal() shouldBe 1.5.toBigDecimal().setScale(6)
        }
        "BigDecimal::formatMonetario" {
            13345.98.toBigDecimal().formatMonetario() shouldBe "13.345,98"
            13345.989.toBigDecimal().formatMonetario() shouldBe "13.345,99"
            13345.983.toBigDecimal().formatMonetario() shouldBe "13.345,98"
            13345.98345.toBigDecimal().format() shouldBe "13.345,983"
        }

        "BigDecimal.formatExtenso()"{
            800.toBigDecimal().formatExtenso() shouldBe "oitocentos reais"
            1500.toBigDecimal().formatExtenso() shouldBe "um mil, quinhentos reais"
            1513.toBigDecimal().formatExtenso() shouldBe "um mil, quinhentos e treze reais"
            13345.98.toBigDecimal().formatExtenso() shouldBe
                "treze mil, trezentos e quarenta e cinco reais e noventa e oito centavos"
            13345.989.toBigDecimal().formatExtenso() shouldBe
                "treze mil, trezentos e quarenta e cinco reais e noventa e nove centavos"
            zero().formatExtenso() shouldBe "zero"
            1540.toBigDecimal().formatExtenso() shouldBe "um mil, quinhentos e quarenta reais"
            1000.toBigDecimal().formatExtenso() shouldBe "um mil reais"
            0.30.toBigDecimal().formatExtenso() shouldBe "trinta centavos"
            1.toBigDecimal().formatExtenso() shouldBe "um real"
            1.05.toBigDecimal().formatExtenso() shouldBe "um real e cinco centavos"
            1.19.toBigDecimal().formatExtenso() shouldBe "um real e dezenove centavos"
        }

    }
}
