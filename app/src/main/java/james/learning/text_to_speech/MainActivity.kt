package james.learning.text_to_speech

import android.os.Build.VERSION_CODES.LOLLIPOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tts : TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.play)
        val textToSpeak = findViewById<EditText>(R.id.speechText)
        tts = TextToSpeech(this) { status ->
            if (status != TextToSpeech.ERROR) {
                tts.language = Locale.UK
            }
        }

        button.setOnClickListener {
            val text = textToSpeak.text.toString()
            if (text.isNotEmpty()) {
                if (BuildConfig.VERSION_CODE >= LOLLIPOP) {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                } else {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                }
            }
        }
    }

    override fun onPause() {
        tts.stop()
        tts.shutdown()
        super.onPause()
    }

    override fun onResume() {
        tts.language = Locale.UK
        super.onResume()
    }
}