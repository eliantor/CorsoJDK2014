package me.aktor.corsoapp.corso;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrea Tortorella on 21/06/14.
 */
public class FragmentHellos extends Fragment {

    public static interface OnShowDetailsListener {
        public void onShowDetails(String item);
    }

    private Adapter mAdapter;
    private OnShowDetailsListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnShowDetailsListener){
            mListener = (OnShowDetailsListener)activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        ListView lv = (ListView)view.findViewById(R.id.lv_text_list);
        mAdapter = new Adapter();
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(mAdapter);
        return view;
    }

    public void addHello(String text){
        mAdapter.add(text);
    }


    private class Adapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private final List<String> mData;
        private final LayoutInflater mInflater;

        Adapter(){
            mData = new ArrayList<String>();
            mInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getViewTypeCount() {
            ///return 2;
            return super.getViewTypeCount();
        }


        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HellosViewHolder h;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_list_text_content, parent, false);
                h = new HellosViewHolder(convertView);
                convertView.setTag(h);
            } else {
                h =(HellosViewHolder)convertView.getTag();
            }
            String item =mData.get(position);
            h.bind(item);
            return convertView;
        }

        public void add(String text) {
            mData.add(text);
            notifyDataSetChanged();
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String item = mData.get(position);
            if (mListener == null){
                Toast.makeText(getActivity(),item,Toast.LENGTH_LONG).show();
            } else {
                mListener.onShowDetails(item);
            }
        }

    }


}
