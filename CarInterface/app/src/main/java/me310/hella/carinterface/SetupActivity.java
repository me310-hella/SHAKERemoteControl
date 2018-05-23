package me310.hella.carinterface;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A Setup Activity to set parameters like MAC-Address
 */
public class SetupActivity extends AppCompatActivity {

    public static final String MAC_ADDRESS = "mac_address";//"com.example.myfirstapp.MESSAGE";
    private static SharedPreferences sharedPreferences;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private SetupTask mSetupTask = null;

    // UI references.
    private TextView mMacAddressView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        // Set up the login form.
        mMacAddressView = (TextView) findViewById(R.id.mac_address);

        Button mStartButton = (Button) findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptStart();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mMacAddressView.setText(sharedPreferences.getString("macAddress", ""));
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptStart() {
        if (mSetupTask != null) {
            return;
        }

        // Reset errors.
        mMacAddressView.setError(null);

        // Store values at the time of the login attempt.

        String macAddress = mMacAddressView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid macAddress address.
        if (TextUtils.isEmpty(macAddress)) {
            mMacAddressView.setError(getString(R.string.error_field_required));
            focusView = mMacAddressView;
            cancel = true;
        } else if (!isMacAddressValid(macAddress)) {
            mMacAddressView.setError("invalid mac address");
            focusView = mMacAddressView;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            sharedPreferences.edit().putString(MAC_ADDRESS, macAddress).apply();
            mSetupTask = new SetupTask(macAddress);
            mSetupTask.execute((Void) null);
        }
    }

    private boolean isMacAddressValid(String macAddress) {
        //TODO: Replace this with your own logic
        return macAddress.contains(":");
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class SetupTask extends AsyncTask<Void, Void, Boolean> {

        private final String mMacAddress;

        SetupTask(String macAddress) {
            mMacAddress = macAddress;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                /*if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }*/
            }

            // TODO: register the new account here.



            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mSetupTask = null;
            showProgress(false);

            if (success) {
                finish();
                Intent intent = new Intent(SetupActivity.this, FullscreenActivity.class);
                EditText editText = (EditText) findViewById(R.id.mac_address);
                String macAddress = editText.getText().toString();
                intent.putExtra(MAC_ADDRESS, macAddress);
                startActivity(intent);

            } else {
                mMacAddressView.setError("Something is wrong");
                mMacAddressView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mSetupTask = null;
            showProgress(false);
        }
    }
}

