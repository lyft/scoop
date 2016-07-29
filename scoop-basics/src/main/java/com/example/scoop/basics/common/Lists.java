package com.example.scoop.basics.common;

import java.util.ArrayList;
import java.util.List;
import rx.functions.Func1;
import rx.functions.Func2;

public class Lists {

    public static <T> boolean elementsEqual(List<T> list1, List<T> list2, Func2<T, T, Boolean> comparator) {
        if (list1 == null || list2 == null) {
            return false;
        }

        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!comparator.call(list1.get(i), list2.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static <T> boolean isEmptyOrNull(List<T> list) {
        if (list == null) {
            return true;
        }

        if (list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static <T1, T2> List<T2> map(List<T1> list, Func1<T1, T2> converter) {
        ArrayList<T2> mappedList = new ArrayList<>(list.size());
        for (T1 item : list) {
            mappedList.add(converter.call(item));
        }

        return mappedList;
    }
}
