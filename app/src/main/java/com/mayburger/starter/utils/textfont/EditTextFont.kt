package com.mayburger.starter.utils.textfont

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.mayburger.starter.R
import com.mayburger.starter.base.BaseApplication


/**
 * Created by mayburger
 */
class EditTextFont(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    init {
        val array = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.TextFont,
                0, 0)
        val typefaceType: Int
        try {
            typefaceType = array.getInteger(R.styleable.EditTextFont_typeface, 0)
        } finally {
            array.recycle()
        }

        setCustomFont(typefaceType)
        includeFontPadding = false
    }

    fun setCustomFont(typefaceType: Int): Boolean {
        if (BaseApplication.instance?.getTypeFace(typefaceType) != null) {
            typeface = BaseApplication.instance?.getTypeFace(typefaceType)
            return true
        }
        return false
    }
}