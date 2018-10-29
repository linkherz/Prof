package com.example.usuitakumi.reblood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import javax.xml.transform.sax.SAXResult;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "LOGGab_ProfileActivity";
    private Button mBtnLogout;
    private TextView mDisplayName;
    private TextView mEmail;
    private TextView mBloodGroup;
    private TextView mDonationCount;
    private TextView mLastDonationDate;
    private TextView mNextDonationDate;
    private TextView mPMIRegNo;
    private TextView mRhesus;
    private ImageButton mImgProf;
    private CircularProgressBar mPbBloodCount;
    private TextView mDayCount;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseFirestore db;
    private CollectionReference notebookRef;

    private int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 0533;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDisplayName = findViewById(R.id.tv_display_name);
        mEmail = findViewById(R.id.tv_email);
        mBloodGroup = findViewById(R.id.tv_b_blood_group);
        mDonationCount = findViewById(R.id.tv_b_donation_count);
        mLastDonationDate = findViewById(R.id.tv_b_last_donation);
        mNextDonationDate = findViewById(R.id.tv_b_next_donation);
        mPMIRegNo = findViewById(R.id.tv_b_pmi_reg_no);
        mRhesus = findViewById(R.id.tv_b_rhesus);
        mImgProf = findViewById(R.id.img_prof);
        mPbBloodCount = findViewById(R.id.pb_blood_count);
        mPbBloodCount.setProgressBarWidth(getResources().getDimension(R.dimen.default_stroke_width));
        mPbBloodCount.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.default_background_stroke_width));
        mDayCount = findViewById(R.id.tv_day_count);

        Log.w(TAG, "PA");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(ProfileActivity.this,
                            LoginActivity.class));
                }
            }
        };

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        db = FirebaseFirestore.getInstance();
        notebookRef = db.collection("users");

        loadNotes();

        mImgProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(TAG, "ImgButton");
                PopupMenu popupMenu = new PopupMenu(ProfileActivity.this, mImgProf);
                popupMenu.getMenuInflater()
                        .inflate(R.menu.menu_profile, popupMenu.getMenu());
                //popupMenu.getMenuInflater().inflate(R.menu.menu_profile, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(
                                ProfileActivity.this,
                                "You Clicked : " + menuItem.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        if(menuItem.getTitle().equals("Logout")){
                            mAuth.signOut();
                            Toast.makeText(ProfileActivity.this, "You have Sign Out", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        mBtnLogout = findViewById(R.id.btn_log_out);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(ProfileActivity.this, "You have Sign Out", Toast.LENGTH_SHORT).show();
            }
        });

//        Intent signInIntent = getIntent();
//        String x = signInIntent.getStringExtra("uid");
//        Log.w(TAG,"_"+x);

//        FitnessOptions fitnessOptions = FitnessOptions.builder()
//                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
//                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
//                .build();
//
//        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
//            GoogleSignIn.requestPermissions(
//                    this, // your activity
//                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
//                    GoogleSignIn.getLastSignedInAccount(this),
//                    fitnessOptions);
//            Log.w(TAG,"GoogleSignInhasPermissions");
//        } else {
//            accessGoogleFit();
//            Log.w(TAG,"elseGoogleSignInhasPermissions");
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.w(TAG,"onActivityResult");
//        String x = data.getStringExtra("uid");
//        Log.w(TAG,"_"+x);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
//                accessGoogleFit();
//            }
//        }
    }

//    private void accessGoogleFit() {
//        Log.w(TAG,"accessGoogleFit");
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        long endTime = cal.getTimeInMillis();
//        cal.add(Calendar.YEAR, -1);
//        long startTime = cal.getTimeInMillis();
//
//        DataReadRequest readRequest = new DataReadRequest.Builder()
//                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
//                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
//                .build();
//
//        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .readData(readRequest)
//                .addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        Log.d(TAG, "onSuccess()");
//                    }
//
//                    public void onSuccess(DataReadResponse dataReadResponse) {
//                        Log.d(TAG, "onSuccess()");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure()", e);
//                    }
//                })
//                .addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        Log.d(TAG, "onComplete()");
//                    }
//                });
//    }

    private void loadNotes() {
        Log.w(TAG, "PA_loadNotes");

        notebookRef
                .whereEqualTo("uid", "Eo9gjLvSx3apAqoi4yNrRGIKuuw1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getData().get("displayName").toString();
                                String email = document.getData().get("email").toString();
                                String photo = document.getData().get("photoURL").toString();

                                HashMap d = (HashMap) document.getData().get("bloodData");

                                String pmiRegNo = d.get("pmiRegNo").toString();
                                String bloodGroup = d.get("bloodGroup").toString();

                                Timestamp next = (Timestamp) d.get("nextDonationDate");
                                String NextDonationDate = getDate(next.getSeconds());
                                Timestamp last = (Timestamp) d.get("lastDonationDate");
                                String lastDonationDate = getDate(last.getSeconds());

                                String donationCount = d.get("donationCount").toString();
                                String rhesus = d.get("rhesus").toString();

                                mDisplayName.setText(name);
                                //mEmail.setText(email); -> gak jadi nampilin email
                                Glide.with(getApplicationContext())
                                        .load(photo)
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(mImgProf);
                                mBloodGroup.setText(bloodGroup);
                                //mDonationCount.setText(donationCount+" times donor"); -> gak jadi nampilin jumlah donation count
                                mLastDonationDate.setText(lastDonationDate);
                                mNextDonationDate.setText(NextDonationDate);
                                mDayCount.setText("30");
                                mPMIRegNo.setText(pmiRegNo);
                                mRhesus.setText(rhesus);
                                mPbBloodCount.setProgressWithAnimation(Integer.parseInt(donationCount)*10, 1500);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private String getDate(long timeStamp){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStamp*1000);
        String date = DateFormat.format("dd MMM yyyy", cal).toString();
        return date;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}