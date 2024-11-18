package com.example.dynamiclist.data

import com.example.dynamiclist.R
import com.example.dynamiclist.models.Destinations

class DataSource {
    fun GetDestinations(): List<Destinations>{
        return listOf(
            Destinations(R.string.snUno, R.drawable.uno,R.string.anUno,R.string.alnUno,R.string.vUno,R.string.rdUno,R.string.ytUno),
            Destinations(R.string.snDos, R.drawable.dos,R.string.anDos,R.string.alnDos,R.string.vDos,R.string.rdDos,R.string.ytDos),
            Destinations(R.string.snTres, R.drawable.tres,R.string.anTres,R.string.alnTres,R.string.vTres,R.string.rdTres,R.string.ytTres),
            Destinations(R.string.snCuatro, R.drawable.cuatro,R.string.anCuatro,R.string.alnCuatro,R.string.vCuatro,R.string.rdCuatro,R.string.ytCuatro),
            Destinations(R.string.snCinco, R.drawable.cinco,R.string.anCinco,R.string.alnCinco,R.string.vCinco,R.string.rdCinco,R.string.ytCinco),
            Destinations(R.string.snSeis, R.drawable.seis,R.string.anSeis,R.string.alnSeis,R.string.vSeis,R.string.rdSeis,R.string.ytSeis),
            Destinations(R.string.snSiete, R.drawable.siete,R.string.anSiete,R.string.alnSiete,R.string.vSiete,R.string.rdSiete,R.string.ytSiete),
            Destinations(R.string.snOcho, R.drawable.ocho,R.string.anOcho,R.string.alnOcho,R.string.vOcho,R.string.rdOcho,R.string.ytOcho),
            Destinations(R.string.snNueve, R.drawable.nueve,R.string.anNueve,R.string.alnNueve,R.string.vNueve,R.string.rdNueve,R.string.ytNueve),
            Destinations(R.string.snDiez, R.drawable.diez,R.string.anDiez,R.string.alnDiez,R.string.vDiez,R.string.rdDiez,R.string.ytDiez)
        )
    }
}