package cl.daracenad.elearning.quiz.domain.model

import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity

class AppParameter(
    val key:String,
    val exists:Boolean=false, val valueString:String="", val valueInteger:Int=-1, val valueBA:ByteArray?=null)

fun AppParameterEntity.toModel(exist:Boolean) = AppParameter(key = key,valueString = valueString, valueInteger = valueInt,valueBA=valueBA, exists = exist)