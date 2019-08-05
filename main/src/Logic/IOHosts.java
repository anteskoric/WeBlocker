package Logic;
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


import Exceptions.URLAlreadyExistingException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class Logic.IOHosts is used for blocking websites from the browser
 *
 * @author Ante Skoric
 */

public final class IOHosts {
    /**
     * The path of the hosts file
     */
    private final static String HOSTS_FILE = "C:\\Windows\\System32\\drivers\\etc\\hosts";

    private IOHosts() {
    }

    /**
     * Blocks website, the url of the website will be written into the file hosts
     *
     * @param url URL of the website that the user wants to block
     * @throws URLAlreadyExistingException if the website is already blocked
     */

    //TODO make this method better www.reddit.com does not work
    public static void blockSite(String url) {
        String hostName = null;
        try {
            hostName = new URL(url).getHost();
        } catch (IOException a) {
            //TODO change this into popup
        }

        if (checkIfInTheFile(hostName)) {
            //TODO abort with popup
            throw new URLAlreadyExistingException("The URL is already blocked");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HOSTS_FILE, true))) {
            writer.newLine();
            writer.write("127.0.0.1 " + "www." + hostName + " " + hostName);
            writer.flush();
        } catch (IOException a) {
            //TODO change this into logs
            System.err.println(a);
        }
    }

    /**
     * Unblocks website that has been blocked
     *
     * @param url URL of the website
     * @throws IllegalArgumentException if the website is not blocked
     */
    public static void unblockSite(String url, String tempFilePath) {
        String hostName = null;
        try {
            hostName = new URL(url).getHost();
        } catch (MalformedURLException a) {
            //TODO change this into logs
            System.err.println(a);
        }

        if (!checkIfInTheFile(hostName))
            //TODO change this into logs
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
                    .filter(x -> x.matches("^127.0.0.1\\s+www..+"))
                    .map(x -> x.split("\\s+"))
                    .map(x -> x[2])
                    .collect(Collectors.toList());
        } catch (IOException a) {
            //TODO change into logs
            System.err.println(a);
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
     * @param tempFilePath path of the temp file
     * @param hostName     host machine of the website we want to block
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
            System.err.println(a);
        }
    }

    /**
     * Saves the content of temp file into hosts file
     *
     * @param tempFilePath path of the temp file
     */
    //TODO Antivirus does not allow this method
    private static void saveIntoHosts(String tempFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFilePath));
             FileOutputStream writer = new FileOutputStream(HOSTS_FILE)) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim() + System.getProperty("line.separator");
                writer.write(currentLine.getBytes());
            }
            writer.flush();
        } catch (IOException a) {
            //TODO change into logs
            System.err.println(a);
        }
    }
}