package json.classes;

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
 * The class WebsiteVisitTracker represents the data structure for the json objects
 *
 *  @author Ante Skoric
 */
public class WebsiteVisitTracer {

    /**
     * Url of the visited website
     */
    private String Url;

    /**
     * Users input, maximal allowed usage of the website
     */
    private long hours;

    /**
     * Creation point in seconds from 1601
     */
    private long entryCreation;

    public WebsiteVisitTracer(String Url, long hours, long entryCreation){
        this.entryCreation = entryCreation;
        this.hours = hours;
        this.Url = Url;
    }

    /**
     * Getter methods
     */
    public String getUrl() {
        return this.Url;
    }

    public long getHours() {
        return this.hours;
    }

    public long getEntryCreation() {
        return this.entryCreation;
    }

    /**
     * Setter methods
     */
    public void setUrl(String url) {
        this.Url = url;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public void setEntryCreation(long entryCreation) {
        this.entryCreation = entryCreation;
    }

    /**
     *
     * @param o Object to be verified
     * @return boolean true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebsiteVisitTracer)) return false;
        WebsiteVisitTracer that = (WebsiteVisitTracer) o;
        return this.hours == that.hours &&
                this.entryCreation == that.entryCreation &&
                Objects.equals(this.Url, that.Url);
    }

    /**
     * Generates hash code of the object
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.Url, this.hours, this.entryCreation);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "Url: " + Url + "Hours: " + hours+ "Entry creation: " + entryCreation;
    }
}
