package org.calculato.commons.time

import org.calculato.commons.time.dateInLastDayOf
import org.calculato.commons.time.dateOf
import org.calculato.commons.time.timeOf
import org.calculato.commons.time.withLastDayOfMonth
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalTime

class LocalDateUtilsTest : StringSpec({
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
})
