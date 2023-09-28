package cl.daracenad.elearning.quiz.domain.model.sys

import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity

class LocalParameter(
    val key:String,
    val exists:Boolean=false, val valueString:String="", val valueInteger:Int=-1, val valueBA:ByteArray?=null)

fun LocalParameterEntity.toModel(exist:Boolean) = LocalParameter(key = key,valueString = valueString, valueInteger = valueInt,valueBA=valueBA, exists = exist)