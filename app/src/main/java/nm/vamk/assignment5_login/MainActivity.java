package nm.vamk.assignment5_login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView userNameTextView;
    TextView passwordTextView;
    TextView summaryTitleTextView;
    TextView summaryDataTextView;
    EditText userNameEditText;
    EditText passwordEditText;

    Button continueButton;
    Button backButton;
    LayoutParams viewLayoutParams;
    LayoutParams editTextViewLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 400;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 10;
        viewLayoutParams.bottomMargin = 10;

        // Here we define parameters for views
        editTextViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        editTextViewLayoutParams.leftMargin = 280;
        editTextViewLayoutParams.rightMargin = 40;
        editTextViewLayoutParams.topMargin = 10;
        editTextViewLayoutParams.bottomMargin = 10;

        // Here we create the layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Here we define a text view for username
        userNameTextView = new TextView(this);
        userNameTextView.setText(R.string.user_name_txt);
        userNameTextView.setLayoutParams(viewLayoutParams);
        userNameTextView.setTextSize(17);
        linearLayout.addView(userNameTextView);


        // Here we define a text view for password
        passwordTextView = new TextView(this);
        passwordTextView.setText(R.string.password_txt);
        passwordTextView.setLayoutParams(viewLayoutParams);
        passwordTextView.setTextSize(17);
        passwordTextView.setVisibility(View.GONE);
        linearLayout.addView(passwordTextView);

        // Here we define a text view for summary title
        summaryTitleTextView = new TextView(this);
        summaryTitleTextView.setText(R.string.summary_txt);
        summaryTitleTextView.setLayoutParams(viewLayoutParams);
        summaryTitleTextView.setTextSize(17);
        summaryTitleTextView.setVisibility(View.GONE);
        linearLayout.addView(summaryTitleTextView);

        // Here we define a text view for summary data
        summaryDataTextView = new TextView(this);
        summaryDataTextView.setLayoutParams(viewLayoutParams);
        summaryDataTextView.setTextSize(17);
        summaryDataTextView.setVisibility(View.GONE);
        linearLayout.addView(summaryDataTextView);

        // Here we define the edit text for username
        userNameEditText = new EditText(this);
        userNameEditText.setLayoutParams(editTextViewLayoutParams);
        userNameEditText.setHint(R.string.user_name_hint);
        userNameEditText.setOnTouchListener(onTouchListener);
        linearLayout.addView(userNameEditText);

        // Here we define the edit text for password
        passwordEditText = new EditText(this);
        passwordEditText.setLayoutParams(editTextViewLayoutParams);
        passwordEditText.setHint(R.string.password_txt_hint);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        // Here we hide the content of the password field
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordEditText.setVisibility(View.GONE);
        passwordEditText.setOnTouchListener(onTouchListener);
        linearLayout.addView(passwordEditText);

        //Continue Button
        continueButton = new Button(this);
        continueButton.setId(R.id.continue_button);
        continueButton.setText(R.string.continueButton_txt);
        continueButton.setLayoutParams(viewLayoutParams);
        continueButton.setBackgroundColor(getColor(R.color.android_green));
        continueButton.setOnClickListener(buttonClickListener);
        linearLayout.addView(continueButton);

        //Back Button
        backButton = new Button(this);
        backButton.setId(R.id.back_button);
        backButton.setText(R.string.backButton_txt);
        backButton.setLayoutParams(viewLayoutParams);
        backButton.setBackgroundColor(getColor(R.color.android_green));
        backButton.setOnClickListener(buttonClickListener);
        backButton.setVisibility(View.GONE);
        linearLayout.addView(backButton);

        // Here we can define LinearLayout's width and height
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        this.addContentView(linearLayout, linearLayoutParams);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            userNameEditText.setBackgroundColor(0);
            passwordEditText.setBackgroundColor(0);
            return false;
        }
    };

    private OnClickListener buttonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;

            //Continue button clicked
            if(clickedButton.equals(continueButton)) {
                if(userNameEditText.getVisibility() == View.VISIBLE) {
                    if(userNameEditText.getText().length() != 0) {
                        userNameEditText.setBackgroundColor(0);
                        userNameTextView.setVisibility(View.GONE);
                        userNameEditText.setVisibility(View.GONE);
                        passwordTextView.setVisibility(View.VISIBLE);
                        passwordEditText.setVisibility(View.VISIBLE);
                        backButton.setVisibility(View.VISIBLE);
                    } else {
                        userNameEditText.setBackgroundColor(Color.rgb(255,0,0));
                    }
                } else {
                    if(passwordEditText.getText().length() != 0) {
                        passwordEditText.setBackgroundColor(0);
                        passwordTextView.setVisibility(View.GONE);
                        passwordEditText.setVisibility(View.GONE);
                        continueButton.setVisibility(View.GONE);
                        summaryTitleTextView.setVisibility(View.VISIBLE);
                        summaryDataTextView.setVisibility(View.VISIBLE);
                        summaryDataTextView.setText(userNameEditText.getText().toString() + "\n"
                                + passwordEditText.getText().toString() + "\n" + getCurrentDateTime());
                    } else {
                        passwordEditText.setBackgroundColor(Color.rgb(255,0,0));
                    }
                }

            }

            //Back button clicked
            if(clickedButton.equals(backButton)) {
                if(passwordTextView.getVisibility() == View.VISIBLE) {
                    passwordTextView.setVisibility(View.GONE);
                    passwordEditText.setVisibility(View.GONE);
                } else {
                    summaryDataTextView.setText("");
                    summaryTitleTextView.setVisibility(View.GONE);
                    summaryDataTextView.setVisibility(View.GONE);
                    continueButton.setVisibility(View.VISIBLE);
                }
                userNameEditText.setText("");
                passwordEditText.setText("");
                backButton.setVisibility(View.GONE);
                userNameTextView.setVisibility(View.VISIBLE);
                userNameEditText.setVisibility(View.VISIBLE);
            }
        }
    };

    public String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        Date dateTime = new Date();
        return dateFormat.format(dateTime);
    }
}