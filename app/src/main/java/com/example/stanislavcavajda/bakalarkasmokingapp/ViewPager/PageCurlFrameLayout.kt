package com.example.stanislavcavajda.bakalarkasmokingapp.ViewPager

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class PageCurlFrameLayout : FrameLayout, PageCurl {

    private var mCurl: Float = 0.toFloat()

    private var mClipPath: Path? = null

    private var mCurlPath: Path? = null

    private var mCurlStrokePaint: Paint? = null

    private var mCurlFillPaint: Paint? = null

    private val mBottomFold = PointF()

    private val mTopFold = PointF()

    private val mBottomFoldTip = PointF()

    private val mTopFoldTip = PointF()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private fun init() {

        //      if (Build.VERSION.SDK_INT < 18) {  // clipPath() not supported in hardware accel
        //         setLayerType(LAYER_TYPE_SOFTWARE, null);
        //      }

        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        mCurlStrokePaint = Paint()
        mCurlStrokePaint!!.style = Paint.Style.STROKE
        mCurlStrokePaint!!.strokeWidth = 1.2f
        mCurlStrokePaint!!.color = Color.LTGRAY

        mCurlFillPaint = Paint()
        mCurlFillPaint!!.style = Paint.Style.FILL
        mCurlFillPaint!!.color = Color.WHITE

        mCurlPath = Path()

        mClipPath = Path()
    }

    override
    fun setCurlFactor(curl: Float) {
        var curl = curl


        /*
       * From 1.0 to 0.0, clip reveals page from corner to full, as though beneath the folding page that precedes
       * From 0.0 to -1.0 clip hides corner of page and shows page folding over
       */

        mCurl = curl
        val foldingPage = curl < 0
        if (curl < 0) curl += 1f

        val w = width
        val h = height

        // This math based on logic from:
        // https://github.com/moritz-wundke/android-page-curl/blob/master/src/com/mystictreegames/pagecurl/PageCurlView.java

        mBottomFold.x = w * curl
        mBottomFold.y = h.toFloat()

        if (mBottomFold.x > w / 2) {
            // fold is on right edge
            mTopFold.x = w.toFloat()
            mTopFold.y = h - (w - mBottomFold.x) * h / mBottomFold.x
        } else {
            // fold is on top edge
            mTopFold.x = 2 * mBottomFold.x
            mTopFold.y = 0f
        }

        // this is the angle of the fold
        val angle = Math.atan(((h - mTopFold.y) / (mTopFold.x - mBottomFold.x)).toDouble())

        // multiple fold angle by 2 to get the angle of the right page edge
        val cosFactor = Math.cos(2 * angle)
        val sinFactor = Math.sin(2 * angle)

        val foldWidth = w - mBottomFold.x
        mBottomFoldTip.x = (mBottomFold.x + foldWidth * cosFactor).toFloat()
        mBottomFoldTip.y = (h - foldWidth * sinFactor).toFloat()

        if (mBottomFold.x > w / 2) {
            mTopFoldTip.x = mTopFold.x
            mTopFoldTip.y = mTopFold.y
        } else {
            mTopFoldTip.x = (mTopFold.x + (w - mTopFold.x) * cosFactor).toFloat()
            mTopFoldTip.y = (-(sinFactor * (w - mTopFold.x))).toFloat()
        }

        mClipPath!!.reset()
        if (foldingPage) {
            // clip to show the page disappearing as it's folded
            mClipPath!!.moveTo(0f, 0f)
            if (mTopFold.y != 0f) {
                mClipPath!!.lineTo(w.toFloat(), 0f)
            }
            mClipPath!!.lineTo(mTopFold.x, mTopFold.y)
            mClipPath!!.lineTo(mBottomFold.x, mBottomFold.y)
            mClipPath!!.lineTo(0f, h.toFloat())
        } else {
            // clip to show the page underneath revealing
            mClipPath!!.moveTo(w.toFloat(), h.toFloat())
            if (mTopFold.y == 0f) {
                mClipPath!!.lineTo(w.toFloat(), 0f)
            }
            mClipPath!!.lineTo(mTopFold.x, mTopFold.y)
            mClipPath!!.lineTo(mBottomFold.x, mBottomFold.y)
        }
        mClipPath!!.close()

        mCurlPath!!.reset()
        mCurlPath!!.moveTo(mBottomFold.x, mBottomFold.y)
        mCurlPath!!.lineTo(mBottomFoldTip.x, mBottomFoldTip.y)
        mCurlPath!!.lineTo(mTopFoldTip.x, mTopFoldTip.y)
        mCurlPath!!.lineTo(mTopFold.x, mTopFold.y)
        mCurlPath!!.close()

        invalidate()
    }

    override fun dispatchDraw(canvas: Canvas) {
        canvas.save()
        if (mCurl != 0f && mCurl != 1f && mCurl != -1f) {
            canvas.clipPath(mClipPath!!)
        }
        super.dispatchDraw(canvas)
        canvas.restore()
        if (mCurl < 0) {
            canvas.drawPath(mCurlPath!!, mCurlFillPaint!!)
            canvas.drawPath(mCurlPath!!, mCurlStrokePaint!!)
        }
    }

    companion object {
        private val TAG = "PageCurlFrameLayout"
    }
}