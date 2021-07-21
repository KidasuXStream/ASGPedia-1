package com.kidasu.asgpedia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadData : AppCompatActivity() {
    lateinit var btnCancel:Button
    lateinit var etName: EditText
    lateinit var etBrand: EditText
    lateinit var etJenis: EditText
    lateinit var etBahan: EditText
    lateinit var etFPS: EditText
    lateinit var etHarga: EditText
    lateinit var valName: String
    lateinit var valBrand: String
    lateinit var valJenis: String
    lateinit var valBahan: String
    lateinit var valFPS: String
    lateinit var valHarga: String
    lateinit var valId: String
    lateinit var apiService: ServiceInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_data)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration(){
        btnCancel = findViewById(R.id.btn_up_cancel)
        etName = findViewById(R.id.et_up_name)
        etBrand = findViewById(R.id.et_up_brand)
        etJenis = findViewById(R.id.et_up_jenis)
        etBahan = findViewById(R.id.et_up_bahan)
        etFPS = findViewById(R.id.et_up_fps)
        etHarga = findViewById(R.id.et_up_harga)
        apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
    }
    fun getMyData(){
        val myValue = intent.extras
        if (myValue!=null){
            valName = myValue.getString("nama").toString()
            valBrand = myValue.getString("brand").toString()
            valJenis = myValue.getString("jenis").toString()
            valBahan = myValue.getString("bahan").toString()
            valFPS = myValue.getString("fps").toString()
            valHarga = myValue.getString("harga").toString()
            valId = myValue.getString("id").toString()
        }
    }
    fun myfunction(){
        etName.setText(valName)
        etBrand.setText(valBrand)
        etJenis.setText(valJenis)
        etBahan.setText(valBahan)
        etFPS.setText(valFPS)
        etHarga.setText(valHarga)
        val pindah = Intent(this, MainActivity::class.java)
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}