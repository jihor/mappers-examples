package ru.jihor

import mu.KLogging
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

/**
 *
 *
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */
abstract class LoggerAwareSpek(spec: Spec.() -> Unit): Spek(spec) {
    companion object: KLogging()
}