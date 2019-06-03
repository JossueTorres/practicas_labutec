package com.example.practicaslibres;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class metodos {

    public static void fecha(final Context context, final EditText edt){
        int dia,mes, anio;
        String fecha="";
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
                String fechaSelec=dayOfMonth+"/"+(month+1)+"/"+year;
                try {
                    Date date = formatter.parse(fechaSelec);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    edt.setText(formatter.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
                ,anio,mes,dia);
        datePickerDialog.show();
    }

    public static  void hora(final Context context,final EditText edt){
        final int hora;
        int minutos;
        final Calendar c = Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String horaSelec=hourOfDay+":"+minute;
                try {
                    Date date = formatter.parse(horaSelec);
                    System.out.println(date);
                    System.out.println(formatter.format(date));
                    edt.setText(formatter.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }

    public static  boolean  validarFechaDesde(EditText edtFecha) throws ParseException {
        int dia=0,mes=0, anio=0;
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        final Calendar calendario= Calendar.getInstance();
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        mes=calendario.get(Calendar.MONTH);
        anio=calendario.get(Calendar.YEAR);
        String hoy = dia+"/"+(mes+1)+"/"+anio;
        Date   hoy2   = format.parse(hoy);
        String desde  = edtFecha.getText().toString();
        Date   desde2 = format.parse(desde);

        if(desde2.before(hoy2))
        {
            return false;
        }else {
            return true;
        }
    }

    public static  boolean  validarFechaHasta(EditText edtDesde,EditText edtHasta) throws ParseException {

        String desde=edtDesde.getText().toString();
        String hasta=edtHasta.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date desde2 = format.parse(desde);
        Date hasta2 = format.parse(hasta);

        if(hasta2.before(desde2))
        {
            return false;
        }
        return true;
    }
}
