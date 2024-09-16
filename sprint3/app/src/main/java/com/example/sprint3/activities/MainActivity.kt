package com.example.sprint3.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cadastrarProdutoButton = findViewById<Button>(R.id.cadastrarProdutoButton)
        val listarProdutosButton = findViewById<Button>(R.id.listarProdutosButton)

        cadastrarProdutoButton.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

        listarProdutosButton.setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }
    }
}
