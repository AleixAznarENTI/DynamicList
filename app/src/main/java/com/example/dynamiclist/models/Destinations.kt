package com.example.dynamiclist.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Destinations(
    @StringRes val nameResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val singerResourceId: Int,
    @StringRes val albumResourceId: Int,
    @StringRes val viewsResourceId: Int,
    @StringRes val dateResourceId: Int,
    @StringRes val youtubeResourceId: Int
)
