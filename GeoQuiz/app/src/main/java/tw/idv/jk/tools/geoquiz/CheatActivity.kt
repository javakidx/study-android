package tw.idv.jk.tools.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CheatActivity : Activity() {
    private var answerIsTrue: Boolean? = null
    private var answerTextView : TextView? = null
    private var showAnswer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)

        showAnswer = findViewById(R.id.show_answer_button)
        showAnswer?.setOnClickListener {
            when (answerIsTrue) {
                true -> answerTextView?.text = getString(R.string.true_button)
                else -> answerTextView?.text = getString(R.string.false_button)
            }

            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(RESULT_OK, data)
    }

    companion object {
        private val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
        private val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            val i = Intent(packageContext, CheatActivity::class.java)
            i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)

            return i
        }

        fun wasAnswerShown(result: Intent) : Boolean {
            return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }
}
