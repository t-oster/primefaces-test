package org.primefaces.test;

import java.io.Serializable;
import java.util.Objects;

public class ExampleData implements Serializable {
    private String sampleValue = "default";

    public String getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(String sampleValue) {
        this.sampleValue = sampleValue;
    }

    public ExampleData() {
        
    }
    
    public ExampleData(String val) {
        sampleValue = val;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExampleData other = (ExampleData) obj;
        if (!Objects.equals(this.sampleValue, other.sampleValue)) {
            return false;
        }
        return true;
    }
    
    
}
