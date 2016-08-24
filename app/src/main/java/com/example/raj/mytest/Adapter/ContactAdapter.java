package com.example.raj.mytest.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raj.mytest.Interface.Scrollable;
import com.example.raj.mytest.Model.ContactModel;
import com.example.raj.mytest.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Raj on 8/24/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> implements Scrollable {
    private ArrayList<ContactModel> android;
    private Bitmap mIcon_val;
    private Context context;

    public ContactAdapter(ArrayList<ContactModel> android, Context context) {
        this.android = android;
        this.context = context;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txtName.setText(android.get(i).getName());
        viewHolder.txtPhone.setText(android.get(i).getPhoneNumber());
        if (android.get(i).getPhotoURL() != null) {
            viewHolder.imgPhoto.setImageURI(Uri.parse(android.get(i).getPhotoURL()));
        } else
            viewHolder.imgPhoto.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_male));
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    @Override
    public String getIndicatorForPosition(int childposition, int groupposition) {
        return Character.toString(android.get(childposition).getName().charAt(0));

    }

    @Override
    public int getScrollPosition(int childposition, int groupposition) {
        return childposition;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtPhone;
        private CircleImageView imgPhoto;

        public ViewHolder(View view) {
            super(view);

            txtName = (TextView) view.findViewById(R.id.txt_name);
            txtPhone = (TextView) view.findViewById(R.id.txt_phone);
            imgPhoto = (CircleImageView) view.findViewById(R.id.imgPhoto);

        }
    }

}
