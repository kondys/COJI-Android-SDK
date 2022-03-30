package com.wowwee.coji_android_sampleproject.com.wowwee.coji_android_sampleproject.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wowwee.bluetoothrobotcontrollib.coji.CojiRobot;
import com.wowwee.bluetoothrobotcontrollib.coji.CojiRobotFinder;
import com.wowwee.coji_android_sampleproject.R;
import com.wowwee.coji_android_sampleproject.utils.FragmentHelper;

/**
 * Created by davidchan on 22/3/2017.
 */

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null)
            return null;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);


        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        ListView listView = (ListView)view.findViewById(R.id.menuTable);
        String[] robotNameArr = {"Change LED color", "Button and accelerometer Test", "Drive COJI", "Modules Setting Features", "Playing Features"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, robotNameArr);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (CojiRobotFinder.getInstance().getCojiRobotConnectedList().size() > 0) {
                    CojiRobot robot = (CojiRobot)CojiRobotFinder.getInstance().getCojiRobotConnectedList().get(0);
                    switch (position) {
                        case 0:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new ChangeLEDFragment(), R.id.view_id_content, false);
                            break;
                        case 1:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new ButtonTestFragment(), R.id.view_id_content, false);
                            break;
                        case 2:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new DriveFragment(), R.id.view_id_content, false);
                            break;
                        case 3:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new ModuleSettingFragment(), R.id.view_id_content, false);
                            break;
                        case 4:
                            FragmentHelper.switchFragment(getActivity().getSupportFragmentManager(), new PlayingFragment(), R.id.view_id_content, false);
                            break;
                    }
                }
            }
        });


        return view;
    }
}
