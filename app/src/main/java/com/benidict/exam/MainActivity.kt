package com.benidict.exam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import com.benidict.exam.ui.theme.ExamTheme
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : ComponentActivity() {
    val input = "Glass"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val result = checkCharacter(input.lowercase(Locale.getDefault()))
                    val finalOutput = if (result.counter > 1) {
                        "$input (should output ${result.char})"
                    } else "should output an error message such as no repeating characters"

                   Log.d("makerChecker", finalOutput)
                }
            }
        }
    }
}

data class CharCounter(
    val char: Char,
    var counter: Int = 1
)

private fun checkCharacter(s: String): CharCounter {
    val arrString = s.toCharArray()
    val charCounter: ArrayList<CharCounter> = ArrayList()
    arrString.forEachIndexed { _, c ->
        val char = CharCounter(
            c,
            1
        )
        if (charCounter.find { it.char == c }?.char == c) {
            //charCounter.
            val currentCounter = charCounter.find { it.char == c }?.counter
            charCounter.remove(char)
            charCounter.add(
                CharCounter(
                    c,
                    currentCounter?.plus(1) ?: 0
                )
            )
        } else {
            charCounter.add(char)
        }
    }

    val sortedCharCounter = charCounter.sortedByDescending {
        it.counter
    }

    return sortedCharCounter[0]
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExamTheme {
        Greeting("Android")
    }
}