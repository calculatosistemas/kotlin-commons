package br.com.calculato.commons

import br.com.calculato.commons.number.div
import br.com.calculato.commons.number.toDecimal
import br.com.calculato.commons.number.zero
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
        }
        "Duration.toDecimal"{
            Duration.parse("PT12H33M").toDecimal() shouldBe 12.55.toBigDecimal().setScale(6)
            Duration.ofMinutes(90).toDecimal() shouldBe 1.5.toBigDecimal().setScale(6)
        }
    }
}
