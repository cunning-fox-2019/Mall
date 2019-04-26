package com.seven.lib_model.model.extension;

import java.util.List;

/**
 * Created by xxxxxxH on 2019/4/25.
 */
public class MyInterViewEntity {
    private ParentInfo parent_info;
    private List<ChildList> child_list;

    public ParentInfo getParent_info() {
        return parent_info;
    }

    public void setParent_info(ParentInfo parent_info) {
        this.parent_info = parent_info;
    }

    public List<ChildList> getChild_list() {
        return child_list;
    }

    public void setChild_list(List<ChildList> child_list) {
        this.child_list = child_list;
    }
}
