
package com.example.FinalProjectAnNada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.finalprojectAnNada.AboutActivity
import com.example.finalprojectAnNada.DatasetActivity
import com.example.finalprojectAnNada.SimulasiActivity
import com.example.finalprojectannada.FeaturesActivity
import com.example.finalprojectannada.ModelActivity
import com.example.finalprojectannada.R

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnAbout = findViewById<CardView>(R.id.btnAbout)
        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val btnDataset = findViewById<CardView>(R.id.btnDataset)
        btnDataset.setOnClickListener {
            val intent = Intent(this, DatasetActivity::class.java)
            startActivity(intent)
        }

        val btnFeatures = findViewById<CardView>(R.id.btnFeatures)
        btnFeatures.setOnClickListener {
            val intent = Intent(this, FeaturesActivity::class.java)
            startActivity(intent)
        }

        val btnModel = findViewById<CardView>(R.id.btnModel)
        btnModel.setOnClickListener {
            val intent = Intent(this, ModelActivity::class.java)
            startActivity(intent)
        }

        val btnSimulation = findViewById<CardView>(R.id.btnSimulation)
        btnSimulation.setOnClickListener {
            val intent = Intent(this, SimulasiActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}
