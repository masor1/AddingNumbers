package com.masorone.addingnumbers.presentation.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.masorone.addingnumbers.R
import com.masorone.addingnumbers.domain.entity.GameResult

interface OnAnswerClickListener {
    fun onClick(number: Int)
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentOfRightAnswer(gameResult)
    )
}

private fun getPercentOfRightAnswer(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0)
        0
    else
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, winner: Boolean) {
    val resId = if (winner) R.drawable.ic_smile else R.drawable.ic_sad
    imageView.setImageResource(resId)
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean) {
    textView.setTextColor(getColorByState(enough, textView.context))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean) {
    progressBar.progressTintList =
        ColorStateList.valueOf(getColorByState(enough, progressBar.context))
}

private fun getColorByState(state: Boolean, context: Context): Int {
    val colorResId = if (state)
        android.R.color.holo_green_light
    else
        android.R.color.holo_red_light
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("intToString")
fun intToString(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOnOptionClickListener(textView: TextView, clickListener: OnAnswerClickListener) {
    textView.setOnClickListener {
        clickListener.onClick(textView.text.toString().toInt())
    }
}