package ru.makarov.kotlin.example.core.utils

import android.content.Context
import ru.makarov.kotlin.example.preferences.Preference
import kotlin.reflect.KProperty

/**
 * @author Maxim Makarov
 * @since 13.08.2017
 */
object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()

    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
    //usage: var zipCode: Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)
}

class NotNullSingleValueVar<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} " + "not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}
