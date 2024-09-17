package com.example.sprint3.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sprint3.R
import com.example.sprint3.repository.ProductRepository

class ProductListActivity : AppCompatActivity() {

    private lateinit var repository: ProductRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        repository = ProductRepository()

        val productsListView = findViewById<ListView>(R.id.productsListView)

        repository.getProductsFromFirebase { products, errorMessage ->
            if (errorMessage == null) {
                val productNames = products.map { it.name }
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productNames)
                productsListView.adapter = adapter
            } else {
                Toast.makeText(this, "Erro ao carregar produtos: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
