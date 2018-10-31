package com.example.mahiti.json3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private List<Data> dataList;
    private Context context;

    public CustomAdapter(List<Data> datalist, Context cont) {
        this.dataList = datalist;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.dataList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.lhid = (TextView) convertView.findViewById(R.id.lhid);
            holder.lmid = (TextView) convertView.findViewById(R.id.lmid);
            holder.given_name = (TextView) convertView.findViewById(R.id.given_name);
            holder.surname = (TextView) convertView.findViewById(R.id.surname);
            holder.household_name = (TextView) convertView.findViewById(R.id.household_name);
            holder.door_no = (TextView) convertView.findViewById(R.id.door_no);
            holder.building_no = (TextView) convertView.findViewById(R.id.building_no);
            holder.street = (TextView) convertView.findViewById(R.id.street);
            holder.hamlet = (TextView) convertView.findViewById(R.id.hamlet);
            holder.primary_occupation = (TextView) convertView.findViewById(R.id.primary_occupation);
            holder.secondary_occupation = (TextView) convertView.findViewById(R.id.secondary_occupation);
            holder.caste = (TextView) convertView.findViewById(R.id.caste);
            holder.subcaste = (TextView) convertView.findViewById(R.id.subcaste);
            holder.state = (TextView) convertView.findViewById(R.id.state);
            holder.district = (TextView) convertView.findViewById(R.id.district);
            holder.village = (TextView) convertView.findViewById(R.id.village);
            holder.relationship = (TextView) convertView.findViewById(R.id.relationship);
            holder.username = (TextView) convertView.findViewById(R.id.username);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Data stu = dataList.get(position);
        holder.id.setText(Integer.toString(stu.getId()));
        holder.lhid.setText(stu.getLhid());
        holder.lmid.setText(stu.getLmid());
        holder.given_name.setText(stu.getGivenName());
        holder.surname.setText(stu.getSurname());
        holder.household_name.setText(stu.getHouseholdName());
        holder.door_no.setText(stu.getDoorNo());
        holder.building_no.setText(stu.getBuildingNo());
        holder.street.setText(stu.getStreet());
        holder.hamlet.setText(stu.getHamlet());
        holder.primary_occupation.setText(stu.getPrimaryOccupation());
        holder.secondary_occupation.setText(stu.getSecondaryOccupation());
        holder.caste.setText(stu.getCaste());
        holder.subcaste.setText(stu.getSubcaste());
        holder.state.setText(stu.getState());
        holder.district.setText(stu.getDistrict());
        holder.village.setText(stu.getVillage());
        holder.relationship.setText(stu.getRelationship());
        holder.username.setText(stu.getUsername());
        return convertView;
    }

    private static class ViewHolder {
        public TextView id;
        public TextView lhid;
        public TextView lmid;
        public TextView given_name;
        public TextView surname;
        public TextView household_name;
        public TextView door_no;
        public TextView building_no;
        public TextView street;
        public TextView hamlet;
        public TextView primary_occupation;
        public TextView secondary_occupation;
        public TextView caste;
        public TextView subcaste;
        public TextView state;
        public TextView district;
        public TextView village;
        public TextView relationship;
        public TextView username;

    }
}
