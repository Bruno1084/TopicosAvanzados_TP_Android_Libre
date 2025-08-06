package com.example.tpandroid_libre;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.tpandroid_libre.ejercicio4a.Clases.Genero;
import com.example.tpandroid_libre.ejercicio4a.Clases.Obra;
import com.example.tpandroid_libre.ejercicio4a.Clases.Oferta;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "tpandroid_libre_db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Crear tabla artista
        db.execSQL("CREATE TABLE artistas(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "nacionalidad TEXT NOT NULL)");

        // Crear tabla géneros
        db.execSQL("CREATE TABLE generos(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        // Crear tabla obras
        db.execSQL("CREATE TABLE obras(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idArtista INTEGER NOT NULL," +
                "idGenero INTEGER NOT NULL," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "fecha TEXT NOT NULL," +
                "precioEstimado INTEGER NOT NULL," +
                "duenio TEXT NOT NULL," +
                "path INTEGER NOT NULL," +
                "FOREIGN KEY (idArtista) REFERENCES artistas(id)," +
                "FOREIGN KEY (idGenero) REFERENCES generos(id))");

        // Crear tabla ofertas
        db.execSQL("CREATE TABLE ofertas(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idObra INTEGER NOT NULL," +
                "fechaOferta TEXT NOT NULL," +
                "comprador TEXT NOT NULL," +
                "precio REAL NOT NULL," +
                "FOREIGN KEY (idObra) REFERENCES obras(id))");

        insertMockingData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS artistas");
        db.execSQL("DROP TABLE IF EXISTS generos");
        db.execSQL("DROP TABLE IF EXISTS obras");
        db.execSQL("DROP TABLE IF EXISTS ofertas");
        onCreate(db);
    }


    // Insertar en tablas
    public long insertOferta(int idObra, String fechaOferta, String comprador, float precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idObra", idObra);
        contentValues.put("fechaOferta", fechaOferta);
        contentValues.put("comprador", comprador);
        contentValues.put("precio", precio);
        long resultado = db.insert("ofertas", null, contentValues);
        db.close();
        return resultado;
    }


    // Consultar a tablas
    public List<Oferta> getAllOfertas() {
        List<Oferta> ofertas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, idObra, fechaOferta, comprador, precio FROM ofertas",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int idObra = cursor.getInt(1);
                String fecha = cursor.getString(2);
                String comprador = cursor.getString(3);
                float precio = cursor.getFloat(4);
                ofertas.add(new Oferta(id, idObra, precio, fecha, comprador));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ofertas;
    }

    public List<Genero> getAllGeneros() {
        List<Genero> generos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT id ,nombre FROM generos", null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                generos.add(new Genero(id, nombre));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return generos;
    }

    public ArrayList<Obra> getAllObrasFromArtista(int idArtista) {
        ArrayList<Obra> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT obras.id, obras.nombre, obras.descripcion, obras.fecha, obras.precioEstimado, obras.duenio, obras.path " +
                        "FROM obras WHERE idArtista = ?",
                new String[] { String.valueOf(idArtista) }
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                String fecha = cursor.getString(3);
                int precioEstimado = cursor.getInt(4);
                String duenio = cursor.getString(5);
                int path = cursor.getInt(6);
                lista.add(new Obra(id, nombre, descripcion, fecha, precioEstimado, duenio , path));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public List<Oferta> getOfertasFromObra(int idObra) {
        List<Oferta> ofertas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, fechaOferta, comprador, precio FROM ofertas WHERE idObra = ?",
                new String[]{String.valueOf(idObra)}
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fechaOferta = cursor.getString(1);
                String comprador = cursor.getString(2);
                long precio = cursor.getInt(3);

                ofertas.add(new Oferta(id, idObra, precio, fechaOferta, comprador));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ofertas;
    }

    public List<String> getAllArtistasFromGenero(String generoBuscado) {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT DISTINCT artistas.id, artistas.nombre, artistas.nacionalidad " +
                    "FROM artistas " +
                    "INNER JOIN obras ON artistas.id = obras.idArtista " +
                    "INNER JOIN generos ON obras.idGenero = generos.id " +
                    "WHERE generos.nombre = ?",
                new String[]{generoBuscado}
        );

        if (cursor.moveToFirst()) {
            do {
                String id = String.valueOf(cursor.getInt(0));
                String nombre = cursor.getString(1);
                String nacionalidad = cursor.getString(2);
                lista.add(id + " - " + nombre + " - " + nacionalidad);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public Obra getObraById(int idObra) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, nombre, descripcion, fecha, precioEstimado, duenio, path " +
                    "FROM obras WHERE obras.id = ?", new String []{String.valueOf(idObra)}
        );

        cursor.moveToFirst();
        int id = cursor.getInt(0);
        String nombre = cursor.getString(1);
        String descripcion = cursor.getString(2);
        String fecha = cursor.getString(3);
        int precioEstimado = cursor.getInt(4);
        String duenio = cursor.getString(5);
        int path = cursor.getInt(6);

        cursor.close();
        db.close();

        return new Obra(id, nombre, descripcion, fecha, precioEstimado, duenio, path);
    }


    // Estos son los valores de prueba para la base de datos
    public void insertMockingData(SQLiteDatabase db) {
        Log.d("DBHelper", "Insertando datos de prueba...");

        ContentValues cv = new ContentValues();

        // Géneros
        String[] generos = {"Pintura", "Escultura", "Arte Abstracto", "Manga"};
        for (String genero : generos) {
            cv.clear();
            cv.put("nombre", genero);
            db.insert("generos", null, cv);
        }

        // Artistas
        String[][] artistas = {
                {"Vincent Van Gogh", "Países Bajos"},
                {"Claude Monet", "Francia"},
                {"Pablo Picasso", "España"},
                {"Michelangelo Caravaggio", "Italia"},

                {"Auguste Rodin", "Francia"},
                {"Michelangelo", "Italia"},
                {"Luo Li Rong", "China"},
                {"Gian Lorenzo Bernini", "Italia"},

                {"Wassily Kandinsky", "Rusia"},
                {"Jackson Pollock", "Estados Unidos"},
                {"Hilma af Klint", "Suecia"},
                {"Piet Mondrian", "Paises Bajos"},

                {"Hirohiko Araki", "Japón"},
                {"Kentaro Miura", "Japón"},
                {"Tsubasa Yamaguchi", "Japón"},
                {"Tatsuki Fujimoto", "Japón"}
        };
        for (String[] artista : artistas) {
            cv.clear();
            cv.put("nombre", artista[0]);
            cv.put("nacionalidad", artista[1]);
            db.insert("artistas", null, cv);
        }

        // Obras
        Object[][] obras = {
                // Pintores
                // Van Gogh (id 1)
                {1, 1, "La Noche Estrellada", "Óleo sobre lienzo", "01-06-1889", 1001, "Museo MoMA", R.drawable.img_starry_night},
                {1, 1, "Los Girasoles", "Naturaleza Muerta", "01-06-1889", 32515, "Galería Nacional", R.drawable.img_sunflowers},
                {1, 1, "Autorretrato", "Autorretrato", "01-06-1889", 5256, "Museo Van Gogh", R.drawable.img_self_portrait},
                {1, 1, "La Cosecha de Provenza", "Cosecha", "00-00-1888", 43626, "Museo Van Gogh", R.drawable.img_cosecha_en_provenza},
                {1, 1, "El Cuarto", "El cuarto de Van Gogh", "00-10-1888", 4375726, "Museo Van Gogh", R.drawable.img_the_bedroom},

                // Claude Monet (id 2)
                {2, 1, "Impresión, Sol Naciente", "Paisaje", "00-00-1872", 37626, "Museo Marmottan", R.drawable.img_sunrise},
                {2, 1, "Nenúfares", "Naturaleza", "00-00-1926", 79858854, "Orangerie", R.drawable.img_water_lilies},
                {2, 1, "Joven Mujer con un Parasol", "Retrato impresionista", "00-00-1875", 246810, "Museo de Orsay", R.drawable.img_woman_with_a_parasol},
                {2, 1, "La Grenouillère", "Escena al aire libre", "00-00-1869", 135790, "Museo Metropolitano", R.drawable.img_la_grenouillere},
                {2, 1, "La Estación Saint-Lazare", "Tren y vapor", "00-00-1877", 987654, "Museo de Orsay", R.drawable.img_the_saint_lazare_station},

                // Pablo Picasso (id 3)
                {3, 1, "Las Señoritas de Avignon", "Cubismo", "00-00-1907", 32423452, "Museo MoMA", R.drawable.img_les_demoiselles},
                {3, 1, "La Vida", "Simbólica", "1903", 251361, "Cleveland Museum of Art", R.drawable.img_la_vie},
                {3, 1, "Guernica", "Guerra Civil Española", "00-00-1937", 321321, "Museo Reina Sofía", R.drawable.img_guernica},
                {3, 1, "El viejo Guitarrista", "Periodo Azul", "00-00-1903", 123123, "Art Institute of Chicago", R.drawable.img_the_old_guitarist},
                {3, 1, "El Sueño", "Retrato cubista", "00-00-1932", 342000, "Colección Privada", R.drawable.img_el_sueno},

                // Caravaggio (id 4)
                {4, 1, "La Vocación de San Mateo", "Barroco", "00-00-1600", 525252, "San Luigi dei Francesi", R.drawable.img_the_calling_of_saint_matthew},
                {4, 1, "Judith decapitando a Holofernes", "Dramático", "00-00-1598", 333444, "Galleria Nazionale d'Arte Antica", R.drawable.img_judith_beheading_holofernes},
                {4, 1, "David con la cabeza de Goliat", "Tenebrismo", "00-00-1610", 777888, "Galería Borghese", R.drawable.img_david_head_goliat},
                {4, 1, "Baco", "Mitología", "00-00-1596", 246246, "Galería Uffizi", R.drawable.img_baco},
                {4, 1, "La Crucifixión de San Pedro", "Martirio", "00-00-1601", 135135, "Santa Maria del Popolo", R.drawable.img_la_crusifixion_san_pedro},


                // Escultores
                // Rodin (id 5)
                {5, 2, "El Pensador", "Bronce", "00-00-1904", 25243242, "Museo Rodin", R.drawable.img_thinker},
                {5, 2, "La Puerta del Infierno", "Bronce", "00-00-1917", 111111111, "Museo Rodin", R.drawable.img_gates_of_hell},
                {5, 2, "La Eterna Primavera", "Romanticismo", "00-00-1901", 444000, "Museo Rodin", R.drawable.img_la_eterna_primavera},
                {5, 2, "El Beso", "Romanticismo", "00-00-1882", 555000, "Museo Rodin", R.drawable.img_el_beso},
                {5, 2, "Hombre Caminando", "Estudio de movimiento", "00-00-1907", 666000, "Museo Rodin", R.drawable.img_hombre_caminando},

                // Michelangelo (id 6)
                {6, 2, "David", "Mármol", "00-00-1504", 573642462, "Galería de la Academia", R.drawable.img_david},
                {6, 2, "Cristo Redentor", "Cristiana", "00-00-1519", 264347967, "Santa Maria sopra Minerva", R.drawable.img_christ_the_redeemer},
                {6, 2, "La Piedad", "Cristiana", "00-00-1499", 888888, "Basílica de San Pedro", R.drawable.img_the_pieta},
                {6, 2, "Madonna de Brujas", "Virgen María", "00-00-1504", 777777, "Iglesia de Nuestra Señora", R.drawable.img_madonna_de_brujas},
                {6, 2, "Esclavo Rebelde", "Esclavo", "00-00-1509", 364226, "Iglesia de Nuestra Señora", R.drawable.img_rebellious_slave},

                // Luo Li Rong (id 7)
                {7, 2, "La Jeune Fille", "Escultura figurativa", "00-00-2015", 222222, "Colección privada", R.drawable.img_la_jeune_fille},
                {7, 2, "Danseuse", "Movimiento y elegancia", "00-00-2016", 333333, "Colección privada", R.drawable.img_danseuse},
                {7, 2, "En Attendant", "Contemplativa", "00-00-2017", 444444, "Galería Li Rong", R.drawable.img_en_attendant},
                {7, 2, "Gracieuse", "Belleza natural", "00-00-2019", 555555, "Galería Li Rong", R.drawable.img_en_attendant},
                {7, 2, "Aurore", "Inspiración", "00-00-2020", 666666, "Colección privada", R.drawable.img_aurore},

                // Gian Lorenzo Bernini (id 8)
                {8, 2, "Éxtasis de Santa Teresa", "Religioso", "00-00-1652", 111000, "Santa Maria della Vittoria", R.drawable.img_extasis_de_santa_teresa},
                {8, 2, "Apolo y Dafne", "Mitología", "00-00-1625", 222000, "Galería Borghese", R.drawable.img_apollo_and_dafne},
                {8, 2, "David", "Biblia", "00-00-1624", 333000, "Galería Borghese", R.drawable.img_david_bernini},
                {8, 2, "Rapto de Proserpina", "Mitología", "00-00-1622", 444000, "Galería Borghese", R.drawable.img_rapto_de_proserpina},
                {8, 2, "Fuente de los Cuatro Ríos", "Arquitectura y escultura", "00-00-1651", 555000, "Piazza Navona", R.drawable.img_fuente_cuatro_rios},


                // Pintores Abstractos
                // Kandinsky (id 9)
                {9, 3, "Improvisación 28", "Abstracto Lírico", "00-00-1912", 65625, "Museo Guggenheim",  R.drawable.img_improvisation_28},
                {9, 3, "Composición VIII", "Sinestesia geométrica", "00-00-1923", 4362525, "Museo Guggenheim", R.drawable.img_composition_viii},
                {9, 3, "Cuadrado Rojo", "Minimalismo", "00-00-1915", 42625626, "Colección Privada", R.drawable.img_cuadrado_rojo},
                {9, 3, "Músicos", "Figuras estilizadas", "00-00-1925", 261523, "Museo Hermitage", R.drawable.img_musicos},
                {9, 3, "Círculos en un círculo", "Figuración abstracta", "00-00-1923", 464262, "Philadelphia Museum", R.drawable.img_circulos_en_circulo},

                // Pollock (id 10)
                {10, 3, "Convergencia", "Pintura de Acción", "00-00-1952", 1264215, "Albright-Knox", R.drawable.img_convergence},
                {10, 3, "Bruma Lavanda", "Expresionismo", "00-00-1912", 1243264,"National Gallery of Art", R.drawable.img_lavender_mist},
                {10, 3, "N°5, 1948", "Expresionismo abstracto", "00-00-1948", 4364262, "Colección Privada", R.drawable.img_n5_1948},
                {10, 3, "Ritmo de Otoño", "Drip painting", "00-00-1950", 324625136, "Colección Privada", R.drawable.img_ritmo_otono},
                {10, 3, "Full Fathom Five", "Capas texturizadas", "00-00-1947", 3646215, "Museo MoMA", R.drawable.img_full_fathom_five},

                // Klint (id 11)
                {11, 3, "El Gran Cuadro", "Simbolismo esotérico", "00-00-1917", 4364262, "Moderna Musset", R.drawable.img_gran_cuadro},
                {11, 3, "Árbol del Conocimiento", "Mística natural", "00-00-1913", 264376256, "Museo Spritmuseum", R.drawable.img_arbol_conocimiento},
                {11, 3, "Grupo X, No.1", "Abstracto visionario", "00-00-1915", 253252, "Colección Af Klint", R.drawable.img_grupo_x},
                {11, 3, "Serie Azul", "Espiritualidad abstracta", "00-00-1917", 3255252, "Moderna Museet", R.drawable.img_serie_azul},
                {11, 3, "Serie Altarpiece", "Geometría espiritual", "00-00-1915", 52535, "Gemeentemuseum", R.drawable.img_serie_altarpiece},

                // Mondrian (id 12)
                {12, 3, "Composición con Rojo, Amarillo y Azul", "Geometría pura", "00-00-1919", 324626, "Museo MoMA", R.drawable.img_composicion_rojo_verde_azul},
                {12, 3, "Broadway Boogie Woogie", "Última hora", "00-00-1944", 4322526, "Kunstmuseum Den Haag", R.drawable.img_broadway_boogie_woogie},
                {12, 3, "Árbol en flor", "Naturaleza estilizada", "00-00-1912", 525326, "Museo Stedekijk", R.drawable.img_arbol_flor},
                {12, 3, "Composición en Blanco y Negro", "Minimalistmo", "00-00-1930", 26352622, "Colección Privada", R.drawable.img_composicion_blanco_negro},


                // Mangakas
                // Araki (id 13)
                {13, 4, "Phantom Blood Vol.1", "Primera parte del manga Jojo's Bizarre Adventure", "01-01-1987", 500, "Weekly Shōnen Jump", R.drawable.img_phantom_blood_vol1},
                {13, 4, "Battle Tendency Vol.1", "Segunda parte del manga Jojo's Bizarre Adventure", "01-11-1987", 500, "Weekly Shōnen Jump", R.drawable.img_battle_tendency_vol1},
                {13, 4, "Stardust Crusaders Vol.1", "Tercera parte del manga Jojo's Bizarre Adventure", "01-03-1989", 500, "Weekly Shōnen Jump", R.drawable.img_stardust_crusaders_vol1},
                {13, 4, "Diamond is Unbreakable Vol.1", "Cuarta parte de Jojo's Bizarre Adventure", "00-00-1992", 600, "Weekly Shōnen Jump", R.drawable.img_diamond_is_unbreakable},
                {13, 4, "Vento Aureo Vol.1", "Quinta parte de Jojo's Bizarre Adventure", "00-12-1995", 600, "Weekly Shōnen Jump", R.drawable.img_vento_aureo},

                // Miura (id 14)
                {14, 4, "Berserk Vol.1", "Primer volumen de Berserk", "00-10-1989", 500, "Hakusensha", R.drawable.img_berserk_vol1},
                {14, 4, "Berserk Vol.2", "Segundo volumen de Berserk", "00-00-1991", 500, "Hakusensha", R.drawable.img_berserk_vol2},
                {14, 4, "Berserk Vol.3", "Tercer volumen de Berserk", "00-00-1991", 500, "Hakusensha", R.drawable.img_berserk_vol3},
                {14, 4, "Berserk Vol.4", "Cuarto volumen de Berserk", "00-00-1992", 500, "Hakusensha", R.drawable.img_berserk_vol4},
                {14, 4, "Berserk Vol.5", "Quinto volumen de Berserk", "00-00-1993", 500, "Hakusensha", R.drawable.img_berserk_vol5},

                // Yamaguchi (id 15)
                {15, 4, "Kanojo to Kanojo no Neko", "Oneshot by Tsubasa Yamaguchi", "09-9-2019", 2000, "IVrea", R.drawable.img_kanojo_no_neko},
                {15, 4, "Blue Period Vol.1", "Primer volumen de Blue Period", "00-00-2017", 2555, "Monthly Afternoon", R.drawable.img_blue_period_vol1},
                {15, 4, "Blue Period Vol.2", "Segundo volumen de Blue Period", "00-00-2018", 2555, "Monthly Afternoon", R.drawable.img_blue_period_vol2},
                {15, 4, "Blue Period Vol.3", "Tercer volumen de Blue Period", "00-00-2019", 2555, "Monthly Afternoon", R.drawable.img_blue_period_vol3},
                {15, 4, "Blue Period Vol.4", "Cuarto volumen de Blue Period", "00-00-2020", 2555, "Monthly Afternoon", R.drawable.img_blue_period_vol4},

                // Fujimoto (id 16)
                {16, 4, "Look Back", "Manga Oneshot", "19-07-2021", 3000, "Shūeisha", R.drawable.img_look_back},
                {16, 4, "Fire Punch Vol.1", "Firepuch by Tatsuki Fujimoto", "01-03-2018", 3000, "Shūeisha", R.drawable.img_fire_punch_vol1},
                {16, 4, "Goodbye Eri", "Manga Oneshot", "11-04-2022", 3000, "Shūeisha", R.drawable.img_goodbye_eri},
                {16, 4, "Chainsaw Man", "Chainsaw Man by Tatsuki Fujumoto", "13-12-2018", 3000, "Shūeisha", R.drawable.img_chainsaw_man},
                {16, 4, "Just Listen to the Song", "Oneshot by Tatsuki Fujimoto", "00-07-2022", 3000, "Shōnen Jump+", R.drawable.img_just_listen_to_the_song}
        };

        for (Object[] obra : obras) {
            cv.clear();
            cv.put("idArtista", (int) obra[0]);
            cv.put("idGenero", (int) obra[1]);
            cv.put("nombre", (String) obra[2]);
            cv.put("descripcion", (String) obra[3]);
            cv.put("fecha", (String) obra[4]);
            cv.put("precioEstimado", (int) obra[5]);
            cv.put("duenio", (String) obra[6]);
            cv.put("path", (int) obra[7]);
            db.insert("obras", null, cv);
        }
    }
}
