package dsirbu.com.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    Button save, show_details, cancel;
    LinearLayout details;
    EditText name, phone, email, address, job, company, website, im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
         show_details = findViewById(R.id.show_details);
        details = findViewById(R.id.details);
        save = findViewById(R.id.save_button);
        cancel = findViewById(R.id.cancel_button);

        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        email = findViewById(R.id.editTextEmail);
        address = findViewById(R.id.editTextAddress);
        job = findViewById(R.id.editTextJob);
        company = findViewById(R.id.editTextCompany);
        website = findViewById(R.id.editTextWebsite);
        im = findViewById(R.id.editTextIm);

        Intent intent = getIntent();
        if (intent != null) {

            String phone_no = intent.getStringExtra("dsirbu.com.contactsmanager.PHONE_NUMBER_KEY");
            phone.setText(phone_no);
        }

        show_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show_details.getText().toString() == "SHOW ADDITIONAL FIELDS") {
                    show_details.setText("HIDE ADDITIONAL FIELDS");
                    details.setVisibility(View.VISIBLE);
                }
                else {
                    show_details.setText("SHOW ADDITIONAL FIELDS");
                    details.setVisibility(View.GONE);
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }
                if (phone.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
                }
                if (email.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (address.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
                }
                if (job.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, job.getText().toString());
                }
                if (company.getText() != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website.getText() != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website.getText().toString());
                    contactData.add(websiteRow);
                }
                if (im.getText() != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im.getText().toString());
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
