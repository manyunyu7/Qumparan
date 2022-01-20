package com.henrylabs.qumparan.customview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.databinding.CustomStatusBinding

class CustomStatus : FrameLayout {

    private var title: String = ""

    private lateinit var binding: CustomStatusBinding

    init { // inflate binding and add as view
        binding = CustomStatusBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
    }

    constructor(context: Context) : super(context) {
        initView(context)
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
    }

    fun build(
        text: String
    ) {
        binding.tvStatus.text=text
    }

    fun title(title: String) {
        this.title = title
        binding.tvStatus.text = title
    }

    fun fontSizeSp(size: Float) {
        binding.tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    fun fontSizeSp(titleSize: Float, valueSize: Float) {
        binding.tvStatus.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)
    }

    private fun extractAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomStatus)
        title = typedArray.getString(R.styleable.CustomStatus_CustomTitle) ?: title
        typedArray.recycle()
    }

}