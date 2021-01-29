package org.calculato.commons.time

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Duration

private val duration = Duration.parse("PT12H33M53S")

class NumberUtilsTest : StringSpec() {
    init {
        "Duration.secondsPart"{
            duration.secondsPart shouldBe 53
        }
        "Duration.minutesPart"{
            duration.minutesPart shouldBe 33
        }
        "Duration.hoursPart"{
            duration.hoursPart shouldBe 12
        }
        "toMinutesOfDuration"{
            150.toShort().toMinutesOfDuration() shouldBe Duration.ofMinutes(150)
        }
        "div"{
            Duration.ofMinutes(150).div(2) shouldBe Duration.ofMinutes(75)
            Duration.ofMinutes(150).div(3) shouldBe Duration.ofMinutes(50)
        }
        "times"{
            Duration.ofMinutes(20).times(2) shouldBe Duration.ofMinutes(40)
            Duration.ofMinutes(20).times(3) shouldBe Duration.ofMinutes(60)
        }
        "hoursToDuration"{
            0.01.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(1)
            0.03.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(2)
            0.05.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(3)
            0.28.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(17)
            0.383.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(23)
            0.40.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(24)
            0.43.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(26)
            0.43333.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(26)
            0.44.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(26)
            0.5.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(30)
            0.55.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(33)
            0.65.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(39)
            0.66.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(40)
            1.5.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(90)
            1.33.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(80)
            2.toBigDecimal().hoursToDuration() shouldBe Duration.ofMinutes(120)
        }
    }
}
