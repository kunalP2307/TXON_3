package com.example.quizapp;

import java.util.ArrayList;

public class JavaQuizQuestions {

    static private String javaQuestions[] = new String[]{
           "1. What will be the output of below program?\n" +
                   "import java.io.IOException;\n" +
                   "\n" +
                   "public class Test {\n" +
                   "\tpublic static void main(String[] args) {\n" +
                   "\t\ttry {\n" +
                   "\t\t\tthrow new IOException(\"Hello\");\n" +
                   "\t\t} catch (IOException | Exception e) {\n" +
                   "\t\t\tSystem.out.println(e.getMessage());\n" +
                   "\t\t}\n" +
                   "\t}\n" +
                   "}",
            "2. Which of the below is valid way to instantiate an array in java?",
            "3. What will happen if we try to compile and run below program?\n" +
                    "\n" +
                    "interface Foo{ int x = 10;}\n" +
                    "\n" +
                    "public class Test { \n" +
                    "    public static void main(String[] args) { \n" +
                    "        Foo.x = 20; \n" +
                    "        System.out.println(Foo.x); \n" +
                    "    }\n" +
                    "}",
            "4. What will be the output of below program?\n" +
                    "\n" +
                    "public class Test {\n" +
                    "\tpublic static String toString() {\n" +
                    "\t\tSystem.out.println(\"Test toString called\");\n" +
                    "\t\treturn \"\";\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpublic static void main(String args[]) {\n" +
                    "\t\tSystem.out.println(toString());\n" +
                    "\t}\n" +
                    "}",
            "5.  Which environment variable is used to set the java path?",
            "6. What is not the use of “this” keyword in Java?",
            "7. What will be the output of the following Java program?\n" +
                    "\n" +
                    "class leftshift_operator {\n" +
                    "\tpublic static void main(String args[]) {        \n" +
                    "    \tbyte x = 64;\n" +
                    "    \tint i;\n" +
                    "        byte y; \n" +
                    "        i = x << 2;\n" +
                    "        y = (byte) (x << 2);\n" +
                    "        System.out.print(i + \" \" + y);\n" +
                    "    } \n" +
                    "}",
            "8. Which of the following is a superclass of every class in Java?",
            "9. Which of these statements is incorrect about Thread?",
            "10. Which of the following is true about servlets?"

    };

    static private String options[][] = new String[][]{
            {"Compile-time error", "Prints “Hello"," Runtime Error", "None of These" },
            {"int myArray [] = {1, 3, 5};", "int myArray [] [] = {1,2,3,4};", "int [] myArray = (5, 4, 3);", "int [] myArray = {“1”, “2”, “3”};"},
            {"Prints 10", "Prints 20", "Compile Time Error", "Runtime error because Foo.x is final"},
            {"Test toString called", "Compile-time error", "'Test@7fh2bd8' (Object class toString() method is being called)", "None of these"},
            {"MAVEN_Path", "JavaPATH", "JAVA", "JAVA_HOME"},
            {"Referring to the instance variable when a local variable has the same name", "Passing itself to the method of the same class", "Passing itself to another method", "Calling another constructor in constructor chaining"},
            {"0 256","0 64", "256 0", "64 0"},
            {"ArrayList", "Abstract class", "Object class", "String"},
            {"start() method is used to begin execution of the thread", "run() method is used to begin execution of a thread before start() method in special cases","A thread can be formed by implementing Runnable interface only","A thread can be formed by a class that extends Thread class"},
            {"Servlets can use the full functionality of the Java class libraries", "Servlets execute within the address space of web server, platform independent and uses the functionality of java class libraries"," Servlets execute within the address space of web server", "Servlets are platform-independent because they are written in java"}
    };

    static private int answers[] = new int[]{
            1,1,3,2,4,2,3,3,2,2
    };

    public static ArrayList<Question> getQuiz(){
        ArrayList<Question> questions = new ArrayList<Question>();
        for(int i=0; i< javaQuestions.length; i++)
            questions.add(new Question((i+1), answers[i],Question.DEFAULT, javaQuestions[i], options[i]));

        return questions;
    }
}
