package com.playvault.common

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
    data class Dynamic(val value: String) : UiText()
    class StringRes(@StringRes val resId: Int, vararg val args: Any) : UiText()

    fun asString(context: Context): String = when (this) {
        is Dynamic -> value
        is StringRes -> context.getString(resId, *args)
    }

    companion object { fun of(value: String) = Dynamic(value) }
}
