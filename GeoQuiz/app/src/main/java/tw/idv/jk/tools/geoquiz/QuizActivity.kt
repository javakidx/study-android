package tw.idv.jk.tools.geoquiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class QuizActivity : Activity() {
    private var trueButton: Button? = null
    private var falseButton: Button? = null
    private var nextButton: ImageButton? = null
    private var previousButton: ImageButton? = null
    private var questionText: TextView? = null

    companion object {
        private val TAG = "QuizActivity"
        private val KEY_INDEX = "index"
    }

    private val questionBank = arrayOf(Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentIndex = savedInstanceState?.getInt(KEY_INDEX) ?: 0

        Log.d(TAG, "onCreate(Bundle) called")
        setContentView(R.layout.activity_quiz)

        trueButton = findViewById<Button>(R.id.true_button) as Button

        trueButton?.setOnClickListener {
//            Toast.makeText(this@QuizActivity, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
            checkAnswer(true)
        }

        falseButton = findViewById<Button>(R.id.false_button) as Button
        falseButton?.setOnClickListener {
//            Toast.makeText(this@QuizActivity, R.string.correct_toast, Toast.LENGTH_LONG).show()
            checkAnswer(false)
        }

        questionText = findViewById<TextView>(R.id.question_text) as TextView
//        questionText?.setText(this.questionBank[currentIndex].textResourceId)
        updateQuestion()

        questionText?.setOnClickListener(nextItemListen)

        nextButton = findViewById<ImageButton>(R.id.next_button) as ImageButton
        nextButton?.setOnClickListener(nextItemListen)

        previousButton = findViewById<ImageButton>(R.id.previous_button) as ImageButton
        previousButton?.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex < 0) {
                currentIndex = questionBank.size - 1
            }
//            questionText?.setText(questionBank[currentIndex].textResourceId)
            updateQuestion()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "onStop() called")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause() called")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "onResume() called")
    }
    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy() called")
    }

    private val nextItemListen = View.OnClickListener {
        currentIndex = (currentIndex + 1) % questionBank.size
//            questionText?.setText(questionBank[currentIndex].textResourceId)
        updateQuestion()
    }

    private fun updateQuestion() {
        val question = questionBank[currentIndex].textResourceId
        questionText?.setText(question)
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = questionBank[currentIndex].answerTrue

        val messageResourceId = when(userPressedTrue) {
            answerIsTrue -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this@QuizActivity, messageResourceId, Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        Log.d(TAG, "onSaveInstanceState() called")
        outState?.putInt(KEY_INDEX, currentIndex)
    }
}
