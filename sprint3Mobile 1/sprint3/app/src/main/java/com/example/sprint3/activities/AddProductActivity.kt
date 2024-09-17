package com.example.sprint3.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.R
import com.example.sprint3.models.Produto
import com.example.sprint3.repository.ProductRepository

class AddProductActivity : AppCompatActivity() {

    private lateinit var repository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        repository = ProductRepository()

        val productNameEditText = findViewById<EditText>(R.id.productNameEditText)
        val productDescriptionEditText = findViewById<EditText>(R.id.productDescriptionEditText)
        val productPriceEditText = findViewById<EditText>(R.id.productPriceEditText)
        val addProductButton = findViewById<Button>(R.id.addProductButton)

        addProductButton.setOnClickListener {
            val name = productNameEditText.text.toString()
            val description = productDescriptionEditText.text.toString()
            val price = productPriceEditText.text.toString().toDoubleOrNull()

            if (price == null) {
                Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val product = Produto(name = name, description = description, price = price)

            repository.addProductToFirebase(product) { success, errorMessage ->
                if (success) {
                    Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val message = errorMessage ?: "Erro ao cadastrar produto"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
