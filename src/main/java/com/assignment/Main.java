package com.assignment;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Scanner;


/**
 * Created by M on 2016-03-04.
 */
public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String [ ] args)
    {
        scanLog();
        logger.debug("log level was set to "+ LogManager.getRootLogger().getLevel().toString());
        logger.info("Application started ...");
        scanCommand();

        String x = "";
    }

    public static void scanLog (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter log level(INFO,ERROR,DEBUG):\n");
        String logLevel = scanner.nextLine();

        switch (logLevel.toUpperCase()) {
            case "INFO":
                LogManager.getRootLogger().setLevel(Level.INFO);
                break;
            case "ERROR":
                LogManager.getRootLogger().setLevel(Level.ERROR);
                break;
            case "DEBUG":
                LogManager.getRootLogger().setLevel(Level.DEBUG);
                break;

            default:
                System.out.print("Invalid log level, use following options(INFO,ERROR,DEBUG): "+ logLevel+"\n");
                scanLog();
        }
    }

    public static void scanCommand(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command:\n");
        String command = scanner.nextLine();
        logger.debug("command '"+ command + "' entered \n");
    }
}
