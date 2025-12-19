package com.github.Xujia118;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting to process...");

        try (
                LogWriter logWriter = new LogWriter("app.log");
                DataProcessor dataProcessor = new DataProcessor("CloudDatabase")
        ) {
            logWriter.writeLog("System initialized.");
            dataProcessor.processData();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("--- Try-with-Resources Finished ---");


        // Traditional approach
        System.out.println("Traditional approach");
        LogWriter logger = null;
        DataProcessor processor = null;
        try {
            logger = new LogWriter("app.log");
            processor = new DataProcessor("CloudDatabase");

            logger.writeLog("System initialized.");
            processor.processData();
        } finally {
            // We must manually check for null and handle potential exceptions during close
            try {
                if (processor != null) processor.close();
            } catch (Exception e) {
                /* handle */
            }
            try {
                if (logger != null) logger.close();
            } catch (Exception e) {
                /* handle */
            }
        }

        /**
         * It's clear that try-with-resources is much more concise and less error-prone.
         * And there is no manual checking and closing.
         */
    }
}