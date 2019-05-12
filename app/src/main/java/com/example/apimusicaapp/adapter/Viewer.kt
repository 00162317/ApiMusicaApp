package com.example.apimusicaapp.adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.apimusicaapp.Fragmento.FragmentoInfoSong
import com.example.apimusicaapp.R

class Viewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)

        val bundle:Bundle= Bundle()
        bundle.putParcelable("holi", this.intent.extras.getParcelable("holi"))

        val fragmento = FragmentoInfoSong()
        fragmento.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.land_segundo,fragmento).commit()
    }

}
