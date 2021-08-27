package org.calculato.commons.time

import io.kotest.assertions.asClue
import org.calculato.commons.time.actualPeriodoMonth
import org.calculato.commons.time.firstPeriodoDate
import org.calculato.commons.time.lastPeriodoDate
import org.calculato.commons.time.periodoMonth
import org.calculato.commons.time.periodoYear
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class PeriodoUtilsTest : StringSpec({

    val periodoJan18 = 201801
    val periodoFeb19 = 201902
    val periodoJun20 = 202006
    val periodoDec21 = 202112
    val periodoDecTer = 202013
    val periodoError = 180016

    "firstPeriodoDate"{
        periodoJan18.firstPeriodoDate() shouldBe LocalDate.parse("2018-01-01")
        periodoFeb19.firstPeriodoDate() shouldBe LocalDate.parse("2019-02-01")
        periodoJun20.firstPeriodoDate() shouldBe LocalDate.parse("2020-06-01")
        periodoDec21.firstPeriodoDate() shouldBe LocalDate.parse("2021-12-01")

        with(shouldThrow<IllegalStateException> {
            periodoError.firstPeriodoDate()
        }) {
            this.message shouldBe "16 is not an actual periodo month"
        }

    }

    "lastPeriodoDate"{
        periodoJan18.lastPeriodoDate() shouldBe LocalDate.parse("2018-01-31")
        periodoFeb19.lastPeriodoDate() shouldBe LocalDate.parse("2019-02-28")
        periodoJun20.lastPeriodoDate() shouldBe LocalDate.parse("2020-06-30")
        periodoDec21.lastPeriodoDate() shouldBe LocalDate.parse("2021-12-31")

        with(shouldThrow<IllegalStateException> {
            periodoError.lastPeriodoDate()
        }
        ) {
            this.message shouldBe "16 is not an actual periodo month"
        }
    }

    "actualPeriodoMonth"{
        periodoJan18.actualPeriodoMonth() shouldBe 1
        periodoFeb19.actualPeriodoMonth() shouldBe 2
        periodoJun20.actualPeriodoMonth() shouldBe 6
        periodoDec21.actualPeriodoMonth() shouldBe 12

        with(shouldThrow<IllegalStateException> { periodoDecTer.actualPeriodoMonth() }) {
            this.message shouldBe "13 is not an actual periodo month"
        }

        with(shouldThrow<IllegalStateException> { periodoError.actualPeriodoMonth() }) {
            this.message shouldBe "16 is not an actual periodo month"
        }
    }

    "periodoMonth"{
        periodoJan18.periodoMonth() shouldBe 1
        periodoFeb19.periodoMonth() shouldBe 2
        periodoJun20.periodoMonth() shouldBe 6
        periodoDec21.periodoMonth() shouldBe 12
        periodoDecTer.periodoMonth() shouldBe 13

        with(shouldThrow<IllegalStateException> { periodoError.periodoMonth() }) {
            this.message shouldBe "Cannot convert 16 to month"
        }
    }

    "periodoYear"{
        periodoJan18.periodoYear() shouldBe 2018
        periodoFeb19.periodoYear() shouldBe 2019
        periodoJun20.periodoYear() shouldBe 2020
        periodoDec21.periodoYear() shouldBe 2021
        periodoError.periodoYear() shouldBe 1800
    }

    "getPeriodo"{
        LocalDate.of(2020,2,3).getPeriodo() shouldBe 202002
        LocalDate.of(2019,12,1).getPeriodo() shouldBe 201912
    }
    "isInPeriodo"{
        LocalDate.of(2020,3,1).asClue {
            it.isInPeriodo(202003) shouldBe true
            it.isInPeriodo(202004) shouldBe false
        }
    }
    "formatPeriodoBR"{
        202006.formatPeriodoBR() shouldBe "Junho/2020"
        201901.formatPeriodoBR() shouldBe "Janeiro/2019"
        202102.formatPeriodoBR() shouldBe "Fevereiro/2021"
    }
    "periodoOf"{
        periodoOf(2020,1) shouldBe 202001
        periodoOf(2020,3) shouldBe 202003
        periodoOf(2020,10) shouldBe 202010
        periodoOf(2020,11) shouldBe 202011
        periodoOf(2020,12) shouldBe 202012
        periodoOf(2020,13) shouldBe 202013
    }
})
