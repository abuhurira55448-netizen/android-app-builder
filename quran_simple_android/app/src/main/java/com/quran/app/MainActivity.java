package com.quran.app;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.net.Uri;
import android.view.*;
import android.widget.*;
import org.json.*;
import java.io.*;
import java.net.*;

public class MainActivity extends Activity {
    LinearLayout root, menu, contentBox;
    TextView title, content;
    ListView list;
    int tasbeeh = 0;

    String[] surahs = {
        "1 - الفاتحة","2 - البقرة","3 - آل عمران","4 - النساء","5 - المائدة","6 - الأنعام","7 - الأعراف","8 - الأنفال","9 - التوبة","10 - يونس",
        "11 - هود","12 - يوسف","13 - الرعد","14 - إبراهيم","15 - الحجر","16 - النحل","17 - الإسراء","18 - الكهف","19 - مريم","20 - طه",
        "21 - الأنبياء","22 - الحج","23 - المؤمنون","24 - النور","25 - الفرقان","26 - الشعراء","27 - النمل","28 - القصص","29 - العنكبوت","30 - الروم",
        "31 - لقمان","32 - السجدة","33 - الأحزاب","34 - سبأ","35 - فاطر","36 - يس","37 - الصافات","38 - ص","39 - الزمر","40 - غافر",
        "41 - فصلت","42 - الشورى","43 - الزخرف","44 - الدخان","45 - الجاثية","46 - الأحقاف","47 - محمد","48 - الفتح","49 - الحجرات","50 - ق",
        "51 - الذاريات","52 - الطور","53 - النجم","54 - القمر","55 - الرحمن","56 - الواقعة","57 - الحديد","58 - المجادلة","59 - الحشر","60 - الممتحنة",
        "61 - الصف","62 - الجمعة","63 - المنافقون","64 - التغابن","65 - الطلاق","66 - التحريم","67 - الملك","68 - القلم","69 - الحاقة","70 - المعارج",
        "71 - نوح","72 - الجن","73 - المزمل","74 - المدثر","75 - القيامة","76 - الإنسان","77 - المرسلات","78 - النبأ","79 - النازعات","80 - عبس",
        "81 - التكوير","82 - الانفطار","83 - المطففين","84 - الانشقاق","85 - البروج","86 - الطارق","87 - الأعلى","88 - الغاشية","89 - الفجر","90 - البلد",
        "91 - الشمس","92 - الليل","93 - الضحى","94 - الشرح","95 - التين","96 - العلق","97 - القدر","98 - البينة","99 - الزلزلة","100 - العاديات",
        "101 - القارعة","102 - التكاثر","103 - العصر","104 - الهمزة","105 - الفيل","106 - قريش","107 - الماعون","108 - الكوثر","109 - الكافرون","110 - النصر",
        "111 - المسد","112 - الإخلاص","113 - الفلق","114 - الناس"
    };

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        buildUi();
        showHome();
    }

    TextView tv(String s, int sp, int c, int gravity) {
        TextView v = new TextView(this); v.setText(s); v.setTextSize(sp); v.setTextColor(c); v.setGravity(gravity); v.setPadding(16,16,16,16); return v;
    }
    Button btn(String s) { Button b = new Button(this); b.setText(s); b.setTextSize(18); return b; }

    void buildUi(){
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(11,11,11)); root.setPadding(10,10,10,10);
        title = tv("۞ القرآن الكريم ۞", 30, Color.rgb(212,175,55), Gravity.CENTER); root.addView(title);
        menu = new LinearLayout(this); menu.setOrientation(LinearLayout.HORIZONTAL); menu.setGravity(Gravity.CENTER);
        Button q=btn("السور"), a=btn("الأذكار"), t=btn("التسبيح"), d=btn("المطور");
        menu.addView(q); menu.addView(a); menu.addView(t); menu.addView(d); root.addView(menu);
        contentBox = new LinearLayout(this); contentBox.setOrientation(LinearLayout.VERTICAL); contentBox.setBackgroundColor(Color.rgb(18,18,18));
        root.addView(contentBox, new LinearLayout.LayoutParams(-1,0,1));
        setContentView(root);
        q.setOnClickListener(v -> showSurahs());
        a.setOnClickListener(v -> showAzkar());
        t.setOnClickListener(v -> { tasbeeh++; showText("۞ عداد التسبيح ۞\n\n"+tasbeeh+"\n\nسبحان الله والحمد لله ولا إله إلا الله والله أكبر", Gravity.CENTER); });
        d.setOnClickListener(v -> showDeveloper());
    }

    void clear(){ contentBox.removeAllViews(); }
    void showText(String s, int g){ clear(); ScrollView sv=new ScrollView(this); content=tv(s,24,Color.WHITE,g); content.setLineSpacing(8,1); sv.addView(content); contentBox.addView(sv,new LinearLayout.LayoutParams(-1,-1)); }
    void showHome(){ showText("مرحباً بك\n\nاختر السور لقراءة القرآن الكريم، أو الأذكار، أو التسبيح.\n\nتصميم بسيط داكن بلمسة ذهبية.", Gravity.CENTER); }

    void showSurahs(){
        clear(); list=new ListView(this); list.setBackgroundColor(Color.rgb(26,26,26));
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, surahs){
            public View getView(int p, View v, android.view.ViewGroup parent){ TextView x=(TextView)super.getView(p,v,parent); x.setTextColor(Color.WHITE); x.setTextSize(21); x.setGravity(Gravity.RIGHT); x.setPadding(12,18,12,18); return x; }
        };
        list.setAdapter(ad); contentBox.addView(list,new LinearLayout.LayoutParams(-1,-1));
        list.setOnItemClickListener((p,v,pos,id)->{ showText("جاري تحميل السورة...",Gravity.CENTER); new LoadSurah().execute(String.valueOf(pos+1)); });
    }

    void showAzkar(){ showText("۞ الأذكار ۞\n\nسبحان الله\n\nالحمد لله\n\nلا إله إلا الله\n\nالله أكبر\n\nأستغفر الله العظيم\n\nاللهم صل وسلم على نبينا محمد\n\nلا حول ولا قوة إلا بالله", Gravity.RIGHT); }
    void showDeveloper(){
        clear(); ScrollView sv=new ScrollView(this); LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setGravity(Gravity.CENTER); box.setPadding(12,12,12,12);
        ImageView img=new ImageView(this); img.setImageResource(getResources().getIdentifier("developer","drawable",getPackageName())); img.setAdjustViewBounds(true); img.setMaxHeight(650); box.addView(img);
        TextView info=tv("۞ معلومات المطور ۞\n\nالاسم: أبو هريرة محمد ابراهيم\n\nالوظيفة: مطور تطبيقات وبرامج\n\nواتساب: 0963571187\n\nالنبذة: مطور تطبيقات وبرامج يعمل لدى مؤسسة تكنولوجية.",22,Color.WHITE,Gravity.RIGHT); box.addView(info);
        Button w=btn("فتح واتساب"); box.addView(w); w.setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/249963571187"))));
        sv.addView(box); contentBox.addView(sv,new LinearLayout.LayoutParams(-1,-1));
    }

    class LoadSurah extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... p){
            try{
                URL url=new URL("https://api.alquran.cloud/v1/surah/"+p[0]+"/quran-uthmani");
                HttpURLConnection con=(HttpURLConnection)url.openConnection(); con.setConnectTimeout(15000); con.setReadTimeout(20000);
                BufferedReader r=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8")); StringBuilder raw=new StringBuilder(); String line;
                while((line=r.readLine())!=null) raw.append(line); r.close();
                JSONObject data=new JSONObject(raw.toString()).getJSONObject("data"); JSONArray ay=data.getJSONArray("ayahs");
                StringBuilder out=new StringBuilder("۞ "+data.getString("name")+" ۞\n\n");
                for(int i=0;i<ay.length();i++){ out.append(ay.getJSONObject(i).getString("text")).append(" ﴿").append(i+1).append("﴾\n\n"); }
                return out.toString();
            }catch(Exception e){ return "تعذر تحميل السورة. تأكد من اتصال الإنترنت ثم حاول مرة أخرى."; }
        }
        protected void onPostExecute(String s){ showText(s,Gravity.RIGHT); }
    }
}
