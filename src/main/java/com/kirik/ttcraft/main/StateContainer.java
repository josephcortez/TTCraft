package com.kirik.ttcraft.main;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class StateContainer {

    @Retention(RetentionPolicy.RUNTIME) protected @interface Loader {String[] value();}
    @Retention(RetentionPolicy.RUNTIME) protected @interface Saver {String[] value();}

    class Closure {
        StateContainer instance;
        Method method;

        public Closure(StateContainer instance, Method method) {
            super();
            this.instance = instance;
            this.method = method;
        }

        public String toString() {
            return method.getDeclaringClass().getName()+"."+method.getName()+"()";
        }

        public void invoke() {
            try {
                if(Modifier.isStatic(method.getModifiers())) {
                    method.invoke(null);
                }else{
                    method.invoke(instance);
                }
            }catch(IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static Map<String, Closure> loadersByName = new HashMap<>();
    private static Map<String, Closure> saversByName = new HashMap<>();
    private static Set<Closure> loaders = new HashSet<>();
    private static Set<Closure> savers = new HashSet<>();

    public StateContainer() {
        for(Method method : getClass().getMethods()) {
            final Loader loaderAnnotation = method.getAnnotation(Loader.class);
            final Saver saverAnnotation = method.getAnnotation(Saver.class);
            if(loaderAnnotation != null) {
                final Closure closure = new Closure(this, method);

                loaders.add(closure);
                for(String name : loaderAnnotation.value()) {
                    loadersByName.put(name, closure);
                }
            }

            if(saverAnnotation != null) {
                final Closure closure = new Closure(this, method);

                savers.add(closure);
                for(String name : saverAnnotation.value()) {
                    saversByName.put(name,closure);
                }
            }
        }
    }

    public static void loadAll() {
        for(Entry<String, Closure> entry : loadersByName.entrySet()) {
            Closure closure = entry.getValue();

            closure.invoke();
        }
    }

    public static boolean loadSingle(String loaderName) {
        final Closure closure = loadersByName.get(loaderName);
        if(closure == null)
            return false;

        closure.invoke();
        return true;
    }

    public static void saveAll() {
        for(Entry<String, Closure> entry : saversByName.entrySet()) {
            Closure closure = entry.getValue();

            closure.invoke();
        }
    }

    public static boolean saveSingle(String saverName) {
        final Closure closure = saversByName.get(saverName);
        if(closure == null)
            return false;

        closure.invoke();
        return true;
    }
}
