package com.example.sprint3.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.R
import com.example.sprint3.models.Product
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddProductActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        // Inicialize o Firebase Realtime Database
        database = FirebaseDatabase.getInstance().reference

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

            val productId = database.child("products").push().key
            val product = Product(name, description, price)

            if (productId != null) {
                database.child("products").child(productId).setValue(product)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Produto cadastrado com sucesso", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Erro ao cadastrar produto", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}
