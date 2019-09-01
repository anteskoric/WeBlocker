package database.classes;

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

import java.util.Objects;

/**
 * The class WebSiteVisit represents the data structure from the table visits and urls of the database history
 *
 * @author Ante Skoric
 */
public class WebSiteVisit {

    /**
     * The duration of all visits to the website
     */
    private Long visitDuration;

    /**
     * The duration of all visits to the website in the form of a string
     */
    private String hoursMinutes;

    /**
     * Title of the website
     */
    private String title;

    public WebSiteVisit(String title, Long visitDuration, String hoursMinutes) {
        this.title = title;
        this.visitDuration = visitDuration;
        this.hoursMinutes = hoursMinutes;
    }

    /**
     * Getter methods
     */
    public long getVisitDuration() {
        return visitDuration;
    }

    public String getTitle() {
        return title;
    }

    public String getHoursMinutes() {
        return hoursMinutes;
    }

    /**
     * Setter methods
     */
    public void setVisitDuration(long visitDuration) {
        this.visitDuration = visitDuration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHoursMinutes(String hoursMinutes) {
        this.hoursMinutes = hoursMinutes;
    }

    /**
     * @param o Object to be verified
     * @return boolean true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebSiteVisit)) return false;
        WebSiteVisit that = (WebSiteVisit) o;
        return Objects.equals(visitDuration, that.visitDuration) &&
                Objects.equals(hoursMinutes, that.hoursMinutes) &&
                Objects.equals(title, that.title);
    }

    /**
     * Generates hash code of the object
     *
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(visitDuration, title, hoursMinutes);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "WebSiteVisit{" +
                "visitDuration=" + visitDuration +
                ", hoursMinutes='" + hoursMinutes + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
