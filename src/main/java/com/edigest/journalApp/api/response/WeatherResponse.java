package com.edigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
   // private Request request;
   // private Location location;
    private Current current;

//    public class AirQuality{
//        private String co;
//        private String no2;
//        private String o3;
//        private String so2;
//        private String pm2_5;
//        private String pm10;
//       // private String us-epa-index;
//        //private String gb-defra-index;
//    }

    @Getter
    @Setter
    public class Current{

        private int temperature;

        @JsonProperty("weather_description")
        private List<String> weatherDescriptions;

        @JsonProperty("feelslike")
        private int feelsLike;

        @JsonProperty("uv_index")
        private int uvIndex;
    }

//    public class Location{
//        private String name;
//        private String country;
//        private String region;
//        private String lat;
//        private String lon;
//        private String timezone_id;
//        private String localtime;
//        private int localtime_epoch;
//        private String utc_offset;
//    }

//    public class Request{
//        private String type;
//        private String query;
//        private String language;
//        private String unit;
//    }
}



