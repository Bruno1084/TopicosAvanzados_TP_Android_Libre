package com.example.tpandroid_libre.ejercicio1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.tpandroid_libre.ejercicio1.fragments.GruposFragment;
import com.example.tpandroid_libre.ejercicio1.fragments.LlamadasFragment;
import com.example.tpandroid_libre.ejercicio1.fragments.MensajesFragment;
import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter (@NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LlamadasFragment();
            case 1:
                return new MensajesFragment();
            case 2:
                return new GruposFragment();
            default:
                return new MensajesFragment();
        }
    };

    @Override
    public int getItemCount() {
        return 3;
    };
}
