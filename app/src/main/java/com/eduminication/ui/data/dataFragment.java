package com.eduminication.ui.data;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.eduminication.R;
import com.github.clans.fab.FloatingActionMenu;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

public class dataFragment extends Fragment implements TbsReaderView.ReaderCallback {
    private dataViewModel dataViewModel;
    private TbsReaderView mTbsReaderView;
    private RelativeLayout rl_tbsView;
    private String fileName = "向阳.pptx";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataViewModel =
                ViewModelProviders.of(this).get(dataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data, container, false);

        FloatingActionMenu fab = (FloatingActionMenu)root.findViewById(R.id.fab);
        fab.setClosedOnTouchOutside(true);

        mTbsReaderView = new TbsReaderView(this.getActivity(), this);
        rl_tbsView = (RelativeLayout)root.findViewById(R.id.rl_tbsView);
        rl_tbsView.addView(mTbsReaderView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        local_display();

        return root;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    private void local_display(){
        Bundle bundle = new Bundle();
        bundle.putString("filePath", getLocalFile().getPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory()
                .getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(fileName), false);
        if (result)
            mTbsReaderView.openFile(bundle);
    }

    private final String[][] MIME_MapTable = {
            // {后缀名，MIME类型}
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx",
                    "application/vnd.openxmlformats-officedocument.presentationml.presentation"}
    };

    private File getLocalFile() {
        return new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName);
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}