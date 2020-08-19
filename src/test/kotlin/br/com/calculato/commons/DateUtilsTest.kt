package br.com.calculato.commons

import br.com.calculato.commons.time.dateInLastDayOf
import br.com.calculato.commons.time.dateOf
import br.com.calculato.commons.time.formatBR
import br.com.calculato.commons.time.timeOf
import br.com.calculato.commons.time.withLastDayOfMonth
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalTime

class DateUtilsTest : StringSpec({
    "withLastDayOfMonth"{
        LocalDate.of(2020, 4, 27).withLastDayOfMonth() shouldBe LocalDate.of(2020, 4, 30)
    }
    "dateInLastDayOf"{
        dateInLastDayOf(2020, 4) shouldBe LocalDate.of(2020, 4, 30)
    }
    "dateof"{
        dateOf(2020, 4, 27) shouldBe LocalDate.of(2020, 4, 27)
    }
    "timeOfWithoutSeconds"{
        timeOf(23, 59) shouldBe LocalTime.of(23, 59)
    }
    "timeOfWithSeconds"{
        timeOf(23, 59, 45) shouldBe LocalTime.of(23, 59, 45)
    }
    "LocalDateToformatBR"{
        dateOf(2020,8,17).formatBR() shouldBe "17/08/2020"
    }
})
