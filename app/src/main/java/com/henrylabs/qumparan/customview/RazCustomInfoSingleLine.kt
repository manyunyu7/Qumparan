package com.henrylabs.qumparan.customview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.databinding.RazCustomInfoHorizontalBinding

class RazCustomInfoSingleLine : FrameLayout {
    var title: String = ""
    var value: String = ""
    private var hint: String = ""
    private var textSize: Float = 16f
    private var isHintVisible: Boolean = false
    private var binding: RazCustomInfoHorizontalBinding =
        RazCustomInfoHorizontalBinding.inflate(LayoutInflater.from(context))


    constructor(context: Context) : super(context) {
        initView(context)
    }


    init { // inflate binding and add as view
        addView(binding.root)
    }


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        extractAttributes(attributeSet)
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        extractAttributes(attributeSet)
        initView(context)
    }

    private fun initView(context: Context?) {
        title(title)
        value(value)
        hint(hint)
        size(textSize)
    }

    fun build(
        title: String = "",
        value: String = "",
        hint: String = "",
        showHint: Boolean = false
    ) {
        title(title)
        value(value)
        hint(hint)
        if (showHint)
            binding.tvHint.visibility = View.VISIBLE
        else
            binding.tvHint.visibility = View.GONE
    }

    fun title(title: String) {
        this.title = title
        binding.tvInfoTitle.text = title
    }

    fun size(size: Float) {
        fontSizeSp(size)
    }

    fun isHintVisible(value: Boolean = false) {
        if (value) {
            binding.tvHint.visibility = View.VISIBLE
        } else {
            binding.tvHint.visibility = View.GONE
        }
    }

    fun hint(hint: String) {
        this.hint = hint
        binding.tvHint.text = hint
        isHintVisible()
    }

    fun value(value: String) {
        this.value = value
        binding.tvInfoValue.text = value
    }

    fun fontSizeSp(size: Float) {
        binding.tvInfoTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        binding.tvInfoValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    fun fontSizeSp(titleSize: Float, valueSize: Float) {
        binding.tvInfoTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
        binding.tvInfoValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, valueSize)
    }

    private fun extractAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RazCustomInfoSingleLine)
        typedArray.apply {
            title = getString(R.styleable.RazCustomInfoSingleLine_customInfoHzTitle) ?: title
            hint = getString(R.styleable.RazCustomInfoSingleLine_customInfoHzHint) ?: hint
            value = getString(R.styleable.RazCustomInfoSingleLine_customInfoHzContent) ?: value
            textSize =
                getFloat(R.styleable.RazCustomInfoSingleLine_customInfoHzFontSize, 16f) ?: textSize
            isHintVisible = getBoolean(R.styleable.RazCustomInfoSingleLine_isHintVisible, false)
        }

        typedArray.recycle()
    }
}