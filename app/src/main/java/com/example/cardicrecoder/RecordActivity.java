package com.example.cardicrecoder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {
    Toolbar toolbar;
    private String heartbeat;
    private String systolicpressure;
    private String diastolicpressure;
    private String date;
    private String time;
    private String comment;
    private String status;
    private int position;


    TextView heart_beat;
    TextView systolic_pressure;
    TextView diastolic_pressure;
    TextView Date;
    TextView Time;
    TextView Comment;
    TextView Status;
    Button update;
    Button delete;

    TextView heart_beat_d;
    TextView systolic_pressure_d;
    TextView diastolic_pressure_d;
    TextView comment_d;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setToolbar();

        Intent intent=getIntent();
        heartbeat= intent.getStringExtra("heart_beat");
        systolicpressure= intent.getStringExtra("systolic_pressure");
        diastolicpressure= intent.getStringExtra("diastolic_pressure");
        date= intent.getStringExtra("date");
        time= intent.getStringExtra("time");
        comment= intent.getStringExtra("comment");
        status= intent.getStringExtra("status");
        position=intent.getIntExtra("position",-1);

        int hbm=Integer.parseInt(heartbeat);
        int sp=Integer.parseInt(systolicpressure);
        int dp=Integer.parseInt(diastolicpressure);

        heart_beat=findViewById(R.id.hbm_r);
        systolic_pressure=findViewById(R.id.systolic_pressure_r);
        diastolic_pressure=findViewById(R.id.diastolic_pressure_r);
        Date=findViewById(R.id.date_r);
        Time=findViewById(R.id.time_r);
        Comment=findViewById(R.id.comment_r);
        Status=findViewById(R.id.status_r);


        Status.setText(status);
        if(hbm>100)
        heart_beat.setText(heartbeat+" (High)");
        else if (hbm<60)
            heart_beat.setText(heartbeat+" (Low)");
        else
        heart_beat.setText(heartbeat);

        if(sp>120)
        systolic_pressure.setText(systolicpressure+" mmHg (High)");
        else if(sp<90)
            systolic_pressure.setText(systolicpressure+" mmHg (Low)");
        else
            systolic_pressure.setText(systolicpressure+" mmHg");
        if(dp>80)
        diastolic_pressure.setText(diastolicpressure+" mmHg (High)");
        else if(dp<60)
            diastolic_pressure.setText(diastolicpressure+" mmHg (Low)");
            else
            diastolic_pressure.setText(diastolicpressure+" mmHg");

        Date.setText(date);
        Time.setText(time);
        Comment.setText(comment);


       // update=findViewById(R.id.update);
      //  update.setOnClickListener(v -> showDialog());

       // delete.setOnClickListener(v->delete());




    }


    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.toolbartitle);
        ImageButton back=toolbar.findViewById(R.id.back);

        title.setText("Measurement Details");
        back.setOnClickListener(v->onBackPressed());
    }
    /*
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.datainput_dialolog, null);

        builder.setView(view);
        AlertDialog dialog= builder.create();
        dialog.show();

        heart_beat_d=view.findViewById(R.id.hbm_i);
        systolic_pressure_d=view.findViewById(R.id.systolic_pressure_i);
        diastolic_pressure_d=view.findViewById(R.id.diastolic_pressure_i);
        comment_d=view.findViewById(R.id.comment_i);

        heart_beat_d.setText(heartbeat);
        systolic_pressure_d.setText(systolicpressure);
        diastolic_pressure_d.setText(diastolicpressure);
        comment_d.setText(comment);


        Button  cancel_d=view.findViewById(R.id.cancel);
        Button update_d=view.findViewById(R.id.add);
        update_d.setText("UPDATE");

        cancel_d.setOnClickListener(v->dialog.dismiss());
        update_d.setOnClickListener(v->{
            boolean isdismiss= updaterecord();
            if(isdismiss)
            dialog.dismiss();
        });


    }
    private boolean updaterecord() {
        String heartbeat_d=heart_beat_d.getText().toString();
        String systolicpressure_d=systolic_pressure_d.getText().toString();
        String diastolicpressure_d=diastolic_pressure_d.getText().toString();
        String comnt_d=comment_d.getText().toString();
        if (heartbeat_d.isEmpty()) {
            heart_beat_d.setError("Enter Heart Beat");
            heart_beat_d.requestFocus();
            return false;
        }
        if (systolicpressure_d.isEmpty()) {
            systolic_pressure_d.setError("Enter systolic pressure");
            systolic_pressure_d.requestFocus();
            return false;
        }
        if (diastolicpressure_d.isEmpty())
        {
            diastolic_pressure_d.setError("Enter diastolic pressure");
            diastolic_pressure_d.requestFocus();
            return false;
        }
        int hbm=Integer.parseInt(heartbeat_d);
        if(hbm<0 || hbm>200)
        {
            heart_beat_d.setError("Heat beat range 1 to 200");
            heart_beat_d.requestFocus();
            return false;
        }
        int sp=Integer.parseInt(systolicpressure_d);
        if(sp<70 || sp>250)
        {
            systolic_pressure_d.setError("Systolic pressure range 70 to 250");
            systolic_pressure_d.requestFocus();
            return false;
        }
        int dp=Integer.parseInt(diastolicpressure_d);
        if(dp<40 || dp>150)
        {
            diastolic_pressure_d.setError("diastolic pressure range 40 to 150");
            diastolic_pressure_d.requestFocus();
            return false;
        }



        String status_d;
        if( (sp>=80 && sp<=120) && (dp>=60 && dp<=80))
        {
            status_d="Normal pressure";
        }
        else if(sp>120 || dp>80)
        {
            status_d="High pressure";
        }
        else
            status_d="low pressure";


        Status.setText(status_d);
        if(hbm>100)
            heart_beat.setText(heartbeat_d+" (High)");
        else if (hbm<60)
            heart_beat.setText(heartbeat_d+" (Low)");
        else
            heart_beat.setText(heartbeat_d);

        if(sp>120)
            systolic_pressure.setText(systolicpressure_d+" mmHg (High)");
        else if(sp<90)
            systolic_pressure.setText(systolicpressure_d+" mmHg (Low)");
        else
            systolic_pressure.setText(systolicpressure_d+" mmHg");
        if(dp>80)
            diastolic_pressure.setText(diastolicpressure_d+" mmHg (High)");
        else if(dp<60)
            diastolic_pressure.setText(diastolicpressure_d+" mmHg (Low)");
        else
            diastolic_pressure.setText(diastolicpressure_d+" mmHg");

       // recorditems.add(new Recorditem( heartbeat,systolicpressure,diastolicpressure,status,date,time,comnt));
        //recordAdapter.notifyDataSetChanged();

        return true;
    }
    /*
    private void delete() {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("position",position);
        setResult(RESULT_OK,intent);
        finish();
    }
*/
}