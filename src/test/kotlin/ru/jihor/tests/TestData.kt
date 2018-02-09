package ru.jihor.tests

import java.time.LocalDate

/**
 *
 *
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-09
 */

const val Smith = "Smith"
const val John = "John"
const val desc = "Not a nice guy"
const val female = "Female"

val newYearsEve = LocalDate.of(1878, 12, 31)

val married = LocalDate.of(1898, 10, 28) to "married"
val divorced = LocalDate.of(1899, 11, 14) to "divorced"
val changedGender = LocalDate.of(1901, 5, 7) to "changed gender"