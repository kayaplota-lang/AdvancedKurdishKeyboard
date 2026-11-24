package com.example.advancedkurdish

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.view.inputmethod.EditorInfo

class KurdishImeService : InputMethodService() {
    private val bg = Color.rgb(7, 13, 12)
    private val keyBg = Color.rgb(13, 25, 22)
    private var symbols = false

    private val letterRows = listOf(
        listOf("ق","ڤ","ف","غ","ع","ه","ە","ت","ح","خ"),
        listOf("ش","س","ی","ێ","ل","ڵ","ا","ن","ج","چ"),
        listOf("ب","ز","ر","ڕ","د","وو","و","ۆ","ک","گ"),
        listOf("ئ","پ","ژ","م","؟","،",".")
    )
    private val symbolRows = listOf(
        listOf("١","٢","٣","٤","٥","٦","٧","٨","٩","٠"),
        listOf("@","#","$","%","&","-","+","(",")","/"),
        listOf("*","\"","' ",":","؛","!","؟","،","؛","_"),
        listOf("=","<",">","[","]","{","}","\\","|")
    )

    override fun onCreateInputView() = buildKeyboard()

    private fun buildKeyboard(): LinearLayout {
        val outer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(dp(4), dp(4), dp(4), dp(4))
        }
        val rows = if (symbols) symbolRows else letterRows
        rows.forEachIndexed { index, row ->
            val line = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }
            row.forEach { label ->
                line.addView(makeKey(label) { handle(label) },
                    LinearLayout.LayoutParams(0, dp(if (index == 3) 58 else 62), 1f).apply {
                        setMargins(dp(2), dp(2), dp(2), dp(2))
                    })
            }
            outer.addView(line)
        }

        val controls = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        val controlsList = listOf("↔", "ئ", "½", "فاصله", "؟!", "⌫")
        controlsList.forEach { label ->
            val weight = if (label == "فاصله") 3.6f else if (label == "⌫" || label == "↔") 1.2f else 1f
            controls.addView(makeKey(label) { handle(label) },
                LinearLayout.LayoutParams(0, dp(58), weight).apply {
                    setMargins(dp(2), dp(2), dp(2), dp(2))
                })
        }
        outer.addView(controls)
        val bottom = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        listOf("🌐", "↵").forEach { label ->
            bottom.addView(makeKey(label) { handle(label) },
                LinearLayout.LayoutParams(0, dp(48), 1f).apply {
                    setMargins(dp(2), dp(2), dp(2), dp(2))
                })
        }
        outer.addView(bottom)
        return outer
    }

    private fun makeKey(label: String, action: () -> Unit): TextView =
        TextView(this).apply {
            text = label
            textSize = if (label == "فاصله") 16f else 26f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT
            background = GradientDrawable().apply {
                setColor(keyBg)
                cornerRadius = dp(1).toFloat()
            }
            isClickable = true
            isFocusable = true
            setOnClickListener { action() }
        }

    private fun handle(k: String) {
        val ic = currentInputConnection ?: return
        when (k) {
            "⌫" -> ic.deleteSurroundingText(1, 0)
            "↵" -> {
                val action = currentInputEditorInfo?.imeOptions?.and(EditorInfo.IME_MASK_ACTION)
                if (action != null && action != EditorInfo.IME_ACTION_NONE &&
                    action != EditorInfo.IME_ACTION_UNSPECIFIED) ic.performEditorAction(action)
                else ic.commitText("\n", 1)
            }
            "فاصله" -> ic.commitText(" ", 1)
            "🌐" -> switchToNextInputMethod(false)
            "½" -> {
                symbols = !symbols
                setInputView(buildKeyboard())
            }
            "؟!" -> {
                symbols = true
                setInputView(buildKeyboard())
            }
            "↔" -> ic.commitText("\u200e", 1)
            else -> ic.commitText(k, 1)
        }
    }

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()
}
