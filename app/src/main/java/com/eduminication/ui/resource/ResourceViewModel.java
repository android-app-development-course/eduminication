package com.eduminication.ui.resource;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ResourceViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Item>> data=new MutableLiveData<>();

    public ResourceViewModel() {
        ArrayList<Item> itemArrayList=new ArrayList<>();
        String[] pptResources= new String[]{"课程1","课程2","课程3","课程4","课程5"};
        itemArrayList.add(new Item("数据库",pptResources));
        itemArrayList.add(new Item("编译原理",pptResources));
        itemArrayList.add(new Item("数字图像处理", pptResources));
    }

    public MutableLiveData<ArrayList<Item>> getData() {
        return data;
    }
}
