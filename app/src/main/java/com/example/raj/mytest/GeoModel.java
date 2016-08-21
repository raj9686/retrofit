
package com.example.raj.mytest;

import java.util.ArrayList;
import java.util.List;

public class GeoModel {

    private List<Geoname> geonames = new ArrayList<Geoname>();

    /**
     * @return The geonames
     */
    public List<Geoname> getGeonames() {
        return geonames;
    }

    /**
     * @param geonames The geonames
     */
    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
    }

}
