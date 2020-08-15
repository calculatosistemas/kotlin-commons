package br.com.calculato.commons

import br.com.calculato.commons.text.emptyString
import br.com.calculato.commons.text.format
import br.com.calculato.commons.text.plural
import br.com.calculato.commons.time.hoursPart
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Duration

private val duration = Duration.parse("PT12H33M53S")

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
    }
}
