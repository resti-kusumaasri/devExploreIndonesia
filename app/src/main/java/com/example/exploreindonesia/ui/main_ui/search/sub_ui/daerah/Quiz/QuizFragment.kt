package com.example.exploreindonesia.ui.main_ui.search.sub_ui.daerah.Quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.exploreindonesia.MainActivity
import com.example.exploreindonesia.R
import com.example.exploreindonesia.data.response.QuizResponse
import com.example.exploreindonesia.ui.main_ui.ScoreActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class QuizFragment : Fragment() {

    private val userAnswers = mutableMapOf<Int, String>()
    private  var score = 0

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var pertanyaan: TextView
    private lateinit var setjawaban: RadioGroup
    private lateinit var jawabanA: RadioButton
    private lateinit var jawabanB: RadioButton
    private lateinit var jawabanC: RadioButton
    private lateinit var jawabanD: RadioButton
    private lateinit var btnNext: Button
    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val daerah = arguments?.getString("daerah") ?: run {
            Toast.makeText(context, "Daerah tidak ditemukan", Toast.LENGTH_SHORT).show()
            return
        }

        val kategori = arguments?.getString("kategori") ?: run {
            Toast.makeText(context, "Kategori tidak ditemukan", Toast.LENGTH_SHORT).show()
            return
        }

        val c = arguments?.getBoolean("c") ?: false

        Log.d("QuizFragment", "Daerah: $daerah, Kategori: $kategori")

        pertanyaan = view.findViewById(R.id.pertanyaan)
        setjawaban = view.findViewById(R.id.setjawaban)
        jawabanA = view.findViewById(R.id.jawabanA)
        jawabanB = view.findViewById(R.id.jawabanB)
        jawabanC = view.findViewById(R.id.jawabanC)
        jawabanD = view.findViewById(R.id.jawabanD)
        btnNext = view.findViewById(R.id.btn_next)

        quizViewModel.quizList.observe(viewLifecycleOwner) { quizList ->
            if (quizList.isNotEmpty()) {
                updateQuestionUI(quizList)
            } else {
                Toast.makeText(context, "Quiz tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        btnNext.setOnClickListener {
            if (isAnswerSelected()) {
                quizViewModel.quizList.value?.let { quizList ->
                    saveUserAnswer()
                    val correctAnswer = quizList[currentQuestionIndex].rightAnswer
                    val userAnswer = userAnswers[currentQuestionIndex]
                    if (userAnswer == correctAnswer) {
                        score++
                        //Toast.makeText(context, "Jawaban Benar!", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(context, "Jawaban Salah!", Toast.LENGTH_SHORT).show()
                    }
                    if (currentQuestionIndex < quizList.size - 1) {
                        currentQuestionIndex++
                        updateQuestionUI(quizList)
                    } else {
                        val nilai = score
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(1000)
                            val intent = Intent(context, ScoreActivity::class.java)
                            intent.putExtra("score", nilai)
                            intent.putExtra("jml_soal", quizList.size)
                            startActivity(intent)
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Silakan pilih jawaban terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        if (c==true) {
            quizViewModel.getQuizCategory(kategori)
        }
        else {
            quizViewModel.getQuiz(daerah, kategori)
        }
    }

    private fun updateQuestionUI(quizList: List<QuizResponse>) {
        val quiz = quizList[currentQuestionIndex]
        pertanyaan.text = quiz.question

        if (quiz.answerOptions.size >= 4) {
            jawabanA.text = quiz.answerOptions[0]
            jawabanB.text = quiz.answerOptions[1]
            jawabanC.text = quiz.answerOptions[2]
            jawabanD.text = quiz.answerOptions[3]
        } else {
            Toast.makeText(context, "Pilihan jawaban tidak valid", Toast.LENGTH_SHORT).show()
        }

        setjawaban.clearCheck()
    }

    private fun saveUserAnswer() {
        val selectedId = setjawaban.checkedRadioButtonId
        val selectedAnswer = when (selectedId) {
            R.id.jawabanA -> jawabanA.text.toString()
            R.id.jawabanB -> jawabanB.text.toString()
            R.id.jawabanC -> jawabanC.text.toString()
            R.id.jawabanD -> jawabanD.text.toString()
            else -> null
        }

        selectedAnswer?.let {
            userAnswers[currentQuestionIndex] = it
        }
    }

    private fun isAnswerSelected(): Boolean {
        return setjawaban.checkedRadioButtonId != -1
    }


}