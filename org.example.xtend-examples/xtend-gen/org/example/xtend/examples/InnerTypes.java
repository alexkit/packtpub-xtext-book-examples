package org.example.xtend.examples;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.example.xtend.examples.MyJavaClass;

@SuppressWarnings("all")
public class InnerTypes {
  public static void main(final String[] args) {
    InputOutput.<String>println(MyJavaClass.MyInnerInterface.s);
    InputOutput.<String>println(MyJavaClass.MyInnerInterface.s);
  }
}
