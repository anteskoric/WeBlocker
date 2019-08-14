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

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The class Url represents the data structure from the table url of the database history
 * @author Ante Skoric
 */
public class Url {

    /**
     * The id of the Url
     */
    private Integer id;

    /**
     * The title of the Url
     */
    private String title;

    /**
     * The Url of the website
     */
    private String url;

    /**
     * The number of visits
     */
    private Integer visitCount;

    /**
     * The last visited time
     */

    private LocalDateTime lastVisitTime;

    public Url(Integer id, String title, String url, int visitCount, LocalDateTime lastVisitTime){
        this.id = id;
        this.title = title;
        this.url = url;
        this.visitCount = visitCount;
        this.lastVisitTime = lastVisitTime;
    }

    /**
     * The field getters
     */
    public Integer getId(){return this.id;}

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public Integer getVisitCount(){
        return this.visitCount;
    }

    public LocalDateTime getLastVisitTime(){
        return this.lastVisitTime;
    }

    /**
     * The field setters
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public void setLastVisitTime(LocalDateTime lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    /**
     *
     * @param o Object to be verified
     * @return boolea true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Url)) return false;
        Url url1 = (Url) o;
        return Objects.equals(title, url1.title) &&
                Objects.equals(url, url1.url) &&
                Objects.equals(visitCount, url1.visitCount) &&
                Objects.equals(lastVisitTime, url1.lastVisitTime);
    }

    /**
     * Generates hash code of the object
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, url, visitCount, lastVisitTime);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "Url{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", visitCount=" + visitCount +
                ", lastVisitTime=" + lastVisitTime +
                '}';
    }
}
