package com.joshua.stanns;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddUser extends AppCompatActivity implements View.OnClickListener {


    EditText editHallTicketNUmber,editNameStudent,editContactNumber,editBranch,editEmialId,editSscMarks,editInterMarks,editDegreeSem1,editDegreeSem2,editDegreeSem3,editDegreeSem4;
    Button buttonAddUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_user);

        editHallTicketNUmber = (EditText)findViewById(R.id.hall_ticket_number);
        editNameStudent = (EditText)findViewById(R.id.name_student);
        editContactNumber = (EditText)findViewById(R.id.contact_number);
        editBranch = (EditText) findViewById(R.id.branch);
        editEmialId = (EditText)findViewById(R.id.email_id);
        editSscMarks = (EditText) findViewById(R.id.ssc_marks);
        editInterMarks = (EditText) findViewById(R.id.inter);
        editDegreeSem1 = (EditText) findViewById(R.id.degree_sem_1);
        editDegreeSem2 = (EditText) findViewById(R.id.degree_sem_2);
        editDegreeSem3 = (EditText)findViewById(R.id.degree_sem_3);
        editDegreeSem4 = (EditText) findViewById(R.id.degree_sem_4);

        buttonAddUser = (Button)findViewById(R.id.btn_register1);
        buttonAddUser.setOnClickListener(this);


    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls

    private void   addUserToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"Adding User","Please wait");
        final String htnumber = editHallTicketNUmber.getText().toString().trim();
        final String name = editNameStudent.getText().toString().trim();
        final String number = editContactNumber.getText().toString().trim();
        final String branch= editBranch.getText().toString().trim();
        final String email = editEmialId.getText().toString().trim();
        final String ssc = editSscMarks.getText().toString().trim();
        final String inter = editInterMarks.getText().toString().trim();
        final String degreea = editDegreeSem1.getText().toString().trim();
        final String degreeb = editDegreeSem2.getText().toString().trim();
        final String degreec = editDegreeSem3.getText().toString().trim();
        final String degreed = editDegreeSem4.getText().toString().trim();






        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbxU1TasHvQXyM6PUFQl81elTJU-3GhmZU3rcGOWrQttQYRkuX4/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(AddUser.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","addUser");
                parmas.put("htnumber",htnumber);
                parmas.put("name",name);
                parmas.put("number",number);
                parmas.put("branch",branch);
                parmas.put("email",email);
                parmas.put("ssc",ssc);
                parmas.put("inter",inter);
                parmas.put("degreea",degreea);
                parmas.put("degreeb",degreeb);
                parmas.put("degreec",degreec);
                parmas.put("degreed",degreed);



                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }




    @Override
    public void onClick(View v) {

        if(v==buttonAddUser){
            addUserToSheet();

            //Define what to do when button is clicked
        }
    }
}
