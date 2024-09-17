package com.example.sprint3.repository

import com.example.sprint3.api.RetrofitClient
import com.example.sprint3.models.Produto
import com.google.firebase.database.*

class ProductRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance().reference
    private val retrofitApi = RetrofitClient.api
    private val productsNode = "products"

    fun addProductToFirebase(product: Produto, onComplete: (Boolean, String?) -> Unit) {
        val productId = firebaseDatabase.child(productsNode).push().key
        if (productId != null) {
            firebaseDatabase.child(productsNode).child(productId).setValue(product)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onComplete(true, null)
                    } else {
                        onComplete(false, task.exception?.message)
                    }
                }
        } else {
            onComplete(false, "Erro ao gerar o ID do produto")
        }
    }

    fun getProductsFromFirebase(onDataReceived: (List<Produto>, String?) -> Unit) {
        firebaseDatabase.child(productsNode).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = mutableListOf<Produto>()
                for (snapshot in dataSnapshot.children) {
                    val product = snapshot.getValue(Produto::class.java)
                    if (product != null) products.add(product)
                }
                onDataReceived(products, null)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onDataReceived(emptyList(), databaseError.message)
            }
        })
    }
}
