package com.example.finalprojectAnNada

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.finalprojectannada.R
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SimulasiActivity : AppCompatActivity() {
    private lateinit var interpreter: Interpreter
    private val mModelPath = "heartfailure.tflite"

    private lateinit var resultText: TextView
    private lateinit var age: EditText
    private lateinit var anaemia: EditText
    private lateinit var diabetes: EditText
    private lateinit var high_blood_pressure: EditText
    private lateinit var serum_sodium: EditText
    private lateinit var sex: EditText
    private lateinit var smoking: EditText
    private lateinit var time: EditText
    private lateinit var checkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simulasi)

        resultText = findViewById(R.id.txtResult)
        age = findViewById(R.id.age)
        anaemia = findViewById(R.id.anemia)
        diabetes = findViewById(R.id.diabetes)
        high_blood_pressure = findViewById(R.id.high_blood_pressure)
        serum_sodium = findViewById(R.id.serum_sodium)
        sex = findViewById(R.id.sex)
        smoking = findViewById(R.id.smooking)
        time = findViewById(R.id.time)
        checkButton = findViewById(R.id.btnCheck)

        checkButton.setOnClickListener {
            val result = doInference(
                age.text.toString().toFloatOrNull() ?: 0f,
                anaemia.text.toString().toFloatOrNull() ?: 0f,
                diabetes.text.toString().toFloatOrNull() ?: 0f,
                high_blood_pressure.text.toString().toFloatOrNull() ?: 0f,
                serum_sodium.text.toString().toFloatOrNull() ?: 0f,
                sex.text.toString().toFloatOrNull() ?: 0f,
                smoking.text.toString().toFloatOrNull() ?: 0f,
                time.text.toString().toFloatOrNull() ?: 0f
            )
            runOnUiThread {
                resultText.text = if (result == 0) "Yes" else "No"
            }
        }
        initInterpreter()
    }

    private fun initInterpreter() {
        val options = Interpreter.Options()
        options.setNumThreads(4)
        options.setUseNNAPI(false) // Nonaktifkan penggunaan NNAPI
        interpreter = Interpreter(loadModelFile(assets, mModelPath), options)
    }

    private fun doInference(vararg input: Float): Int {
        val inputVal = arrayOf(input)
        val output = Array(1) { FloatArray(2) }
        interpreter.run(inputVal, output)

        Log.e("result", (output[0].toList() + " ").toString())

        return output[0].indexOfFirst { it == output[0].maxOrNull() }
    }

    private fun loadModelFile(assetManager: AssetManager, modelPath: String): MappedByteBuffer {
        val fileDescriptor = assetManager.openFd(modelPath)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }
}
