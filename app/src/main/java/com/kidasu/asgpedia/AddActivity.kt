package com.kidasu.asgpedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etBrand: EditText
    lateinit var etJenis: EditText
    lateinit var etBahan: EditText
    lateinit var etFPS: EditText
    lateinit var etHarga: EditText
    lateinit var apiService: ServiceInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etName = findViewById(R.id.et_add_name)
        etBrand = findViewById(R.id.et_add_brand)
        etJenis = findViewById(R.id.et_add_jenis)
        etBahan = findViewById(R.id.et_add_bahan)
        etFPS = findViewById(R.id.et_add_fps)
        etHarga = findViewById(R.id.et_add_harga)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = AirsoftData()
            array.nama = etName.text.toString()
            array.brand = etBrand.text.toString()
            array.jenis = etJenis.text.toString()
            array.bahan = etBahan.text.toString()
            array.fps = etFPS.text.toString()
            array.harga = etHarga.text.toString()
            apiService.postKontak(array).enqueue(object : Callback<AirsoftData>{
                override fun onResponse(call: Call<AirsoftData>, response: Response<AirsoftData>) {
                    startActivity(Intent(this@AddActivity, MainActivity::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<AirsoftData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}