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
 * The class Cookie represents the data structure from the table cookies of the database history
 *
 * @author Ante Skoric
 */
public class Cookie {

    /**
     * Name of the cookie
     */
    private String name;

    /**
     * Host key of the cookie
     */
    private String hostKey;

    /**
     * Is the cookie expired
     * 0 if it is expired else 1
     */
    private int hasExpired;

    /**
     * The time of creation used for DELETING from db
     */
    private Long creationUtc;

    /**
     * The time of creation
     */
    private String creationTime;

    /**
     * The time and date when the cookie expires
     */
    private String expiresTime;

    /**
     * Last access of the cookie
     */
    private String lastAccessTime;

    public Cookie(String name, String hostKey, int hasExpired, Long creationUtc, String creationTime, String expiresTime, String lastAccessTime) {
        this.name = name;
        this.hostKey = hostKey;
        this.hasExpired = hasExpired;
        this.creationUtc = creationUtc;
        this.creationTime = creationTime;
        this.expiresTime = expiresTime;
        this.lastAccessTime = lastAccessTime;
    }

    /**
     * Field getters
     */
    public String getName() {
        return this.name;
    }

    public String getHostKey() {
        return this.hostKey;
    }

    public int getHasExpired() {
        return this.hasExpired;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public String getExpiresTime() {
        return this.expiresTime;
    }

    public String getLastAccessTime() {
        return this.lastAccessTime;
    }

    public long getCreationUtc() {
        return creationUtc;
    }

    /**
     * Field setters
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setHostKey(String hostKey) {
        this.hostKey = hostKey;
    }

    public void setHasExpired(int hasExpired) {
        this.hasExpired = hasExpired;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setExpiresTime(String expiresTime) {
        this.expiresTime = expiresTime;
    }

    public void setLastAccessTime(String lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public void setCreationUtc(long creationUtc) {
        this.creationUtc = creationUtc;
    }

    /**
     * @param o Object to be verified
     * @return boolean true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cookie)) return false;
        Cookie cookie = (Cookie) o;
        return hasExpired == cookie.hasExpired &&
                Objects.equals(name, cookie.name) &&
                Objects.equals(hostKey, cookie.hostKey) &&
                Objects.equals(creationUtc, cookie.creationUtc) &&
                Objects.equals(creationTime, cookie.creationTime) &&
                Objects.equals(expiresTime, cookie.expiresTime) &&
                Objects.equals(lastAccessTime, cookie.lastAccessTime);
    }

    /**
     * Generates hash code of the object
     *
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, hostKey, hasExpired, creationUtc, creationTime, expiresTime, lastAccessTime);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "Cookie{" +
                "name='" + name + '\'' +
                ", hostKey='" + hostKey + '\'' +
                ", hasExpired=" + hasExpired +
                ", creationUtc=" + creationUtc +
                ", creationTime=" + creationTime +
                ", expiresTime=" + expiresTime +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }

}
