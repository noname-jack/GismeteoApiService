package ru.tn.nnjack.GismeteoApiService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO {
    private DateInfo date;
    private TemperatureInfo temperature;
    private DescriptionInfo description;
    private String icon;
    private PrecipitationInfo precipitation;
    private WindInfo wind;
    private int city;

    @Getter
    @Setter
    public static class DateInfo {
        private long unix;
    }

    @Getter
    @Setter
    public static class TemperatureInfo {
        private Temperature air;

        @Getter
        @Setter
        public static class Temperature {
            @JsonProperty("C")
            private double c;
        }
    }

    @Getter
    @Setter
    public static class DescriptionInfo {
        private String full;
    }

    @Getter
    @Setter
    public static class PrecipitationInfo {
        private int type;
        private double amount;
    }

    @Getter
    @Setter
    public static class WindInfo {
        private Direction direction;
        private Speed speed;

        @Getter
        @Setter
        public static class Direction {
            private int scale_8;
        }

        @Getter
        @Setter
        public static class Speed {
            private int m_s;
        }
    }
}