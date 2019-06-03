package com.example.practicaslibres;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class metodos {

    public static void fecha(final Context context, final EditText edt){
        int dia,mes, anio;
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }
                ,anio,mes,dia);
        datePickerDialog.show();
    }

    public static  void hora(final Context context,final EditText edt){
        int hora;
        int minutos;
        final Calendar c = Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edt.setText(hourOfDay+":"+minute);
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }
}
