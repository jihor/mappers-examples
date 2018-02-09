package ru.jihor.tests

import ma.glasnost.orika.impl.DefaultMapperFactory
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import ru.jihor.LoggerAwareSpek
import ru.jihor.orika.converters.ChangesToLastChangeConverter
import ru.jihor.orika.converters.StringToGenderConverter
import ru.jihor.orika.model.source.SourcePerson
import ru.jihor.orika.model.target.Gender
import ru.jihor.orika.model.target.TargetPerson
import java.time.LocalDate
import kotlin.test.assertEquals
import kotlin.test.assertNull


/**
 *
 *
 * @author jihor (dmitriy_zhikharev@rgs.ru)
 * Created on 2018-02-08
 */

class OrikaTest : LoggerAwareSpek({

    val source = SourcePerson().apply {
        firstName = John
        lastName = Smith
        birthDate = newYearsEve
        comment = desc
        gender = female
        changes = mapOf(married, divorced, changedGender)
    }

    describe("Orika mapping") {

        val mapperFactory = DefaultMapperFactory.Builder().build()

        val changesToLastChangeConverterId = "changesToLastChangeConverter"
        val stringToGenderConverterId = "stringToGenderConverter"
        val changesToLastChangeDescriptionConverterId = "changesToLastChangeDescriptionConverter"

        mapperFactory.converterFactory.registerConverter(changesToLastChangeConverterId, ChangesToLastChangeConverter())
        mapperFactory.converterFactory.registerConverter(stringToGenderConverterId, StringToGenderConverter())
        mapperFactory.converterFactory.registerConverter(changesToLastChangeDescriptionConverterId, ru.jihor.orika.converters.ChangesToLastChangeDescriptionConverter())

        mapperFactory.classMap(SourcePerson::class.java, TargetPerson::class.java)
                .field("comment", "description")
                .fieldMap("changes", "lastChanged").converter(changesToLastChangeConverterId).add()
                .fieldMap("gender", "gender").converter(stringToGenderConverterId).add()
                .fieldMap("changes", "lastChangedDescription").converter(changesToLastChangeDescriptionConverterId).add()
                .byDefault()
                .register()


        val mapper = mapperFactory.mapperFacade

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