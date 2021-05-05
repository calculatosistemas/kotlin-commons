package org.calculato.commons.text

import org.calculato.commons.time.format
import org.calculato.commons.time.hoursPart
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Duration

private val duration = Duration.parse("PT12H33M53S")
private enum class Enum { PESSOA_FISICA }

class StringUtilsTest : StringSpec() {
    init {
        "emptyString"{
            emptyString() shouldBe ""
        }
        "pluralWithInd"{
            1.run { "$this ${"dia".plural(this)} de trabalho" } shouldBe "1 dia de trabalho"
            12.run { "$this ${"dia".plural(this)} de trabalho" } shouldBe "12 dias de trabalho"
        }
        "pluralWithDuration"{
            Duration.ofHours(1)
                .run { "${this.hoursPart} ${"hora".plural(this)} de trabalho" } shouldBe "1 hora de trabalho"
            Duration.ofHours(12)
                .run { "${this.hoursPart} ${"hora".plural(this)} de trabalho" } shouldBe "12 horas de trabalho"
        }
        "durationFormatToString"{
            Duration.parse("PT12H33M").format() shouldBe "12:33"
            duration.format() shouldBe "12:33:53"
        }
        "normalizeText"{
            Enum.PESSOA_FISICA.normalizeText() shouldBe "pessoa fisica"
        }
        "Int.format"{
            1.format(3) shouldBe "001"
            15.format(3) shouldBe "015"
            115.format(3) shouldBe "115"
        }
    }
}
