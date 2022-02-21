package com.masorone.addingnumbers.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.masorone.addingnumbers.R
import com.masorone.addingnumbers.databinding.FragmentGameFinishedBinding

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<GameFinishedFragmentArgs>()
    private val gameResult by lazy { args.gameResult }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
        parseGameResult()
    }

    private fun setupClickListener() {
        binding.buttonRetry.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun parseGameResult() {
        val resId = if (gameResult.winner) R.drawable.ic_smile else R.drawable.ic_sad
        with(binding) {
            emojiResult.setImageResource(resId)
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswer()
            )
        }
    }

    private fun getPercentOfRightAnswer() = with(gameResult) {
        if (countOfQuestions == 0)
            0
        else
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}