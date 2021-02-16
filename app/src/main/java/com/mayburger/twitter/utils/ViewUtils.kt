package com.mayburger.twitter.utils

import android.animation.*
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField


object ViewUtils {

    private var screenWidth = 0
    private var screenHeight = 0

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

    fun View.getCoordinates(): IntArray {
        return IntArray(2).apply {
            this@getCoordinates.getLocationInWindow(this)
        }
    }

    fun View.flipX(
        duration: Long? = 400,
        onFlip: (() -> Unit)? = {},
        onEnd: (() -> Unit)? = {},
        after: Long? = 0
    ) {
        Handler().postDelayed({
            AnimatorSet().apply {
                playSequentially(
                    ObjectAnimator.ofFloat(this@flipX, View.SCALE_X, 0f).apply {
                        setDuration((duration ?: 400) / 2)
                    },
                    ObjectAnimator.ofFloat(this@flipX, View.SCALE_X, 1f).apply {
                        addUpdateListener {
                            if (it.animatedFraction > 0.7) {
                                onFlip?.invoke()
                            }
                        }
                        setDuration((duration ?: 400) / 2)
                        doOnEnd {
                            onEnd?.invoke()
                        }
                    }
                )
                start()
            }
        }, after ?: 0)
    }

    fun MotionLayout.addTransitionListener(
        onStart: ((animator: MotionLayout?, startId: Int, endId: Int) -> Unit)? = null,
        onChange: ((animator: MotionLayout?, startId: Int, endId: Int, progress: Float) -> Unit)? = null,
        onEnd: ((animator: MotionLayout?, startId: Int) -> Unit)? = null,
    ) {
        addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                onStart?.invoke(p0, p1, p2)
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                onChange?.invoke(p0, p1, p2, p3)
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                onEnd?.invoke(p0, p1)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                TODO("Not yet implemented")
            }
        })
    }

    fun delay(value: Long, runnable: () -> Unit) {
        Handler().postDelayed({
            runnable.invoke()
        }, value)
    }

    fun View.animToY(
        y: Float,
        animate: Boolean? = true,
        after: Long? = 0,
        duration: Long? = 500,
        onEnd: (() -> Unit)? = {},
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@animToY, View.TRANSLATION_Y, dpToPx(y)).apply {
                this.duration = if (animate != false) duration ?: 500 else 0
                doOnEnd {
                    onEnd?.invoke()
                }
                interpolator?.let {
                    this.interpolator = it
                }
            }).after(after ?: 0)
            start()
        }
    }

    fun View.animToX(
        x: Float,
        animate: Boolean? = true,
        duration: Long? = 500,
        onEnd: (() -> Unit)? = {},
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@animToX, View.TRANSLATION_X, dpToPx(x)).apply {
                this.duration = duration?:0
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            })
            start()
        }
    }

    fun View.scale(scale: Float, duration: Long? = 1000, onEnd: (() -> Unit)? = {}, after: Long? = 0, interpolator:TimeInterpolator) {
        AnimatorSet().apply {
            playSequentially()
            play(ObjectAnimator.ofFloat(this@scale, View.SCALE_X, scale).apply {
                this.duration = duration ?: 1000
                this.interpolator = interpolator
            }).after(after ?: 0)
            play(ObjectAnimator.ofFloat(this@scale, View.SCALE_Y, scale).apply {
                this.duration = duration ?: 1000
                this.interpolator = interpolator
                doOnEnd {
                    onEnd?.invoke()

                }
            }).after(after ?: 0)
            start()
        }
    }

    fun View.scaleAnimY(
        scale: Float,
        y: Float,
        duration: Long? = 1000,
        onEnd: (() -> Unit)? = {},
        after: Long? = 0,
        interpolator: TimeInterpolator? = null
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scaleAnimY, View.SCALE_X, scale).apply {
                this.duration = duration ?: 1000
            }).after(after ?: 0)
            play(ObjectAnimator.ofFloat(this@scaleAnimY, View.SCALE_Y, scale).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            }).after(after ?: 0)
            play(ObjectAnimator.ofFloat(this@scaleAnimY, View.TRANSLATION_Y, dpToPx(y)).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
                this.interpolator = interpolator
            }).after(after ?: 0)
            start()
        }
    }


    fun View.scaleY(
        scale: Float,
        duration: Long? = 1000,
        onEnd: (() -> Unit)? = {},
        after: Long? = 0,
        interpolator: TimeInterpolator? = null
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scaleY, View.SCALE_Y, scale).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
                this.interpolator = interpolator
            }).after(after ?: 0)
            start()
        }
    }

    fun View.scaleX(
        scale: Float,
        duration: Long? = 1000,
        after: Long? = 0,
        onEnd: (() -> Unit)? = {},
        onUpdate: ((Float) -> Unit)? = {}
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scaleX, View.SCALE_X, scale).apply {
                this.duration = duration ?: 1000
                doOnEnd {
                    onEnd?.invoke()
                }
                addUpdateListener {
                    onUpdate?.invoke(it.animatedFraction)
                }
            }).after(after ?: 0)
            start()
        }
    }

    fun View.width(
        width: Int, duration: Long? = 500, onEnd: (() -> Unit)? = {}, percent: Float? = 100f,
        onPercent: (() -> Unit)? = {}
    ) {
        ValueAnimator.ofInt(this.width, dpToPx(width)).apply {
            this.duration = duration ?: 500
            Handler().postDelayed({
                onPercent?.invoke()
            }, (500.times(percent ?: 100f) / 100).toLong())
            addUpdateListener {
                this@width.layoutParams.width = it.animatedValue as Int
                this@width.requestLayout()
            }
            addListener(onEnd = {
                onEnd?.invoke()
            })
            start()
        }
    }

    fun View.shrinkHide(callback: (() -> Unit)? = {}) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@shrinkHide, View.SCALE_X, 0f).apply {
                duration = 300
            })
            play(ObjectAnimator.ofFloat(this@shrinkHide, View.SCALE_Y, 0f).apply {
                duration = 300
                addListener(onEnd = {
                    callback?.invoke()
                    this@shrinkHide.visibility = View.GONE
                })
            })
            start()
        }
    }

    fun View.expandShow(callback: (() -> Unit)? = {}) {
        this.visibility = View.VISIBLE
        this.scaleX = 0f
        this.alpha = 1f
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@expandShow, View.SCALE_X, 1f).apply {
                duration = 300
            })
            play(ObjectAnimator.ofFloat(this@expandShow, View.SCALE_Y, 1f).apply {
                duration = 300
                addListener(onEnd = {
                    callback?.invoke()
                })
            })
            start()
        }
    }


    fun View.fadeHide(onEnd: (() -> Unit)? = { }, duration: Long? = 700, after: Long? = 0) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@fadeHide, View.ALPHA, 0f).apply {
                this.duration = duration ?: 700
                addListener(onEnd = {
                    onEnd?.invoke()
                    this@fadeHide.visibility = View.GONE
                })
            }).after(after ?: 0)
            start()
        }
    }

    fun View.fadeShow(onEnd: (() -> Unit)? = {}, duration: Long? = 1000, after: Long? = 0) {
        this.alpha = 0f
        this.visibility = View.VISIBLE
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@fadeShow, View.ALPHA, 1f).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            }).after(after ?: 0)
            start()
        }
    }

    fun showKeyboard(view: View) {
        val imm = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }


    fun getScreenWidth(context: Context): Int {
        if (screenWidth == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenWidth = size.x
        }
        return screenWidth
    }

}

fun Drawable.toBitmap(context: Context): Bitmap {
    val drawable = this
    var bitmap: Bitmap? = null

    if (drawable is BitmapDrawable) {
        val bitmapDrawable = drawable as BitmapDrawable
        if (bitmapDrawable.bitmap != null) {
            return bitmapDrawable.bitmap
        }
    }

    bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
            1,
            1,
            Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
    }

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

fun TextView.setReadMore(
    rootNotes: View,
    showReadMore: ObservableBoolean,
    maxLine: ObservableField<Int>
) {
    this.post {
        showReadMore.set(this.lineCount > 4)
        if (showReadMore.get()) {
            maxLine.set(4)
            rootNotes.setOnClickListener {
                if (maxLine.get() == 4) {
                    maxLine.set(Int.MAX_VALUE)
                    showReadMore.set(false)
                } else {
                    maxLine.set(4)
                    showReadMore.set(true)
                }
            }
        }
    }
}

fun Bitmap.getRoundedCornerBitmap(pixels: Int): Bitmap? {
    val bitmap = this
    val output = Bitmap.createBitmap(
        bitmap.width, bitmap
            .height, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    val roundPx = pixels.toFloat()
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    return output
}

fun View.showKeyboard() {
    val imm = this.context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

