package ru.jihor.tests

import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import ru.jihor.LoggerAwareSpek
import ru.jihor.mapping.selma.mappers.PersonMapper.applyPostMapping
import ru.jihor.mapping.selma.mappers.PersonMapperSelmaGeneratedClass
import ru.jihor.model.source.SourcePerson
import ru.jihor.model.target.Gender
import kotlin.test.assertEquals
import kotlin.test.assertNull


/**
 *
 *
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */

class SelmaTest : LoggerAwareSpek({

    val source = SourcePerson().apply {
        firstName = John
        lastName = Smith
        birthDate = newYearsEve
        comment = desc
        gender = female
        changes = mapOf(married, divorced, changedGender)
    }

    describe("Selma mapping") {
        it("Should map person correctly") {
            val mapper = PersonMapperSelmaGeneratedClass()
// or
//            val mapper = Selma.builder(PersonMapper::class.java).build()
            val target = applyPostMapping(source, mapper.asTargetPerson(source))
            logger.info("Source person = $source")
            logger.info("Target person = $target")
            assertEquals(John, target.firstName)
            assertNull(target.middleName)
            assertEquals(Smith, target.lastName)
            assertEquals(newYearsEve, target.birthDate)
            assertEquals(desc, target.description)
            assertEquals(Gender.FEMALE, target.gender)
            assertEquals(changedGender.first, target.lastChanged)
            assertEquals(changedGender.second, target.lastChangedDescription)
        }
    }
})