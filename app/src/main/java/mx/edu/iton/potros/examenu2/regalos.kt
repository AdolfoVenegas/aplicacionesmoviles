package mx.edu.iton.potros.examenu2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class regalos : AppCompatActivity() {

    var adapter: detalleAdapter? = null
    var regalos = ArrayList<detalles>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regalos)

        val categoria = intent.getStringExtra("categoria")


        cargarDetalle()

        val detallesFiltrados = regalos.filter { it.categoria == categoria }

        adapter = detalleAdapter(this, ArrayList(detallesFiltrados))

        var gridDetalles: GridView = findViewById(R.id.detalles_catalago)

        gridDetalles.adapter = adapter
    }
    fun cargarDetalle(){
        regalos.add(detalles(R.drawable.cumplebotanas, "$280", "detalles"))
        regalos.add(detalles(R.drawable.cumplecheve, "$320", "detalles"))
        regalos.add(detalles(R.drawable.cumpleescolar, "$330", "detalles"))
        regalos.add(detalles(R.drawable.cumplepaletas, "$190", "detalles"))
        regalos.add(detalles(R.drawable.cumplesnack, "$150", "detalles"))
        regalos.add(detalles(R.drawable.cumplevinos, "$370", "detalles"))

        regalos.add(detalles(R.drawable.globoamor,"$240", "globos"))
        regalos.add(detalles(R.drawable.globocumple,"$820", "globos"))
        regalos.add(detalles(R.drawable.globofestejo,"$260", "globos"))
        regalos.add(detalles(R.drawable.globonum,"$760", "globos"))
        regalos.add(detalles(R.drawable.globoregalo,"$450", "globos"))
        regalos.add(detalles(R.drawable.globos,"$240", "globos"))

        regalos.add(detalles(R.drawable.peluchemario,"$320", "peluches"))
        regalos.add(detalles(R.drawable.pelucheminecraft,"$320", "peluches"))
        regalos.add(detalles(R.drawable.peluchepeppa,"$290", "peluches"))
        regalos.add(detalles(R.drawable.peluches,"$", "peluches"))
        regalos.add(detalles(R.drawable.peluchesony,"$330", "peluches"))
        regalos.add(detalles(R.drawable.peluchestich,"$280", "peluches"))
        regalos.add(detalles(R.drawable.peluchevarios,"$195", "peluches"))

        regalos.add(detalles(R.drawable.regaloazul,"$80", "regalos"))
        regalos.add(detalles(R.drawable.regalobebe,"$290", "regalos"))
        regalos.add(detalles(R.drawable.regalocajas,"$140", "regalos"))
        regalos.add(detalles(R.drawable.regalocolores,"$85", "regalos"))
        regalos.add(detalles(R.drawable.regalos,"$", "regalos"))
        regalos.add(detalles(R.drawable.regalovarios,"$75", "regalos"))

        regalos.add(detalles(R.drawable.tazaabuela,"$120", "tazas"))
        regalos.add(detalles(R.drawable.tazalove,"$120", "tazas"))
        regalos.add(detalles(R.drawable.tazaquiero,"$260", "tazas"))
        regalos.add(detalles(R.drawable.tazas,"$280", "tazas"))
    }
}
class detalleAdapter:BaseAdapter{

    var regalos = ArrayList<detalles>()
    var context: Context? = null

    constructor(context: Context, regalos: ArrayList<detalles>) : super(){
        this.regalos = regalos
        this.context = context
    }
    override fun getCount(): Int {
        return regalos.size
    }

    override fun getItem(position: Int): Any {
        return regalos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var regalo = regalos[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.detalle_regalo,null)
        var image: ImageView = vista.findViewById(R.id.iv_regalo_imagen)
        var price: TextView = vista.findViewById(R.id.tv_regalo_precio)
        var cat: TextView = vista.findViewById(R.id.bannerDetails)

        image.setImageResource(regalo.image)
        price.setText(regalo.precio)
        cat.setText(regalo.categoria)

        return vista
    }
}
