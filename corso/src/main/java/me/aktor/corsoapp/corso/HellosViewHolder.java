package me.aktor.corsoapp.corso;

import android.view.View;
import android.widget.TextView;

/**
* Created by Andrea Tortorella on 21/06/14.
*/
public class HellosViewHolder {
    private final TextView mText;
    private final TextView mSubText;

    HellosViewHolder(View parent){
        mText = (TextView)parent.findViewById(R.id.tv_item_text);
        mSubText = (TextView)parent.findViewById(R.id.tv_item_text_sub);
    }

    public void bind(String data){
        mText.setText(data);

    }

    public void bindDetails(String data){
        bind(data);

        mSubText.setText(data.toUpperCase());

        /// ecc
    }
}
