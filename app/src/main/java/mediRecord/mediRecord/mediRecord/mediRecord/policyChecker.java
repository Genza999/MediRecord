package mediRecord.mediRecord.mediRecord.mediRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import mediRecord.mediRecord.mediRecord.mediRecord.data.PatientDetails;
import mediRecord.mediRecord.mediRecord.mediRecord.db.FirebaseDataOperations;
import mediRecord.mediRecord.mediRecord.mediRecord.db.GetDataListener;

public class policyChecker extends AppCompatActivity {

    private Button policyChecker;
    private Button newPatient;
    public String patientNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_checker);


        policyChecker = findViewById(R.id.policy_maker);
        newPatient =  findViewById(R.id.new_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        policyChecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDataOperations firebaseDataOperations = new FirebaseDataOperations();
                patientNo = ((EditText) findViewById(R.id.patientNo)).getText().toString();
                firebaseDataOperations.getPatientDetails(patientNo, new GetDataListener() {
                    @Override
                    public void onStart() {
                        Log.d("MediRecord", "Firebase Data Fetch Started");

                    }

                    @Override
                    public void onSuccess(PatientDetails patientDetails) {
                        Intent intent = new Intent(getApplicationContext(), ViewRecordDetailsActivity.class);
                        intent.putExtra("policyNbr", patientDetails.getPolicyNbr());
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(policyChecker.this,"Patient Number doest Exist", Toast.LENGTH_LONG).show();


                    }
                });

            }
        });


        newPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewRecordActivity.class);
                intent.putExtra("policyNbr", random());
                startActivity(intent);
            }
        });

    }

    public String random(){
        Random r = new Random();
        String barcodeValue;
        int i = r.nextInt(100000 - 10000) + 10000;
        barcodeValue = Integer.toString(i);
        return  barcodeValue;
    }



}
