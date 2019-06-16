package com.ywn.common.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass
import kotlin.annotation.Target
import kotlin.annotation.Retention
import kotlin.annotation.MustBeDocumented

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class ViewModelKey(val value: KClass<out ViewModel>)