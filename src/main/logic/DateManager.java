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

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for LocalDateTime manipulation
 *
 * @author Ante Skoric
 */
public final class DateManager {
    private DateManager(){}

    //TODO make the method better or do it with database

    /**
     * Gets the Date and Time from 1601/1/1 plus the passed microseconds
     * @param microseconds the amount of microseconds we want to add to the year
     * @return new date
     */
    public static String setUnixTime(Long microseconds){
        LocalDateTime startingPoint = LocalDateTime.of(1601, Month.JANUARY,1,0,0);
        long seconds = microseconds / 1000000;
        return setEuropianFormat(startingPoint.plus(Duration.ofSeconds(seconds)));
    }

    /**
     * Get Date and Time in a form of String in european form yyyy/mm/dd
     * @param time The LocalDateTime we want to format
     * @return Date and Time in a String form
     */
    public static String setEuropianFormat(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
