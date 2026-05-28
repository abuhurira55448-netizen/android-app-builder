package com.sheikhhassan.school;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.text.TextUtils;
import android.view.Gravity;
import java.util.*;

public class MainActivity extends Activity {
    final int GREEN = Color.rgb(0, 93, 62);
    final int DARK = Color.rgb(0, 64, 45);

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(new SchoolHome(this));
    }

    int dp(Context c, int v){ return (int)(v*c.getResources().getDisplayMetrics().density + .5f); }

    class SchoolHome extends ScrollView {
        LinearLayout root;
        SchoolHome(Context c){ super(c); setFillViewport(true); setBackgroundColor(Color.rgb(247,248,247)); build(c); }
        void build(Context c){
            root = new LinearLayout(c); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER_HORIZONTAL); addView(root, new ScrollView.LayoutParams(-1,-2));
            HeaderView header = new HeaderView(c); root.addView(header, new LinearLayout.LayoutParams(-1, dp(c,300)));
            String[][] items = {
                    {"دفع الرسوم", "دفع الرسوم الدراسية إلكترونياً", "card"},
                    {"الكتب الدراسية PDF", "تحميل الكتب الدراسية بصيغة PDF", "pdf"},
                    {"اعلام الطلاب", "عرض اعلام الطلاب ونتائجهم", "users"},
                    {"معلومات المطور", "معلومات عن مطور التطبيق", "info"},
                    {"التواصل مع إدارة المدرسة", "تواصل مباشر مع إدارة المدرسة", "head"}
            };
            for(String[] it: items) root.addView(row(c,it[0],it[1],it[2]));
            FooterView f = new FooterView(c); root.addView(f, new LinearLayout.LayoutParams(-1, dp(c,120)));
        }
        View row(Context c, String title, String sub, String icon){
            LinearLayout card = new LinearLayout(c); card.setOrientation(LinearLayout.HORIZONTAL); card.setGravity(Gravity.CENTER_VERTICAL); card.setPadding(dp(c,18),0,dp(c,18),0);
            GradientDrawable bg = new GradientDrawable(); bg.setColor(Color.WHITE); bg.setCornerRadius(dp(c,18)); bg.setStroke(1, Color.rgb(232,232,232)); card.setBackground(bg); card.setElevation(dp(c,4));
            LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, dp(c,116)); cp.setMargins(dp(c,28), dp(c,10), dp(c,28), dp(c,10)); card.setLayoutParams(cp);
            IconView iv = new IconView(c, icon); card.addView(iv, new LinearLayout.LayoutParams(dp(c,70), dp(c,70)));
            LinearLayout texts = new LinearLayout(c); texts.setOrientation(LinearLayout.VERTICAL); texts.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT); texts.setPadding(dp(c,14),0,dp(c,14),0);
            TextView t = tv(c,title,28,DARK,true); TextView s = tv(c,sub,15,Color.rgb(95,95,95),false); texts.addView(t); texts.addView(s);
            card.addView(texts, new LinearLayout.LayoutParams(0,-1,1));
            TextView arrow = tv(c,"›",44,GREEN,true); arrow.setGravity(Gravity.CENTER); card.addView(arrow, new LinearLayout.LayoutParams(dp(c,36),-1));
            card.setOnClickListener(v -> Toast.makeText(c, title, Toast.LENGTH_SHORT).show());
            return card;
        }
        TextView tv(Context c,String txt,int sp,int color,boolean bold){ TextView v=new TextView(c); v.setText(txt); v.setTextSize(sp); v.setTextColor(color); v.setGravity(Gravity.RIGHT); v.setSingleLine(false); v.setEllipsize(TextUtils.TruncateAt.END); v.setTypeface(Typeface.DEFAULT, bold?Typeface.BOLD:Typeface.NORMAL); return v; }
    }

    class HeaderView extends View {
        Paint p = new Paint(1);
        HeaderView(Context c){ super(c); }
        protected void onDraw(Canvas canvas){ super.onDraw(canvas); int w=getWidth(), h=getHeight();
            LinearGradient g = new LinearGradient(0,0,w,h, Color.rgb(0,112,76), Color.rgb(0,70,48), Shader.TileMode.CLAMP); p.setShader(g); canvas.drawRect(0,0,w,h,p); p.setShader(null);
            p.setColor(Color.argb(35,255,255,255)); p.setTextAlign(Paint.Align.CENTER); p.setTextSize(dp(getContext(),72)); canvas.drawText("☪", w*.82f, dp(getContext(),78), p);
            p.setTextSize(dp(getContext(),90)); canvas.drawText("مسجد", w*.82f, dp(getContext(),185), p);
            p.setColor(Color.WHITE); p.setTypeface(Typeface.DEFAULT_BOLD); p.setTextSize(dp(getContext(),26)); canvas.drawText("مدرسة", w*.60f, dp(getContext(),86), p);
            p.setTextSize(dp(getContext(),34)); canvas.drawText("الشيخ حسن محمد نور بجة", w*.63f, dp(getContext(),140), p);
            p.setTypeface(Typeface.DEFAULT); p.setTextSize(dp(getContext(),16)); canvas.drawText("منارة العلم والتربية، تسعى لبناء جيل متعلم", w*.63f, dp(getContext(),185), p); canvas.drawText("واعٍ، ملتزم بقيمه، ومبدع في مجتمعه.", w*.63f, dp(getContext(),212), p);
            p.setColor(Color.rgb(245,245,238)); canvas.drawCircle(w*.18f, dp(getContext(),117), dp(getContext(),72), p); p.setColor(GREEN); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(dp(getContext(),4)); canvas.drawCircle(w*.18f, dp(getContext(),117), dp(getContext(),56), p); p.setStyle(Paint.Style.FILL); p.setTextSize(dp(getContext(),48)); p.setTextAlign(Paint.Align.CENTER); canvas.drawText("☪", w*.18f, dp(getContext(),102), p); p.setTextSize(dp(getContext(),42)); canvas.drawText("📖", w*.18f, dp(getContext(),145), p);
            p.setColor(Color.WHITE); Path wave = new Path(); wave.moveTo(0,h-dp(getContext(),25)); wave.quadTo(w/2,h+dp(getContext(),18),w,h-dp(getContext(),25)); wave.lineTo(w,h); wave.lineTo(0,h); wave.close(); canvas.drawPath(wave,p);
        }
    }

    class IconView extends View { String type; Paint p = new Paint(1); IconView(Context c,String t){super(c); type=t;}
        protected void onDraw(Canvas c){ int w=getWidth(),h=getHeight(); GradientDrawable d=new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{Color.rgb(29,177,122),GREEN}); d.setCornerRadius(dp(getContext(),16)); d.setBounds(0,0,w,h); d.draw(c); p.setColor(Color.WHITE); p.setTypeface(Typeface.DEFAULT_BOLD); p.setTextAlign(Paint.Align.CENTER); p.setTextSize(dp(getContext(),28)); String s="ℹ"; if(type.equals("card"))s="▭"; if(type.equals("pdf"))s="PDF"; if(type.equals("users"))s="☷"; if(type.equals("head"))s="☎"; c.drawText(s,w/2,h/2+dp(getContext(),10),p); }
    }
    class FooterView extends View { Paint p=new Paint(1); FooterView(Context c){super(c);} protected void onDraw(Canvas c){ int w=getWidth(); p.setTextAlign(Paint.Align.CENTER); p.setColor(GREEN); p.setTypeface(Typeface.DEFAULT_BOLD); p.setTextSize(dp(getContext(),20)); c.drawText("مرحباً بك في تطبيق", w/2, dp(getContext(),45), p); p.setTextSize(dp(getContext(),23)); c.drawText("مدرسة الشيخ حسن محمد نور بجة", w/2, dp(getContext(),82), p); }}
}
