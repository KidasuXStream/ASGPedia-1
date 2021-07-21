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

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit:Button
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
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_up_submit)
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
        btnSubmit.setOnClickListener {
            apiService.updateKontak(valId.toInt(), etName.text.toString(), etBrand.text.toString(), etJenis.text.toString(), etBahan.text.toString(), etFPS.text.toString(),etHarga.text.toString()).enqueue(object : Callback<AirsoftData>{
                override fun onResponse(call: Call<AirsoftData>, response: Response<AirsoftData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<AirsoftData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}