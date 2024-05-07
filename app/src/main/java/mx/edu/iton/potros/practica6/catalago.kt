package mx.edu.iton.potros.practica6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class catalago : AppCompatActivity() {

    var adapter: PeliculaAdapter? = null
    var seriesAdapter: PeliculaAdapter? = null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalago)

        cargarPeliculas()
        cargarSeries()

        adapter=PeliculaAdapter(this,peliculas)
        seriesAdapter=PeliculaAdapter(this,series)
        var gridPelis: GridView=findViewById(R.id.movies_catalogo)
        var gridSeries: GridView=findViewById(R.id.series_catalogo)

        gridPelis.adapter = adapter
        gridSeries.adapter = seriesAdapter

    }

    fun cargarPeliculas(){
        peliculas.add(Pelicula("Demon Slayer: Kimetsu no Yaiba -To the Hashira Training", R.drawable.demon, R.drawable.demo,"Demon Slayer: Kimetsu no Yaiba -To the Hashira Training- proyectará por primera vez en cines el episodio 11 del Arco de la Aldea de los Herreros, mostrando así la conclusión de la feroz batalla entre Tanjiro y la Cuarta Luna Creciente, Hatengu, además de cómo Nezuko logra caminar bajo el sol. Le seguirá el episodio 1 del Arco del Entrenamiento de los Pilares, donde veremos el inicio del entrenamiento de los Pilares para prepararse de cara a la próxima batalla contra Muzan Kibutsuji, que se podrá ver por primera vez.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Dune", R.drawable.dune, R.drawable.dune2,"\"Duna: Parte Dos\" explorará el viaje mítico de Paul Atreides mientras se une con Chani y los Fremen mientras está en un camino de venganza contra los conspiradores que destruyeron a su familia. Enfrentando una elección entre el amor de su vida y el destino del universo conocido, se esfuerza por evitar un futuro terrible que solo él pueda prever.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Ghostbusters Apocalipsis",R.drawable.ghostbusters2, R.drawable.ghostbusters, "En Ghostbusters Apocalipsis Fantasma, regresa la familia Spengler a donde todo empezó – la icónica estación de bomberos en Nueva York – donde hace equipo junto con los Ghostbusters originales que han desarrollado un laboratorio de investigación ultrasecreto para llevar la cacería de fantasmas al siguiente nivel. Pero cuando el descubrimiento de un artefacto antiguo libera una fuerza maligna, los nuevos y viejos Ghostbusters unen sus fuerzas para proteger su hogar y salvar al mundo de una segunda era de hielo.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Héroe Por Encargo",R.drawable.heroexencargo,R.drawable.heroeencargo, "Un ex agente de las fuerzas especiales acepta un trabajo para brindar seguridad a una periodista mientras entrevista a un dictador, pero estalla un golpe militar en medio de la entrevista y se ven obligados a escapar a la jungla donde deben sobrevivir.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Vidas Pasadas", R.drawable.vidaspasadas, R.drawable.vidaspasadas1, "Nora y Hae Sung, dos amigos de la infancia profundamente unidos, son separados después de que la familia de Nora emigrara de Corea del Sur. Veinte años después, se reúnen durante una fatídica semana para enfrentarse al amor y al destino.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Madame Web", R.drawable.madameweb,R.drawable.madame,"“Mientras tanto, en otro universo…” En un cambio del típico género, Madame Web cuenta la historia independiente del origen de una de las heroínas más enigmáticas de la editorial Marvel. El thriller de suspenso protagonizado por Dakota Johnson como Cassandra Webb, una paramédico de Manhattan que puede tener habilidades clarividentes. Forzada a enfrentarse a revelaciones de su pasado, ella forja una relación con tres mujeres jóvenes destinadas a tener poderosos futuros… si pueden sobrevivir a un presente mortal.", arrayListOf<Cliente>()))
    }
    fun cargarSeries(){
        peliculas.add(Pelicula("Avatar: La leyenda de Aang",R.drawable.avatar, R.drawable.avatar, "La leyenda de Aang sigue al último sobreviviente de los Nómadas del Aire, quien deberá restaurar el equilibrio en el mundo entre las tres naciones restantes: la Tribu del Agua, el Reino Tierra y la Nación del Fuego.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Halo", R.drawable.halo, R.drawable.halos, "Una evacuación mortal cambia la guerra del Jefe Maestro con el Covenant. En Reach, un nuevo régimen toma el mando en FLEETCOM. John siente una amenaza inminente cerca. Estreno de temporada.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Solo Leveling", R.drawable.sololeveling,R.drawable.sololevelings, "En un mundo en el que ciertos humanos llamados “cazadores” poseen habilidades mágicas, estos deben luchar contra monstruos para proteger a la raza humana de una aniquilación segura. Un cazador muy débil llamado Sung Jinwoo se encuentra en una lucha en la que solo puede tratar de sobrevivir.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Mi Adorable Demonio", R.drawable.adorabledemonio, R.drawable.adorabledemonios, "Se centra en la vida de Jung Koo Won, un temerario demonio que pierde sus poderes tras conocer a Do Do-Hee, una exitosa empresaria con la que deberá de colaborar para recuperarlos.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("El monstruo de la vieja Seul", R.drawable.elmonstruo, R.drawable.elmonstruovieja, "Gyeongseong, 1945. En la oscura era colonial de Seúl, un empresario y una investigadora luchan por sobrevivir y se enfrentan a un monstruo nacido de la avaricia humana.", arrayListOf<Cliente>()))
        peliculas.add(Pelicula("Witcher", R.drawable.thewitcher,R.drawable.thewitchers, "Geralt de Rivia, un cazador de monstruos mutante, viaja en pos de su destino por un mundo turbulento en el que, a menudo, los humanos son peores que las bestias.", arrayListOf<Cliente>()))
    }


}

class PeliculaAdapter:BaseAdapter {
    var peliculas = ArrayList<Pelicula>()
    var context: Context? = null

    constructor(context: Context, peliculas: ArrayList<Pelicula>) : super(){
        this.peliculas = peliculas
        this.context = context
    }

    override fun getCount(): Int {
        return peliculas.size
    }

    override fun getItem(p0: Int): Any {
        return peliculas[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(pe: Int, p1: View?, p2: ViewGroup?): View {
        var pelicula = peliculas[pe]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var vista = inflator.inflate(R.layout.pelicula, null)
        var image: ImageView = vista.findViewById(R.id.image_movie_cell)
        var title: TextView = vista.findViewById(R.id.movie_tittle_cell)

        image.setImageResource(pelicula.image)
        title.setText(pelicula.titulo)

        image.setOnClickListener(){
            var seatsAvailable = 20-pelicula.seats.size
            Log.d("SEATS", "$seatsAvailable")
            val intent = Intent(context, detalle_pelicula::class.java)
            intent.putExtra("titulo", pelicula.titulo)
            intent.putExtra("imagen", pelicula.image)
            intent.putExtra("header", pelicula.header)
            intent.putExtra("sinopsis", pelicula.sinopsis)
            intent.putExtra("numberSeats",(20-pelicula.seats.size))
            intent.putExtra("pos", pe)
            context!!.startActivity(intent)
        }
        return vista
    }
}
