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
 * The class VisitDuration represents the data structure from the table visits of the database history
 * @author Ante Skoric
 */
public class VisitDuration {
    /**
     * The visit time in microseconds
     */
    private long visits;

    public VisitDuration(long visits){
        this.visits = visits;
    }

    /**
     * Getter methods
     */
    public long getVisits() {
        return visits;
    }

    /**
     * Setter methods
     */
    public void setVisits(long visits) {
        this.visits = visits;
    }

    /**
     *
     * @param o Object to be verified
     * @return boolean true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitDuration)) return false;
        VisitDuration that = (VisitDuration) o;
        return visits == that.visits;
    }

    /**
     * Generates hash code of the object
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(visits);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "visits=" + visits;
    }
}
