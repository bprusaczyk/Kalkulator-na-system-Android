package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView wynik;
    String liczbaA, liczbaB, liczbaWynik;
    dzialanie dz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wynik=findViewById(R.id.wynik);
    }

    public void klik(View v)
    {
        try {
            if (wynik.getText() == "Błąd") {
                wynik.setText("0");
            }
            switch (v.getId()) {
                case R.id.przycisk1:
                    zmienLiczbe("1");
                    break;
                case R.id.przycisk2:
                    zmienLiczbe("2");
                    break;
                case R.id.przycisk3:
                    zmienLiczbe("3");
                    break;
                case R.id.przycisk4:
                    zmienLiczbe("4");
                    break;
                case R.id.przycisk5:
                    zmienLiczbe("5");
                    break;
                case R.id.przycisk6:
                    zmienLiczbe("6");
                    break;
                case R.id.przycisk7:
                    zmienLiczbe("7");
                    break;
                case R.id.przycisk8:
                    zmienLiczbe("8");
                    break;
                case R.id.przycisk9:
                    zmienLiczbe("9");
                    break;
                case R.id.przycisk0:
                    zmienLiczbe("0");
                    break;
                case R.id.przyciskPlus:
                    wybierzDzialanie(dzialanie.DODAWANIE);
                    break;
                case R.id.przyciskMinus:
                    wybierzDzialanie(dzialanie.ODEJMOWANIE);
                    break;
                case R.id.przyciskRazy:
                    wybierzDzialanie(dzialanie.MNOZENIE);
                    break;
                case R.id.przyciskPodzielic:
                    wybierzDzialanie(dzialanie.DZIELENIE);
                    break;
                case R.id.przyciskKropka:
                    if (dz == null && liczbaA != null && !liczbaA.contains(".")) {
                        wynik.setText(wynik.getText() + ".");
                        liczbaA += ".";
                    }
                    if (dz != null && liczbaB != null && !liczbaB.contains(".")) {
                        wynik.setText(wynik.getText() + ".");
                        liczbaB += ".";
                    }
                    break;
                case R.id.przyciskZmianaZnaku:
                    if (dz == null && (liczbaA != null || liczbaWynik != null) && liczbaB == null) {
                        if (liczbaWynik != null) {
                            liczbaA = liczbaWynik;
                            liczbaWynik = null;
                        }
                        if (liczbaA.contains("-")) {
                            liczbaA = liczbaA.replace("-", "");
                            wynik.setText(liczbaA);
                        } else {
                            wynik.setText("-" + wynik.getText());
                            liczbaA = "-" + liczbaA;
                        }
                    }
                    if (dz!=null)
                    {
                        if (liczbaB.contains("-"))
                        {
                            liczbaB=liczbaB.replace("-","");
                            wynik.setText(liczbaA+" "+dz+" "+liczbaB);
                        }
                        else
                        {
                            liczbaB="-"+liczbaB;
                            wynik.setText(liczbaA+" "+dz+" "+liczbaB);
                        }
                    }
                    break;
                case R.id.przyciskRownaSie:
                    if (dz == null) return;
                    if (liczbaA == null) liczbaA = "0";
                    if (liczbaB == null) return;
                    double wynikDzialania = 0;
                    switch (dz) {
                        case DODAWANIE:
                            wynikDzialania = Double.parseDouble(liczbaA) + Double.parseDouble(liczbaB);
                            wynik.setText(Double.toString(wynikDzialania));
                            break;
                        case ODEJMOWANIE:
                            wynikDzialania = Double.parseDouble(liczbaA) - Double.parseDouble(liczbaB);
                            wynik.setText(Double.toString(wynikDzialania));
                            break;
                        case MNOZENIE:
                            wynikDzialania = Double.parseDouble(liczbaA) * Double.parseDouble(liczbaB);
                            wynik.setText(Double.toString(wynikDzialania));
                            break;
                        case DZIELENIE:
                            if (Double.parseDouble(liczbaB) == 0) {
                                wynik.setText("Błąd");
                                wynikDzialania = 0;
                                break;
                            }
                            wynikDzialania = Double.parseDouble(liczbaA) / Double.parseDouble(liczbaB);
                            wynik.setText(Double.toString(wynikDzialania));
                            break;
                    }
                    dz = null;
                    liczbaWynik = Double.toString(wynikDzialania);
                    liczbaA = null;
                    liczbaB = null;
                    break;
            }
        }
        catch(Exception e){}
    }

    public void zmienLiczbe(String liczba)
    {
        if (dz==null) {
            liczbaWynik=null;
            if (liczbaA == null) {
                wynik.setText(liczba);
                liczbaA = liczba;
            } else {
                wynik.setText((wynik.getText() + liczba));
                liczbaA += liczba;
            }
        }
        else{
            if (liczbaB == null) {
                wynik.setText(wynik.getText()+liczba);
                liczbaB = liczba;
            } else {
                wynik.setText(wynik.getText() + liczba);
                liczbaB += liczba;
            }
        }
    }

    public void wybierzDzialanie(dzialanie dz){
        if (this.dz==null) {
            if (liczbaWynik!=null) {
                liczbaA =liczbaWynik;
                liczbaWynik=null;
            }
            this.dz = dz;
            wynik.setText(wynik.getText() + " " + dz + " ");
        }
    }

    public enum dzialanie
    {
        DODAWANIE, ODEJMOWANIE, MNOZENIE, DZIELENIE;

        @Override
        public String toString()
        {
            switch (this)
            {
                case DODAWANIE:
                    return "+";
                case ODEJMOWANIE:
                    return "-";
                case MNOZENIE:
                    return "*";
                case DZIELENIE:
                    return "/";
            }
            return null;
        }

    }
}
