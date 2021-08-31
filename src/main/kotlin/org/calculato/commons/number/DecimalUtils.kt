package org.calculato.commons.number

import org.calculato.commons.time.BRAZIL
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.Duration
import kotlin.math.abs
import kotlin.math.roundToInt

fun zero(): BigDecimal = BigDecimal.ZERO

/**
 * Operator Div Standard to scale 6 and RoundingMode HALF_EVEN
 */
operator fun BigDecimal.div(other: BigDecimal): BigDecimal =
    if (other.setScale(6, RoundingMode.HALF_EVEN) == zero().setScale(6, RoundingMode.HALF_EVEN)) other
    else this.divide(other, 6, RoundingMode.HALF_EVEN)

/**
 * Converts [this] clock time to decimal number
 */
fun Duration.toDecimal(): BigDecimal = (this.toMinutes().toBigDecimal() / 60.toBigDecimal())


/**
 * Creates a BigDecimal value from [this] Short
 */
fun Short.toBigDecimal(): BigDecimal = BigDecimal.valueOf(this.toLong())

/**
 * Returns [this] BigDecimal with brazilian monetary scale format
 */
fun BigDecimal.monetary() = setScale(2, RoundingMode.HALF_EVEN)

/**
 * Converts [this] decimal to Brazil's format monetary
 */
fun BigDecimal.formatMonetario(): String =
    NumberFormat.getInstance(BRAZIL).format(this.setScale(2, RoundingMode.HALF_EVEN))

/**
 * Converts [this] decimal to Brazil's format decimal
 */
fun BigDecimal.format(): String =
    NumberFormat.getInstance(BRAZIL).format(this)

fun BigDecimal.formatExtenso(): String {
    val valor = setScale(2, RoundingMode.HALF_EVEN).toDouble()
    if (valor == 0.0) return "zero"
    val inteiro = abs(valor).toLong() // parte inteira do valor
    val resto = valor - inteiro // parte fracionária do valor
    var valorString = inteiro.toString()
    if (valorString.length > 15) return "Erro: valor superior a 999 trilhões."
    var valorExtenso = ""
    var classePorExtenso: String
    var valorClasseString: String
    val centavos = (resto * 100).roundToInt().toString()
    val unidade = arrayOf(
        "", "um", "dois", "três", "quatro", "cinco",
        "seis", "sete", "oito", "nove", "dez", "onze",
        "doze", "treze", "quatorze", "quinze", "dezesseis",
        "dezessete", "dezoito", "dezenove"
    )
    val centena = arrayOf(
        "", "cento", "duzentos", "trezentos",
        "quatrocentos", "quinhentos", "seiscentos",
        "setecentos", "oitocentos", "novecentos"
    )
    val dezena = arrayOf(
        "", "", "vinte", "trinta", "quarenta", "cinquenta",
        "sessenta", "setenta", "oitenta", "noventa"
    )
    val qualificaSingular = arrayOf("", "mil", "milhão", "bilhão", "trilhão")
    val qualificaPlural = arrayOf("", "mil", "milhões", "bilhões", "trilhões")

    // definindo o extenso da parte inteira do valor
    var valorClasse: Int
    var unid: Int
    var dez: Int
    var cent: Int
    var tamanho: Int
    var posicaoClasse = 0
    var isUmReal = false
    var isAteSegundaClasse = false
    while (valorString != "0") {
        tamanho = valorString.length
        // retira das classes, por exemplo, para 123456789:
        // 1a. parte = 789 (classe das unidades)
        // 2a. parte = 456 (classe dos milhares)
        // 3a. parte = 123 (classe dos milhao)
        if (tamanho > 3) {
            valorClasseString = valorString.substring(tamanho - 3, tamanho)
            valorString = valorString.substring(0, tamanho - 3)
        } else { // última parte do valor
            valorClasseString = valorString
            valorString = "0"
        }
        if (valorClasseString != "000") {
            classePorExtenso = ""
            if (valorClasseString == "100") classePorExtenso = "cem" else {
                valorClasse = valorClasseString.toInt(10) // para valorClasse = 371, tem-se:
                cent = valorClasse / 100 // cent = 3 (centena trezentos)
                dez = valorClasse % 100 / 10 // dez  = 7 (dezena setenta)
                unid = valorClasse % 100 % 10 // unid = 1 (unidade um)
                if (cent != 0) classePorExtenso = centena[cent]
                if (valorClasse % 100 in 1..19) {
                    classePorExtenso =
                        if (classePorExtenso.isNotEmpty()) classePorExtenso + " e " + unidade[valorClasse % 100]
                        else unidade[valorClasse % 100]
                } else {
                    classePorExtenso =
                        if (classePorExtenso.isNotEmpty() && dez != 0) classePorExtenso + " e " + dezena[dez]
                        else if (dez > 0) dezena[dez] else classePorExtenso
                    if (unid != 0) {
                        classePorExtenso =
                            if (classePorExtenso.isNotEmpty()) classePorExtenso + " e " + unidade[unid] else unidade[unid]
                    }
                }
            }
            if (valorClasseString == "1" || valorClasseString == "001") {
                if (posicaoClasse == 0) // 1a. parte do valor (um real)
                    isUmReal = true else classePorExtenso = classePorExtenso + " " + qualificaSingular[posicaoClasse]
            } else if (posicaoClasse != 0) classePorExtenso = classePorExtenso + " " + qualificaPlural[posicaoClasse]
            valorExtenso = if (valorExtenso.isNotEmpty()) "$classePorExtenso, $valorExtenso" else classePorExtenso
        }
        if ((posicaoClasse == 0 || posicaoClasse == 1) && valorExtenso.isNotEmpty()) isAteSegundaClasse =
            true // tem centena ou mil no valor
        posicaoClasse += 1 // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
    }
    if (valorExtenso.isNotEmpty()) {
        valorExtenso = if (isUmReal) "$valorExtenso real"
        else if (isAteSegundaClasse) "$valorExtenso reais" else "$valorExtenso de reais"
    }

    // definindo o extenso dos centavos do valor
    if (centavos != "0") { // valor com centavos
        if (valorExtenso.isNotEmpty()) // se não é valor somente com centavos
            valorExtenso = "$valorExtenso e "
        if (centavos == "1") valorExtenso += "um centavo" else {
            valorClasse = centavos.toInt(10)
            if (valorClasse <= 19) valorExtenso += unidade[valorClasse] else { // para valorClasse = 37, tem-se:
                unid = valorClasse % 10 // unid = 37 % 10 = 7 (unidade sete)
                dez = valorClasse / 10 // dez  = 37 / 10 = 3 (dezena trinta)
                valorExtenso += dezena[dez]
                if (unid != 0) valorExtenso = valorExtenso + " e " + unidade[unid]
            }
            valorExtenso = "$valorExtenso centavos"
        }
    }
    return valorExtenso
}
