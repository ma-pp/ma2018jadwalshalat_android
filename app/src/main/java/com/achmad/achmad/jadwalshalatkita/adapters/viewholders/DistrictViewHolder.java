package com.achmad.achmad.jadwalshalatkita.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.achmad.achmad.jadwalshalatkita.R;
import com.achmad.achmad.jadwalshalatkita.fragments.DistrictSelectionFragment;
import com.achmad.achmad.jadwalshalatkita.models.District;

/**
 * Created by akif on 11/05/16.
 */
public class DistrictViewHolder extends RecyclerView.ViewHolder {
    private View districtItemLayout;
    private TextView textViewName;

    private DistrictSelectionFragment.OnDistrictSelectedListener onDistrictSelectedListener;

    public DistrictViewHolder(View districtItemLayout, DistrictSelectionFragment.OnDistrictSelectedListener onDistrictSelectedListener) {
        super(districtItemLayout);

        this.districtItemLayout = districtItemLayout;
        this.onDistrictSelectedListener = onDistrictSelectedListener;

        textViewName = (TextView) districtItemLayout.findViewById(R.id.textView_item_district_name);
    }

    public void setFrom(final District district) {
        textViewName.setText(district.name);

        districtItemLayout.setOnClickListener(v -> onDistrictSelectedListener.onDistrictSelected(district));
    }
}
