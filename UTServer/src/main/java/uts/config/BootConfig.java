package uts.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BootConfig {

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public String getCacheSizeInMB() {
        return cacheSizeInMB;
    }

    public void setCacheSizeInMB(String cacheSizeInMB) {
        this.cacheSizeInMB = cacheSizeInMB;
    }

    private String serverPort;
    private String redisHost;
    private String cacheSizeInMB;

    public String getServerClusterId() {
        return serverClusterId;
    }

    public void setServerClusterId(String serverClusterId) {
        this.serverClusterId = serverClusterId;
    }

    private String serverClusterId;


    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static BootConfig fromJson(InputStream inputStream) throws IOException {
        BootConfig bootstrapConfig = JSON_MAPPER.readValue(inputStream, BootConfig.class);

        return bootstrapConfig;
    }


}
