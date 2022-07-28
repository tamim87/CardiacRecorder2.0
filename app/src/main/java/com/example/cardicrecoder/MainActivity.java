package com.example.cardicrecoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    RecordAdapter recordAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recorditem> recorditems = new ArrayList<>();
    Toolbar toolbar;

    EditText heart_beat;
    EditText systolic_pressure;
    EditText diastolic_pressure;
    EditText comment;


    TextView heart_beat_d;
    TextView systolic_pressure_d;
    TextView diastolic_pressure_d;
    TextView comment_d;

    private String heartbeat;
    private String systolicpressure;
    private String diastolicpressure;
    private String date;
    private String time;
    private String comnt;
    private String status;

    Calendar calendar;
    String Date;
    String Time;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("records");


        fab = findViewById(R.id.fab_main);
        fab.setOnClickListener(v -> showDialog());

        recyclerView=findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        //why linear problem maybe arise
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recordAdapter = new RecordAdapter(this,recorditems);
        //recyclerView.setAdapter(recordAdapter);
        recordAdapter.setOnItemClickListener(position -> {gotoItemActivity(position);});
        setToolbar();


        calendar=Calendar.getInstance();
        //Date=new java.text.SimpleDateFormat("dd-MMM-yyyy").format(calendar.getTime());
        //Time=new java.text.SimpleDateFormat("hh:mm a").format(calendar.getTime());




    }


    @Override
    protected void onStart() {
        super.onStart();
        //fetch data from data base
        //DatabaseReference reference=FirebaseDatabase.getInstance().getReference("records");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recorditems.clear();
                for(DataSnapshot snap:snapshot.getChildren())
                {
                    Recorditem recorditem=snap.getValue(Recorditem.class);
                    recorditems.add(recorditem);
                }
               // recordAdapter = new RecordAdapter(MainActivity.this,recorditems);
                recyclerView.setAdapter(recordAdapter);
               // recordAdapter.setOnItemClickListener(position -> {gotoItemActivity(position);});

                // recordAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setToolbar() {
        toolbar=findViewById(R.id.toolbar);
        TextView title=toolbar.findViewById(R.id.toolbartitle);
        ImageButton back=toolbar.findViewById(R.id.back);
        title.setText("Cardiac Recorder");
        back.setVisibility(View.GONE);
    }

    private void gotoItemActivity(int position) {
        Intent intent =new Intent(this,RecordActivity.class);
        intent.putExtra("heart_beat",recorditems.get(position).getHeart_rate());
        intent.putExtra("systolic_pressure",recorditems.get(position).getSystolic_pressure());
        intent.putExtra("diastolic_pressure",recorditems.get(position).getDiastolic_pressure());
        intent.putExtra("comment",recorditems.get(position).getComment());
        intent.putExtra("date",recorditems.get(position).getDate());
        intent.putExtra("time",recorditems.get(position).getTime());
        intent.putExtra("status",recorditems.get(position).getStatus());
        intent.putExtra("position",position);
        //startActivityForResult(intent,1);
        startActivity(intent);

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                int position=data.getIntExtra("position",-1);
                recorditems.remove(position);
                recordAdapter.notifyDataSetChanged();
            }
        }
    }
*/
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.datainput_dialolog, null);

        builder.setView(view);
       AlertDialog dialog= builder.create();
       dialog.show();

        heart_beat=view.findViewById(R.id.hbm_i);
        systolic_pressure=view.findViewById(R.id.systolic_pressure_i);
        diastolic_pressure=view.findViewById(R.id.diastolic_pressure_i);
        comment=view.findViewById(R.id.comment_i);


        Button  cancel=view.findViewById(R.id.cancel);
        Button add=view.findViewById(R.id.add);

        cancel.setOnClickListener(v->dialog.dismiss());
        add.setOnClickListener(v->{
           boolean isdismiss= addrecord();
           if(isdismiss)
            dialog.dismiss();
        });


    }

    private boolean addrecord() {
         heartbeat = heart_beat.getText().toString();
         systolicpressure = systolic_pressure.getText().toString();
         diastolicpressure = diastolic_pressure.getText().toString();
         comnt = comment.getText().toString();
         date=new java.text.SimpleDateFormat("dd-MMM-yyyy").format(calendar.getTime());
         time=new java.text.SimpleDateFormat("hh:mm a").format(calendar.getTime());
        if (heartbeat.isEmpty()) {
            heart_beat.setError("Enter Heart Beat");
            heart_beat.requestFocus();
            return false;
        }
        if (systolicpressure.isEmpty()) {
            systolic_pressure.setError("Enter systolic pressure");
            systolic_pressure.requestFocus();
            return false;
        }
        if (diastolicpressure.isEmpty())
        {
            diastolic_pressure.setError("Enter diastolic pressure");
        diastolic_pressure.requestFocus();
        return false;
        }
        int hbm=Integer.parseInt(heartbeat);
        if(hbm<0 || hbm>200)
        {
                heart_beat.setError("Heat beat range 1 to 200");
                heart_beat.requestFocus();
                return false;
        }
        int sp=Integer.parseInt(systolicpressure);
        if(sp<70 || sp>250)
        {
            systolic_pressure.setError("Systolic pressure range 70 to 250");
            systolic_pressure.requestFocus();
            return false;
        }
        int dp=Integer.parseInt(diastolicpressure);
        if(dp<40 || dp>150)
        {
            diastolic_pressure.setError("diastolic pressure range 40 to 150");
            diastolic_pressure.requestFocus();
            return false;
        }



        String status;
        if( (sp>=80 && sp<=120) && (dp>=60 && dp<=80))
        {
            status="Normal pressure";
        }
        else if(sp>120 || dp>80)
        {
            status="High pressure";
        }
        else
            status="low pressure";
        String id= databaseReference.push().getKey();
        Recorditem record=new Recorditem( id,heartbeat,systolicpressure,diastolicpressure,status,date,time,comnt);
        recorditems.add(record);
        recordAdapter.notifyDataSetChanged();
       databaseReference.child(id).setValue(record);
        return true;
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                updateshowdialog(item.getGroupId());
                break;
            case 1:
                deleterecord(item.getGroupId());
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void updateshowdialog(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.datainput_dialolog, null);

        builder.setView(view);
        AlertDialog dialog= builder.create();
        dialog.show();

        heart_beat_d=view.findViewById(R.id.hbm_i);
        systolic_pressure_d=view.findViewById(R.id.systolic_pressure_i);
        diastolic_pressure_d=view.findViewById(R.id.diastolic_pressure_i);
        comment_d=view.findViewById(R.id.comment_i);

        heart_beat_d.setText(recorditems.get(position).getHeart_rate());
        systolic_pressure_d.setText(recorditems.get(position).getSystolic_pressure());
        diastolic_pressure_d.setText(recorditems.get(position).getDiastolic_pressure());
        comment_d.setText(recorditems.get(position).getComment());

        TextView title=view.findViewById(R.id.dialog_title);
        title.setText("Update Record");

        Button  cancel_d=view.findViewById(R.id.cancel);
        Button update_d=view.findViewById(R.id.add);
        update_d.setText("UPDATE");

        cancel_d.setOnClickListener(v->dialog.dismiss());
        update_d.setOnClickListener(v->{
            boolean isdismiss= updaterecord(position);
            if(isdismiss)
                dialog.dismiss();
        });
    }

    private boolean updaterecord(int position) {
        String heartbeat_d=heart_beat_d.getText().toString();
        String systolicpressure_d=systolic_pressure_d.getText().toString();
        String diastolicpressure_d=diastolic_pressure_d.getText().toString();
        String comnt_d=comment_d.getText().toString();
        String date_d=recorditems.get(position).getDate();
        String time_d=recorditems.get(position).getTime();
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



        recorditems.get(position).setStatus(status_d);
        recorditems.get(position).setHeart_rate(heartbeat_d);
        recorditems.get(position).setSystolic_pressure(systolicpressure_d);
        recorditems.get(position).setDiastolic_pressure(diastolicpressure_d);
        recorditems.get(position).setComment(comnt_d);
        recordAdapter.notifyItemChanged(position);

        String id=recorditems.get(position).getId();
        Recorditem record=new Recorditem( id,heartbeat_d,systolicpressure_d,diastolicpressure_d,status_d,date_d,time_d,comnt_d);
        databaseReference.child(id).setValue(record);
        return true;
    }


    private void deleterecord(int position) {
        {
            DatabaseReference databaseReference1=databaseReference.child(recorditems.get(position).getId());
            databaseReference1.removeValue();
            recorditems.remove(position);
            recordAdapter.notifyItemRemoved(position);
        }

    }
}