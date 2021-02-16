package com.mayburger.twitter.utils.textfont

import android.content.Context
import android.graphics.Typeface

/**
 * Created by mayburger
 */
class TypeFactory internal constructor(context: Context) {

    private val BOLD = "fonts/Lato-Bold.ttf"
    private val MEDIUM = "fonts/Lato-Medium.ttf"
    private val REGULAR = "fonts/Lato-Regular.ttf"
    private val BLACK = "fonts/Lato-Black.ttf"

    val bold: Typeface
    val medium: Typeface
    val black:Typeface
    val regular: Typeface

    init {
        bold = Typeface.createFromAsset(context.assets, BOLD)
        black = Typeface.createFromAsset(context.assets, BLACK)
        medium = Typeface.createFromAsset(context.assets, MEDIUM)
        regular = Typeface.createFromAsset(context.assets, REGULAR)
    }
}
