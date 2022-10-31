package com.nexis.simsehiroyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SureliOyunActivity extends AppCompatActivity {

    private TextView txtİlBilgi, txtİl, txtToplamPuan, txtSure;
    private EditText editTxtTahmin;
    private Button btnHarfAl, btnTahminEt, btnTekrarOyna;
    private String[] İller = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Aksaray", "Amasya", "Ankara", "Antalya", "Ardahan", "Artvin",
            "Aydın", "Balıkesir", "Bartın", "Batman", "Bayburt", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Düzce", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep",
            "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Iğdır", "Isparta", "İstanbul", "İzmir", "Kahramanmaraş", "Karabük", "Karaman",
            "Kars", "Kastamonu", "Kayseri", "Kilis", "Kırıkkale", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Mardin", "Mersin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Osmaniye", "Rize", "Sakarya", "Samsun", "Şanlıurfa",
            "Siirt", "Sinop", "Sivas", "Şırnak", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Uşak", "Van", "Yalova", "Yozgat", "Zonguldak"};

    private Random rndİl, rndHarf;
    private int rndİlNumber, rndNumberHarf, baslangicHarfSayisi, toplamSure = 180100;
    private String gelenİl, ilBoyutu = "", editTxtGelenTahmin;
    private ArrayList<Character> İlHarfleri;
    private  float maximumPuan = 100.0f, azaltilacakPuan, toplamPuan = 0, bolumToplamPuan =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sureli_oyun);
        txtİlBilgi = (TextView) findViewById(R.id.txtViewİlBilgiS);
        txtİl = (TextView) findViewById(R.id.txtViewİlS);
        editTxtTahmin = (EditText) findViewById(R.id.editTxtTahminS);
        txtToplamPuan = (TextView) findViewById(R.id.txtViewToplamPuanS);
        txtSure = (TextView) findViewById(R.id.txtSureS);
        btnHarfAl = (Button)findViewById(R.id.btnHarfAlS);
        btnTahminEt = (Button)findViewById(R.id.btnTahminEtS);
        btnTekrarOyna = (Button)findViewById(R.id.btnTekrarOynaS);

        new CountDownTimer(toplamSure, 1000) {
            @Override
            public void onTick(long l) {
             txtSure.setText((l / 60000)+ ":" + ((l % 60000) / 1000 ));

            }

            @Override
            public void onFinish() {
              txtSure.setText("0:00");
              editTxtTahmin.setEnabled(false);
              btnHarfAl.setEnabled(false);
              btnTahminEt.setEnabled(false);
              btnTekrarOyna.setVisibility(View.VISIBLE);

              Toast.makeText(getApplicationContext(), "Oyun Bitti\nToplam Puanınız:" + bolumToplamPuan + "\nTekrar Oynamak İçin Butona Basın.", Toast.LENGTH_LONG).show();

            }
        }.start();

        rndHarf = new Random();
        randomDegerleriBelirle();



    }
    public void btnHarfAlS(View v){
        if (İlHarfleri.size() > 0) {
            randomHarfAl();
            toplamPuan -= azaltilacakPuan;
            Toast.makeText(getApplicationContext(), "Kalan Puan=" +toplamPuan,Toast.LENGTH_SHORT).show();


        } else
            Toast.makeText(getApplicationContext(),"Alınacak Harf Kalmadı.", Toast.LENGTH_SHORT).show();


    }
    public  void  btnTekrarOynaS(View v){
        Intent tekrarOyna = new Intent(this,SureliOyunActivity.class);
        finish();
        startActivity(tekrarOyna);

    }


    public void btnTahminEtS(View v){
        editTxtGelenTahmin = editTxtTahmin.getText().toString();

        if (!TextUtils.isEmpty(editTxtGelenTahmin)) {
            if (editTxtGelenTahmin.equals(gelenİl)) {
                bolumToplamPuan += toplamPuan;
                Toast.makeText(getApplicationContext(), "Tebrikler Tahminde Bulundunuz.",Toast.LENGTH_SHORT).show();
                txtToplamPuan.setText("Toplam Bülüm Puanı : " + bolumToplamPuan);

                editTxtTahmin.setText("");
                randomDegerleriBelirle();

            } else
                Toast.makeText(getApplicationContext(),"Yanlış Tahminde Bulundunuz.", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(),"Tahmin Değeri Boş Olamaz.", Toast.LENGTH_SHORT).show();



    }
    private void randomDegerleriBelirle() {
        ilBoyutu = "";

        rndİl = new Random();
        rndİlNumber = rndİl.nextInt(İller.length);
        gelenİl = İller[rndİlNumber];
        System.out.println(rndİlNumber + " = " + gelenİl);
        txtİlBilgi.setText(gelenİl.length() + "Harfli İlimiz");

        if (gelenİl.length() >= 5 && gelenİl.length() <= 7)
            baslangicHarfSayisi = 1;
        else if (gelenİl.length() >= 8 && gelenİl.length() < 10)
            baslangicHarfSayisi = 2;
        else if (gelenİl.length() >=10)
            baslangicHarfSayisi = 3;
        else
            baslangicHarfSayisi = 0;

        System.out.println("il Harf Sayısı=" + gelenİl.length() + "Başlangıç Harf Sayısı=" + baslangicHarfSayisi);


        for (int i = 0; i < gelenİl.length(); i++) {
            if (i < gelenİl.length() - 1)
                ilBoyutu += "_ ";
            else
                ilBoyutu += "_";
        }
        txtİl.setText(ilBoyutu);


        İlHarfleri = new ArrayList<>();

        for (char c : gelenİl.toCharArray())
            İlHarfleri.add(c);

        for (int c = 0; c < baslangicHarfSayisi; c++)
            randomHarfAl();

        azaltilacakPuan = maximumPuan / İlHarfleri.size();
        toplamPuan = maximumPuan;
    }
    private void randomHarfAl(){

        rndNumberHarf = rndHarf.nextInt(İlHarfleri.size());
        String[] txtHarfler = txtİl.getText().toString().split(" ");
        char[] gelenİlHarfleri = gelenİl.toCharArray();

        for (int i = 0; i < gelenİl.length(); i++) {
            if (txtHarfler[i].equals("_") && gelenİlHarfleri[i] == İlHarfleri.get(rndNumberHarf)) {
                txtHarfler[i] = String.valueOf(İlHarfleri.get(rndNumberHarf));
                ilBoyutu = "";

                for (int j = 0; j < gelenİl.length(); j++) {
                    if (j == i)
                        ilBoyutu += txtHarfler[j] + " ";
                    else if (j < gelenİl.length() - 1)
                        ilBoyutu += txtHarfler[j] + " ";
                    else
                        ilBoyutu += txtHarfler[j];
                }

                break;
            }
        }

        txtİl.setText(ilBoyutu);
        İlHarfleri.remove(rndNumberHarf);

    }
}