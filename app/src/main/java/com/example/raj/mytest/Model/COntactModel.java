package com.example.raj.mytest.Model;

import android.os.Parcelable;

/**
 * Created by Raj on 8/24/2016.
 */
public class ContactModel implements  Comparable<ContactModel>{
    String photoURL;
    String name;
    String phoneNumber;
    private int hashCode;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isContactEqual = false;
        if (obj != null && obj instanceof ContactModel) {
            isContactEqual = this.getPhoneNumber().equals(((ContactModel) obj).getPhoneNumber());
        }
        return isContactEqual;
    }

    @Override
    public int compareTo(ContactModel contactModel) {
        return getPhoneNumber().compareToIgnoreCase(contactModel.getPhoneNumber());
    }
}
