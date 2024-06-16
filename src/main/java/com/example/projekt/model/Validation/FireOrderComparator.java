package com.example.projekt.model.Validation;

import com.example.projekt.model.FireOrder;

import java.util.Comparator;

/**
 * Najpierw patrzy która data jest późniejsza, a jeśli takie same to który rozkaz jest najdłuższy.
 */
public class FireOrderComparator implements Comparator<FireOrder> {
    @Override
    public int compare(FireOrder o1, FireOrder o2) {
        if (o2.getDate().compareTo(o1.getDate()) != 0)
            return o2.getDate().compareTo(o1.getDate());
        else
            return Integer.compare(o2.getDuration(), o1.getDuration());
    }
}
