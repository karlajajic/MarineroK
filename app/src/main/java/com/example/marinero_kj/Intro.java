package com.example.marinero_kj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Intro extends AppCompatActivity {

    private static final int RC_SIGN_IN = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        checkIfSignedIn();

        //so that navigation bar does not show
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

//        //to start the main activity
//        new Timer().schedule(new TimerTask(){
//            public void run() {
//                Intro.this.runOnUiThread(new Runnable() {
//                    public void run() {
//                        startActivity(new Intent(Intro.this, SignIn.class));
//                    }
//                });
//            }
//        }, 3500);
    }

    private void checkIfSignedIn() { //if signed in continue to mai, else start intention
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)
            goToMainActivity();
        else signIn();
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, Main.class));
        finish();
    }

    public void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_logo) //set logo
                .setTheme(R.style.AppTheme)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInRequest(resultCode);
        }
    }

    private void handleSignInRequest(int resultCode) {
        if (resultCode == RESULT_OK)
            goToMainActivity();
        else
            Toast.makeText(this, R.string.intro_sign_eror, Toast.LENGTH_SHORT).show();
    }
}
