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
import java.util.stream.IntStream;

/**
 * The class Url represents the data structure from the table url of the database history
 * @author Ante Skoric
 */
public class Url {
    private String title;
    private String url;
    private Integer visitCount;
    private LocalDateTime lastVisitTime;

    public Url(String title, String url, int visitCount, LocalDateTime lastVisitTime){
        this.title = title;
        this.url = url;
        this.visitCount = visitCount;
        this.lastVisitTime = lastVisitTime;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(title, url, visitCount, lastVisitTime);
    }

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
