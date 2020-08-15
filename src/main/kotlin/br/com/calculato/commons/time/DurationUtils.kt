package br.com.calculato.commons.time

import br.com.calculato.commons.text.emptyString
import org.apache.commons.lang3.time.DurationFormatUtils
import java.time.Duration

/**
 *
 */
fun Duration.format(): String =
    DurationFormatUtils.formatDuration(
        toMillis(), "HH:mm${if (secondsPart != 0.toLong()) {
            ":ss"
        } else emptyString()}"
    )