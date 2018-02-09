package ru.jihor.tests

import org.dozer.DozerBeanMapper
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import ru.jihor.LoggerAwareSpek
import ru.jihor.dozer.model.source.SourcePerson
import ru.jihor.dozer.model.target.Gender
import ru.jihor.dozer.model.target.TargetPerson
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull


/**
 *
 *
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */

class DozerTest : LoggerAwareSpek({

    val source = SourcePerson().apply {
        firstName = John
        lastName = Smith
        birthDate = newYearsEve
        comment = desc
        gender = female
        changes = mapOf(married, divorced, changedGender)
    }

    describe("Dozer xml-based mapping") {

        val mappingFiles:ArrayList<String> = ArrayList()
        mappingFiles.add("dozerJdk8Converters.xml")
        mappingFiles.add("dozer/personMapping.xml")
        val mapper = DozerBeanMapper()
        mapper.mappingFiles = mappingFiles

        it("Should map person correctly") {
            val target = mapper.map(source, TargetPerson::class.java)
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