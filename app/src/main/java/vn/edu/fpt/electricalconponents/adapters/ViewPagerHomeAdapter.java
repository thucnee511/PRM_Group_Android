package vn.edu.fpt.electricalconponents.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.fpt.electricalconponents.fragments.CartFragment;
import vn.edu.fpt.electricalconponents.fragments.HomeFragment;
import vn.edu.fpt.electricalconponents.fragments.OrderFragment;
import vn.edu.fpt.electricalconponents.fragments.SettingFragment;

public class ViewPagerHomeAdapter extends FragmentStateAdapter {
    public ViewPagerHomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new CartFragment();
            case 2:
                return new OrderFragment();
            case 3:
                return new SettingFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
