package vn.edu.fpt.electricalconponents;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.fpt.electricalconponents.adapters.ViewPagerHomeAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        viewPager = findViewById(R.id.mainAtv_viewPager);
        tabLayout = findViewById(R.id.mainAtv_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        // Init view pager adapter
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(this);
        viewPager.setAdapter(adapter);
        // Attach view pager with tab layout
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    break;
                case 1:
                    tab.setText("Cart");
                    break;
                case 2:
                    tab.setText("Order");
                    break;
                case 3:
                    tab.setText("Setting");
                    break;
            }
        }).attach();
    }
}