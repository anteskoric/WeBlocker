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
 * The class Cookie represents the data structure from the table cookies of the database history
 * @author Ante Skoric
 */
public class Cookie {

    private String name;
    private String hostKey;
    private boolean hasExpired;
    private LocalDateTime creationTime;
    private LocalDateTime expiresTime;
    private LocalDateTime lastAccessTime;

    public Cookie(String name, String hostKey, boolean hasExpired, LocalDateTime creationTime, LocalDateTime expiresTime, LocalDateTime lastAccessTime){
        this.name = name;
        this.hostKey = hostKey;
        this.hasExpired = hasExpired;
        this.creationTime = creationTime;
        this.expiresTime = expiresTime;
        this.lastAccessTime = lastAccessTime;
    }

    public String getName() {
        return this.name;
    }

    public String getHostKey() {
        return this.hostKey;
    }

    public boolean getHasExpired() {
        return this.hasExpired;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public LocalDateTime getExpiresTime() {
        return this.expiresTime;
    }

    public LocalDateTime getLastAccessTime() {
        return this.lastAccessTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cookie)) return false;
        Cookie cookie = (Cookie) o;
        return hasExpired == cookie.hasExpired &&
                Objects.equals(name, cookie.name) &&
                Objects.equals(hostKey, cookie.hostKey) &&
                Objects.equals(creationTime, cookie.creationTime) &&
                Objects.equals(expiresTime, cookie.expiresTime) &&
                Objects.equals(lastAccessTime, cookie.lastAccessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hostKey, hasExpired, creationTime, expiresTime, lastAccessTime);
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "name='" + name + '\'' +
                ", hostKey='" + hostKey + '\'' +
                ", hasExpired=" + hasExpired +
                ", creationTime=" + creationTime +
                ", expiresTime=" + expiresTime +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }
}
