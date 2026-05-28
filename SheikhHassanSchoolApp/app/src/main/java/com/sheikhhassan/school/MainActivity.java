package com.sheikhhassan.school;

import android.app.*;import android.os.*;import android.view.*;import android.graphics.*;import android.graphics.drawable.*;import android.content.*;import java.util.*;

public class MainActivity extends Activity{
 public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(Color.rgb(0,92,58));setContentView(new HomeView(this));}
 static class HomeView extends View{
  Paint p=new Paint(1),t=new Paint(1); String[] titles={"دفع الرسوم","PDF الكتب الدراسية","اعلام الطلاب","معلومات المطور","التواصل مع إدارة المدرسة"};
  String[] subs={"دفع الرسوم الدراسية إلكترونياً","تحميل الكتب الدراسية بصيغة PDF","عرض اعلام الطلاب ونتائجهم","معلومات عن مطور التطبيق","تواصل مباشر مع إدارة المدرسة"};
  HomeView(Context c){super(c);t.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD));setLayerType(View.LAYER_TYPE_SOFTWARE,null);} int W,H; float d;
  protected void onDraw(Canvas c){W=getWidth();H=getHeight();d=getResources().getDisplayMetrics().density; c.drawColor(Color.rgb(248,249,249)); drawHeader(c); drawCards(c); drawFooter(c);}
  void txt(Canvas c,String s,float x,float y,float size,int col,Paint.Align a,boolean bold){t.setTextSize(size*d);t.setColor(col);t.setTextAlign(a);t.setTypeface(Typeface.create(Typeface.SANS_SERIF,bold?Typeface.BOLD:Typeface.NORMAL));c.drawText(s,x,y,t);} 
  void drawHeader(Canvas c){float hh=285*d; LinearGradient g=new LinearGradient(0,0,0,hh,Color.rgb(0,95,60),Color.rgb(0,120,78),Shader.TileMode.CLAMP);p.setShader(g);c.drawRect(0,0,W,hh,p);p.setShader(null);p.setColor(Color.argb(28,255,255,255)); for(int i=0;i<12;i++){c.drawCircle(W*(i/11f),hh-30*d+(i%2)*20*d,35*d,p);} p.setColor(Color.argb(42,255,255,255)); p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(2*d); for(int i=0;i<7;i++){c.drawCircle((70+i*85)*d,(55+i%3*55)*d,55*d,p);} p.setStyle(Paint.Style.FILL);
   // menu
   p.setColor(Color.WHITE);p.setStrokeWidth(6*d);p.setStrokeCap(Paint.Cap.ROUND); for(int i=0;i<3;i++)c.drawLine(W-58*d,42*d+i*15*d,W-24*d,42*d+i*15*d,p);
   // logo
   p.setColor(Color.WHITE);c.drawCircle(105*d,105*d,67*d,p);p.setColor(Color.rgb(0,95,60));p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(5*d);c.drawArc(new RectF(67*d,63*d,144*d,156*d),120,150,false,p);c.drawArc(new RectF(67*d,63*d,144*d,156*d),-90,150,false,p);p.setStyle(Paint.Style.FILL);txt(c,"☾",105*d,74*d,30,Color.rgb(0,95,60),Paint.Align.CENTER,true);txt(c,"▱",92*d,130*d,42,Color.rgb(0,95,60),Paint.Align.CENTER,true);txt(c,"▱",119*d,130*d,42,Color.rgb(0,95,60),Paint.Align.CENTER,true);txt(c,"مدرسة",W/2,82*d,25,Color.WHITE,Paint.Align.CENTER,true);txt(c,"الشيخ حسن محمد نور بجة",W/2,132*d,35,Color.WHITE,Paint.Align.CENTER,true);txt(c,"منارة العلم والتربية، تسعى لبناء جيل متعلم",W/2,187*d,18,Color.WHITE,Paint.Align.CENTER,true);txt(c,"واعٍ، ملتزم بقيمه، ومبدع في مجتمعه.",W/2,218*d,18,Color.WHITE,Paint.Align.CENTER,true);p.setColor(Color.rgb(212,174,75));p.setStrokeWidth(2*d);c.drawLine(W/2-55*d,162*d,W/2+55*d,162*d,p);txt(c,"◇",W/2,168*d,20,Color.rgb(212,174,75),Paint.Align.CENTER,false);
   p.setColor(Color.WHITE);Path wave=new Path();wave.moveTo(0,hh-15*d);wave.quadTo(W/2,hh+35*d,W,hh-15*d);wave.lineTo(W,hh+35*d);wave.lineTo(0,hh+35*d);wave.close();c.drawPath(wave,p);
  }
  void round(Canvas c,RectF r,int color,float rad){p.setColor(color);p.setStyle(Paint.Style.FILL);p.setShadowLayer(12*d,0,4*d,Color.argb(50,0,0,0));c.drawRoundRect(r,rad,rad,p);p.clearShadowLayer();}
  void drawCards(Canvas c){float y=315*d; for(int i=0;i<5;i++){RectF r=new RectF(45*d,y,W-45*d,y+95*d);round(c,r,Color.WHITE,18*d);RectF ic=new RectF(65*d,y+16*d,125*d,y+76*d);LinearGradient gg=new LinearGradient(ic.left,ic.top,ic.right,ic.bottom,Color.rgb(31,178,117),Color.rgb(0,104,64),Shader.TileMode.CLAMP);p.setShader(gg);c.drawRoundRect(ic,14*d,14*d,p);p.setShader(null);drawIcon(c,i,ic);txt(c,titles[i],W-145*d,y+45*d,24,Color.rgb(0,70,45),Paint.Align.RIGHT,true);txt(c,subs[i],W-145*d,y+75*d,15,Color.rgb(92,92,92),Paint.Align.RIGHT,false);txt(c,"›",W-75*d,y+60*d,48,Color.rgb(0,95,60),Paint.Align.CENTER,false); y+=112*d;}}
  void drawIcon(Canvas c,int i,RectF r){p.setColor(Color.WHITE);p.setStyle(Paint.Style.FILL);float cx=r.centerX(),cy=r.centerY();t.setTextAlign(Paint.Align.CENTER);String s=i==0?"▰":i==1?"PDF":i==2?"♟":i==3?"i":"☎";txt(c,s,cx,cy+10*d,i==1?17:31,Color.WHITE,Paint.Align.CENTER,true);} 
  void drawFooter(Canvas c){p.setColor(Color.argb(35,0,95,60));Path m=new Path();float y=H-78*d;m.moveTo(0,H);m.lineTo(0,y+25*d);for(int i=0;i<10;i++){m.lineTo(i*W/9f,y+(i%2==0?0:25*d));}m.lineTo(W,H);m.close();c.drawPath(m,p);txt(c,"✧",W/2,H-105*d,22,Color.rgb(0,116,72),Paint.Align.CENTER,true);txt(c,"مرحباً بك في تطبيق",W/2,H-73*d,20,Color.rgb(0,116,72),Paint.Align.CENTER,true);txt(c,"❖ مدرسة الشيخ حسن محمد نور بجة ❖",W/2,H-38*d,21,Color.rgb(0,80,50),Paint.Align.CENTER,true);} 
 }
}
