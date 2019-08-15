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
 * The class Term represents the data structure from the table keyword_search_terms of the database history
 * @author Ante Skoric
 */
public class Term {

    /**
     * The id of the url
     */
    private Long urlId;

    /**
     * The name (term)
     */
    private String term;

    /**
     * The id of the keyword
     */
    private Long keywordID;

    public Term(String term,Long keywordId,Long urlId){
        this.term = term;
        this.keywordID = keywordId;
        this.urlId = urlId;
    }

    /**
     * Getter methods
     */
    public String getTerm() {
        return term;
    }

    public Long getUrlId() {
        return urlId;
    }

    public Long getKeywordID() {
        return keywordID;
    }

    /**
     * The setter methods
     */
    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setKeywordID(Long keywordID) {
        this.keywordID = keywordID;
    }

    /**
     *
     * @param o Object to be verified
     * @return boolean true if the objects are equal else false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;
        Term term1 = (Term) o;
        return Objects.equals(urlId, term1.urlId) &&
                Objects.equals(term, term1.term) &&
                Objects.equals(keywordID, term1.keywordID);
    }

    /**
     * Generates hash code of the object
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(urlId, term, keywordID);
    }

    /**
     * @return object in the form of string
     */
    @Override
    public String toString() {
        return "Term{" +
                "urlId=" + urlId +
                ", term='" + term + '\'' +
                ", keywordID=" + keywordID +
                '}';
    }
}
