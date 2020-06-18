package com.mageddo.jvmti.poc;

import com.mageddo.jvmti.JvmtiClass;

public class CurrentProcessJvmAttach {
  public static void main(String[] args) {
//    new NativeLoader(new JvmtiNativeLibraryFinder()).load();

    new JiraIssue("x1");
    new JiraIssue("x2");
    System.out.printf("instances: %d%n", JvmtiClass.countInstances(JiraIssue.class));
//    System.out.printf("classes: %s%n", JvmtiClass.findLoadedClasses());
    final Class[] classes = JvmtiClass.findLoadedClasses();
    for (Class jclass : classes) {
      System.out.println(jclass.getSimpleName());
    }
    System.out.println(classes.length);

    final Object[] instances = JvmtiClass.getClassInstances(JiraIssue.class);
    System.out.printf("found %d instances%n", instances.length);
    for (Object instance : instances) {
      System.out.printf("instance = %s%n", instance);
    }


  }
}