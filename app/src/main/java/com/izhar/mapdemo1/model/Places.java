package com.izhar.mapdemo1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.izhar.mapdemo1.objects.Place;

import java.util.ArrayList;
import java.util.List;

public class Places extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public Places(Context context) {
        super(context, "Database", null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table places(category text, name text, latitude real, longtude real)");
        inset(db, "Hotel", "Awash National Hotel", "8.550013416667653", "39.2666642874855");
        inset(db, "Hotel", "Dallol Hotel", "8.548836122761529", "39.254271956527965");
        inset(db, "Hotel", "Adama Gete Hotel", "8.536380091276337", "39.24773675102307");
        inset(db, "Hotel", "Tokuma Hotel", "8.5365870363883", "39.248755787209305");
        inset(db, "Hotel", "Dire International Hotel", "8.537137413786493", "39.25119416633805");
        inset(db, "Hotel", "Caneth Hotel", "8.537459719431832", "39.2530602620477");
        inset(db, "Hotel", "Dembel View International Hotel", "8.5376724871402", "39.25524539933145");
        inset(db, "Hotel", "Safari Lodge", "8.537598469008165", "39.2570116681695");
        inset(db, "Hotel", "Adama Tsion Hotel", "8.54119279306095", "39.259625427076024");
        inset(db, "Hotel", "Adama Makonnen Hotel", "8.538978719371046","39.26035198597566");
        inset(db, "Hotel", "Ayu International Hotel", "8.54013691932925", "39.26497799762956");
        inset(db, "Hotel", "Haile Resort", "8.53333017765458", "39.245788943186376");
        inset(db, "Hotel", "Melka Adama Hotel", "8.527962041560638", "39.24018315334577");
        inset(db, "Hotel", "Kereyu Hill Resort Hotel", "8.54140095038945", "39.26888863584439");
        inset(db, "Hotel", "Selam Adama Hotel", "8.538099763533657", "39.25866494156466");
        inset(db, "Hotel", "Sisay International Hotel", "8.531420114593", "39.27682965652783");
        inset(db, "Hotel", "Adama German Hotel", "8.54018235620912", "39.26051838597555");
        inset(db, "Hotel", "FENET HOTEL", "8.541241307824107", "39.266139528305246");
        inset(db, "Hotel", "Eagle Hotel", "8.546314223876097", "39.270486631716935");
        inset(db, "Hotel", "Kuriftu Resort and Spa", "8.537844677566593", "39.25447747922661");
        inset(db, "Hotel", "Adama Ras Hotel", "8.538598739222692", "39.26060360928889");
        inset(db, "Hotel", "Ekram Muslim Restaurant", "8.531356494315112", "39.27497448850881");
        inset(db, "Hotel", "Mendi Restaurant", "8.532684736704724", "39.27153656684146");
        inset(db, "Hotel", "Chef Mesay No. 4", "8.539620592000677", "39.26407040593075");
        inset(db, "Hotel", "Asay Cultural Restaurant", "8.540728430922355", "39.259386160468296");
        inset(db, "Hotel", "Mafi Restaurant", "8.542839123884571", "39.26925467760298");
        inset(db, "Hotel", "hani cake", "8.54255995340369", "39.27889252509322");
        inset(db, "Hotel", "My Kitchen", "8.543345574075461", "39.26971127984979");
        inset(db, "Hotel", "Al-Mukalla Mendi Restaurant", "8.536127934353223", "39.276410632060156");
        inset(db, "Hotel", "Yilma Butcher", "8.547537910228119", "39.27513609143622");
        inset(db, "Hotel", "Tizu coffee", "8.5241208564411", "39.277711168773564");
        inset(db, "Hotel", "Efrata Juice and Pizza", "8.541337474273574", "39.26594417502762");
        inset(db, "Hotel", "mana buna", "8.542238039789424", "39.26854686871596");
        inset(db, "Hotel", "Unique cafe", "8.542551686302929", "39.26939335328876");
        inset(db, "Hotel", "Darik Coffee", "8.556985115598621", "39.28882811897571");
        inset(db, "Hotel", "Amy Cafe", "8.546436021681199", "39.27143214570925");
        inset(db, "Hotel", "Kulbi cafe", "8.549994071609378", "39.27002472734124");
        //inset(db, "Hotel", "", "");

        inset(db, "Hospital", "Adama General Hospital and Medical College", "8.560870829312364", "39.28274515652794");
        inset(db, "Hospital", "Adama Blood Bank", "8.55904109767229", "39.28560224887181");
        inset(db, "Hospital", "Medanialem Hospital", "8.554569176454809", "39.272938253753026");
        inset(db, "Hospital", "FGAE Adama Model Clinic", "8.552311540940108", "39.26902256818392");
        inset(db, "Hospital", "Adama Teaching Hospital", "8.549133682096105", "39.27562151545423");
        inset(db, "Hospital", "Adama Referral Hospital", "8.54894735051009", "39.27604061060157");
        inset(db, "Hospital", "FamCare Clinic", "8.54391509980858", "39.27762709762926");
        inset(db, "Hospital", "St Fransisco Hospital", "8.527604430376764", "39.267186968184454");
        inset(db, "Hospital", "Alpha specialized mental clinic", "8.537630201227648", "39.26619972656536");
        inset(db, "Hospital", "Aroge Adama", "8.524364983058687", "39.23496455652767");

        inset(db, "Bank", "Bank Of Abyssinia", "8.551394272930935", "39.27815561905044");
        inset(db, "Bank", "Bank Of Abyssinia", "8.542270603337327", "39.26932719748429");
        inset(db, "Bank", "Cooperative Bank of Oromia", "8.56397857839469", "39.262333822170774");
        inset(db, "Bank", "Cooperative Bank Of Oromia", "8.547059095939284", "39.270864211403364");
        inset(db, "Bank", "Awash International Bank", "8.540572261914175", "39.26408225652776");
        inset(db, "Bank", "Awash international bank", "8.546409519094604", "39.27106708597563");
        inset(db, "Bank", "Commercial Bank of Ethiopia", "8.547272213257367", "39.27009612404661");
        inset(db, "Bank", "Commercial Bank of Ethiopia", "8.54843689541469", "39.267453362205266");
        inset(db, "Bank", "Commercial Bank of Ethiopia", "8.542659450617522", "39.26970306373885");
        inset(db, "Bank", "Commercial Bank of Ethiopia", "8.537812558220097", "39.2552299003898");
        inset(db, "Bank", "Commercial Bank of Ethiopia", "8.54266181604437", "39.26970494058311");
        inset(db, "Bank", "Dashen Bank", "8.54155139304584", "39.26731867431894");
        inset(db, "Bank", "Nib International Bank", "8.547520982155673", "39.26873593873718");
        inset(db, "Bank", "Nib International Bank", "8.547648220430812", "39.27490102094532");
        inset(db, "Bank", "Berhan International Bank", "8.547377947413743", "39.269008577133675");
        inset(db, "Bank", "Wegagen Bank", "8.54037566944718", "39.2645732452616");
        inset(db, "Bank", "Enat Bank", "8.529454320145279", "39.27067983443971");

        inset(db, "Hall", "KUTEBA SEFER", "8.525990041002922", "39.26586559763225");
        inset(db, "Hall", "Warehouse 1", "8.547807682145656", "39.25600851542749");
        inset(db, "Hall", "Olympic Hotel Adama", "8.544241080282625", "39.26967380870221");
        inset(db, "Hall", "Adama Ras Hotel", "8.538598739222692", "39.26060360928889");
        inset(db, "Hall", "Ayu International Hotel", "8.540127840459077", "39.26497685672696");

        inset(db, "University", "Harambee University", "8.547581682155183", "39.27735262708003");
        inset(db, "University", "Adama Science and Technology University", "8.559100570958869", "39.28563565100592");
        inset(db, "University", "Adama Polytechnic College", "8.555139287858154", "39.27454888597578");
        inset(db, "University", "Adama Special boarding school", "8.523160219746437", "39.25068805484978");
        inset(db, "University", "ALPHA DISTANCE COLLAGE", "8.526363321396246", "39.258332107788334");
        inset(db, "University", "Rift valley university", "8.538174366918351", "39.264674009288825");
        inset(db, "University", "East Africa College", "8.54089425618386", "39.26410272437669");
        inset(db, "University", "Oromia College", "8.541290583956139", "39.27358416083122");
        inset(db, "University", "Adama Medical College", "8.54857432488585", "39.27598889763224");
        inset(db, "University", "Genius Land University College", "8.54750099463529", "39.255322154068665");

        inset(db, "Gas", "TOTAL NAZRETH WENJI MAZORIA SERVICE STATION", "8.53844559315051", "39.253753668184295");
        inset(db, "Gas", "Adama NOC", "8.508230678587196", "39.24430701542232");
        inset(db, "Gas", "All Way Fuel Station", "8.54175117714021", "39.2833439749982");


        inset(db, "Stadium", "Adama Science and Technology University Stadium", "8.565600439683385", "39.294580986588336");
        inset(db, "Stadium", "Abiyot Fire Meda", "8.557429318490136", "39.27454065815339");
        inset(db, "Stadium", "Stadium", "8.539551885375774", "39.270989526845106");
        inset(db, "Stadium", "Abebe Bikila Stadium", "8.526440044643408", "39.25835591664673");


        inset(db, "Market", "kamil green food", "8.571715202840029", "39.29172579763233");
        inset(db, "Market", "Supermarketa Eldana", "8.552103517231455", "39.27778412368653");
        inset(db, "Market", "Gabi Market Plaza", "8.541540792421317", "39.28150127642697");
        inset(db, "Market", "Adebo Grocery Store", "8.544872309407914", "39.27134905591526");
        inset(db, "Market", "Shewa Supermarket", "8.544022404532209", "39.269641838123995");
        inset(db, "Market", "Sanaanam mini market", "8.54607126667955", "39.270559592455825");
        inset(db, "Market", "chuchu supermarket", "8.540602280852092", "39.26575484105018");
        inset(db, "Market", "Star Supermarket", "8.539863277514193", "39.26154475652782");
        inset(db, "Market", "Faye Fruits and Juice", "8.538080977558794", "39.25508932707994");
        inset(db, "Market", "Atsedu Mini market", "8.514525283372851", "39.260620553822356");


        inset(db, "Mosque", "Adama Mosque", "8.550852942165166", "39.26840580894701");
        inset(db, "Mosque", "Masjiida Aa'ishaa", "8.535114061283737", "39.280021866129765");
        inset(db, "Mosque", "Masjiida Umar", "8.536138288156193", "39.26336064950939");
        inset(db, "Mosque", "Masjiida Hudaa", "8.529464410775542", "39.25729029943227");
        inset(db, "Mosque", "Adama Mosque", "8.53978166919133", "39.29100354990749");
        inset(db, "Mosque", "Bole Mosque", "8.568984009710162", "39.28473492536837");
        inset(db, "Mosque", "Beraka Mosque", "8.570411174329966", "39.26883640241966");
        inset(db, "Mosque", "Bilal Mosque", "8.530732131273512", "39.26264202069351");
        inset(db, "Mosque", "Ansar mosque", "8.52145911341163", "39.26497378643429");
        inset(db, "Mosque", "Adama Central Mosque", "8.538865107270357", "39.273038798899826");
        inset(db, "Mosque", "Jemi'al Hebib Mosque", "8.550860748991244", "39.26841064741035");
        inset(db, "Mosque", "Baba Mosque", "8.55469515859092", "39.26811990161072");

        inset(db, "Church", "Medhanyalem Church", "8.565162007678172", "39.27899649522099");
        inset(db, "Church", "WBK Rehobot Adama", "8.579678805865727", "39.277742635226566");
        inset(db, "Church", "Nazret Eyesus Church", "8.547941595387092", "39.27386928658131");
        inset(db, "Church", "St.Marry Church Adams", "8.544177137451417", "39.275011403135494");
        inset(db, "Church", "Mehal Adama MKC Church", "8.54193780777451", "39.27660798791996");
        inset(db, "Church", "Saint Michael Church", "8.5443209885361", "39.31006319352273");
        inset(db, "Church", "Antsokeya Genet Church", "8.52011617681648", "39.24887558501989");
        inset(db, "Church", "kidane Meheret Church", "8.528187977320165", "39.234784850647884");
        inset(db, "Church", "North Naztharet Mesert Kirstos Church", "8.551348491071526", "39.27825350109951");
        inset(db, "Church", "Adama Full Gosple church", "8.541894425594943", "39.260350719113994");

        //inset(db, "Church", "", "");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists places");
        onCreate(db);
    }

    public List<Place> getPlaces(){
        List<Place> places = new ArrayList<>();
        Cursor cursor = db.query("places", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            places.add(new Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        return places;
    }

    private void inset(SQLiteDatabase db, String cat, String name, String la, String lo){
        ContentValues contentValues = new ContentValues();
        contentValues.put("category", cat);
        contentValues.put("name", name);
        contentValues.put("latitude", la);
        contentValues.put("longtude", lo);
        db.insert("places", null, contentValues);
    }

    public Place getPlace(String na){
        Cursor cursor = db.rawQuery("select * from places where name=" + na , null);
        return new Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }
}
