package com.example.raj.mytest.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.raj.mytest.Adapter.ContactAdapter;
import com.example.raj.mytest.Model.ContactModel;
import com.example.raj.mytest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raj on 8/23/2016.
 */
public class ContactFragment extends Fragment {
    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private SimpleCursorAdapter mCursorAdapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private ContactAdapter contactAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_contact);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        showContacts();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(activity, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Show the contacts in the ListView.
     */
    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            ArrayList<ContactModel> contacts = getContactNames();

            Set<ContactModel> hs = new HashSet<>();
            hs.addAll(contacts);
            contacts.clear();
            contacts.addAll(hs);
            Collections.sort(contacts, new Comparator<ContactModel>() {
                @Override
                public int compare(ContactModel t1, ContactModel t2) {
                    return t1.getName().compareTo(t2.getName());
                }
            });

            contactAdapter = new ContactAdapter(contacts, activity);
            recyclerView.setAdapter(contactAdapter);
        }
    }

    /**
     * Read the name of all the contacts.
     *
     * @return a list of names.
     */
    private ArrayList<ContactModel> getContactNames() {
        ArrayList<ContactModel> contacts = new ArrayList<>();
        // Get the ContentResolver
        ContentResolver cr = activity.getContentResolver();
        // Get the Cursor of all the contacts
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        // Move the cursor to first. Also check whether the cursor is empty or not.

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ContactModel contactModel = new ContactModel();

                String id = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts._ID));


                if (cursor.getInt(cursor.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));

                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String photoURL = pCur.getString(pCur.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                        contactModel.setName(name);
                        contactModel.setPhoneNumber(phoneNo);
                        contactModel.setPhotoURL(photoURL);
                        contacts.add(contactModel);
                    }
                    pCur.close();
                }
            }
        }
        // Close the curosor
        cursor.close();
        return contacts;
    }
}
