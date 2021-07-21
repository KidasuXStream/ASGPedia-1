package com.kidasu.asgpedia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirsoftAdapter(private val listku: ArrayList<AirsoftData>): RecyclerView.Adapter<AirsoftAdapter.AirsoftViewHolder>(){
    inner class AirsoftViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvNama: TextView = viewku.findViewById(R.id.tv_nama)
        var tvJenis: TextView = viewku.findViewById(R.id.tv_jenis)
        var tvHarga: TextView = viewku.findViewById(R.id.tv_harga)
        var btnDelte: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var btnLoad: ImageButton = viewku.findViewById(R.id.btn_data_load)
        var apiIterface: ServiceInterface? = Repository.getDataAPI().create(ServiceInterface::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirsoftViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return AirsoftViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: AirsoftViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvNama.text = dataku.nama
        holder.tvJenis.text = dataku.jenis
        holder.tvHarga.text = dataku.harga
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("nama", dataku.nama.toString())
            bundle.putString("brand", dataku.brand.toString())
            bundle.putString("jenis", dataku.jenis.toString())
            bundle.putString("bahan", dataku.bahan.toString())
            bundle.putString("fps", dataku.fps.toString())
            bundle.putString("harga", dataku.harga.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnDelte.setOnClickListener {
            holder.apiIterface!!.deleteAirsoft(dataku.id!!).enqueue(object : Callback<AirsoftData> {
                override fun onResponse(call: Call<AirsoftData>, response: Response<AirsoftData>) {
                    if (response.isSuccessful){
                        listku.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<AirsoftData>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
        holder.btnLoad.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, LoadData::class.java)
            bundle.putString("id", dataku.id.toString())
            bundle.putString("nama", dataku.nama.toString())
            bundle.putString("brand", dataku.brand.toString())
            bundle.putString("jenis", dataku.jenis.toString())
            bundle.putString("bahan", dataku.bahan.toString())
            bundle.putString("fps", dataku.fps.toString())
            bundle.putString("harga", dataku.harga.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return listku.size
    }

}