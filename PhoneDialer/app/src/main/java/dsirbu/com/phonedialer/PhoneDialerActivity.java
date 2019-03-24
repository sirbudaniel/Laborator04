package dsirbu.com.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneDialerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        final EditText phone = (EditText) findViewById(R.id.edit_number);


        final Button[] buttons = new Button[12];
        buttons[0] = (Button) findViewById(R.id.zero);
        buttons[1] = (Button) findViewById(R.id.one);
        buttons[2] = (Button) findViewById(R.id.two);
        buttons[3] = (Button) findViewById(R.id.three);
        buttons[4] = (Button) findViewById(R.id.four);
        buttons[5] = (Button) findViewById(R.id.five);
        buttons[6] = (Button) findViewById(R.id.six);
        buttons[7] = (Button) findViewById(R.id.seven);
        buttons[8] = (Button) findViewById(R.id.eight);
        buttons[9] = (Button) findViewById(R.id.nine);
        buttons[10] = (Button) findViewById(R.id.star);
        buttons[11] = (Button) findViewById(R.id.hashtag);

        ImageButton call = (ImageButton) findViewById(R.id.call);
        ImageButton hang = (ImageButton) findViewById(R.id.hang);
        ImageButton delete = (ImageButton) findViewById(R.id.delete);
        ImageButton save = findViewById(R.id.save);

        for (int i = 0; i < 12; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    phone.setText(phone.getText().toString() + ((Button)view).getText().toString());
                }

            });
        }

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            0);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phone.getText().toString()));
                    startActivity(intent);
                }
            }
        });

        hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone.setText(phone.getText().toString().substring(0,phone.getText().toString().length()-1));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phone.getText().toString();
                if (phoneNumber.length() > 0) {
                    Intent intent = new Intent("dsirbu.com.contactsmanager.intent.action.ContactsManagerActivity");
                    intent.putExtra("dsirbu.com.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
