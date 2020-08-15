package br.com.calculato.commons

import br.com.calculato.commons.time.hoursPart
import br.com.calculato.commons.time.minutesPart
import br.com.calculato.commons.time.secondsPart
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
    }
}
