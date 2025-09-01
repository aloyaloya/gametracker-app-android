package ru.aloyaloya.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// TODO: Add KDoc

data class TopBarAction(
    @DrawableRes val iconResId: Int,
    @StringRes val descriptionResId: Int,
    val actionUnit: () -> Unit,
    val actionFilled: Boolean = false
)