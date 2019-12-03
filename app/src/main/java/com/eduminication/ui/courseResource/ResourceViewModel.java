package com.eduminication.ui.courseResource;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.URL;
import java.util.ArrayList;

class ResourceViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Item>> data = new MutableLiveData<>();

    //TODO for test
    private MutableLiveData<URL> url = new MutableLiveData<>();

    ResourceViewModel() {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        Resource[] pptResources = new Resource[]{
                new Resource("课程1", ""),
                new Resource("课程1", ""),
                new Resource("课程1", ""),
                new Resource("课程1", ""),
                new Resource("课程1", "")
        };
        itemArrayList.add(new Item("数据库", pptResources));
        itemArrayList.add(new Item("编译原理", pptResources));
        itemArrayList.add(new Item("数字图像处理", pptResources));
        data.setValue(itemArrayList);
    }

    MutableLiveData<ArrayList<Item>> getData() {
        return data;
    }
}
