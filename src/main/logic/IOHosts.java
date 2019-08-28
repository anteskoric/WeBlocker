package logic;
// The MIT License
//
//Copyright (c) 2010-2019 Google, Inc. http://angularjs.org

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.


import exceptions.URLAlreadyExistingException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class logic.IOHosts is used for blocking websites from the browser
 *
 * @author Ante Skoric
 */

public final class IOHosts {
    /**
     * The path of the hosts file
     */
    private final static String HOSTS_FILE = "C:\\Windows\\System32\\drivers\\etc\\hosts";

    /**
     * The constructor is private because it is utility class
     */
    private IOHosts() {
    }

    /**
     * The method getHostName is used for getting host name from users input
     *
     * @param url url of the website for example www.example.com or https://www.example.com/
     * @throws IllegalArgumentException if user does not input url of a website
     */

    public static String getHostName(String url) {
        String hostName = null;
        //TODO add regex "^https://.+\\.com.+"
        try {
            String wwwPattern = "^www\\..+\\.com$";
            String protocolPattern = "^https://www\\..+\\.com.+";
            if (url.matches(wwwPattern)) {
                hostName = new URL("https://" + url).getHost().substring(4);
            } else if (url.matches(protocolPattern)) {
                hostName = new URL(url).getHost().substring(4);
            } else {
                //TODO popup or into logs
                throw new IllegalArgumentException("Please use URL of the website");
            }
        } catch (IOException a) {
            //TODO logs
            System.err.println(a.getMessage());
        }
        return hostName;
    }

    /**
     * Unblocks website that has been blocked
     *
     * @param url          URL of the website
     * @param tempFilePath the temporal file that will be used for storing text
     * @throws IllegalArgumentException if the website is not blocked
     */
    public static void unblockSite(String url, String tempFilePath) {
        String hostName = null;
        try {
            hostName = new URL("https://" + url).getHost();
        } catch (MalformedURLException a) {
            //TODO change this into logs
            System.err.println(a.getMessage());
        }

        if (!checkIfInTheFile(hostName))
            //TODO change this into logs or popup
            throw new IllegalArgumentException("The website is not blocked");

        saveInTempFile(tempFilePath, hostName);
        saveIntoHosts(tempFilePath);
    }

    /**
     * Return all blocked websites
     *
     * @return List with host machines of the blocked website in a form of string
     */
    public static List<String> getBlockedSites() {
        try {
            return Files.lines(Path.of(HOSTS_FILE), StandardCharsets.ISO_8859_1)
                    .parallel()
                    .filter(x -> x.matches("^0.0.0.0\\s+www..+"))
                    .map(x -> x.split("\\s+"))
                    .map(x -> x[1])
                    .collect(Collectors.toList());
        } catch (IOException a) {
            //TODO change into logs
            System.err.println(a.getMessage());
        }
        return List.of();
    }

    /**
     * ChangeRights of a file
     *
     * @param path Path of the file
     */
    //TODO make this method work for windows
    public static void changeRights(String path) {
        //TODO use the class FilePermission
        try {
            Runtime.getRuntime().exec("icacls " + path + "/grant Users:F");
        } catch (IOException a) {
            //TODO change into logs
        }
    }

    /**
     * Check if the url of a website is already blocked
     *
     * @param url the host machine of the website
     * @return boolean, true if the url is already blocked (in the file hosts),
     * false if the url is not blocked(not in the file)
     */
    private static boolean checkIfInTheFile(String url) {
        try {
            return Files.lines(Path.of(HOSTS_FILE), StandardCharsets.ISO_8859_1)
                    .parallel()
                    .anyMatch(x -> x.contains(url));
        } catch (IOException a) {
            //TODO change into logs
        }
        return false;
    }

    /**
     * Save content from hosts file into temp file
     * without line that contains the host machine(hostName) that we want to block
     *
     * @param tempFilePath Path of the temp file
     * @param hostName     Host machine of the website we want to block
     */
    private static void saveInTempFile(String tempFilePath, String hostName) {
        File tempFile = new File(tempFilePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(HOSTS_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, false))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().contains(hostName)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.flush();
        } catch (IOException a) {
            //TODO change into logs
            System.err.println(a.getMessage());
        }
    }

    /**
     * Saves the content of temp file into hosts file
     *
     * @param tempFilePath path of the temp file
     */
    //TODO Antivirus does not allow this method
    private static void saveIntoHosts(String tempFilePath) {
        File hostsFile = new File(HOSTS_FILE);
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFilePath));
             //TODO remove comment
             //new FileWriter(hostsFile,false) throws Exception,
             //because of the append in the FileOutputStream.opens method,
             //if append is set to true the method will work
             //else if append is set to false it will throw a Exception
             //It does not work because I dont have permission to the windows folder
             BufferedWriter writer = new BufferedWriter(new FileWriter(hostsFile, false))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim() + System.getProperty("line.separator");
                writer.write(currentLine);
            }
            writer.flush();
            //TODO remove the code below
        } catch (IOException a) {
            //TODO change into logs
            System.out.println(a.toString());
            StackTraceElement[] stackTraceElements = a.getStackTrace();
            Arrays.stream(stackTraceElements).forEach(System.out::println);
        }
    }

    /**
     * The method writeIntoHosts writes String into hosts file
     *
     * @param hostName String to be written into file
     * @throws NullPointerException        If the input is null the exception will be thrown
     * @throws URLAlreadyExistingException If the String is already written into the file the exception will be thrown
     */

    public static void writeIntoHost(String hostName) {
        if (hostName == null) {
            //TODO make into logs
            throw new NullPointerException();
        }

        if (checkIfInTheFile(hostName)) {
            //TODO make into popup or into logs
            throw new URLAlreadyExistingException("The URL is already blocked");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOSTS_FILE, true))) {
            writer.newLine();
            writer.write("0.0.0.0 " + "www." + hostName + " " + hostName);
            writer.flush();
        } catch (IOException a) {
            //TODO change this into logs
            System.err.println(a.getMessage());
        }
    }

    /**
     * Check if users input (URL) matches the regex
     * There are two matches for users input protocolPattern for example https://www.google.com/
     * and easyProtocolPattern for example https://google.com/
     *
     * @param url users input
     * @throws IllegalArgumentException if users input doesnt match one of the regexs
     */
    public static void checkUsersUrlInput(String url) {
        String protocolPattern = "^https://www\\..+\\.com(.*)";
        String easyProtocolPattern = "^https://.+\\.com(.*)";
        if (!url.matches(protocolPattern) && !url.matches(easyProtocolPattern)) {
            //TODO popup or into logs
            throw new IllegalArgumentException("Please use URL of the website");
        }
    }
}