package de.frees.backendgameserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

  //    @Bean
  //    public MongoCustomConversions customConversions() {
  //        return new MongoCustomConversions(List.of(
  //                new Converter<OffsetDateTime, Date>() {
  //                    @Override
  //                    public Date convert(OffsetDateTime source) {
  //                        return Date.from(source.toInstant());
  //                    }
  //                },
  //                new Converter<Date, OffsetDateTime>() {
  //                    @Override
  //                    public OffsetDateTime convert(Date source) {
  //                        return source.toInstant().atOffset(ZoneOffset.UTC);
  //                    }
  //                }
  //        ));
  //    }

}
