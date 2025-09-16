/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tienich;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
import java.util.*;
import java.util.function.BiConsumer;

public class EventBus {

    private static Map<String, List<BiConsumer<String, Object[]>>> listeners = new HashMap<>();

    public static void subscribe(String event, BiConsumer<String, Object[]> listener) {
        listeners.computeIfAbsent(event, k -> new ArrayList<>()).add(listener);
    }

    public static void publish(String event, Object... data) {
        if (listeners.containsKey(event)) {
            for (BiConsumer<String, Object[]> listener : listeners.get(event)) {
                listener.accept(event, data);
            }
        }
    }
}
